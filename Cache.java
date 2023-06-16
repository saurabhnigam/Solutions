import java.util.*;


/*
Build a cache data structure which implements the following eviction policy:

1) evict an expired item if it exists
2) otherwise find the items with the lowest priority and evict the Least Recently Used of these items



C = new Cache(5) // capacity is 5
3.R///C.Set(key="A", value=5, priority=5, expiration=10001)
2.R///C.Set(key="B", value=4, priority=1, expiration=40006)
C.Set(key="C", value=3, priority=5, expiration=10001)
1.R///C.Set(key="D", value=2, priority=9, expiration= 500)
4.R///C.Set(key="E", value=1, priority=5, expiration=10004)
C.Get("C") 

// Current Time = 900 system.get_current_time() POSIX/Unix epoch

I///C.Set(key="F", value=10, priority=5, expiration= 5001)
I///C.Set(key="G", value=9, priority=5, expiration= 5004)
I///C.Set(key="H", value=-1, priority=5, expiration= 5009)
I///C.Set(key="I", value=1, priority=5, expiration= 5011)
U///C.Set(key="C", value=1, priority=5, expiration= 5021)

C.Get("D") // return -1

Cache
C5-I5-H5-G5-F5

DoublyLinkedList head-Node2-Node1-Node3-tail 
Map<Priority, List<Objs1>> priorityMap 
Map<Key, Node> keyMap //for get
TreeMap<Expiry, Node> expirymap O(logn)

100 ->N1
200 ->N2
300 ->N3

map.floor().  //
less than the curr Value
class Node{
  Node left, right
}

//set
//check for expiry

*/

class Cache{
  int capacity = 10;//default
  int len = 0;
  Node head = new Node();
  Node tail = head;
  TreeMap<Long, Node> expirymap = new TreeMap(); // O(logn)
  Map<Integer, List<Node>> priorityMap = new HashMap(); 
  Map<String, Node> keyMap  = new HashMap();//for get

  
  public Cache(int capacity){
    this.capacity = capacity;
  }

  public void set(String key, int value, int priority, long expiration){
    //check for capcity
    if(len == capacity){
    //check for expiry
      removeExpiredItems();
      if(len == capacity){
          //pick for lowest priority

          //nodes with lowest priority
            //pick node lru from tail of LL
      }
       
    }
    if(keyMap.containsKey(key)){
      remove(keyMap.get(key));
    }
    add(new Node(key, value, priority, expiration));
    
  }

  private void add(Node node){
    //add node to head of LL. O(1)
    //add it to keyMap 
    //add it to expiRymap. O(logn )
    //add it to priorityMap
  }

///Remove only the prev expired items
  private void removeExpiredItems(){
    Map<Long, Node>lowerKeymap = expirymap.headMap(System.currentTimeMillis()); // all the kleys strictkly less than 

    //all the items which have expired now
    for(Map.Entry<Long, Node> e : lowerKeymap.entrySet()){
      remove( e.getValue());
      break;
    }
  }

//remove a node
  private void remove(Node node){
    len--;
      //remove item from expiRymap
      expirymap.remove(node.expiry);  O(logn)
      //remove item from priorityMap
      for(Node currNode : priorityMap.get(node.priority)){ O(1)
        if(node == currNode ){
            priorityMap.get(node.priority).remove(node);
            break;
        }
      }
        //remove item from keYMap
        keyMap.remove(node.key); 

        //remove it from LL
        Node nxtTmp = node.next;
        Node prevTmp = node.prev ;
        prevTmp.next = nxtTmp;
        nxtTmp.prev = prevTmp;

  }

  class Node{
    long expiry;
    int priority;
    String key;
    Node next,prev;

    Node(){

    }
  }
}

