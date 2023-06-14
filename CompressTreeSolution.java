/**Input : all existing
/a/k.txt
/b/w.txt
/b/y.txt
/c/y.txt
/c/z.txt
/c/f/k.txt
/c/f/w.txt
/d/a/k.txt

Selected:
/b/w.txt
/b/y.txt
/c/y.txt
/c/f/k.txt
/c/f/w.txt
/d/a/k.txt

Input will be all files
Selected will be selection
Output : compressed format
/b
/c/y.txt
/c/f
/d

create tree from alls the input
    b
w.txt y.txt

traverse over selection
/b/w.txt
              c
            b
w.txt-visited
    
traversal
Selection
/r/p/b/w.txt     
/r/p/b/y.txt  
            r- visitedChildren =2 unvisited
          p visitedChildren =2 unvisited
        b-  visitedChildren =2 unvisited
w.txt-unvisited = 1   y.txt-unvisited = 1

  Input list of files
/a/k.txt
/b/w.txt
/b/y.txt
/c/y.txt
  
         root
        /  |   \
 /-----/   |    \-----------|-----------\
a f -1          b           c0            d 
|         / \             / | \          |
|        /   \           /  |  \         |
k.txt  w.txt y.txt  y.txt1 z.txt1 f 2       a
                               / \       |
                              /   \      |
                           k.txt w.txt 1 k.txt 1
                           
                           ****/
class CompressTreeSolution{

  public List<String> compress(List<String> allFiles, List<String> selection){
    //allFiles > selection
    //allFiles cannot be null
    //selection duplicate check
    //very long string paths
    
    //createTrie(allFiles)
    Trie root = createTrie(allFiles);
    //iterate over selection
    for(String file: selection){
        //decrement the occurenec
      traverseSelection(root, file) ;
    }
    //find out all 0s parent
    List<String> output = getZeroParent(root);
    
  }
  
  private void  traverseSelection(Trie root, String file){
     char sep = '/';
    //split file based on /
    String[] names = file.split(sep);
    if(names.length > 2){
      String name =nextFolder(names, 0)
      Trie curr = root;
      int i =0;
       while(name != fileName(names)){ // last node
         curr = curr.get(name);  // root.get (c)
         curr.freq--;
         name = nextFolder(names, i); // f
         
       }
      //
      curr.freq--;
    }
    //. /c/f/k.txt

  }
  
  
  
  
class Trie{
  String name;
 Map<String, Trie> children  = new HashMap();
 int freq = 0;
}

}

