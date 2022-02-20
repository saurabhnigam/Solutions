/***
Robot Room Cleaner

Solution
You are controlling a robot that is located somewhere in a room. The room is modeled as an m x n binary grid where 0 represents a wall and 1 represents an empty slot.

The robot starts at an unknown location in the room that is guaranteed to be empty, and you do not have access to the grid, but you can move the robot using the given API Robot.

You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room). The robot with the four given APIs can move forward, turn left, or turn right. Each turn is 90 degrees.

When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell.

Design an algorithm to clean the entire room using the following APIs:

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Note that the initial direction of the robot will be facing up. You can assume all four edges of the grid are all surrounded by a wall.

 

Custom testing:

The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the four mentioned APIs without knowing the room layout and the initial robot's position.

 

Example 1:


Input: room = [[1,1,1,1,1,0,1,1],[1,1,1,1,1,0,1,1],[1,0,1,1,1,1,1,1],[0,0,0,1,0,0,0,0],[1,1,1,1,1,1,1,1]], row = 1, col = 3
Output: Robot cleaned all rooms.
Explanation: All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
Example 2:

Input: room = [[1]], row = 0, col = 0
Output: Robot cleaned all rooms.
 

Constraints:

m == room.length
n == room[i].length
1 <= m <= 100
1 <= n <= 200
room[i][j] is either 0 or 1.
0 <= row < m
0 <= col < n
room[row][col] == 1
All the empty cells can be visited from the starting position.
***/

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 
 To track the cells the robot has cleaned, start a index pair (i, j) from (0, 0). When go up, i-1; go down, i+1; go left, j-1; go right: j+1.
Also use currDir to record the current direction of the robot
 * }
 */

class RobotRoomCleaner {
    int i =0,j=0;
    Set<String> visited = new HashSet();
    char currDir = 't';
    public void cleanRoom(Robot robot) {
        i =0;
        j = 0;
        visited = new HashSet();
        currDir = 't';
        
        goClean(robot);
    }
    
    private boolean goClean(Robot robot){
        robot.clean();
        if(visited.add(i+"_"+j)) {

            if(!visited.contains((i-1)+"_"+j) && goTop(robot, false) && goClean(robot)){
                robot.clean();
                goDown(robot);
            }
            if(!visited.contains((i)+"_"+(j-1)) && goLeft(robot) && goClean(robot)){
                robot.clean();
                goRight(robot);
            }
            if(!visited.contains((i+1)+"_"+j) && goDown(robot) &&  goClean(robot)){
                robot.clean();
                goTop(robot, false);
            }
            if(!visited.contains(i+"_"+(j+1)) && goRight(robot) &&  goClean(robot)){
                robot.clean();
                goLeft(robot);
            }
            return true;
        }else{
            return false;
        }
        
    }
    
    
    private boolean goTop(Robot robot, boolean turnOnly){
        if(currDir!='t'){
            if(currDir=='l'){
                robot.turnRight();
            }else if(currDir == 'r'){
                robot.turnLeft();
            }else{
                //bottom dir
                robot.turnLeft();
                robot.turnLeft();
            }
        }
        currDir = 't';
        if(turnOnly){
            return false;
        }
        boolean b = robot.move();
        if(b){
            i=i-1;
        }
        return b;
    }
    
    private boolean goDown(Robot robot){
        goTop(robot, true);
        robot.turnLeft();
        robot.turnLeft();
        boolean b = robot.move();
        if(b){
            i=i+1;
        }
        currDir = 'd';
        return b;
    }
    
    
    private boolean goRight(Robot robot){
        goTop(robot, true);
        robot.turnRight();
        boolean b = robot.move();
        if(b){
            j=j+1;
        }
        currDir = 'r';
        return b;
    }
    
    
    private boolean goLeft(Robot robot){
        goTop(robot, true);
        robot.turnLeft();
        boolean b = robot.move();
        if(b){
            j=j-1;
        }
        currDir = 'l';
        return b;
    }
    
}
