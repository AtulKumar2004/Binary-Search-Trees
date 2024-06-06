import java.util.*;

public class BalancetheBST {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }
    public static void preOrder(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }
    public static void inorderSeq(Node root,ArrayList<Integer> inorder) {
        if(root == null) {
            return;
        }
        inorderSeq(root.left, inorder);
        inorder.add(root.data);
        inorderSeq(root.right, inorder);
    }
    public static Node balance(ArrayList<Integer> inorder,int st,int end) {
        if(st > end) {
            return null;
        }
        int mid = (st + end) / 2;
        Node root = new Node(inorder.get(mid));
        root.left = balance(inorder, st, mid-1);
        root.right = balance(inorder, mid+1, end);
        return root;
    }

    public static Node balanceBST(Node root) { // TC = O(n)
        ArrayList<Integer> inorder = new ArrayList<>();
        // generate the inorder sequence - TC = O(n)
        inorderSeq(root,inorder);

        // Convert the inorder sequence to a balanced BST - TC = O(n)
        root = balance(inorder, 0, inorder.size()-1);
        return root;
    }
    public static void main(String[] args) {
        /*
              8
             / \
            6   10
           /     \
          5       11
         /         \
        3           12
        given BST
        */
        Node root = new Node(8);
        root.left = new Node(6);
        root.left.left = new Node(5);
        root.left.left.left = new Node(3);
        root.right = new Node(10);
        root.right.right = new Node(11);
        root.right.right.right = new Node(12);

        /*
                8
               / \
              5   11
             / \  / \ 
            3   6 10 12
            expected BST
        */
        root = balanceBST(root);
        preOrder(root);
    }
}
