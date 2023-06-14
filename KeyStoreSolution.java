/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class KeyStoreSolution {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java " + Runtime.version().feature());

    String[] commands = new String[]{
 "PUT a 10",
"EXPIRE a 1",
"TTL a",
"GET a",
"SLEEP 2", 
"GET a"
  };
    processCommand(commands);

   
  }

private static Map<String , StoreVal> store = new HashMap();
private static final String PUT = "PUT";
private static final String GET = "GET";
private static final String DEL = "DEL";
private static final String EXPIRE = "EXPIRE";
private static final String TTL = "TTL";
private static final String SLEEP = "SLEEP";



private static final String DELIM = " ";


/****
process the commands passed
 */
  private static void processCommand(String[] commands){
      //PUT k v   
      //GET k
      //DEL a
      //EXPIRE k s
      //TTL k
      //SLEEP s
      for(String command : commands){
        //PUT
        if(command.startsWith(PUT)){
          handlePut(command);
        }else if (command.startsWith(GET)){
          handleGet(command);
        }else if (command.startsWith(DEL)){
          handleDel(command);
        }else if (command.startsWith(EXPIRE)){
          handleExpire(command);
        }else if (command.startsWith(TTL)){
          handleTTL(command);
        }else if (command.startsWith(SLEEP)){
          handleSleep(command);
        }
      }

  }

  private static void handlePut(String command){
    //TODO valdiation null & empty
    String[] args = command.split(DELIM);
    String key = args[1].trim();
    int val =  Integer.parseInt(args[2].trim());
    store.put(key, new StoreVal(val));
  }

  private static void handleGet(String command){
    //TODO validation null & empty
    String[] args = command.split(DELIM);
    String key = args[1].trim();
    StoreVal val = store.get(key);
    if(val == null || isExpired(val)){
      store.remove(key); // remove key as it has expired
      System.out.println("NOT FOUND");
    }else{
      System.out.println(val.val);
    }
  }
  
   private static void handleDel(String command){
    //TODO validation null & empty
    String[] args = command.split(DELIM);
    String key = args[1].trim();
    store.remove(key);
  }

  private static void handleExpire(String command){
    //TODO validation null & empty
    String[] args = command.split(DELIM);
    String key = args[1].trim();
    long delay = Long.parseLong(args[2].trim());

    StoreVal storeVal = store.get(key);
    if(storeVal == null || isExpired(storeVal)){
      store.remove(key); // remove key as it has expired
      System.out.println("NOT FOUND");
    }else{
      store.put(key, new StoreVal(storeVal.val, delay));
    }
  }

  private static void handleTTL(String command){
    //TODO validation null & empty
    String[] args = command.split(DELIM);
    String key = args[1].trim();
    StoreVal val = store.get(key);
    if(val == null || isExpired(val)){
      store.remove(key); // remove key as it has expired
      System.out.println("NOT FOUND");
    }else{
      long sec = (val.ttl - System.currentTimeMillis() )/100;
      System.out.println(sec+"");
    }
  }
  
  private static void handleSleep(String command){
    //TODO validation null & empty
    String[] args = command.split(DELIM);
    long sleepSec = Long.parseLong(args[1].trim());
    try {
          Thread.sleep(sleepSec*100);

    } catch (Exception e) {
      // TODO: handle exception
    }
    
  }

 static class StoreVal{
    Integer val;
    long ttl = -1; // no timeout set
    public StoreVal(Integer val , long sec){
      ttl = System.currentTimeMillis()+ (sec*100);
      this.val = val;
    }

    public StoreVal(Integer val){
       this.val = val;
    }
  }

  private static  boolean isExpired(StoreVal storeVal){
    if(storeVal.ttl != -1 && storeVal.ttl <= System.currentTimeMillis()){
      return true;
    }else{
      return false;
    }
  }

}


// Your previous Plain Text content is preserved below:

// #Key value store
// We are going to make a key-value store! In this store, keys are always strings and values are always integers. Feel free to use any builtin data structure(s) (List, Array, Map,Set, etc.) to implement your store. Your solution will need to be capable of parsing and executing these commands:

// STAGE ONE

// PUT k v
// This command should set the key (k) to hold a value (v). If the key already holds a value then it should be overwritten.

// GET k
// Get the value of the key and print it out. If the key does not exist then the command should print out "NOT FOUND".

// DEL k
// Removes the specified key from the store. If the key does not exist in the store then the command is ignored.

// Test case 1:
// commands = [
//  'PUT a 10',
//  'GET a',
//  'DEL a',
//  'GET a',
// ]
 
// OUTPUT
// 10
// NOT FOUND

// STAGE TWO

// EXPIRE k s
// This command sets a key’s time to live (TTL) in seconds. The command operates on this set of rules:
// If the key does not exist in the database then the command should print "NOT FOUND".
// If a GET command is performed on an expired key, then "NOT FOUND" should be printed.
// If a PUT command is performed on a key with a timeout, then the timeout should be cleared.
// If an EXPIRE command is performed on an expired key, then "NOT FOUND" should be printed.
// If an EXPIRE command is performed on a non-expired key, then it should update the TTL.

// TTL k
// This command prints out the remaining time to live (in seconds) of a key that has a timeout. The rules for this command are:
// If the key does not exist in the database then the command should print "NOT FOUND".
// If the key exists in the database but it does not have a timeout then the command should print "NONE".
// If the key exists in the database and it has expired then the command should print "NOT FOUND".
// The printed out value should be an integer.

// SLEEP s
// Delay the evaluation of the next command for the given number of seconds

// Test case 1:
// commands = [
//  'PUT a 10',
//  'EXPIRE a 1',
//  'TTL a',
//  'GET a',
//  'SLEEP 2', 
//  'GET a',
// ]
 
// OUTPUT
//   1
//   10
//   NOT FOUND
