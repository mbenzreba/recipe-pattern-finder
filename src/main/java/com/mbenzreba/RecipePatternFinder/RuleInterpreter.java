package com.mbenzreba.RecipePatternFinder;


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
    public static RuleInterpreter getSingleton() {
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
        // TODO: construct the interpreter
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
    }
    
}
