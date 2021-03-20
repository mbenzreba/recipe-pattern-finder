package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.util.ArrayList;


/**
 * @author Mohamed Benzreba
 */
public class RulebookKeeper {


    /** Tracks fulfillment rules for each target */
    private RulePointerMap _fulfills;
    /** Tracks fix rules for each target */
    private RulePointerMap _fixes;


    /**
     * Returns a RulebookKeeper that keeps a library of every relevant location in the
     * requested rulebook.
     * 
     * @param rulebook  resource file that contains the rules to fulfill
     */
    public RulebookKeeper(String rulebook) {
        // TODO: Open up 'rulebook', which should be found in resources
        // Set up pointers to each section of the rulebook
        // For both _fulfills and _fixes
    }



    /**
     * Returns a list of targets (as Strings) that this rulebook is attempting to fulfill.
     * 
     * @return  ArrayList of target names
     */
    public ArrayList<String> getTargets() {
        // TODO: return a list of targets from the rulebook, that some Arborist can use
        return new ArrayList<String>();
    }



    /**
     * Returns a RuleTree that reperesents the next fix for the requested target.
     * 
     * @param target    name of target to fix
     * @return          an extended RuleTree that potentially fixes the issue the target is facing
     */
    public RuleTree getNextFix(String target) {
        // TODO: return a RuleTree that could potentially fix the requested target
        return new CorrectRuleTree("dummy-rule");
    }



    /**
     * Returns a RuleTree that represents the next fulfill for the requested target.
     * 
     * @param target    name of target to fulfill
     * @return          an extended RuleTree that potentially fulfills the target
     */
    public RuleTree getNextFulfill(String target) {
        // TODO: return a RuleTree that could potentially fulfill the requested target
        return new CorrectRuleTree("dummy-rule");
    }


    /**
     * @author Mohamed Benzreba
     */
    private class RulePointerMap {
        private ArrayList<String> _names;
        private ArrayList<Integer> _locations;

        public RulePointerMap() {
            this._names = new ArrayList<String>();
            this._locations = new ArrayList<Integer>();
        }
    }
    
}
