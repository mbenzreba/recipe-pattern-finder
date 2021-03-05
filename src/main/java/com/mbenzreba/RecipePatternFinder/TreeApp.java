package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.io.File;
import java.io.IOException;


/**
 * @author Mohamed Benzreba
 */
public class TreeApp {

    static public void main(String[] args) 
    {
        FileLooper fl = new FileLooper("recipes_parsed");

        fl.startReading();
        String line = fl.nextLine();
        while (!line.isEmpty()) 
        {
            WordTree tree = new WordTree(line);
            tree.printTree();

            try
            {
                System.in.read();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            line = fl.nextLine();
        }

        fl.stopReading();
    }
    
}


