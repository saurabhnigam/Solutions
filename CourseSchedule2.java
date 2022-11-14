/******
210. Course Schedule II

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]
 

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.

TC O(V+E)
SC O(V+E)

Refs :
https://youtu.be/bP3MWJHeohc
https://www.youtube.com/watch?v=tggiFvaxjrY
*****/

class CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> visited = new ArrayList();
        Map<Integer, List<Integer>> depMap = new HashMap();
        Queue<Integer> q = new LinkedList();
       
        //createIndegree & dep map
        int[] inDegree = new int[numCourses];
        Arrays.fill(inDegree, 0);
        
        for(int[] pair: prerequisites){  // Adjacency list - no of edges - O(E)
            inDegree[pair[0]]++;
            List<Integer> depList = depMap.getOrDefault(pair[1], new ArrayList());
            depList.add(pair[0]);
            depMap.put(pair[1], depList);
        }
        
        //find node with 0 indegree
        for(int i =0; i<inDegree.length; i++){   //O(V)
            if(inDegree[i] == 0){
                q.add(i);
            }
        }
        
        while(q.peek() != null){    //O(V+E) (1+E1)+(1+E2)+(1+E3) + (1+E4) = V+Total Edges
            int node = q.poll(); //O(1)
            
            //find node adja to i & decrement their indegree
            List<Integer> depList = depMap.getOrDefault(node, new ArrayList());
            for(int dep: depList){ //O(E)  (E1+E2+E3 + E4)
                inDegree[dep]--;
                if(inDegree[dep] == 0){
                    q.add(dep); // add to q if indegree 0 O(1)
                }
            }
            visited.add(node);
        }
        
        if(visited.size() != numCourses){ // we have a cycle)
            return new int[0];
        }
        
        int []finalOrder = new int[numCourses];

        for(int i =0; i<visited.size();i++){   //O(V)
            finalOrder[i]= visited.get(i);
        }
        return finalOrder;
    }
    
}
