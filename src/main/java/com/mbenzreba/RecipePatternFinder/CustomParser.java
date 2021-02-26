package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


// Java imports
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.tokenize.TokenizerME;


/**
 * <...>
 * 
 * @author  Mohamed Benzreba
 */
public class CustomParser {

    // Instance members
    private String inputFile;
    private ModelLoader loader;


    /**
     * Simple constructor.
     * 
     * @param   inputFile   relative path of plain-text recipe file
     */
    public CustomParser(String inputFile)
    {
        
        this.inputFile = inputFile;
        
    }



    /**
     * Pipeline for preparing a plain-text recipe for parsing. Runs the recipe through
     * various OpenNLP tools.
     */
    private void _prepareCorpus()
    {
        // Pipeline goes here
    }



    /**
     * Splits the raw argument into an array by sentence boundaries. 
     * 
     * @param   raw raw string, no processing done on it
     * @return      string broken up into sentences, each element is a sentence
     */
    private String[] _NLPSentDetect(String raw)
    {
        SentenceDetectorME sentDetector = this.loader.loadSentenceDetector();
        return sentDetector.sentDetect(raw);
    }


    /**
     * Splits the sentence argument into an array by tokenization boundaries.
     * 
     * @param   sentence    a single sentence
     * @return              the sentence broken up into tokens, each element is a token
     */
    private String[] _NLPTokenize(String sentence)
    {
        TokenizerME tokenizer = this.loader.loadTokenizer();
        return tokenizer.tokenize(sentence);
    }



    /**
     * Returns an array of phrases-of-speech, each element corresponding to its matching
     * index placement in the tokens argument (i.e. for the 2nd element in the tokens argument, 
     * the 2nd element in the returned phrases-of-speech String array will signify that token's
     * phrase of speech).
     * 
     * @param   tokens  an array of tokens, the smallest unit of language a sentence can be
     *                  be broken up into
     * @return          an array of length equal to the length of the tokens argument, each
     *                  element the phrase-of-speech of its corresponding element in the tokens
     *                  argument
     */
    private String[] _NLPPOSTag(String[] tokens)
    {
        POSTaggerME posTagger = this.loader.loadPOSTagger();
        return posTagger.tag(tokens);
    }



    /**
     * Returns a Parse array for the sentence, that can represent the structure of a POS-tagged
     * sentence better using Parse.show().
     * 
     * @param   sentence    a single sentence
     * @return              a Parse tree of the sentence argument, where each token in the sentence
     *                      is represented by a leaf in the tree
     * @see                 opennlp.tools.parser.Parse
     */
    private Parse[] _NLPParse(String sentence)
    {
        Parser parser = this.loader.loadParser();
        Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
        return topParses;
    }


    // Not sre what I am going to do with this yet...
    // Leave here for now!!
    // TODO: Find a place in CustomParser where I can read the recipe in line by line 
    public void runLineByLine()
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
    
}
