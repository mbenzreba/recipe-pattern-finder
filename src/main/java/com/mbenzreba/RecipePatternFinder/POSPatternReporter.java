package com.mbenzreba.RecipePatternFinder;



// Java imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;



/**
 * Used to streamline the analysis of phrase-of-speech patterns found in
 * recipes.
 * 
 * @author Mohamed Benzreba
 */
public class POSPatternReporter {


    /** File to read in POS-tagged text from. */
    private File _corpus;
    /** Used to read from _corpus. */
    private BufferedReader _reader;


    /**
     * Returns an instance of POSPatternReporter that is ready to read and analyze patterns
     * found in the file pointed to by the resource key passed into this function.
     * 
     * @param resource  name of file containing POS-tagged text
     */
    public POSPatternReporter(String resource) 
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
            this._corpus = new File(c.getResource(corpusProperty).getFile());

            iStream.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }



    /**
     * Returns the number of times the posPattern argument occurs in the corpus this POSPatternReporter
     * object was instantiated with. Also prints to screen each pattern found, in plain text.
     * 
     * @param   posPattern  an array of phrases-of-speech signifiers
     * @return              the number of occurrences posPattern shows up in _corpus
     */
    public int findPattern(String[] posPattern) 
    {
        // Open file
        this.startReading();
        POSMap map = this._readLine();

        int patternOccurrences = 0;

        while (map != null)
        {
            // Analyze the map pattern and report findings
            ArrayList<POSMap> matchesFound = map.containsPattern(posPattern);
            patternOccurrences += matchesFound.size();

            for (POSMap match : matchesFound)
            {
                System.out.println(match.getWordSentence());
            }

            // Read next line into a POSMap
            map = this._readLine();
        }

        // Close file
        this.stopReading();
        return patternOccurrences;
    }



    /**
     * Reads a line of POS-tagged text using _reader and returns it as a ready-made POSMap.
     * 
     * @return  next line in _reader as POSMap
     * @see     POSMap
     */
    private POSMap _readLine() {
        POSMap map = null;
        try 
        {
            String line = this._reader.readLine();
            String[] tokens = line.split(" ");
            map = new POSMap(tokens);
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

        return map;
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
        try 
        {
            this._reader = new BufferedReader(new FileReader(this._corpus, StandardCharsets.UTF_16LE));
        } 
        catch (FileNotFoundException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

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




    /**
     * Used to neatly organize a sentence into its respective words and phrases-of-speech. It assumes
     * that the delimiter is an udnerscore (_). Preserves ordering of words, unlike a HashMap.
     * 
     * @author Mohamed Benzreba
     */
    private class POSMap
    {

        /** Sentence preserved as a list of regular words */
        private ArrayList<String> _wordSentence;
        /** Tracks where in the sentence we are, according to word position */
        private int _word_i;
        /** Sentence preserved as a list of phrases-of-speech */
        private ArrayList<String> _posSentence;
        /** Tracks where in the sentence we are, according to phrase-of-speech position */
        private int _pos_i;



        /**
         * Returns an empty POSMap.
         */
        public POSMap()
        {
            this._wordSentence = new ArrayList<String>();
            this._posSentence = new ArrayList<String>();
            this._word_i = 0;
            this._pos_i = 0;
        }



        /**
         * Returns a POSMap filled with words and POSs according to the posTokens argument. This
         * argument should be a sentence or phrase, and each element should be a String containing
         * a word and a phrase-of-speech: the word must come first, then the phrase-of-speech, and
         * they must be seperated by an underscore (_). Apache OpenNLP's POSTagging tool conducts
         * its parsing this way automatically.
         * 
         * @param posTokens a sentence already parsed by a POSTagging tool
         */
        public POSMap(String[] posTokens)
        {
            this._wordSentence = new ArrayList<String>();
            this._posSentence = new ArrayList<String>();
            this._word_i = 0;
            this._pos_i = 0;

            this._constructMap(posTokens);   
        }



        /**
         * Returns a list of POSMaps; each POSMap matches the posPattern in number, type, and
         * ordering of phrases-of-speech.
         * 
         * @param   posPattern  pattern of POSs to find in the current _posSentence, order-sensitive
         * @return              every match found in _posSentence
         */
        public ArrayList<POSMap> containsPattern(String[] posPattern)
        {
            ArrayList<POSMap> matches = new ArrayList<POSMap>();

            POSMap map = new POSMap();
            int pattern_i = 0;
            for (int i = 0; i < this._wordSentence.size(); i++)
            {
                if (posPattern[pattern_i].equals(this._posSentence.get(i)))
                {
                    // The POSs match
                    map.addPair(this._wordSentence.get(i), this._posSentence.get(i));
                    pattern_i++;

                    if (pattern_i >= posPattern.length)
                    {
                        // The entire phrase has been captured, there is a complete match
                        matches.add(map);

                        // Reset our values
                        // (In case there is another matching phrase within this sentence)
                        map = new POSMap();
                        pattern_i = 0;
                    }
                }
                else
                {
                    if (!map.isEmpty())
                    {
                        // Map is not empty
                        // Only a partial pattern match was found, so reset our map again
                        // TODO: Create a POSMap.clear() function so that an object does not need to be instantiated every time
                        map = new POSMap();
                    }
                    pattern_i = 0;
                }
            }
            return matches;
        }



        /**
         * Adds a word and corresponding POS to the map.
         * 
         * @param pair  {word, pos}
         */
        public void addPair(String[] pair)
        {
            this._wordSentence.add(pair[0]);
            this._posSentence.add(pair[1]);
        }



        /**
         * Adds a word and corresponding POS to the map.
         * 
         * @param word  the plain word
         * @param pos   the phrase-of-speech of that word
         */
        public void addPair(String word, String pos)
        {
            this._wordSentence.add(word);
            this._posSentence.add(pos);
        }



        /**
         * Determines if the map is empty.
         * 
         * @return  true if the map is empty; false otherwise
         */
        public boolean isEmpty()
        {
            return (this._wordSentence.size() == 0);
        }



        /**
         * Returns _wordSentence as a simple String.
         * 
         * @return  _wordSentence
         */
        public String getWordSentence()
        {
            String sent = "";
            for (String word : this._wordSentence)
            {
                sent += word + " ";
            }
            return sent;
        }



        /**
         * Helper method to the constructor. Constructs the _wordSentence and _posSentence instance
         * members using an array of POS-tagged tokens.
         * 
         * @param posTokens POS-tagged tokens, delimited by an underscore (_)
         */
        private void _constructMap(String[] posTokens)
        {
            for (String tok : posTokens)
            {
                String[] wordAndPos = tok.split("_");
                if (wordAndPos.length == 2)
                {
                    this._wordSentence.add(wordAndPos[0]);
                    this._posSentence.add(wordAndPos[1]);
                }
                
            }
        }



        /**
         * Gets the word and corresponding POS at the index argument position.
         * 
         * @param   index   index of sentence
         * @return          {word, pos} at index position of this POSMap's sentence
         */
        public String[] getPair(int index)
        {
            String[] pair = new String[2];
            pair[0] = this._wordSentence.get(index);
            pair[1] = this._posSentence.get(index);
            return pair;
        }


        // DEPRECATED
        // Was gonna use this, but nahh
        public String[] getNextPair()
        {
            String[] ret = new String[2];
            ret[0] = this._wordSentence.get(this._word_i++);
            ret[1] = this._posSentence.get(this._pos_i++);
            return ret;
        }


        // DEPRECATED
        // Was gonna use this, but nahh
        public boolean isFinished()
        {
            return (this._word_i >= this._wordSentence.size());
        }


    }
}
