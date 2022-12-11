/******
48. Rotate Image

You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

 

Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000

TC O(m.n)
SC O(1)
*****/

class RotateImage {
    public void rotate(int[][] a) { //TC O(m.n)
        int n = a.length;
        for(int i =0; i<n;i++){
            for(int j =i;j<(n-i-1);j++){
                int t =a[i][j];
                
              //row1 = col & col1 = n-1-row
                int i1=j,j1=n-1-i;
                int t1 = a[i1][j1];
                a[i1][j1]=t;
                
              
              //row2 = col1 & col2 = n-1-row1
                int i2=j1,j2=n-1-i1;
                int t2= a[i2][j2];
                a[i2][j2] = t1;
                
              
              //row3 = col2 & col3 = n-1-row2
                int i3=j2,j3=n-1-i2;
                int t3 = a[i3][j3];
                a[i3][j3] = t2;
                
                a[i][j] =t3;
                
            }
        }
    }
}
