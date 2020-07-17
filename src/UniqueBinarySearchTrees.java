import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Rohit on 01/07/20.
 */
public class UniqueBinarySearchTrees {

  public static void main(String[] args) {


    generateUniqueBinarySearchTree(4).forEach(tree -> printTree(tree));

  }


  private static void printTree(TreeNode root){

    Queue<TreeNode> queue = new LinkedList<>();

    queue.add(root);

    while (!queue.isEmpty()){
      TreeNode curr = queue.poll();
      if(curr.lChild != null) {
        queue.add(curr.lChild);
      }
      if(curr.rChild != null) {
        queue.add(curr.rChild);
      }
      System.out.print(curr.val + "-");
    }
    System.out.println();
  }

  static class TreeNode {
    int val;
    TreeNode lChild;
    TreeNode rChild;

    public TreeNode(int val) {
      this.val = val;
    }

  }


  public static List<TreeNode> generateUniqueBinarySearchTree(int num) {
    List<TreeNode> result = new ArrayList<>();
    for (int i = 1; i <= num; i++) {
      result.addAll(generateUniqueBinarySearchTreeByRootNode(i, 1, num));
    }
    return result;
  }


  public static List<TreeNode> generateUniqueBinarySearchTreeByRootNode(int root,int start, int end){
    List<TreeNode> currResult = new ArrayList<>();
    List<TreeNode> finalResult = new ArrayList<>();
    if(start == end){
      return new ArrayList(Arrays.asList(new TreeNode(root)));
    }

    for(int i = start ; i < root; i++){
      currResult.addAll(generateUniqueBinarySearchTreeByRootNode(i, start ,root-1));
    }

    for(int i = root+1; i<=end ;i++){
      currResult.addAll(generateUniqueBinarySearchTreeByRootNode(i, root +1 , end));
    }

    for(TreeNode treeNode :  currResult){
      TreeNode newRoot = new TreeNode(root);
      if(newRoot.val > treeNode.val)
      newRoot.lChild = treeNode;
      else newRoot.rChild = treeNode;
      finalResult.add(newRoot);
    }

    return finalResult;
  }



}
