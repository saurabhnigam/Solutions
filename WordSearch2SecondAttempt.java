/****
212. Word Search II

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
All the strings of words are unique

Got Time Limit Exceeded
TC: O(m * n * min(4^wl, m * n) + wl * num_words)
wl is the average length of the words 

******/

class WordSearch2SecondAttempt{
    Set<String> foundWords = new HashSet();
    public List<String> findWords(char[][] board, String[] words) {
        foundWords = new HashSet();
        Trie root = buildTrie(words);
        for(int i =0; i<board.length; i++){
            for(int j =0;j<board[0].length;j++){
                explore(i,j, board, root, new HashSet());
            }
        }
        List<String> foundList = new ArrayList();
        foundList.addAll(foundWords);
        return foundList;
    }
    
    private void explore(int i, int j, char[][] board, Trie trie, Set<String> visitedCells){
        //check out of bounds
        
        if(isValid(i, j, board) && trie.trieMap.containsKey(board[i][j]) && visitedCells.add(i+"_"+j) ){
            char curChar = board[i][j];
            Trie curTrie = trie.trieMap.get(curChar);
            if(curTrie.isEnd){
                foundWords.addAll(curTrie.words);
            }
            explore(i+1, j, board, curTrie, visitedCells);
            explore(i-1, j, board, curTrie, visitedCells);
            explore(i, j+1, board, curTrie, visitedCells);
            explore(i, j-1, board, curTrie, visitedCells);
                
            visitedCells.remove(i+"_"+j);
        }
    }
    
    private boolean isValid(int i , int j , char[][] board){
        return !(i<0 || j<0 || i>=board.length || j >=board[0].length);
    }
    
    private Trie buildTrie(String[] words){
        Trie root = new Trie();
        for(String word: words){
            Trie cur = root;
            char[] charArr = word.toCharArray();
            int len = charArr.length;
            for(int i =0; i< len; i++){
                Trie childTrie = cur.trieMap.getOrDefault(charArr[i], new Trie());
                cur.trieMap.put(charArr[i], childTrie);
                if(i == (len-1)){
                    //is last char
                    childTrie.isEnd = true;
                    childTrie.words.add(word);
                }
                cur = childTrie;
            }
        }
        return root;
    }
    
    class Trie{
        Map<Character, Trie> trieMap = new HashMap();
        boolean isEnd = false;
        List<String> words = new ArrayList();
    }
}
