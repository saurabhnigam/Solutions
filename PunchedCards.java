import java.util.*;
    import java.io.*;

    
    /***Problem
A secret team of programmers is plotting to disrupt the programming language landscape and bring punched cards back by introducing a new language called Punched Card Python that lets people code in Python using punched cards! Like good disrupters, they are going to launch a viral campaign to promote their new language before even having the design for a prototype. For the campaign, they want to draw punched cards of different sizes in ASCII art.

Example Punched Card.

The ASCII art of a punched card they want to draw is similar to an R×C matrix without the top-left cell. That means, it has (R⋅C)−1 cells in total. Each cell is drawn in ASCII art as a period (.) surrounded by dashes (-) above and below, pipes (|) to the left and right, and plus signs (+) for each corner. Adjacent cells share the common characters in the border. Periods (.) are used to align the cells in the top row.

For example, the following is a punched card with R=3 rows and C=4 columns:

..+-+-+-+
..|.|.|.|
+-+-+-+-+
|.|.|.|.|
+-+-+-+-+
|.|.|.|.|
+-+-+-+-+
There are more examples with other sizes in the samples below. Given the integers R and C describing the size of a punched card, print the ASCII art drawing of it as described above.

Input
The first line of the input gives the number of test cases, T. T lines follow, each describing a different test case with two integers R and C: the number of rows and columns of the punched card that must be drawn.

Output
For each test case, output one line containing Case #x:, where x is the test case number (starting from 1). Then, output (2⋅R)+1 additional lines with the ASCII art drawing of a punched card with R rows and C columns.

Limits
Time limit: 5 seconds.
Memory limit: 1 GB.
Test Set 1 (Visible Verdict)
1≤T≤81.
2≤R≤10.
2≤C≤10.
Sample
Sample Input
save_alt
content_copy
3
3 4
2 2
2 3
Sample Output
save_alt
content_copy
Case #1:
..+-+-+-+
..|.|.|.|
+-+-+-+-+
|.|.|.|.|
+-+-+-+-+
|.|.|.|.|
+-+-+-+-+
Case #2:
..+-+
..|.|
+-+-+
|.|.|
+-+-+
Case #3:
..+-+-+
..|.|.|
+-+-+-+
|.|.|.|
+-+-+-+
Sample Case #1 is the one described in the problem statement. Sample Cases #2 and #3 are additional examples. Notice that the output for each case contains exactly R⋅C+3 periods.**/
    public class PunchedCards {
      public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
          int n = in.nextInt();
          int m = in.nextInt();
          System.out.println("Case #" + i + ": " );
          drawPunchedCard(n, m);
        }
      }
      
      private static void drawPunchedCard(int r, int c) {
    	  char [][] a = new char[2*r+1][2*c+1];
    	  for(int i =0;i<r;i++) {
    		  for (int j =0;j<c;j++) {
    			  int x = 2*j+1;
    			  int y= 2*i+1;
    			  if(x==1 && y==1) {
    				  a[y][x-1]='.';
    				  a[y-1][x]= '.';
    				  a[y-1][x-1]= '.';
    			  }else {
    				  a[y][x-1]='|';
    				  a[y-1][x]= '-';
    				  a[y-1][x-1]= '+';
    			  }
    			  a[y][x]='.';
    			  a[y][x+1] = '|';
    			  a[y+1][x] = '-';
    			  a[y-1][x+1] = '+';
    			  a[y+1][x-1]='+';
    			  a[y+1][x+1]='+';
    		  }
    	  }
    	  //print
    	  for(int i =0;i<a.length;i++) {
    		  for (int j =0;j<a[0].length;j++) {
    			  System.out.print(a[i][j]);
    		  }
    		  System.out.println("");
    	  }
      }
    }
