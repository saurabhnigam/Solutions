/*****
425. Word Squares
Hard

1002

66

Add to List

Share
Given an array of unique strings words, return all the word squares you can build from words. The same word from words can be used multiple times. You can return the answer in any order.

A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 

Example 1:

Input: words = ["area","lead","wall","lady","ball"]
Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input: words = ["abat","baba","atan","atal"]
Output: [["baba","abat","baba","atal"],["baba","abat","baba","atan"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 4
All words[i] have the same length.
words[i] consists of only lowercase English letters.
All words[i] are unique.

Incorrect Sollution
TC : words^(word.length) beacuse at most length of tree would be 'word.length' & each level will have 'words' child
*****/

class WordSquares {
    List<List<String>> finalWords = new ArrayList();
    public List<List<String>> wordSquares(String[] words) {
        finalWords = new ArrayList();
        Trie trie = buildTrie(words);
        placeWords(new ArrayList(), 0, trie, words[0].length());
        return finalWords;
    }
    
    private void placeWords(List<String> placedWords,  int index, Trie trie, int targetIndex){ // O ()
        if(index == targetIndex){
            if(placedWords.size() == (targetIndex+1)){
                finalWords.add(placedWords);
            }
            return;
        }
        String prefix = getPrefix(placedWords, index); //O(word.length)
        List<String> matchingWords = findWords(trie, prefix); // O(word.length)
        
        for(String word : matchingWords){           //O (words)
            placedWords.add(word);
            placeWords(placedWords, index+1, trie, targetIndex);  //O(words)
            placedWords.remove(word);
        }
    }
    
    private String getPrefix(List<String> placedWords,  int index){ // O(word.length)
        StringBuilder prefix = new StringBuilder();
        if(placedWords.size() > index){
            
            for(int k = 0; k<index; k++){
                 prefix.append(placedWords.get(k).charAt(index));
            }
        }
        return prefix.toString();
    }
    
    private Trie buildTrie(String[] words){ // O(words * word.length)
        Trie root = new Trie();
        
        for(String word: words){
            char[] charArray = word.toCharArray();
            Trie curTrie = root;
            for(int i =0; i<charArray.length; i++){
                int curCharVal = charArray[i]-'a';
                Trie t = curTrie.chars[curCharVal];
                if(t == null){
                    t = new Trie();
                }
                curTrie.chars[curCharVal] = t;
                t.possibilities.add(word);
                if(i == charArray.length -1){
                    t.isEnd = true;
                }
                curTrie = t;
            }
        }
        
        return root;
    }
    
    private List<String> findWords(Trie trie, String prefix){ //O(word.length)
        Trie curTrie = trie;
        char[] ch = prefix.toCharArray();
        for(int i =0; i<ch.length; i++){
            Trie t = curTrie.chars[ch[i] -'a'];
            if(t != null){
                curTrie = t;
            }else{
                return new ArrayList();
            }
        }
        if(curTrie != null){
            return curTrie.possibilities;
        }else{
            return new ArrayList();
        }
    }
    
    class Trie{
        Trie[] chars  = new Trie[26];
        List<String> possibilities = new ArrayList<>();
        boolean isEnd = false;
    }
}
