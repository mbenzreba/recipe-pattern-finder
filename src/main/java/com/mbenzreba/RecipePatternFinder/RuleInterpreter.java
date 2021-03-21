package com.mbenzreba.RecipePatternFinder;


import java.lang.annotation.ElementType;
import java.util.ArrayList;

// Package imports
import com.mbenzreba.RecipePatternFinder.RuleTree.RuleTreeNode;


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

    public final static String OPEN_NODE_CH = "(";
    public final static String CLOSE_NODE_CH = ")";

    public final static String OPEN_LEAF_CH = "<";
    public final static String CLOSE_LEAF_CH = ">";

    public final static String LEAF_COLLECTOR_CH = ",";

    public final static String TARGET_MARKER_CH = ":";
    public final static String LITERAL_MARKER_CH = "=";

    public final static String ENFORCE_GENERAL_NODE_CHILDREN_PATTERN = "...";
    public final static String ENFORCE_POS_CORRECTION = "->";


    private enum CommandType {
        PHRASE,
        POS,
        LEAF_COLLECTION,
        TARGET,
        LITERAL,
        GENERAL_CHILDREN_STRUCTURE,
        CORRECT_TO,
        UNKNOWN,
    }



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
        // TODO: interpret just one 'piece' of the rule, and alter refNode in place
        // STEP 1. Get each element defined by seperation using a delimiter
        String elements[] = rulePiece.split(RuleInterpreter.DELIMITER);

        // STEP 2. Identify what each element in the rulePiece means
        ArrayList<CommandType> commands = new ArrayList<CommandType>(); 
        for (String element : elements) {
            commands.add(this._identifyElementCommand(element));
        }
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
    
}
