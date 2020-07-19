import java.util.*;
import java.util.stream.Collectors;

public class BoggleBoard2 {

    static int[] dx = {0,1,-1,0,1,1,-1,-1};
    static int[] dy = {1,0,0,-1,1,-1,1,-1};

    public static void main(String[] args) {
        String[] words = {"this",
                "is",
                "not",
                "a",
                "simple",
                "boggle",
                "board",
                "test",
                "REPEATED",
                "NOTRE-PEATED"};
        char[][] board = {
                {'t', 'h', 'i', 's', 'i', 's', 'a'},
                {'s', 'i', 'm', 'p', 'l', 'e', 'x'},
                {'b', 'x', 'x', 'x', 'x', 'e', 'b'},
                {'x', 'o', 'g', 'g', 'l', 'x', 'o'},
                {'x', 'x', 'x', 'D', 'T', 'r', 'a'},
                {'R', 'E', 'P', 'E', 'A', 'd', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'N', 'O', 'T', 'R', 'E', '-', 'P'},
                {'x', 'x', 'D', 'E', 'T', 'A', 'E'}
        };
        System.out.println(boggleBoard(board, words));
    }

    static private class Trie {
        String word;
        Map<Integer, Trie> children;
        boolean isEnd;

        public Trie(String word){
            this.word = word;
            this.children = new HashMap<>();
        }

        public Trie(){
            this.children = new HashMap<>();
        }
    }
    static Trie root;
    static List<String> result;
    public static List<String> boggleBoard(char[][] board, String[] words) {
        root = new Trie();
        for(String word : words){
            buildTrie(root, word);
        }
        int l = board.length;
        int b = board[0].length;
        result = new ArrayList<>();
        for(int i = 0; i < l;i++){
            for (int j = 0; j < b; j++){
                boolean[][] visited = new boolean[l][b];
                DFS(board, visited, new StringBuilder(), i, j, root);
            }
        }
        return result.stream().distinct().collect(Collectors.toList());
    }

    static void DFS(char[][] board,boolean[][] visited, StringBuilder sb, int i, int j, Trie root){
        visited[i][j] = true;
        sb.append(board[i][j]);
        String curr = sb.toString();
        if(root != null && !root.children.containsKey(sb.charAt(sb.length()-1)-'a')){
            return;
        }
        if(root.children.get(sb.charAt(sb.length()-1)-'a').isEnd){
                result.add(curr);
        }

        for (int itr = 0; itr < dx.length; itr++) {
            int ni = dx[itr] + i;
            int nj = dy[itr] + j;
            if(isValidCor(ni,nj, board.length, board[0].length) && !visited[ni][nj]) {
                DFS(board, visited, new StringBuilder(sb), ni, nj,root.children.get(sb.charAt(sb.length()-1)-'a'));
                visited[ni][nj] = false;
            }
        }

    }

    static boolean isValidCor(int x, int y, int l, int b){
        return x >= 0 && y >= 0 && x < l && y < b;
    }



    static public void buildTrie(Trie trie, String word){
        char[] letters = word.toCharArray();
//        if(!trie.children.containsKey(letters[0])){
//            trie.children.put(letters[0]-'a',new Trie());
//        }
//        trie = trie.children.get(letters[0]-'a');
        for(int i = 0; i < letters.length; i++){
            if(!trie.children.containsKey(letters[i]-'a')){
                trie.children.put(letters[i]-'a',new Trie());
            }
            if(i == letters.length-1) {
                trie.children.get(letters[i] - 'a').word = word;
                trie.children.get(letters[i]-'a').isEnd = true;
            }
            trie = trie.children.get(letters[i]-'a');
        }
    }

    static public String searchInTrie(String word, Trie trie){
        char[] letters  = word.toCharArray();

        if(!trie.children.containsKey(letters[0]-'a')) return "";

        for(int i = 0; i< letters.length && trie != null ; i++){
            Trie next = trie.children.get(letters[i]-'a');
            if(next!=null && i == letters.length-1 && next.isEnd){
                return word;
            }
            trie = next;
        }
        return "";
    }
}
