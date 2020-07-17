import java.util.*;
import java.util.stream.Collectors;

class BoogleBoard {
  public static void main(String[] args) {

    char[][] board =
        {{'a', 'b', 'c', 'd', 'e'}, {'f', 'g', 'h', 'i', 'j'}, {'k', 'l', 'm', 'n', 'o'}};

    String[] words = {"abc", "afk", "de", "cio", "deji"};
//    Trie root = new Trie();
//    for (String word : words) {
//      buildTrie(word, root);
//    }

    System.out.println(boggleBoard(board, words));

  }

  public static List<String> boggleBoard(char[][] board, String[] words) {
    List<String> result = new ArrayList<>();

    Trie root = new Trie();
    for(String word : words) {
      buildTrie(word, root);
    }
    boolean[][] visited;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++){
        Trie curr = root;
        visited  = new boolean[board.length][board[0].length];
        dfs(board, curr ,i ,j, visited, result);
      }
    }

    return result.stream().distinct().collect(Collectors.toList());
  }


  static int[] dx = {1,0,-1,1,-1,-1,0,1};
  static int[] dy = {0,1,0,1,1,-1,-1,-1};

  private static void dfs(char[][] board, Trie root, int i, int j, boolean[][] visited, List<String> foundWords){

    char c = board[i][j];
    if(root == null || !root.children.containsKey(c-'a')){
      return;
    }

    if(root.children.get(c-'a').endOfWorld){
      foundWords.add(root.children.get(c-'a').word);
    }

    visited[i][j] = true;

    for(int dir = 0; dir <dx.length; dir++){
      int x  = dx[dir] + i;
      int y  = dy[dir] + j;
      if(isValidCoordinate(x, y, board) && !visited[x][y])
      dfs(board, root.children.get(c-'a'), x, y, visited, foundWords);
    }

  }

  private static boolean isValidCoordinate(int i, int j,  char[][] board){
    return (i>=0 && j>= 0 && i<board.length && j<board[0].length);
  }




  private static boolean search(String word, Trie root) {
    int position = 0;
    Trie current = root;
    while ( position < word.length() && current != null && current.children.containsKey(word.charAt(position)-'a')) {
      current = current.children.get(word.charAt(position)-'a');
      position++;
    }
    if (current.endOfWorld == true && position == word.length())
      return true;
    return false;
  }


  private static void buildTrie(String word, Trie root){
    Trie current = root;
    for(char c : word.toCharArray()){
      if(current.children.containsKey(c - 'a')){
        current = current.children.get(c-'a');
      }else {
        current.children.put(c-'a', new Trie());
        current = current.children.get(c-'a');
      }
    }
    current.endOfWorld = true;
    current.word = word;
  }

  static class Trie {
    String word;
    Map<Integer, Trie> children;
    boolean endOfWorld;

    public Trie() {
      children = new HashMap<>();
      endOfWorld = false;
    }
  }
}




