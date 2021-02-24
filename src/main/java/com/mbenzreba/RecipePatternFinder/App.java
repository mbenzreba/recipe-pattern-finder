package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.util.Scanner;


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
        System.out.println( "A lot still to do..." );

        // TODO: Check if this txt file containing the recipe actually exists
        String inputFile = args[0];
        CustomParser recipeParser = new CustomParser(inputFile);

        // Start a stdin scanner and a variable to store user input
        Scanner scanner = new Scanner(System.in);
        String userInput;

        // Main program
        boolean runProgram = true;
        while (runProgram)
        {
            // TODO: Call recipeParser to output latest sentence
            // ...

            userInput = scanner.nextLine();

            // TODO: Each system out should be replaced by a call to CustomParser
            switch (userInput)
            {
                case "ingredient":
                    System.out.println("asked for an ingredient");
                    break;
                case "quantity":
                    System.out.println("asked for quantity of ingredient");
                    break;
                case "action":
                    System.out.println("asked for the action");
                    break;
                case "cookware":
                    System.out.println("asked for cookware used");
                    break;
                case "step":
                    System.out.println("asked for step number");
                    break;
                default:
                    runProgram = false;
                    break;
            }
        }

        scanner.close();
    }
    
}
