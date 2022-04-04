import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**Problem
You are part of the executive committee of the Database Design Day festivities. You are in charge of promotions and want to print three D's to create a logo of the contest. You can choose any color you want to print them, but all three have to be printed in the same color.

Illustration of Sample #1.

You were given three printers and will use each one to print one of the D's. All printers use ink from 4 individual cartridges of different colors (cyan, magenta, yellow, and black) to form any color. For these printers, a color is uniquely defined by 4 non-negative integers c, m, y, and k, which indicate the number of ink units of cyan, magenta, yellow, and black ink (respectively) needed to make the color.

The total amount of ink needed to print a single D is exactly 106 units. For example, printing a D in pure yellow would use 106 units of yellow ink and 0 from all others. Printing a D in the Code Jam red uses 0 units of cyan ink, 500000 units of magenta ink, 450000 units of yellow ink, and 50000 units of black ink.

To print a color, a printer must have at least the required amount of ink for each of its 4 color cartridges. Given the number of units of ink each printer has in each cartridge, output any color, defined as 4 non-negative integers that add up to 106, such that all three printers have enough ink to print it.

Input
The first line of the input gives the number of test cases, T. T test cases follow. Each test case consists of 3 lines. The i-th line of a test case contains 4 integers Ci, Mi, Yi, and Ki, representing the number of ink units in the i-th printer's cartridge for the colors cyan, magenta, yellow, and black, respectively.

Output
For each test case, output one line containing Case #x: r, where x is the test case number (starting from 1) and r is IMPOSSIBLE if there is no color that can be printed by all 3 printers. Otherwise, r must be equal to "c m y k" where c, m, y, and k are non-negative integers that add up to 106 and c≤Ci, m≤Mi, y≤Yi, and k≤Ki, for all i.

If there are multiple solutions, you may output any one of them. (See "What if a test case has multiple correct solutions?" in the Competing section of the FAQ.) This information about multiple solutions will not be explicitly stated in the remainder of the 2022 contest.

Limits
Time limit: 5 seconds.
Memory limit: 1 GB.
Test Set 1 (Visible Verdict)
1≤T≤100.
0≤Ci≤106, for all i.
0≤Mi≤106, for all i.
0≤Yi≤106, for all i.
0≤Ki≤106, for all i.
Sample
Sample Input
save_alt
content_copy
3
300000 200000 300000 500000
300000 200000 500000 300000
300000 500000 300000 200000
1000000 1000000 0 0
0 1000000 1000000 1000000
999999 999999 999999 999999
768763 148041 178147 984173
699508 515362 534729 714381
949704 625054 946212 951187
Sample Output
save_alt
content_copy
Case #1: 300000 200000 300000 200000
Case #2: IMPOSSIBLE
Case #3: 400001 100002 100003 399994
Sample Case #1 is the image provided above. The proposed color is using up all of the ink in the cyan, magenta, and yellow cartridges of the first printer and all of the ink in the black cartridge of the last printer. This means that no additional unit of ink could be used from any of the 4 ink colors, so the given sample output is the only possible output for this case.

In Sample Case #2, magenta is the only color that both the first and second printers have, so our only chance would be to use 106 units of magenta. Unfortunately, the third printer does not have quite enough, making this case impossible.

In Sample Case #3, other correct outputs are: "400000 100000 100000 400000", "300000 0 0 700000", and "350000 140000 160000 350000", among lots of others. Notice that "300000 140000 160000 700000" would not be a valid answer because, even though there is enough ink in all printers to do that, the total number of ink units must be exactly 106.****/

public class ThreeDPrinting {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
        	int[][] c = new int[3][4];
        	for(int j=0;j<3;j++) {
        		for(int k=0;k<4;k++) {
        			c[j][k]= Integer.parseInt(in.next());
        		}
        	}
        	
          System.out.println("Case #" + i + ": " + checkCartridge(c));
        }
      }

	private static String checkCartridge(int[][] c) {
		int[] minCartridge = new int[4];
		for(int i=0;i<4;i++) {
			minCartridge[i] = c[0][i];
		}
		for(int i=0;i<c.length;i++) {
			for(int j=0;j<4;j++) {
				minCartridge[j]= Math.min(minCartridge[j], c[i][j]);
			}
		}
		
		//sum of all mins
		int sum =0;
		for(int k=0;k<minCartridge.length;k++) {
			sum+= minCartridge[k];
		}
		int MAX= (int) Math.pow(10.0, 6.0);
		int remainder = sum -MAX;
		if(remainder > 0) {
			int p =0;
			while((remainder -minCartridge[p])>0) {
				remainder-=minCartridge[p];
				minCartridge[p]=0;
				p++;
			}
			minCartridge[p] -= remainder;
			
			StringBuilder sb = new StringBuilder();
			for(int color : minCartridge) {
				sb.append(color+" ");
			}
			return sb.toString();
		}else if(remainder ==0) {
			StringBuilder sb = new StringBuilder();
			for(int color : minCartridge) {
				sb.append(color+" ");
			}
			return sb.toString();
		}
		else {
			return "IMPOSSIBLE";
		}
	}
}
