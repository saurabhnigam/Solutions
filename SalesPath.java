/***
Sales Path
The car manufacturer Honda holds their distribution system in the form of a tree (not necessarily binary). The root is the company itself, and every node in the tree represents a car distributor that receives cars from the parent node and ships them to its children nodes. The leaf nodes are car dealerships that sell cars direct to consumers. In addition, every node holds an integer that is the cost of shipping a car to it.

Take for example the tree below:

Sales path

A path from Honda’s factory to a car dealership, which is a path from the root to a leaf in the tree, is called a Sales Path. The cost of a Sales Path is the sum of the costs for every node in the path. For example, in the tree above one Sales Path is 0→3→0→10, and its cost is 13 (0+3+0+10).

Honda wishes to find the minimal Sales Path cost in its distribution tree. Given a node rootNode, write a function getCheapestCost that calculates the minimal Sales Path cost in the tree.

Implement your function in the most efficient manner and analyze its time and space complexities.

For example:

Given the rootNode of the tree in diagram above

Your function would return:

7 since it’s the minimal Sales Path cost (there are actually two Sales Paths in the tree whose cost is 7: 0→6→1 and 0→3→2→1→1)

Constraints:

[time limit] 5000ms

[input] Node rootNode

0 ≤ rootNode.cost ≤ 100000
[output] integer
***/

import java.io.*;
import java.util.*;

class Solution {


    /***
    Node root
    min =Integer.MAX;
    for(child:root.children){
        cost =  backtrack(child)
        min(min, cost)
    }

    backtrack(){
        min =Integer.MAX;
        if(no child)
            return node.cost
        else    
                for(child:root.children){
                    cost =  backtrack(child)
                    min(min, cost)

     return min;
    }
    }

    
    ***/
 
  static class Node {
      
    int cost;
    Node[] children;
    Node parent;

    Node(int cost) {
      this.cost = cost;
      children = null;
      parent = null;
    }
  }

  static class SalesPath {
        
    int getCheapestCost(Node rootNode) {
      return backtrack(rootNode);
    }

    private int backtrack(Node rootNode){
        
        if(rootNode == null){
            return 0;
        }else if((rootNode != null && rootNode.children ==null) || rootNode.children.length ==0) { //no child
            return rootNode.cost;
        }else{
            int min = Integer.MAX_VALUE;
            for(Node child : rootNode.children){
                int cost = backtrack(child)+rootNode.cost;
                min = Math.min(min, cost);
            }
            return min;
        }

        
    }
  }

  //(9, 7)b(0) - (9)b(5) - 4 b(4) 
    
  /*********************************************
   * Driver program to test above method     *
   *********************************************/

  public static void main(String[] args) {
    
  }
}
