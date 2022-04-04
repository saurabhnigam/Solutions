import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/***Problem
While the most typical type of dice have 6 sides, each of which shows a different integer 1 through 6, there are many games that use other types. In particular, a dk is a die with k sides, each of which shows a different integer 1 through k. A d6 is a typical die, a d4 has four sides, and a d1000000 has one million sides.

Dice from sample case 1

In this problem, we start with a collection of N dice. The i-th die is a dSi, that is, it has Si sides showing integers 1 through Si. A straight of length ℓ starting at x is the list of integers x,x+1,…,x+(ℓ−1). We want to choose some of the dice (possibly all) and pick one number from each to form a straight. What is the longest straight we can form in this way?

Input
The first line of the input gives the number of test cases, T. T test cases follow. Each test case is described in two lines. The first line of a test case contains a single integer N, the number of dice in the game. The second line contains N integers S1,S2,…,SN, each representing the number of sides of a different die.

Output
For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is the maximum number of input dice that can be put in a straight.

Limits
Memory limit: 1 GB.
1≤T≤100.
Test Set 1 (Visible Verdict)
Time limit: 5 seconds.
1≤N≤10.
4≤Si≤20, for all i.
Test Set 2 (Visible Verdict)
Time limit: 15 seconds.
1≤N≤105.
4≤Si≤106, for all i.
Sample
Sample Input
save_alt
content_copy
4
4
6 10 12 8
6
5 4 5 4 4 4
10
10 10 7 6 7 4 4 5 7 4
1
10
Sample Output
save_alt
content_copy
Case #1: 4
Case #2: 5
Case #3: 9
Case #4: 1
In Sample Case #1, there are multiple ways to form a straight using all 4 dice. One possible way is shown in the image above.

In Sample Case #2, since none of the dice can show an integer greater than 5, there is no way to have a straight with more than 5 dice. There are multiple ways to form a straight with exactly 5 dice. For example, pick the integers 4 and 5 for both d5⁠'s and then integers 1,2, and 3 for three of the d4⁠'s to form 1,2,3,4,5.

In Sample Case #3, it is possible to form the straight 1,2,3,4,5,6,7,8,9 by discarding one d4 and using the d4⁠'s, d5, and d6 to get 1 through 4; the d7⁠'s to get 5 through 7; and the d10⁠'s to get 8 and 9. There is no way to form a straight of length 10, so this is the best that can be done.

In Sample Case #4, we can only form a straight of length 1, but we can do so by picking any integer for the d10 we are given.**/
public class DP100000Dices {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {

	        int d =in.nextInt();
	        int[] dice = new int[d];
	        for(int j=0;j<d;j++) {
	        	dice[j]= Integer.parseInt(in.next());
	        }
	        System.out.println("Case #" + i + ": " + maxStraight(dice));
	       }
        }

	private static int maxStraight(int[] dice) {
		int counter =0;
		Arrays.sort(dice);
		for(int i=0;i<dice.length;i++) {
			if(counter<dice[i]) {
				counter++;
			}
		}
		return counter;
	}
      

}
