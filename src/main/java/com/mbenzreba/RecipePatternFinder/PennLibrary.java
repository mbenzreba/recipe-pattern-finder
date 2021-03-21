package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Mohamed Benzreba
 */
public class PennLibrary {
    
    /******************************************************************************************/
    /********************************* SINGLETON MANAGEMENT ***********************************/
    /******************************************************************************************/


    /** Single instance accessor */
    private static PennLibrary singleton = null;


    /**
     * Must be called to access the PennLibrary's capabilities. Returns the only existing
     * instance of the PennLibrary; if it does not exist, it makes one.
     * 
     * @return  a PennLibrary
     */
    public static PennLibrary get() {
        if (singleton == null) {
            singleton = _createSingleton();
        }
        return singleton;
    }



    /**
     * Creates an instance of a PennLibrary.
     * 
     * @return  a new instance of the PennLibrary
     */
    private static PennLibrary _createSingleton() {
        return new PennLibrary();
    }



    /**
     * Private constructor, meant for use only by _createSingleton().
     */
    private PennLibrary() {
        // Init set of clause level tags
        ArrayList<String> pos = new ArrayList<String>();

        pos.add("S");
        pos.add("SBAR");
        pos.add("SBARQ");
        pos.add("SINV");
        pos.add("SQ");

        this._clauseLevelSet = new HashSet<String>(pos);


        // Init set of phrase level tags
        pos = new ArrayList<String>();

        pos.add("ADJP");
        pos.add("ADVP");
        pos.add("CONJP");
        pos.add("FRAG");
        pos.add("INTJ");
        pos.add("LST");
        pos.add("NAC");
        pos.add("NP");
        pos.add("NX");
        pos.add("PP");
        pos.add("PRN");
        pos.add("PRT");
        pos.add("QP");
        pos.add("RRC");
        pos.add("UCP");
        pos.add("VP");
        pos.add("WHADJP");
        pos.add("WHAVP");
        pos.add("WHNP");
        pos.add("WHPP");
        pos.add("X");

        this._phraseLevelSet = new HashSet<String>(pos);


        // Init set of adjectives
        pos = new ArrayList<String>();

        pos.add("JJ");
        pos.add("JJR");
        pos.add("JJS");

        this._adjectiveSet = new HashSet<String>(pos);


        // Init set of adverbs
        pos = new ArrayList<String>();

        pos.add("RB");
        pos.add("RBR");
        pos.add("RBS");

        this._adverbSet = new HashSet<String>(pos);


        // Init set of conjunctions
        pos = new ArrayList<String>();

        pos.add("CC");
        pos.add("IN");

        this._conjunctionSet = new HashSet<String>(pos);


        // Init set of nouns
        pos = new ArrayList<String>();

        pos.add("NN");
        pos.add("NNS");
        pos.add("NNP");
        pos.add("NNPS");

        this._nounSet = new HashSet<String>(pos);


        // Init set of verbs
        pos = new ArrayList<String>();

        pos.add("VB");
        pos.add("VBD");
        pos.add("VBG");
        pos.add("VBN");
        pos.add("VBP");
        pos.add("VBZ");

        this._verbSet = new HashSet<String>(pos);


        // Init set of wh- words
        pos = new ArrayList<String>();

        pos.add("WDT");
        pos.add("WP");
        pos.add("WP$");
        pos.add("WRB");

        this._whSet = new HashSet<String>(pos);


        // Init set of other words
        pos = new ArrayList<String>();

        pos.add("CD");
        pos.add("DT");
        pos.add("EX");
        pos.add("FW");
        pos.add("LS");
        pos.add("MD");
        pos.add("PDT");
        pos.add("POS");
        pos.add("PRP");
        pos.add("PRP$");
        pos.add("RP");
        pos.add("SYM");
        pos.add("TO");
        pos.add("UH");

        this._otherWordsSet = new HashSet<String>(pos);

    }


    /******************************************************************************************/
    /********************************** INSTANCE OPERATIONS ***********************************/
    /******************************************************************************************/


    private HashSet<String> _clauseLevelSet;
    private HashSet<String> _phraseLevelSet;

    private HashSet<String> _adjectiveSet;
    private HashSet<String> _adverbSet;
    private HashSet<String> _conjunctionSet;
    private HashSet<String> _nounSet;
    private HashSet<String> _verbSet;
    private HashSet<String> _whSet;
    private HashSet<String> _otherWordsSet;


    public boolean isClause(String idk) {
        return this._clauseLevelSet.contains(idk);
    }

    public boolean isPhrase(String idk) {
        return this._phraseLevelSet.contains(idk);
    }

    public boolean isParentTag(String idk) {
        return (this.isClause(idk) || this.isPhrase(idk));
    }

    public boolean isAdjective(String idk) {
        return this._adjectiveSet.contains(idk);
    }

    public boolean isAdverb(String idk) {
        return this._adverbSet.contains(idk);
    }

    public boolean isConjunction(String idk) {
        return this._conjunctionSet.contains(idk);
    }

    public boolean isNoun(String idk) {
        return this._nounSet.contains(idk);
    }

    public boolean isVerb(String idk) {
        return this._verbSet.contains(idk);
    }

    public boolean isWh(String idk) {
        return this._whSet.contains(idk);
    }

    public boolean isLeafTag(String idk) {
        return (this.isAdjective(idk) || this.isAdverb(idk) || this.isConjunction(idk) ||
            this.isNoun(idk) || this.isVerb(idk) || this.isWh(idk) || this._otherWordsSet.contains(idk));
    }

    public boolean isTag(String idk) {
        return (this.isParentTag(idk) || this.isLeafTag(idk));
    }
}
