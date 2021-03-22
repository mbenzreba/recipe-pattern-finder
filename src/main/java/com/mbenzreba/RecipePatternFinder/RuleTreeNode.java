package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.util.ArrayList;


/**
 * @author Mohamed Benzreba
 */
public class RuleTreeNode {


    /******************************************************************************************/
    /************************************** PROPERTIES ****************************************/
    /******************************************************************************************/


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


    /******************************************************************************************/
    /************************************* CONSTRUCTORS ***************************************/
    /******************************************************************************************/


    /**
     * Returns an empty RuleTreeNode.
     */
    public RuleTreeNode() {
        this._pos = null;
        this._value = null;
        this._children = new ArrayList<RuleTreeNode>();
        this._ncp = NodeChildrenPattern.NONE;
        this._leafType = LeafType.NONE;
    }



    /**
     * Returns a RuleTreeNode with its POS/Phrase value filled.
     * 
     * @param pos   POS value to set the RuleTreeNode
     */
    public RuleTreeNode(String pos) {
        this._pos = pos;
        this._value = null;
        this._children = new ArrayList<RuleTreeNode>();
        this._ncp = NodeChildrenPattern.NONE;
        this._leafType = LeafType.NONE;
    }



    /**
     * Returns a fully configured RuleTreeNode, although with no children nodes.
     * 
     * @param pos   POS value of the node
     * @param value value corresponding to the pos of the node
     * @param pat   the structural pattern this node's children follow
     * @param type  the type of leaf this node is, if it is one
     */
    public RuleTreeNode(String pos, String value, NodeChildrenPattern pat, LeafType type) {
        this._pos = pos;
        this._value = value;
        this._children = new ArrayList<RuleTreeNode>();
        this._ncp = pat;
        this._leafType = type;
    }


    /******************************************************************************************/
    /*********************************** PUBLIC OPERATIONS ************************************/
    /******************************************************************************************/


    /**
     * Adds a child to this node's list of children.
     * 
     * @param newChild  node that this new child points to
     */
    public void addChild(RuleTreeNode newChild) {
        this._children.add(newChild);
    }



    /**
     * Returns the last child added to this node.
     * 
     * @return  the last RuleTreeNode that was added as a child to this node
     */
    public RuleTreeNode getLastChild() {
        return this._children.get(this._children.size() - 1);
    }

}
