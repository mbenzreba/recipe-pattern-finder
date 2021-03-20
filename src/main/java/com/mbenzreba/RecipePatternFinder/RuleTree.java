package com.mbenzreba.RecipePatternFinder;


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


    /**
     * @author Mohamed Benzreba
     */
    protected class RuleTreeNode {

        /** Phrase of speech of the node */
        protected String _pos;
        /** Value; note, NOT 'word', since value of the node can now be eitehr a word, or target, etc. */
        protected String _value;

        /** Structural pattern of this node's children
         * @see com.mbenzreba.RecipePatternFinder.types.NodeChildrenPattern
         */
        protected NodeChildrenPattern _ncp;

        /** Type of the leaf of this node
         * @see com.mbenzreba.RecipePatternFinder.types.LeafType
         */
        protected LeafType _leafType;

        public RuleTreeNode(String pos) {
            this._pos = pos;
            this._value = null;
            this._ncp = NodeChildrenPattern.NONE;
            this._leafType = LeafType.NONE;
        }

    }
    
}




