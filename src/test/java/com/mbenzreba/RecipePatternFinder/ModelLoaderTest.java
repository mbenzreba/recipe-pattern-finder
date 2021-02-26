package com.mbenzreba.RecipePatternFinder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class ModelLoaderTest 
{
    private ModelLoader _loader;

    @Before
    public void init()
    {
        this._loader = new ModelLoader();
    }

    @Test
    public void sentDetectorLoads()
    {
        assertTrue(this._loader.loadSentenceDetector() != null);
    }

    @Test
    public void tokenizerLoads()
    {
        assertTrue(this._loader.loadTokenizer() != null);
    }

    @Test
    public void posTaggerLoads()
    {
        assertTrue(this._loader.loadPOSTagger() != null);
    }

    @Test
    public void parserLoads()
    {
        assertTrue(this._loader.loadParser() != null);
    }
}
