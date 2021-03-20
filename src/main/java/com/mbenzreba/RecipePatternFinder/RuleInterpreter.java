package com.mbenzreba.RecipePatternFinder;


// Package imports
import com.mbenzreba.RecipePatternFinder.RuleTree.RuleTreeNode;


/**
 * @author Mohamed Benzreba
 */
public class RuleInterpreter {


    /******************************************************************************************/
    /********************************* SINGLETON MANAGEMENT ***********************************/
    /******************************************************************************************/


    /** Single instance accessor */
    private static RuleInterpreter singleton = null;


    /**
     * 
     * @return
     */
    public static RuleInterpreter getSingleton() {
        if (singleton == null) {
            singleton = _createSingleton();
        }
        return singleton;
    }



    /**
     * 
     * @return
     */
    private static RuleInterpreter _createSingleton() {
        // TODO
        return new RuleInterpreter();
    }



    /**
     * 
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
