package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.util.Scanner;

import opennlp.tools.parser.Parse;


/**
 * <...>
 * 
 * @author  Mohamed Benzreba
 */
public class App 
{

    /**
     * Program entry point.
     * 
     * @param args  command-line options, expects 1: filename where recipe is contained
     */
    public static void main( String[] args )
    {
        // TODO: Check if this txt file containing the recipe actually exists
        //String inputFile = args[0];
        //CustomParser recipeParser = new CustomParser(inputFile);

        // Start a stdin scanner and a variable to store user input
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        FileLooper f = new FileLooper("peru");
        f.startReading();
        String tmp = f.nextLine();
        String raw = "";
        while (tmp != null) {
            raw += tmp + " ";
            tmp = f.nextLine();
        }
        f.stopReading();
        CustomParser cp = new CustomParser(raw);

        // Main program
        boolean runProgram = true;
        while (runProgram)
        {
            // TODO: Call recipeParser to output latest sentence
            // ...

            userInput = scanner.nextLine();

            // TODO: Each system out should be replaced by a call to CustomParser
            Parse p[] = cp.getNextParse();
            StringBuffer sb = new StringBuffer();
            p[0].show(sb);
            RecipeWordTree rwt = new RecipeWordTree(sb.toString());

            
            System.out.println(rwt.getSentence());
            
        }

        scanner.close();
    }
    
}
