package com.mbenzreba.RecipePatternFinder;


import java.io.InputStream;
// Java imports
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class RecipeWordTree extends WordTree 
{


    /**
     * 
     * @param sentence
     */
    public RecipeWordTree(String sentence) 
    {
        // extends WordTree
        super(sentence);
    }


    public boolean fulfill(Target t)
    {
        // Try to fix it
        // TODO
        return true;
    }
    
}
