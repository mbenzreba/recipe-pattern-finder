package com.mbenzreba.RecipePatternFinder;


// Java utils
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Mohamed Benzreba
 */
public class RecipeWordTree extends WordTree 
{

    private TargetCollection _targets;
    private HashMap<String, ArrayList<String>> _fulfilledTargets;


    /**
     * 
     * @param sentence
     */
    public RecipeWordTree(String sentence) 
    {
        // extends WordTree
        super(sentence);
        this._targets = new TargetCollection();
    }


    public boolean fulfill(String target)
    {
        // Try to fix it
        // TODO
        return true;
    }


    public String getSentence() {
        return super.getSentence();
    }


    /******************************************************************************************/
    /************************************** OPERATIONS ****************************************/
    /******************************************************************************************/


    public boolean operate(FulfillTargetRuleTree rt) {
        return false;
    }

    public boolean operate(CorrectRuleTree rt) {
        return false;
    }




    /******************************************************************************************/
    /******************************* SPH (SHITTY PLACEHOLDERS) ********************************/
    /******************************************************************************************/
    


    public void SPH_setTarget(String target, String val) {
        // If the target already exists, add to it
        if (this._targets._collec.containsKey(target)) {
            this._targets._collec.get(target)._isFulfilled = true;
            this._targets._collec.get(target)._values.add(val);
        }
        // If it doesn't, make it and then add to it
        else {
            this._targets._collec.put(target, new Target(target, val));
        }
    }
    

    public String SPH_getTarget(String target) {
        return this._targets._collec.get(target)._values.get(0);
    }

}
