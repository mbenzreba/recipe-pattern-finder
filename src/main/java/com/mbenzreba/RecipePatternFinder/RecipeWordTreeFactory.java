package com.mbenzreba.RecipePatternFinder;

public class RecipeWordTreeFactory 
{

    static public RecipeWordTree create(String sentence)
    {
        return new RecipeWordTree(sentence);
    }
    
}
