package com.mbenzreba.RecipePatternFinder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

/**
 * <...>
 * 
 * @author  Mohamed Benzreba
 */
public class CustomParser {

    private Parser parser;
    private String inputFile;

    public CustomParser(String modelPath, String inputFile)
    {
        InputStream modelIn = null;
        try 
        {
            modelIn = new FileInputStream(modelPath);
            ParserModel model = new ParserModel(modelIn);
            parser = ParserFactory.create(model);
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        finally 
        {
            if (modelIn != null) 
            {
                try 
                {
                    modelIn.close();
                }
                catch (IOException e) 
                {
                }
            }
        }

        this.inputFile = inputFile;
        
    }


    public void RunLineByLine()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(this.inputFile));
            String line = reader.readLine();
            while (line != null)
            {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void ReportPatterns()
    {
        // ...
    }


    private class ParsedExample
    {
    }
    
}
