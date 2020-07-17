import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Rohit on 13/07/20.
 */
public class TrieDataStructure {

  public static void main(String[] args) {
    ArrayList<String> testInput = new ArrayList<>(Arrays.asList("cat","dog", "cow", "tiger","elephant"));
    Trie trie = new Trie();
    for(String s : testInput) {
      buildTrie(trie, s);
    }

    System.out.println(searchTrie(trie, "tiger"));
    System.out.println(searchTrie(trie, "elephant"));
    System.out.println(searchTrie(trie, "ele"));
    System.out.println(searchTrie(trie, "doggy"));
    System.out.println(searchTrie(trie, "cow"));
    System.out.println(searchTrie(trie, "dog"));

  }
  int minSeq;
  public void BFS(String A, String B, Trie root){

    Set<String> checkedWord = new HashSet();
    Queue<String> queue = new LinkedList();

    queue.add(A);

    int currLevel = 1;
    int nextLevel = 0;
    int words = 0;
    checkedWord.add(A);
    while(!queue.isEmpty()){
      String currString = queue.poll();
      currLevel--;
      char[] curr = currString.toCharArray();
      if(currString  == B){ minSeq = words; break;}

      for(int i = 0; i < curr.length; i++){
        for(int alp = 0; alp < 26; alp++){
          if(curr[i]-'a' != alp){
            curr[i] = (char) (alp + 'a');
            String newWord = new String(curr);
            if(searchTrie(root, newWord) != "" && !checkedWord.contains(newWord)){
              checkedWord.add(newWord);
              queue.add(newWord);
              nextLevel++;
            }
          }
        }
      }

      if(currLevel == 0){
        currLevel = nextLevel;
        nextLevel = 0;
        words++;
      }
    }

  }


  static public void buildTrie(Trie root, String word){
    int pos = 0;

    while(pos < word.length()){
      if(!root.children.containsKey(word.charAt(pos) - 'a')){
        root.children.put(word.charAt(pos) - 'a', new Trie());
      }
      root = root.children.get(word.charAt(pos) - 'a');
      pos++;
    }
    root.word = word;
    root.isEnd = true;
  }


  static public String searchTrie(Trie root, String word){
    int pos  = 0;
    while (pos < word.length() && root.children.containsKey(word.charAt(pos) - 'a')){
      root = root.children.get(word.charAt(pos) - 'a');
      if(pos == word.length()-1 && root.isEnd) return word;
      if(pos == word.length()-1 && !root.isEnd) return "";
      pos++;
    }
    return "";
  }

  static public class Trie{
    String word;
    Map<Integer, Trie> children;
    boolean isEnd;

    public Trie(){
      children = new HashMap();
    }
  }

}
