package com.mbenzreba.RecipePatternFinder;

// Java imports
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    // TODO: Extract model loading process to a master method, since it's pretty samey-samey
    // across the board
    // TODO: Extract mdoel paths to a config.properties file

    // OpenNLP model paths for loading
    private final static String SENTDETECTOR_MODEL_PATH = ".\\models\\en-sent.bin";
    private final static String TOKENIZER_MODEL_PATH = ".\\models\\en-token.bin";
    private final static String POSTAGGER_MODEL_PATH = ".\\models\\en-pos-maxent.bin";
    private final static String PARSER_MODEL_PATH = ".\\models\\en-parser-chunking.bin";


    public ModelLoader()
    {
        // Check that each model exist, throw an error otherwise
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
            modelIn = new FileInputStream(ModelLoader.SENTDETECTOR_MODEL_PATH);
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
            modelIn = new FileInputStream(ModelLoader.TOKENIZER_MODEL_PATH);
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
            modelIn = new FileInputStream(ModelLoader.POSTAGGER_MODEL_PATH);
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
            modelIn = new FileInputStream(ModelLoader.PARSER_MODEL_PATH);
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
