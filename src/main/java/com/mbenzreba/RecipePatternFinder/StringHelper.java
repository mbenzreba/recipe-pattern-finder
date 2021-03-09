package com.mbenzreba.RecipePatternFinder;



/**
 * Encapsulates static helper methods for use with Java strings.
 * 
 * @author Mohamed Benzreba
 */
public class StringHelper {


    /* ---------------------------------------------------------------------------------------------------- */
    /* ------------------------------------------ PUBLIC HELPERS ------------------------------------------ */
    /* ---------------------------------------------------------------------------------------------------- */



    /**
     * Returns the index of the nth occurrence of substr within str. Returns -1 if the nth occurrence
     * is never found within str.
     * 
     * @param str       string to search
     * @param substr    substr to find the nth occurrence of, within str
     * @param n         occurrence to find
     * @return          index of the nth occurrence of substr within str, -1 if not found
     */
    static public int findNthOccurrence(String str, String substr, int n) 
    {
        int fromIndex = 0;
        int occurrence = 0;
        boolean found = false;
        while (!found)
        {
            fromIndex = str.indexOf(substr, fromIndex);
            occurrence++;

            if ((occurrence == n) || (fromIndex < 0))
            {
                found = true;
            }
            else
            {
                fromIndex++;
            }
            
        }

        return fromIndex;
    }



    /**
     * Returns the original argument string with the insertion argument inserted into original, 
     * beginning at the index specified by the insertAt argument. Simply returns the original
     * string if the given insertAt index is out of bounds of the original string.
     * 
     * @param original  string to insert into
     * @param insertion string to insert
     * @param insertAt  index to insert insertion into string
     * @return          modified string, after insertion
     */
    static public String insertString(String original, String insertion, int insertAt)
    {
        String newStr = new String();

        for (int i = 0; i < original.length(); i++)
        {
            newStr += original.charAt(i);
            if (i == insertAt)
            {
                newStr += insertion;
            }
        }

        return newStr;
    }



    /**
     * Returns the amount of times the c argument occurs in the str argument.
     * 
     * @param str   string to iterate through
     * @param c     character to find occurrences of within string
     * @return      amount of times c is found in str
     */
    static public int countChar(String str, char c)
    {
        int count = 0;

        for(int i=0; i < str.length(); i++)
        {    
            if(str.charAt(i) == c)
            count++;
        }

        return count;
    }



    /**
     * Returns the str argument with all unwanted characters removed.
     * 
     * @param str       string to purge unwanted characters of
     * @param unwanted  unwanted character
     * @return          str without any of the unwanted character
     */
    static public String purgeCharacter(String str, char unwanted)
    {
        String newStr = "";

        for (int i=0; i < str.length(); i++)
        {
            if (str.charAt(i) != unwanted)
            {
                newStr += str.charAt(i);
            }
        }

        return newStr;
    }
}
