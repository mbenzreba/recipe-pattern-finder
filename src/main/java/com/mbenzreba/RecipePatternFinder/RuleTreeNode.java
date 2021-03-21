package com.mbenzreba.RecipePatternFinder;

import java.util.ArrayList;

/**
 * @author Mohamed Benzreba
 */
public class RuleTreeNode {

    /** Phrase of speech of the node */
    protected String _pos;
    /** Value; note, NOT 'word', since value of the node can now be eitehr a word, or target, etc. */
    protected String _value;
    /** Children of this node */
    protected ArrayList<RuleTreeNode> _children;

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
        this._children = new ArrayList<RuleTreeNode>();
        this._ncp = NodeChildrenPattern.NONE;
        this._leafType = LeafType.NONE;
    }

    public RuleTreeNode(String pos, String value, NodeChildrenPattern pat, LeafType type) {
        this._pos = pos;
        this._value = value;
        this._children = new ArrayList<RuleTreeNode>();
        this._ncp = pat;
        this._leafType = type;
    }

    public void addChild(RuleTreeNode newChild) {
        this._children.add(newChild);
    }

}
