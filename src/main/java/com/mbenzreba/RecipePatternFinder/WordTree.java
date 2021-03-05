package com.mbenzreba.RecipePatternFinder;



// Java
import java.util.ArrayList;



/**
 * Organizes a sentence parsed by OpenNLP (ie the sentence is now in OpenNLP notation)
 * for easier analysis.
 * 
 * Although the OpenNLP library already offers a <code>Parse</code> object, and using the command-line 
 * parser tool is frowned upon, I found the Parse object and the Parse[] it returns to be hard to work
 * with. Making my own tree from a pre-processed piece of text (i.e. by using the OpenNLP Parser
 * command-line tool) better suits my needs, as well as allows me greater control over manipulating
 * the tree structure.
 * 
 * @author Mohamed Benzreba
 */
public class WordTree 
{
    /** Pre-processed sentence split up by tokens (whitespace) */
    private String[] _tokens;
    /** Tracking integer for building the tree */
    private int _tokens_i;
    /** Root WordTreeNode of the tree */
    private WordTreeNode _root;
    /** Used to build the tree */
    private int _goToLevel;
    /** Used to flag that something is wrong -- i.e. too few tokens are in the tree */
    private boolean _isEmpty;



    /**
     * Constructs a WordTree, where each node of the tree represents a phrase-of-speech and/or
     * a word. Expects a pre-parsed sentence.
     * 
     * @param sentence  pre-processed by OpenNLP.tools.Parser
     */
    public WordTree(String sentence)
    {
        this._tokens = sentence.split(" ");
        this._goToLevel = 1;

        if (this._tokens.length < 2)
        {
            this._isEmpty = true;
        }
        else
        {
            this._isEmpty = false;
            this._root = this._initializeNode(1);
        }
        
    }



    /**
     * Returns whether the tree is empty.
     * 
     * @return  true if the tree is empty (i.e. unworkable), false otherwise
     */
    public boolean isEmpty()
    {
        return this._isEmpty;
    }



    /**
     * Initializes a node and returns it. Uses object-wide instance members to initialize the node, 
     * rather than using local variables (i.e. the tree is built contextually).
     * 
     * @param currLevel current level (the root node being level 1) of the node being initialized
     * @return          the node just initialized
     */
    private WordTreeNode _initializeNode(int currLevel)
    {
        WordTreeNode node;
        this._goToLevel = currLevel;

        // The first term has to be a pos

        if (this._tokens[this._tokens_i+1].contains("("))
        {
            // The next token is a pos as well, so make an empty pos node here
            node = new WordTreeNode(this._tokens[this._tokens_i].substring(1));
            this._tokens_i++;
            
            boolean stillAtParse = true;
            while (stillAtParse)
            {
                node.addChild(_initializeNode(currLevel+1));
                if (currLevel != this._goToLevel)
                {
                    stillAtParse = false;
                }
            }
            
            return node;
        }
        else
        {
            // BASE CASE!!
            // The next token is a word, so this represents a leaf of the tree
            node = new WordTreeNode(this._tokens[this._tokens_i].substring(1),
                this._tokens[_tokens_i+1].substring(0, this._tokens[this._tokens_i+1].length()));
            this._tokens_i += 2;

            // Go to the level signified by number of closing brackets
            this._goToLevel -= StringHelper.countChar(node._word, ')');
            return node;
        }

    }



    /**
     * Prints the tree to console.
     */
    public void printTree()
    {
        this._printNode(this._root, 1);
    }



    /**
     * Prints the current node to console.
     * 
     * @param node  the current node to print
     * @param level the current level of the node being printed (root node being level 1)
     */
    private void _printNode(WordTreeNode node, int level)
    {
        String tab = "  ";

        // STEP 1. Print the node we're on
        String indent = "";
        for (int numTabs = 1; numTabs < level; ++numTabs)
        {
            indent += tab;
        }

        String nodeDetails = indent + "+-" + node._pos;
        if (node._children.size() == 0)
        {
            nodeDetails += "\n" + indent + tab + "+-" + node._word;
        }

        System.out.println(nodeDetails);

        // STEP 2. Re-enter _printNode() for each child
        for (int childIndex = 0; childIndex < node._children.size(); ++childIndex)
        {
            this._printNode(node._children.get(childIndex), level+1);
        }
    }



    /**
     * A node for use in the WordTree. Can hold onto a phrase-of-speech, a word, as well as
     * an ArrayList of references to its possible node children. A leaf of the tree is identified
     * by its lack of children.
     * 
     * @author Mohamed Benzreba
     */
    private class WordTreeNode
    {
        /** Phrase of speech */
        private String _pos;
        /** Word */
        private String _word;
        /** Node's children */
        private ArrayList<WordTreeNode> _children;



        /**
         * Constructor using only a phrase-of-speech tag.
         * 
         * @param pos   phrase-of-speech
         */
        public WordTreeNode(String pos)
        {
            this._pos = pos;
            this._word = null;
            this._children = new ArrayList<WordTreeNode>();
        }



        /**
         * Constructor using both a phrase-of-speech and a word (for the leaves of the tree).
         * 
         * @param pos   phrase-of-speech
         * @param word  word
         */
        public WordTreeNode(String pos, String word)
        {
            this._pos = pos;
            this._word = word;
            this._children = new ArrayList<WordTreeNode>();
        }



        // This really isn't needed, since the WordTree class can peek into the WordTreeNode class
        // by way of being its container
        public void addChild(WordTreeNode node)
        {
            this._children.add(node);
        }
    }
    
}
