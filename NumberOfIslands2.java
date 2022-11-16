/*****305. Number of Islands II

You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).

We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.

Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:


Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
Output: [1,1,2,3]
Explanation:
Initially, the 2d grid is filled with water.
- Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
- Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
- Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
- Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
Example 2:

Input: m = 1, n = 1, positions = [[0,0]]
Output: [1]
 

Constraints:

1 <= m, n, positions.length <= 104
1 <= m * n <= 104
positions[i].length == 2
0 <= ri < m
0 <= ci < n
 

Follow up: Could you solve it in time complexity O(k log(mn)), where k == positions.length?

My solution couldn ot clear all testcases

Time Complexity should be k*4(four directions)*2(to find tree parent)
SC:O(mn)
https://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/UnionFind.pdf
https://stackoverflow.com/questions/6342967/why-is-the-ackermann-function-related-to-the-amortized-complexity-of-union-find
https://stackoverflow.com/questions/71453109/is-this-union-find-really-on-as-they-claim
https://www.geeksforgeeks.org/union-by-rank-and-path-compression-in-union-find-algorithm/
https://leetcode.com/problems/number-of-islands-ii/discuss/2108226/Clean-Readable-Code-oror-Union-Find-and-Dictionary-oror-O(k)-time-complexity-and-O(k)-space-complexity
******/

class NumberOfIslands2 {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int islands = 0;
        List<Integer> islandList = new ArrayList();
        int[][] matrix = new int[m][n];
        int[] parent = new int[m*n];
        int[] rank = new int[m*n];
        
        Arrays.fill(parent, -1);
        for(int i =0; i<m; i++){
            Arrays.fill(matrix[i], -1);
        }
        
        for(int i =0; i<positions.length; i++){ //O(positions*4*2)
            int[] pair = positions[i];
            int r = pair[0];
            int c = pair[1];
            if(matrix[r][c] == -1){ // if is water
                islands = explore(r,c, matrix, islands, parent, rank);
                islandList.add(islands);
                print(matrix);
            }else{
                islandList.add(islands); // no change
            }
        }
        return islandList;
    }
    
    private int explore(int r , int c, int[][]m, int islands, int[] parent, int[] rank){
        int curval = m[r][c];
        int n = m[0].length;
        boolean surroundedByWater = true;
        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        System.out.println("inserting  at "+r+","+c+" islands ="+islands+" parent ="+Arrays.toString(parent));
        
        for(int i =0;i<dirs.length; i++){. //O(4*2)
            int rHash = r+ dirs[i][0];
            int cHash = c+ dirs[i][1];
            
            if(isValid(rHash, cHash, m)){
                
                if(m[r][c] == -1 && m[rHash][cHash]>=0){
                    surroundedByWater = false;
                    m[r][c] = findParent(m[rHash][cHash], parent); //parent of adjacent to new cell .O(2) because it only takes max 2 steps to find parent
                    parent[r*n+c] = m[r][c];
                    rank[m[r][c]]++; //now parent of r,c & rHash, cHash is same increment their parents rank
                }else if(m[r][c] >= 0 && m[rHash][cHash] >= 0 && m[rHash][cHash] != m[r][c]){
                    surroundedByWater = false;
                    //merge
                    int p = findParent(r*n+c, parent);
                    int pHash = findParent(rHash*n+cHash, parent);
                    if(p != pHash){
                        islands--;
                        int bRank = rank[r*n+c];
                        int bRankHash = rank[rHash*n+cHash];
                        if(bRank < bRankHash){
                            parent[r*n+c] = pHash;
                            m[r][c] = pHash;
                            //rank wont change
                        }else {
                            //p!= pHash || bRank >= bRankHash|| p == pHash
                            parent[rHash*n+cHash] = p;
                            m[rHash][cHash] = p;
                        }
                    }
                    
                }
            }
        }
        if(islands == 0 || surroundedByWater){
            islands ++; //all sides water only
            parent[r*n+c] = -1;
            m[r][c] = r*n+c;
        }
        System.out.println("Done  at "+r+","+c+" islands ="+islands+" parent ="+Arrays.toString(parent));

        return islands;
    }
    
    private  boolean isValid(int r, int c, int[][] matrix){
        if(r<0 || r>=matrix.length || c<0 || c>=matrix[0].length){
            return false;
        }
        return true;
    }
    
    private int findParent(int pos, int[] parent){//O(2) because it only takes max 2 steps to find parent using Path Compression
        if(parent[pos] == -1){
            return pos; //is absolute parent/root Node
        }else{
            int root = findParent(parent[pos], parent);
            parent[pos] = root; //path compression
            return root;
        }
    }
    
    private void print(int[][] matrix){
        for(int i =0; i<matrix.length;i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
  
  
  
  /*****Correct SOlution from https://leetcode.com/problems/number-of-islands-ii/discuss/75470/Easiest-Java-Solution-with-Explanations 
  
  int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

public List<Integer> numIslands2(int m, int n, int[][] positions) {
    List<Integer> result = new ArrayList<>();
    if(m <= 0 || n <= 0) return result;

    int count = 0;                      // number of islands
    int[] roots = new int[m * n];       // one island = one tree
    Arrays.fill(roots, -1);            

    for(int[] p : positions) {
        int root = n * p[0] + p[1];     // assume new point is isolated island
        roots[root] = root;             // add new island
        count++;

        for(int[] dir : dirs) {
            int x = p[0] + dir[0]; 
            int y = p[1] + dir[1];
            int nb = n * x + y;
            if(x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) continue;
            
            int rootNb = findIsland(roots, nb);
            if(root != rootNb) {        // if neighbor is in another island
                roots[root] = rootNb;   // union two islands 
                root = rootNb;          // current tree root = joined tree root
                count--;               
            }
        }

        result.add(count);
    }
    return result;
}

public int findIsland(int[] roots, int id) { //path compression
    while(id != roots[id]) {
        roots[id] = roots[roots[id]];   // only one line added
        id = roots[id];
    }
    return id;
}

  
  ****/

}
