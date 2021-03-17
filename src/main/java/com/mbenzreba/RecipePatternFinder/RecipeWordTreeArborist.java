package com.mbenzreba.RecipePatternFinder;


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
        // TODO
    }
    
}
