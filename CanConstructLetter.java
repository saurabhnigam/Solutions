/****
Ransom Note
Easy



Share
Given two strings ransomNote and magazine, return true if ransomNote can be constructed from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.

 

Example 1:

Input: ransomNote = "a", magazine = "b"
Output: false
Example 2:

Input: ransomNote = "aa", magazine = "ab"
Output: false
Example 3:

Input: ransomNote = "aa", magazine = "aab"
Output: true
 

Constraints:

1 <= ransomNote.length, magazine.length <= 105
ransomNote and magazine consist of lowercase English letters.

***/

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class CanConstructLetter {

    /*
     * Complete the 'canConstructLetter' function below.
     *
     * The function is expected to return a BOOLEAN.
     * The function accepts following parameters:
     *  1. STRING text
     *  2. STRING note
     */
//Time Complexity is O(n) where n= length of string text
    public static boolean canConstructLetter(String text, String note) {
    //hashmap of word counts
    Map<Character, Integer> charCountMap = new HashMap();

    if(note == null || text == null){
        return false;
    }

    for(char ch:text.toCharArray()){
        if(ch ==' '){
            continue;
        }
        int occurence = charCountMap.getOrDefault(ch, 0);
        charCountMap.put(ch, occurence+1);
    }
    

    for(char ch: note.toCharArray()){
        if(ch ==' '){
            continue;
        }else if(charCountMap.containsKey(ch)){
            int occurence = charCountMap.get(ch);
            if(occurence == 0){
                return false;
            }
            charCountMap.put(ch, occurence-1);
        }else{
            return false;
        }
    }
        return true;
    }

}
