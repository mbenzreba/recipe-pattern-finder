package com.mbenzreba.RecipePatternFinder;

import java.util.ArrayList;

/**
 * @author Mohamed Benzreba
 */
public abstract class RuleTree {

    protected RuleTreeNode _root;

    protected RuleTree(String rule) {
        this._initializeNode(rule);
    }

    protected RuleTreeNode _initializeNode(String rule) {
        int currLevel = 1;

        // TODO: Set up a loop to process every single command found in the rule
        // This method calls the RuleInterpreter -- the interpreter then passes an appropriate node back

        // TODO: return something good
        return new RuleTreeNode("EMPTY-TREE");
    }

    protected void _cleanAnnotations() {
        // TODO: clean nodes of unwanted string artifacts
    }


    
    
}




