package com.mbenzreba.RecipePatternFinder;


/**
 * @author Mohamed Benzreba
 */
enum NodeChildrenPattern {
    /** Children of this node must be arranged in the same way as this node's children */
    SPECIFIC,
    /** Children of this node must exist in this order, but can accept some variance (e.g. some other children 
     * exist in between the requisite nodes) */
    GENERAL,
    /** Examine the leaves extending from this node, no matter how far down they might be */
    LEAF,
    /** This node has no requisite children */
    NONE,
}



/**
 * @author Mohamed Benzreba
 */
enum LeafType {
    /** The 'pos' and 'word' values of the leaf must match exactly to the compared node */
    SPECIFIC,
    /** The leaf fulfills a target that the rule defines */
    TARGET,
    /** The 'pos' of the leaf can be variable */
    ANY,
    /** There is no leaf */
    NONE,
}


/**
 * 
 */
enum CommandType {
    PHRASE,
    POS,
    LEAF_COLLECTION,
    TARGET,
    LITERAL,
    GENERAL_CHILDREN_STRUCTURE,
    CORRECT_TO,
    UNKNOWN,
}
