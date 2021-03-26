package com.mbenzreba.RecipePatternFinder;


// Java imports
import java.util.HashMap;


/**
 * @author Mohamed Benzreba
 */
public class TargetCollection {

    protected HashMap<String, Target> _collec;

    public TargetCollection() {
        this._collec = new HashMap<String, Target>();
    }


    public void addTarget(String name) {
        this._collec.put(name, new Target());
    }
    
}
