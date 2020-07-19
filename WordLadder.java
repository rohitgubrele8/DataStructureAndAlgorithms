//import java.util.*;
//
//public class WordLadder {
//
//    public static void main(String[] args) {
//        System.out.println(solve("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log"))));
//    }
//
//    static public int solve(String A, String B, ArrayList<String> C) {
//        Trie root = new Trie();
//        for (String s : C) {
//            buildTrie(root, s);
//        }
//        buildTrie(root, A);
//        buildTrie(root, B);
//        int seqLength = 0;
//        char[] curr = A.toCharArray();
//        Set<String> words = new HashSet<>();
//        while (new String(curr)  =)
//        for (int i = 0; i < A.length(); i++) {
//            boolean breakVal = false;
//            for (int j = 0; j < 26; j++) {
//                if (j != curr[i] - 'a') {
//                    char prev = curr[i];
//                    curr[i] = (char) (j + 'a');
//                    String currString = new String(curr);
//                    curr[i] = prev;
//                    if (searchInTrie(currString, root) != "" && !words.contains(currString)) {
//                        //System.out.println(currString);
//                        seqLength++;
//                        words.add(currString);
//                        if (currString == B) {
//                            breakVal = true;
//                            break;
//                        }else {
//                            i = -1;
//                            break;
//                        }
//                    }
//                }
//            }
//            if (breakVal) break;
//        }
//
//        return seqLength;
//}
//
//
//static private class Trie {
//    String word;
//    Map<Integer, Trie> children;
//    boolean isEnd;
//
//    public Trie(String word) {
//        this.word = word;
//        this.children = new HashMap<>();
//    }
//
//    public Trie() {
//        this.children = new HashMap<>();
//    }
//
//}
//
//    static public void buildTrie(Trie trie, String word) {
//        char[] letters = word.toCharArray();
////        if(!trie.children.containsKey(letters[0])){
////            trie.children.put(letters[0]-'a',new Trie());
////        }
////        trie = trie.children.get(letters[0]-'a');
//        for (int i = 0; i < letters.length; i++) {
//            if (!trie.children.containsKey(letters[i] - 'a')) {
//                trie.children.put(letters[i] - 'a', new Trie());
//            }
//            if (i == letters.length - 1) {
//                trie.children.get(letters[i] - 'a').word = word;
//                trie.children.get(letters[i] - 'a').isEnd = true;
//            }
//            trie = trie.children.get(letters[i] - 'a');
//        }
//    }
//
//    static public String searchInTrie(String word, Trie trie) {
//        char[] letters = word.toCharArray();
//
//        if (!trie.children.containsKey(letters[0] - 'a')) return "";
//
//        for (int i = 0; i < letters.length && trie != null; i++) {
//            Trie next = trie.children.get(letters[i] - 'a');
//            if (next != null && i == letters.length - 1 && next.isEnd) {
//                return word;
//            }
//            trie = next;
//        }
//        return "";
//    }
//
//}
