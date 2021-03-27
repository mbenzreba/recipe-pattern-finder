package com.mbenzreba.RecipePatternFinder;


// Java utils
import java.util.ArrayList;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
// Apache OpenNLP
import opennlp.tools.parser.Parser;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.tokenize.TokenizerME;


// TODO: This class probably does better as a singleton


/**
 * @author Mohamed Benzreba
 */
public class PreParser {


    /** Sentence detector model living in memory */
    private SentenceDetectorME _sentDetector;
    private TokenizerME _tokenizer;
    private Parser _parser;

    public PreParser() {
        // TODO: construction
        ModelLoader ml = new ModelLoader();

        // Load in models
        this._sentDetector = ml.loadSentenceDetector();
        this._tokenizer = ml.loadTokenizer();
        this._parser = ml.loadParser();
    }



    /**
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
