package com.mbenzreba.RecipePatternFinder;



import java.util.ArrayList;



/**
 * Organizes a sentence parsed by OpenNLP (ie the sentence is now in OpenNLP notation)
 * for easy analysis.
 * 
 * @author Mohamed Benzreba
 */
public class WordTree 
{
    private String[] _tokens;   // Sentence split up by tokens (words)
    private int _tokens_i;      // Tracker integer for building the tree
    private WordTreeNode _root; // Root node

    public WordTree(String sentence)
    {
        // ...
        this._tokens = sentence.split(" ");
        this._tokens_i = 0;
        this._root = this.InitializeNode();
    }


    private WordTreeNode InitializeNode()
    {
        WordTreeNode node;

        // The first term has to be a pos

        if (_tokens[_tokens_i+1].contains("("))
        {
            // The next token is a pos as well, so make an empty pos node here
            node = new WordTreeNode(_tokens[_tokens_i].substring(1));
            _tokens_i++;
            node.AddChild(InitializeNode());
            return node;
        }
        else
        {
            // BASE CASE!!
            // The next token is a word, so this represents a leaf of the tree
            node = new WordTreeNode(_tokens[_tokens_i].substring(1),
                _tokens[_tokens_i+1].substring(0, _tokens[_tokens_i].length()-2));
            _tokens_i += 2;
            return node;
        }

    }


    public void PrintTree()
    {
        // ....
    }

    private class WordTreeNode
    {
        private String _pos;
        private String _word;
        private ArrayList<WordTreeNode> _children;

        public WordTreeNode(String pos)
        {
            this._pos = pos;
        }

        public WordTreeNode(String pos, String word)
        {
            this._pos = pos;
            this._word = word;
        }

        public void AddChild(WordTreeNode node)
        {
            this._children.add(node);
        }
    }
    
}
