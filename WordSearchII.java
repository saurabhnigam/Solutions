/****212. Word Search II

Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example 1:


Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Example 2:


Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []
 

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.***/

/***
N = num of rows
M = num of columns
X = number of words in dictionary
Y = length of longest word in dictionary
//Time Complexity : O(M*N*(4^min(Y, M*N)) 
because we are iterating over evry cell 4 times . And we can only go till max M*N
//Space Complexity : O(words*letters in each word) for Trie Storage
***/

class WordSearchII {
    List<String> adjacentWords = new ArrayList();
    public List<String> findWords(char[][] board, String[] words) {
        adjacentWords = new ArrayList();   
        Trie t = buildTrie(words);
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                Trie thisTrie = t.children[board[i][j]-'a'];
                if(thisTrie != null){
                    lookupWord(i,j,thisTrie, new HashSet(), board);
                }
            }
        }
        return adjacentWords;
    }
    
    //recursively looks up/down,right, left to find the matching letter
    private void lookupWord(int i, int j, Trie currTrie, Set<String> visited, char[][] board){
        if(visited.add(i+"_"+j)){
            if(currTrie.word != null && !adjacentWords.contains(currTrie.word)){
                adjacentWords.add(currTrie.word);
            }
            //up
            if(i>0 && currTrie.children[board[i-1][j]-'a'] != null){
                lookupWord(i-1, j, currTrie.children[board[i-1][j]-'a'],visited, board);
            }
            //down
            if(i<board.length-1 && currTrie.children[board[i+1][j]-'a'] != null){
                lookupWord(i+1, j, currTrie.children[board[i+1][j]-'a'],visited, board);
            }
            //right
            if(j<board[0].length-1 && currTrie.children[board[i][j+1]-'a'] != null){
                lookupWord(i, j+1, currTrie.children[board[i][j+1]-'a'],visited, board);
            }
            //left
            if(j>0 && currTrie.children[board[i][j-1]-'a'] != null){
                lookupWord(i, j-1, currTrie.children[board[i][j-1]-'a'],visited, board);
            }
            visited.remove(i+"_"+j);
        }
    }
    
    //builds Trie
    private Trie buildTrie(String[] words){
        Trie root = new Trie();
        
        for(String word: words){
            Trie current = root;
            char[] letters = word.toCharArray();
            for(int i =0; i<letters.length;i++){
                if(current.children[letters[i]-'a'] == null){
                    current.children[letters[i]-'a'] = new Trie();
                }
                current = current.children[letters[i]-'a'];
                if(i == letters.length -1){
                    //if last char of word then add String here
                    current.word = word;
                }
                
            }
        }
        return root;
    }
    
    class Trie{
        Trie children[] = new Trie[26];
        String word = null; 
    }
}

