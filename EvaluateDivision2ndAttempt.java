/*****
399. Evaluate Division

You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

 

Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]
 

Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.

//TC O(Edges + Queries*(V+Edges)
        //SC O(V^2) 
*****/
class EvaluateDivision2ndAttempt {
   public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //TC O(Edges + Queries*(V+Edges)
        //SC O(V^2)  
        Map<String, Map<String, Double>> nodeMap = new HashMap();
        double[] results = new double[queries.size()];
        
        //create adj map
        for(int i =0; i<equations.size();i++){  // O(Equations)
            String source = equations.get(i).get(0);
            String dest = equations.get(i).get(1);
            double val = values[i];
            
            Map<String, Double> adjMap = nodeMap.getOrDefault(source, new HashMap<String, Double>());
            adjMap.put(source, 1.0); //path to self is 1
            adjMap.put(dest, val);
            nodeMap.put(source, adjMap);
            
            //add inverse path also from dest to source
            adjMap = nodeMap.getOrDefault(dest, new HashMap<String, Double>());
            adjMap.put(dest, 1.0); //self path as 1
            adjMap.put(source, (1/val));
            
            nodeMap.put(dest, adjMap);

        }
        
        
        //find path
        for(int i =0; i<queries.size(); i++){   
            List<String> queryList = queries.get(i);
            String source = queryList.get(0);
            String dest = queryList.get(1);
            
            results[i] = lookup(source, dest, nodeMap, new HashSet<String>());  //Q(q*E)  E = total edges
            //System.out.println(nodeMap.toString());

        }
        return results;
    }
    
    //path lookup TC O(V+E) = O(1+1+1+... + E1+E2+E3+...)
    private double lookup(String source, String dest, Map<String, Map<String, Double>> nodeMap, Set<String> visited ){
        double finalResult = -1;
        if(visited.add(source) && nodeMap.containsKey(source)){ // if it is not visited   //O(1)
            Map<String, Double> adjMap = nodeMap.get(source);
            if(adjMap.containsKey(dest)){
                return adjMap.get(dest);
            }
            
            for(String adj: adjMap.keySet()){  //TC(O(Ei)) 
                double res = lookup(adj, dest, nodeMap, visited);
                if(res >= 0.0){
                    finalResult = res;
                    adjMap.put(dest, adjMap.get(adj)*finalResult);  //cache update
                    return adjMap.get(adj)*finalResult;
                }
            }
        }
        return finalResult;
    }
    
   
}
