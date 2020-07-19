import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rohit on 02/07/20.
 */
public class UniqueBinaryTrees {


  public static void main(String[] args) {
    System.out.println(generateTrees(4).size());
  }

  static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  public static ArrayList<TreeNode> generateTrees(int a) {
    ArrayList<TreeNode> result  = new ArrayList();

    for(int i  = 1; i <=a ; i++){
      result.addAll(trees(1,i,a));
    }

    return result;

  }


  static ArrayList<TreeNode> trees(int start ,int curr , int end){

    if(start > end) return new ArrayList();
    if(start == end) return new ArrayList(Arrays.asList(new TreeNode(start)));

    List<TreeNode> ltrees = new ArrayList<>();
    for(int i = start; i<curr ; i++){
      ltrees.addAll(trees(start, i, curr-1));
    }

    List<TreeNode> rtrees = new ArrayList<>();
    for(int i = curr+1; i<=end ; i++){
      rtrees.addAll(trees(curr+1, i, end));
    }

    ArrayList<TreeNode> result = new ArrayList();

    if(rtrees.isEmpty()){
      for(TreeNode ltree : ltrees){
        TreeNode root  = new TreeNode(curr);
        root.left = ltree;
        result.add(root);
      }
    }else if(ltrees.isEmpty()){
      for(TreeNode rtree : rtrees){
        TreeNode root  = new TreeNode(curr);
        root.right = rtree;
        result.add(root);
      }
    } else {
      for(TreeNode rtree : rtrees){
        for(TreeNode ltree : ltrees){
          TreeNode root  = new TreeNode(curr);
          root.right = rtree;
          root.left = ltree;
          result.add(root);
        }
      }

    }

    return result;
  }


}
