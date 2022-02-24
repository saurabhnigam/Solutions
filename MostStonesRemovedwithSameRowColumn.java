/***
Most Stones Removed with Same Row or Column


Share
On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
Example 3:

Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 

Constraints:

1 <= stones.length <= 1000
0 <= xi, yi <= 104
No two stones are at the same coordinate point.
***/

class Solution {
    class Node{
        int i,j;
        List<Node> neighbours = new ArrayList();
        
        public String toString(){
            return "["+i+","+j+"] with children"+neighbours.size();
        }
    }
    int counter =0;
    public int removeStones(int[][] stones) {
        counter =0;
       Map<Integer, Node> colNodes = new HashMap();
        Map<Integer, Node> rowNodes = new HashMap();
        Set<Node> allNodes = new HashSet();
        
        Comparator nodeComparator = new Comparator<int[]>(){
            public int compare(int[] n1, int[] n2){
                if(n1[0]==n2[0]){
                    return n1[1]-n2[1];
                }
                return n1[0]-n2[0];
            }
        };
        Arrays.sort(stones, nodeComparator);
        
        for(int[] stone : stones){
            int i = stone[0];
            int j = stone[1];
            Node node = new Node();
            node.i =i;
            node.j = j;
            allNodes.add(node);

            if(!rowNodes.containsKey(i) && !colNodes.containsKey(j)){
                rowNodes.put(i,node);
                colNodes.put(j,node);
            }else{
                
                if(rowNodes.containsKey(i)){
                    Node nodeN = rowNodes.get(i);
                        nodeN.neighbours.add(node);
                        node.neighbours.add(nodeN);
                    
                }
                if(colNodes.containsKey(j)){
                    Node nodeN = colNodes.get(j);                        
                        nodeN.neighbours.add(node);
                        node.neighbours.add(nodeN);
                   
                }
                rowNodes.put(i,node);
                colNodes.put(j,node);
            }
        }
        int len=allNodes.size();
        int rootCounter =0;
        while(allNodes.size()!=0){
            Iterator<Node> it = allNodes.iterator();
            Node n = it.next();
            rootCounter++;
            it.remove();
            dfs(n, allNodes);
        }
        return len - rootCounter;
    }
    

    
    private void dfs(Node n, Set<Node> allNodes){
        if(n == null){
            return;
        }
        counter++;
        allNodes.remove(n);
        for(Node nNode:n.neighbours){
                
                if(allNodes.contains(nNode)){
                    dfs(nNode, allNodes);
                }
        }
    }
}
