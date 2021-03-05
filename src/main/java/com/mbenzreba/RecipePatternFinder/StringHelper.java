package com.mbenzreba.RecipePatternFinder;

public class StringHelper {

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
}
