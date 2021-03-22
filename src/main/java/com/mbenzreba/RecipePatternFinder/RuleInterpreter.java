package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.util.ArrayList;


/**
 * The RuleInterpreter is a singleton, due to the fact that it carries no state and only ever 
 * aids RuleTrees in self-construction. RuleTrees use the RuleInterpreter to modify their nodes;
 * this means that the RuleTree must be made aware of how to break up rules into the most basic
 * unit. These small rule "chunks" are then passed into the interpreter, along with the node that
 * needs the rule applied to it, so that that node can be altered.
 * 
 * @author Mohamed Benzreba
 */
public class RuleInterpreter {


    /******************************************************************************************/
    /************************************* STATIC RULES ***************************************/
    /******************************************************************************************/


    public final static String DELIMITER = " ";

    public final static char OPEN_NODE_CH = '(';
    public final static char CLOSE_NODE_CH = ')';

    public final static String OPEN_LEAF_CH = "<";
    public final static String CLOSE_LEAF_CH = ">";

    public final static String LEAF_COLLECTOR_CH = "~";
    public final static String LEAF_COLLECTOR_DELIMITER_CH = ",";

    public final static String TARGET_MARKER_CH = ":";
    public final static String LITERAL_MARKER_CH = "=";

    public final static String ENFORCE_GENERAL_NODE_CHILDREN_PATTERN = "...";
    public final static String ENFORCE_POS_CORRECTION = "->";


    /******************************************************************************************/
    /********************************* SINGLETON MANAGEMENT ***********************************/
    /******************************************************************************************/


    /** Single instance accessor */
    private static RuleInterpreter singleton = null;


    /**
     * Must be called to access the RuleInterpreter's capabilities. Returns the only existing
     * instance of the RuleInterpreter; if it does not exist, it makes one.
     * 
     * @return  a RuleInterpreter
     */
    public static RuleInterpreter get() {
        if (singleton == null) {
            singleton = _createSingleton();
        }
        return singleton;
    }



    /**
     * Creates an instance of a RuleInterpreter.
     * 
     * @return  a new instance of the RuleInterpreter
     */
    private static RuleInterpreter _createSingleton() {
        return new RuleInterpreter();
    }



    /**
     * Private constructor, meant for use only by _createSingleton().
     */
    private RuleInterpreter() {
        // ...
    }


    /******************************************************************************************/
    /********************************** INSTANCE OPERATIONS ***********************************/
    /******************************************************************************************/


    /**
     * Alters the refNode object in place based on the rulePiece argument (rulePiece is simply the smallest
     * chunk a whole rule can be broken down into).
     * 
     * @param refNode   node to apply the rule to
     * @param rulePiece rule to apply to this node
     */
    public void interpret(RuleTreeNode refNode, String rulePiece) {
        // STEP 1. Get each element defined by seperation using a delimiter
        String elements[] = rulePiece.split(RuleInterpreter.DELIMITER);

        // STEP 2. Identify what each element in the rulePiece means
        ArrayList<CommandType> commands = new ArrayList<CommandType>(); 
        for (String element : elements) {
            commands.add(this._identifyElementCommand(element));
        }

        // STEP 3. Based on commands and elements, execute actions on the refNode
        this._fillNode(refNode, elements, commands);
    }



    /**
     * Returns the type of command that the element argument represents, depending on its syntax.
     * 
     * @param element   term to parse and derive CommandType from
     * @return          the CommandType (enum) that the element arg represents
     * @see             com.mbenzreba.RecipePatternFinder.RuleInterpreter.CommandType
     */
    private CommandType _identifyElementCommand(String element) {
        // TODO: change all uses of contains() to a more efficient method that only checks first character

        CommandType command;

        if (element.contains(ENFORCE_GENERAL_NODE_CHILDREN_PATTERN)) {
            command = CommandType.GENERAL_CHILDREN_STRUCTURE;
        }
        else if (element.contains(ENFORCE_POS_CORRECTION)) {
            command = CommandType.CORRECT_TO;
        }
        else if (PennLibrary.get().isParentTag(element)) {
            command = CommandType.PHRASE;
        }
        else if (PennLibrary.get().isLeafTag(element)) {
            command = CommandType.POS;
        }
        else if (element.contains(LEAF_COLLECTOR_CH)) {
            command = CommandType.LEAF_COLLECTION;
        }
        else if (element.contains(TARGET_MARKER_CH)) {
            command = CommandType.TARGET;
        }
        else if (element.contains(LITERAL_MARKER_CH)) {
            command = CommandType.LITERAL;
        }
        else {
            command = CommandType.UNKNOWN;
        }
        

        return command;
    }



    /**
     * Fils the refNode argument with the appropriate properties, based on what elements are present
     * in the rule and what commands these elements represent.
     * 
     * @param refNode   node to fill
     * @param elements  elements of the rule
     * @param commands  CommandType of each respective element
     */
    private void _fillNode(RuleTreeNode refNode, String[] elements, ArrayList<CommandType> commands) {
        // TODO: Extract each of these operations into their own methods
        switch (commands.size()) {
            case 1:
                // The node's children must exist in this order, but other nodes are allowed to exist among them
                if (commands.get(0) == CommandType.GENERAL_CHILDREN_STRUCTURE) {
                    refNode._ncp = NodeChildrenPattern.GENERAL;
                }
                // The node is a parent, and contains a Phrase level value
                else if (commands.get(0) == CommandType.PHRASE) {
                    refNode._pos = elements[0];
                }
                break;
            case 2:
                // A specific pairing of POS and word must be present
                if (commands.get(0) == CommandType.PHRASE && commands.get(1) == CommandType.LITERAL) {
                    refNode._pos = elements[0];
                    refNode._value = elements[1];
                    refNode._ncp = NodeChildrenPattern.NONE;
                    refNode._nodeType = NodeType.SPECIFIC;
                }
                // Leaves of the node are detailed as targets
                else if (commands.get(0) == CommandType.LEAF_COLLECTION && commands.get(1) == CommandType.TARGET) {
                    refNode._ncp = NodeChildrenPattern.LEAF;
                    String leaves[] = elements[0].split(LEAF_COLLECTOR_DELIMITER_CH);
                    for (String leaf : leaves) {
                        refNode.addChild(new RuleTreeNode(leaf, elements[0], NodeChildrenPattern.NONE, NodeType.TARGET));
                    }
                }
                break;
            case 3:
                // Node's leaves detail targets to be fulfilled
                if (commands.get(0) == CommandType.PHRASE && commands.get(1) == CommandType.LEAF_COLLECTION &&
                    commands.get(2) == CommandType.TARGET) {
                    refNode._ncp = NodeChildrenPattern.LEAF;
                    String leaves[] = elements[1].split(LEAF_COLLECTOR_DELIMITER_CH);
                    for (String leaf : leaves) {
                        refNode.addChild(new RuleTreeNode(leaf, elements[2], NodeChildrenPattern.NONE, NodeType.TARGET));
                    }
                }
                else if (commands.get(0) == CommandType.PHRASE && commands.get(1) == CommandType.CORRECT_TO &&
                    commands.get(2) == CommandType.PHRASE) {
                    refNode._pos = elements[0];
                    refNode._nodeType = NodeType.CORRECT_TO;
                    refNode._value = elements[2];
                }
                else if (commands.get(0) == CommandType.POS && commands.get(1) == CommandType.CORRECT_TO &&
                    commands.get(2) == CommandType.POS) {
                    refNode._pos = elements[0];
                    refNode._nodeType = NodeType.CORRECT_TO;
                    refNode._value = elements[2];
                }
                break;
        }
    }
    
}
