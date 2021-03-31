package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Mohamed Benzreba
 */
public class RulebookKeeper {


    /** Really should be in RuleInterpreter, but whatever */
    static public char OPEN_FIXFILL_CH = '[';
    static public char CLOSE_FIXFILL_CH = ']';
    static public String FIX_TYPE_MARKER = "@";
    static public String FULFILL = "fulfill";
    static public String FIX = "fix";

    /** Fix types */
    static public String CORRECT = "correct";


    /** The rulebook */
    private FileLooper _rulebook;
    /** Tracks rules for each target */
    private RulePointerMapCollection _targets;


    /**
     * Returns a RulebookKeeper that keeps a library of every relevant location in the
     * requested rulebook.
     * 
     * @param rulebook  resource file that contains the rules to fulfill
     */
    public RulebookKeeper(String rulebook) {
        // Open up 'rulebook', which should be found in resources
        this._rulebook = new FileLooper(rulebook);

        // Start our target collection
        this._targets = new RulePointerMapCollection();

        // Set up pointers to each section of the rulebook
        int lineNo = 1;

        this._rulebook.startReading();
        String line = this._rulebook.nextLine();

        while (line != null) {
            // Check fix/fulfill heading
            String fixfill = StringHelper.getStringBetween(line, OPEN_FIXFILL_CH, CLOSE_FIXFILL_CH);

            // Main processing of fix/fulfill heading line
            if (!fixfill.isBlank()) {
                // This is a target heading...
                int headingIndex = line.indexOf(" ");
                String heading = line.substring(headingIndex+1).strip();

                // Add the target to our collection if we haven't yet
                if (!this._targets.contains(heading)) {
                    // Add the target
                    this._targets.addTarget(heading);
                }

                // Now set the fix or fill line number for this target
                if (fixfill.contentEquals(FULFILL)) {
                    this._targets.addTargetFulfill(heading, lineNo+1);
                }
                else if (fixfill.contentEquals(FIX)) {
                    this._targets.addTargetFix(heading, lineNo+1);
                }
            }

            // Next line
            line = this._rulebook.nextLine();
            lineNo++;
        }
    }



    /**
     * Returns a list of targets (as Strings) that this rulebook is attempting to fulfill.
     * 
     * @return  ArrayList of target names
     */
    public ArrayList<String> getTargetNames() {
        return this._targets.getTargetNames();
    }


    /**
     * Returns a RuleTree that represents the next fulfill for the requested target.
     * 
     * @param target    name of target to fulfill
     * @return          an extended RuleTree that potentially fulfills the target
     */
    public RuleTree getNextFulfill(String target) {
        // Return a RuleTree that could potentially fulfill the requested target
        String rule = this._rulebook.readLine(this._targets.getNextFulfill(target));
        return new FulfillTargetRuleTree(rule);
    }



    /**
     * Returns a RuleTree that reperesents the next fix for the requested target.
     * 
     * @param target    name of target to fix
     * @return          an extended RuleTree that potentially fixes the issue the target is facing
     */
    public RuleTree getNextFix(String target) {
        String elements[] = this._rulebook.readLine(this._targets.getNextFix(target)).split(FIX_TYPE_MARKER);

        // Compare to figure out which type of fix tree to return
        RuleTree rt;
        if (elements[0].contentEquals(CORRECT)) {
            rt = new CorrectRuleTree(elements[1]);
        }
        else {
            // This is like a dummy word tree
            rt = new VariableRuleTree(elements[1]);
        }
        return rt;
    }



    



    /* ---------------------------------------------------------------------------------------------------- */
    /* ----------------------------------------- INTERNAL CLASSES ----------------------------------------- */
    /* ---------------------------------------------------------------------------------------------------- */



    /**
     * @author Mohamed Benzreba
     */
    private class RulePointerMapCollection {
        private HashMap<String, RulePointerMap> _rulePointers;

        public RulePointerMapCollection() {
            this._rulePointers = new HashMap<String, RulePointerMap>();
        }

        public boolean contains(String key) {
            return this._rulePointers.containsKey(key);
        }

        public void addTarget(String targetName) {
            this._rulePointers.put(targetName, new RulePointerMap());
        }

        public void addTargetFulfill(String targetName, int fix) {
            this._rulePointers.get(targetName)._beginFulfillPoint = fix;
            this._rulePointers.get(targetName)._nextFulfillPoint = fix;
        }

        public void addTargetFix(String targetName, int fix) {
            this._rulePointers.get(targetName)._beginFixPoint = fix;
            this._rulePointers.get(targetName)._nextFixPoint = fix;
        }


        public ArrayList<String> getTargetNames() {
            return new ArrayList<String>(this._rulePointers.keySet());
        }

        public int getNextFulfill(String target) {
            return this._rulePointers.get(target)._nextFulfillPoint++;
        }

        public int getNextFix(String target) {
            return this._rulePointers.get(target)._nextFixPoint++;
        }
    }



    /**
     * @author Mohamed Benzreba
     */
    private class RulePointerMap {
        private int _beginFulfillPoint;
        private int _nextFulfillPoint;
        private int _beginFixPoint;
        private int _nextFixPoint;


        public RulePointerMap() {
        }
    }
    
}
