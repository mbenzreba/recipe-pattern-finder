package com.mbenzreba.RecipePatternFinder;

import java.util.ArrayList;

final class Target 
{
    protected String _name;
    protected boolean _isFulfilled;
    protected ArrayList<String> _values;

    public Target() {
        this._name = "";
        this._isFulfilled = false;
        this._values = new ArrayList<String>();
    }

    public Target(String name)
    {
        this._name = name;
        this._isFulfilled = false;
        this._values = new ArrayList<String>();
    }

    public Target(String name, String val) {
        this._name = name;
        this._isFulfilled = true;
        this._values = new ArrayList<String>();
        this._values.add(val);
    }
}
