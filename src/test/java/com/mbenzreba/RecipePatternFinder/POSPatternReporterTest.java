package com.mbenzreba.RecipePatternFinder;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class POSPatternReporterTest 
{

    private POSPatternReporter reporter;

    @Before
    public void init()
    {
        this.reporter = new POSPatternReporter("peruvian_chicken");
    }

    @Test
    public void fileReadingWorks()
    {
        assertTrue(this.reporter.startReading());
        assertTrue(this.reporter.stopReading());
    }

    @Test 
    public void findsPatterns()
    {
        String[] pattern = {"IN", "DT", "NN"};
        assertTrue(this.reporter.findPattern(pattern) == 6);
    }
    
}
