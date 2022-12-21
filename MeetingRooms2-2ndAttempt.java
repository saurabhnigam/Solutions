/*****
253. Meeting Rooms II

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
*****/

class MeetingRooms2-2ndAttempt {
    //TC O(n)
    //SC O(n)
    public int minMeetingRooms(int[][] intervals) {
        Map<Integer, List<Character>> intervalMap = new TreeMap();
        //create an interval map with open close positions
        for(int[] interval : intervals){ //O(n)
            appendInterval(intervalMap, interval[0], 'o');
            appendInterval(intervalMap, interval[1], 'c');
        }

        int currOpen = 0;
        int maxOverlapping = 0;
        for(Map.Entry<Integer, List<Character>> positionEntry : intervalMap.entrySet()){ // O(2n) as getStateFor will run for at max n once only
            int opens = getStateFor(positionEntry.getValue(), 'o'); 
            currOpen+= opens;
            int closes = getStateFor(positionEntry.getValue(), 'c');
            currOpen-= closes;
            maxOverlapping = Math.max(maxOverlapping, currOpen);
            
        }
        return maxOverlapping;
    }

    private int getStateFor(List<Character> states, char state){
        int stateCount = 0;
        for(char stateChar : states){ //O(n)
            if(stateChar == state){
                stateCount++;
            }
        }
        return stateCount;
    }

    private void appendInterval(Map<Integer, List<Character>> intervalMap, int intervalPos, char state){
        List<Character> states = intervalMap.getOrDefault(intervalPos, new ArrayList<Character>());
        states.add(state);
        intervalMap.put(intervalPos, states);
    }
}
