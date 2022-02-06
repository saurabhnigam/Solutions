/****

Expressive Words
Sometimes people repeat letters to represent extra feeling. For example:

"hello" -> "heeellooo"
"hi" -> "hiiii"
In these strings like "heeellooo", we have groups of adjacent letters that are all the same: "h", "eee", "ll", "ooo".

You are given a string s and an array of query strings words. A query word is stretchy if it can be made to be equal to s by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is three or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has a size less than three. Also, we could do another extension like "ll" -> "lllll" to get "helllllooo". If s = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = s.
Return the number of query strings that are stretchy.

 

Example 1:

Input: s = "heeellooo", words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
Example 2:

Input: s = "zzzzzyyyyy", words = ["zzyy","zy","zyy"]
Output: 3
 

Constraints:

1 <= s.length, words.length <= 100
1 <= words[i].length <= 100
s and words[i] consist of lowercase letters.

****/

class ExpressiveWords {
    public int expressiveWords(String s, String[] words) {
        
        int counter =0;
        Map<String, Integer> m = getMap(s, null);
                
        for(String w: words){
            boolean wordExists = true;
            Map<String,Integer> m1 =  getMap(w, m);
            
            
            if(m1 == null || m1.size() != m.size()){
                    wordExists = false;
                    break;
            }
            if(wordExists && m.size() == m1.size()){

                for(Map.Entry<String, Integer> e :m.entrySet()){
                    if((m1.containsKey(e.getKey()) && e.getValue() == m1.get(e.getKey()))
                      || (m1.containsKey(e.getKey()) && e.getValue() >=3 &&  e.getValue() >m1.get(e.getKey()))){
                        
                    }else{
                        wordExists= false;
                        break;
                    }
                }
            }
            if(wordExists){
                counter++;
            }
        }
        return counter;
    }
    
    private Map<String, Integer> getMap(String s, Map<String, Integer> origM){
        Map<String, Integer> m = new HashMap();
        char prev ='\0';
        int index=-1;

        for(char c : s.toCharArray()){
            String c1 = c+"";
            if(c != prev){
                index++;
            }
            String cw = c+""+index;
            int count = m.getOrDefault(cw,0);
            m.put(cw, count+1);
            
            if((origM != null && !origM.containsKey(cw)) || (origM != null && count>origM.get(cw)) || (origM != null && origM.size() < m.size())){
                    return null;
            }
            prev = c;
        }
        return m;
    }
}
