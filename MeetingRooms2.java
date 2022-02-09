/****
Meeting Rooms II
Medium

Share
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

 

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1
 

Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106
***/


class MeetingRooms2 {
    public int minMeetingRooms(int[][] intervals) {
        List<Meeting> list = new ArrayList();
        for(int i =0;i<intervals.length;i++){
            int[] t = intervals[i];
            list.add(new Meeting(t[0], t[1]));
        }
        
        Comparator<Meeting> comparator = new Comparator<Meeting>(){
            public int compare(Meeting m1, Meeting m2){
                if(m1.s-m2.s == 0){
                    return m2.e-m1.e;
                }
                return m1.s-m2.s;
            }
        };
        
        list.sort(comparator);
        PriorityQueue<Integer> t = new PriorityQueue();
        Meeting m0 = list.get(0);
        int maxEnd = m0.e;
        int nextStart = m0.e;
        int overlap =1;
        t.add(m0.e);
        for(int i =1; i<list.size();i++){
            Meeting mi = list.get(i);
            int k = t.peek();
            if(mi.s < k){
                t.add(mi.e);
                overlap++;
            }else{
                t.poll();
                t.add(mi.e);
            }

        }
        return overlap;
    }
    
    class Meeting{
        int s, e;
        public Meeting(int s, int e){
            this.s = s;
            this.e =e;
        }
        
        public String toString(){
            return s+","+e;
        }
    }
}
