package com.mbenzreba.RecipePatternFinder;


/**
 * The base class of trees that define some kind of rule that a WordTree must adhere to. For
 * example, we might want to identify a target in a WordTree, and we always know that that
 * target appears as a child of a NP (Noun Phrase), with a DT (Determiner) always directly
 * preceding it.
 * 
 * Well then, what we can do is derive a class from RuleTree called TargetRuleTree, use the
 * super class to construct this general structure we're looking for  -- e.g. (NP (DT ) (ANY :target))
 * -- and then pass that TargetRuleTree into the WordTree.
 * 
 * @see     com.mbenzreba.RecipePatternFinder.RuleInterpreter
 * @author  Mohamed Benzreba
 */
public abstract class RuleTree {


    /** Root of the tree */
    protected RuleTreeNode _root;


    /**
     * Returns a fully initialized RuleTree for use in comparison with an object derived from
     * WordTree.
     * 
     * @param rule  rule to build the RuleTree off of
     */
    protected RuleTree(String rule) {
        this._initializeNode(rule);
    }



    /**
     * Initializes the RuleTree with RuleTreeNodes. Specifics for how these nodes are configured
     * are contained in RuleInterpreter.
     * 
     * @param rule  rule to initialize the nodes, with each node based on one set of matching brackets
     *              in the rule (called a rulePiece)
     * @see         com.mbenzreba.RecipePatternFinder.RuleTreeNode
     */
    protected void _initializeNode(String rule) {
        int currLevel = 1;
        int ruleIndex = 1;
        this._root = new RuleTreeNode();
        RuleTreeNode currNode = this._root;
        RuleTreeNode parentNode = new RuleTreeNode();
        StringBuilder runningRulePiece = new StringBuilder();
        

        // This method calls the RuleInterpreter -- the interpreter then passes an appropriate node back
        while (ruleIndex < rule.length()) {
            char c = rule.charAt(ruleIndex);

            if (c == RuleInterpreter.OPEN_NODE_CH) {
                // Start a new node, as a child of the current node
                parentNode = currNode;
                currNode = new RuleTreeNode();
                parentNode.addChild(currNode);
                if (parentNode._children.size() == 1) {
                    // First child, so make sure the pattern is set
                    parentNode._ncp = NodeChildrenPattern.SPECIFIC;
                }

                String ruleToInterpret = runningRulePiece.toString().strip();
                if (!ruleToInterpret.isBlank()) {
                    RuleInterpreter.get().interpret(parentNode, ruleToInterpret);
                    runningRulePiece = new StringBuilder();
                }

                currLevel++;
            }
            else if (c == RuleInterpreter.CLOSE_NODE_CH) {
                // Close this node out, process the rule, go up one level
                String ruleToInterpret = runningRulePiece.toString().strip();
                if (!ruleToInterpret.isBlank()) {
                    RuleInterpreter.get().interpret(currNode, ruleToInterpret);
                    runningRulePiece = new StringBuilder();
                }
                
                currLevel--;
            }
            else {
                // Simply add to the running rule piece
                runningRulePiece.append(c);
            }

            // Process next character on next loop
            ruleIndex++;
        }

    }



    /**
     * Performs a tree traversal and removes any unwanted artifacts from each RuleTreeNode that
     * makes up the RuleTree.
     */
    protected void _cleanAnnotations() {
        // TODO: clean nodes of unwanted string artifacts
    }
    
}




