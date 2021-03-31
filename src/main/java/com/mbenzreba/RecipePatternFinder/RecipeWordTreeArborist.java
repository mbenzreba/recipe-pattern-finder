package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Singleton used as a sort-of facade from which clients can create RecipeWordTrees. So, it
 * is implemented as a singleton, acts as a facade so that clients only need to interact with it
 * (and not other, potentially more confusing classes in this subsystem like RulebookKeeper or
 * RuleTrees), as well as a factory for producing RecipeWordTrees, since RecipeWordTrees should
 * never be instantiated on their own.
 * 
 * @author Mohamed Benzreba
 */
public class RecipeWordTreeArborist {


    /******************************************************************************************/
    /********************************* SINGLETON MANAGEMENT ***********************************/
    /******************************************************************************************/


    /** Single instance accessor */
    private static RecipeWordTreeArborist singleton = null;


    /**
     * 
     * @return
     */
    public static RecipeWordTreeArborist get() {
        if (singleton == null) {
            singleton = _createSingleton();
        }
        return singleton;
    }



    /**
     * 
     * @return
     */
    private static RecipeWordTreeArborist _createSingleton() {
        // TODO
        return new RecipeWordTreeArborist();
    }



    /**
     * 
     */
    private RecipeWordTreeArborist() {
        // TODO: Set up a RuleBookKeeper for RecipeWordTree.rules
        this._rulebook = new RulebookKeeper("RecipeWordTree.rules");
    }


    /******************************************************************************************/
    /********************************** INSTANCE OPERATIONS ***********************************/
    /******************************************************************************************/


    private RulebookKeeper _rulebook;
    private HashMap<String, ArrayList<String>> _targets;


    /**
     * Returns an ArrayList of RecipeWordTrees that adhere to the rules defined in RecipeWordTree.rules.
     * Public getters can be called on the RWT to retrieve natural language information about the
     * sentence the RWT represents.
     * 
     * @param sentence  sentence to create RWT(s) from
     * @return          list of corrected RWTs
     */
    public ArrayList<RecipeWordTree> createTrees(String sentence) {
        // TODO
        RecipeWordTree originalRWT = new RecipeWordTree(sentence);
        ArrayList<RecipeWordTree> correctedRWTs = new ArrayList<RecipeWordTree>();

        boolean finished = false;
        while (!finished) {

            // TODO: Check if targets fulfilled add up to an acceptable state
            // (the acceptable state is defined in the .rules file)
            // If so, we are finished
            // If not, do it again
            
        }

        // TODO
        return correctedRWTs;
    }
    
}
