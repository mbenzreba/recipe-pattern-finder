package com.mbenzreba.RecipePatternFinder;


import static org.junit.Assert.assertTrue;

// Junit
import org.junit.Test;


public class RuleInterpreterTest {

    @Test
    public void interpretsFixCorrection() {
        RuleTree tree = new VariableRuleTree("(NP -> VP (NN -> VB))");

        // Test root
        RuleTreeNode shouldBe = new RuleTreeNode("NP", "VP", NodeChildrenPattern.SPECIFIC, NodeType.CORRECT_TO);
        shouldBe.addChild(new RuleTreeNode());

        assertTrue(tree._root.equalsNode(shouldBe));

        // Test child of root
        shouldBe = new RuleTreeNode("NN", "VB", NodeChildrenPattern.NONE, NodeType.CORRECT_TO);
        assertTrue(tree._root._children.get(0).equalsNode(shouldBe));
    }


    @Test
    public void interpretsFulfillAction() {
        RuleTree tree = new VariableRuleTree("(VP (VBG :action))");

        // Test root
        RuleTreeNode shouldBe = new RuleTreeNode("VP", " ", NodeChildrenPattern.SPECIFIC, NodeType.NONE);
        shouldBe.addChild(new RuleTreeNode("VBG", "action", NodeChildrenPattern.NONE, NodeType.TARGET));

        assertTrue(tree._root.equalsNode(shouldBe));

        // Test VBG child
        assertTrue(tree._root._children.get(0).equalsNode(shouldBe.getLastChild()));
    }
    
}
