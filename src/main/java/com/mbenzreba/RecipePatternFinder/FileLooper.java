package com.mbenzreba.RecipePatternFinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FileLooper 
{
    private File _corpus;
    private BufferedReader _reader;
    private InputStream _resource;

    public FileLooper(String resource)
    {
        // This file extraction is almost identical to the one found in ModelLoader
        // TODO: extract this resource file loading process to some common handler
        InputStream iStream;

        try 
        {
            Properties props = new Properties();
            String propsFileName = "config.properties";

            iStream = getClass().getClassLoader().getResourceAsStream(propsFileName);

            if (iStream != null) 
            {
                props.load(iStream);
            } 
            else 
            {
                throw new FileNotFoundException();
            }

            // Get the actual properties
            ClassLoader c = getClass().getClassLoader();

            // TEMPORARY FIX FROM:
            // https://stackoverflow.com/questions/28673651/how-to-get-the-path-of-src-test-resources-directory-in-junit
            String corpusProperty = props.getProperty(resource);
            this._resource = c.getResourceAsStream(corpusProperty);

            iStream.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Reads a line of POS-tagged text using _reader and returns it as a ready-made POSMap.
     * 
     * @return  next line in _reader as POSMap
     * @see     POSMap
     */
    public String nextLine() {
        String line = "";
        try 
        {
            line = this._reader.readLine();
        } 
        catch (IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            //e.printStackTrace();
        }

        return line;
    }



    /**
     * Starts a reader to make our way through _corpus. Please always call startReading() at the beginning
     * of your method and stopReading() at the end, so as to avoid memory leaks.
     * 
     * @return  true if a BufferedReader of _corpus was started successfully; false otherwise
     * @see     POSPatternReporter.stopReading()
     */
    public boolean startReading()
    {
        // ...
        this._reader = new BufferedReader(new InputStreamReader(this._resource, StandardCharsets.UTF_16LE));
        return true;
    }



    /**
     * Closes the reader.
     * 
     * @return  true if reader was closed successfully; false otherwise
     * @see     POSPatternReporter.startReading()
     */
    public boolean stopReading()
    {
        boolean closedSuccessfully = false;

        try
        {
            this._reader.close();
            closedSuccessfully = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return closedSuccessfully;
    }
}
