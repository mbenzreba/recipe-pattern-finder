package com.mbenzreba.RecipePatternFinder;

public class PatternApp 
{
    public static void main(String[] args)
    {
        POSPatternReporter reporter = new POSPatternReporter("recipes_train");
        String[] pat = {"NN", "DT", "NN"};
        int times = reporter.findPattern(pat);
        System.out.println("\nPattern occurs " + times + " times");
    }
    
}
