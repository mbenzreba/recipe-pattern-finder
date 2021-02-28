package com.mbenzreba.RecipePatternFinder;

public class PatternApp 
{
    public static void main(String[] args)
    {
        POSPatternReporter reporter = new POSPatternReporter("peruvian_chicken");
        String[] pat = {"IN", "DT", "NN"};
        int times = reporter.findPattern(pat);
        System.out.println("\nPattern occurs " + times + " times");
    }
    
}
