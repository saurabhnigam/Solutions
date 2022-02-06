/*****Next Closest Time
Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

 

Example 1:

Input: time = "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.
It is not 19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: time = "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
 

Constraints:

time.length == 5
time is a valid time in the form "HH:MM".
0 <= HH < 24
0 <= MM < 60
****/

class NextClosestTime {
    public String nextClosestTime(String s) {
        TreeSet<Integer> ts = new TreeSet();
        int[] t = new int[4];
        for(int i = 0,y=0; i<s.length();i++){
            char c = s.charAt(i);
            if(c != ':'){
                int x = Integer.parseInt(""+c);
                ts.add(x);
                t[y++] = x;
            }
        }
        
        for(int i=3;i>=0;i--){
            Integer h = ts.higher(t[i]);
            if(h == null || isCurrValid(i, t[i],t) || (h!=null && isNewValid(i,h,t))){
                int l = ts.first();
                t[i] =l;
            }else if(h!=null){
                t[i]=h;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<4;i++){
            sb.append(t[i]);
            if(i==1){
                sb.append(":");
            }
        }
        return sb.toString();
    }
    private boolean isCurrValid(int i, int v, int[] a){
        return ((i==3 && v==9) || (i==2 && v==5) || (i==1 && v==3 && a[0]==2) ||(i==0 && v==2));
    }
    
    private boolean isNewValid(int i, int v, int[] a){
        return ( (i==2 && v>5) || (i==1 && v>3 && a[0]==2) ||(i==0 && v>2));
    }
}
