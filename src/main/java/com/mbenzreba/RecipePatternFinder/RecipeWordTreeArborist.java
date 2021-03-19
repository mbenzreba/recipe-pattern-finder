package com.mbenzreba.RecipePatternFinder;

import java.util.ArrayList;

/**
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
    public static RecipeWordTreeArborist getSingleton() {
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
    }


    /******************************************************************************************/
    /********************************** INSTANCE OPERATIONS ***********************************/
    /******************************************************************************************/


    private ArrayList<Target> _targets;


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
            boolean corrected = true;
            for (int targetIndex = 0; targetIndex < this._targets.size(); targetIndex++) {
                if (!originalRWT.fulfill(this._targets.get(targetIndex))) {
                    corrected = false;
                }
            }

            // TODO: Check if targets fulfilled add up to an acceptable state
            // (the acceptable state is defined in the .rules file)
            // If so, we are finished
            // If not, do it again
        }

        // TODO
        return correctedRWTs;
    }
    
}
