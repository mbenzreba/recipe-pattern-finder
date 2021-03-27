package com.mbenzreba.RecipePatternFinder;


// Java utils
import java.util.ArrayList;


// Apache OpenNLP
import opennlp.tools.parser.Parser;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;


// TODO: This class probably does better as a singleton


/**
 * Instead of processing text on the command-line using files, this class is able to do it
 * programmatically for use inside someother program.
 * 
 * @author Mohamed Benzreba
 */
public class PreParser {


    /** Sentence detector model living in memory */
    private SentenceDetectorME _sentDetector;
    /** Tokenizer model living in memory */
    private TokenizerME _tokenizer;
    /** Parser model living in memory */
    private Parser _parser;


    /**
     * Returns a PreParser that can be used to process raw text using the Apache OpenNLP library.
     */
    public PreParser() {
        // Model loader
        ModelLoader ml = new ModelLoader();

        // Load in models
        this._sentDetector = ml.loadSentenceDetector();
        this._tokenizer = ml.loadTokenizer();
        this._parser = ml.loadParser();
    }



    /**
     * Returns an ArrayList of annotated sentences based on the sentences found in the raw
     * argument (the raw text).
     * 
     * @param raw   raw recipe as one whole string
     * @return      list of sentences in raw, annotated in OpenNLP format
     */
    public ArrayList<String> rawToParses(String raw) {
        // TODO
        String[] sentences = this._sentDetector.sentDetect(raw);

        ArrayList<String> parses = new ArrayList<String>();
        for (String s : sentences) {
            // Tokenize, recombine, parse, then add to our running list
            String tokens[] = this._tokenizer.tokenize(s);
            String tokenizedSent = StringHelper.listToStr(tokens);

            // Now parse
            Parse topParses[] = ParserTool.parseLine(tokenizedSent, this._parser, 1);
            StringBuffer sb = new StringBuffer();
            topParses[0].show(sb);
            
            // Add to running list
            parses.add(sb.toString());
        }

        return parses;
    }

    
}
