package com.mbenzreba.RecipePatternFinder;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RecipeWordTreeTest {

    @Test
    public void checkConstruction() {
        RecipeWordTree rwt = new RecipeWordTree("(TOP (PP (IN Mix) (NP (NN everything)) (. .)))");
        assertTrue(rwt.getSentence().contentEquals("Mix everything ."));
    }
    
}
