package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


// OpenNLP imports
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


/**
 * <...>
 * 
 * @author  Mohamed Benzreba
 */
public class ModelLoader {

    private String _sentDetectorModelPath;
    private String _tokenizerModelPath;
    private String _posTaggerModelPath;
    private String _parserModelPath;


    public ModelLoader()
    {
        // Check that each model exist, throw an error otherwise
        this._findModelPaths();

        String[] filenames ={this._sentDetectorModelPath, this._tokenizerModelPath, 
            this._posTaggerModelPath, this._parserModelPath};
        /*
        try
        {
            this._ensureModelPaths(filenames);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } */
    }


    /**
     * Load in the model paths from config.properties.
     * 
     * @see https://crunchify.com/java-properties-file-how-to-read-config-properties-values-in-java/
     */
    private void _findModelPaths()
    {
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
            File f;
            ClassLoader c = getClass().getClassLoader();


            // TEMPORARY FIX FROM:
            // https://stackoverflow.com/questions/28673651/how-to-get-the-path-of-src-test-resources-directory-in-junit
            // TODO: Increase performance here, since the constant file loading is taking too long
            this._sentDetectorModelPath = props.getProperty("sentdetect_path");
            f = new File(c.getResource(this._sentDetectorModelPath).getFile());
            this._sentDetectorModelPath = f.getAbsolutePath();

            this._tokenizerModelPath = props.getProperty("tokenizer_path");
            f = new File(c.getResource(this._tokenizerModelPath).getFile());
            this._tokenizerModelPath = f.getAbsolutePath();

            this._posTaggerModelPath = props.getProperty("postagger_path");
            f = new File(c.getResource(this._posTaggerModelPath).getFile());
            this._posTaggerModelPath = f.getAbsolutePath();

            this._parserModelPath = props.getProperty("parser_path");
            f = new File(c.getResource(this._parserModelPath).getFile());
            this._parserModelPath = f.getAbsolutePath();

            iStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    /**
     * 
     * @param   files
     * @throws  FileNotFoundException
     */
    private void _ensureModelPaths(String[] files) throws FileNotFoundException
    {
        ClassLoader c = getClass().getClassLoader();
        File f;

        for (String s: files)
        {
            f = new File(s);
            if (!f.exists())
            {
                throw new FileNotFoundException(s + " was not found.");
            }
        }

    }


    private InputStream _getResourceAsStream(String resource)
    {
        // This file extraction is almost identical to the one found in ModelLoader
        // TODO: extract this resource file loading process to some common handler
        InputStream iStream = null;

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
            iStream = c.getResourceAsStream(corpusProperty);

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return iStream;
    }



    /**
     * Loads in a binary model found at ModelLoader.SENTDETECTOR_MODEL_PATH.
     * 
     * @return  loaded in SentenceDetector model
     * @see     ModelLoader.SENTDETECTOR_MODEL_PATH
     */
    public SentenceDetectorME loadSentenceDetector()
    {
        InputStream modelIn = null;
        SentenceDetectorME sentDetector = null;

        try 
        {
            modelIn = this._getResourceAsStream("sentdetect_path");
            SentenceModel model = new SentenceModel(modelIn);
            sentDetector = new SentenceDetectorME(model);
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

        return sentDetector;
    }



    /**
     * Loads in a binary model found at ModelLoader.TOKENIZER_MODEL_PATH.
     * 
     * @return  loaded in TokenizerME model
     * @see     ModelLoader.TOKENIZER_MODEL_PATH
     */
    public TokenizerME loadTokenizer()
    {
        InputStream modelIn = null;
        TokenizerME tokenizer = null;

        try 
        {
            modelIn = this._getResourceAsStream("tokenizer_path");
            TokenizerModel model = new TokenizerModel(modelIn);
            tokenizer = new TokenizerME(model);
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

        return tokenizer;
    }



    /**
     * Loads in a binary model found at ModelLoader.POSTAGGER_MODEL_PATH.
     * 
     * @return  loaded in POSTaggerME model
     * @see     ModelLoader.POSTAGGER_MODEL_PATH
     */
    public POSTaggerME loadPOSTagger()
    {
        InputStream modelIn = null;
        POSTaggerME posTagger = null;
        try 
        {
            modelIn = this._getResourceAsStream("postagger_path");
            POSModel model = new POSModel(modelIn);
            posTagger = new POSTaggerME(model);
            
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

        return posTagger;
    }



    /**
     * Loads in a binary model found at ModelLoader.PARSER_MODEL_PATH.
     * 
     * @return  loaded in Parser model
     * @see     ModelLoader.PARSER_MODEL_PATH
     */
    public Parser loadParser()
    {
        InputStream modelIn = null;
        Parser parser = null;
        try 
        {
            modelIn = this._getResourceAsStream("parser_path");
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

        return parser;
    }
    
}
