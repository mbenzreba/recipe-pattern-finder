package com.mbenzreba.RecipePatternFinder;

import java.util.ArrayList;

final class Target 
{
    private String _name;
    private boolean _isFulfilled;
    private ArrayList<String> _values;
    private int _nextFix;

    public Target(String name)
    {
        this._name = name;
        this._isFulfilled = false;
        this._values = new ArrayList<String>();
    }

    public Target(String name, int fixLine)
    {
        this._name = name;
        this._isFulfilled = false;
        this._values = new ArrayList<String>();
        this._nextFix = fixLine;
    }
}
