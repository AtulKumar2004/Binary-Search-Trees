import java.util.*;

public class merge2BSTs {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }
    public static void getinorder(Node root,ArrayList<Integer> arr) {
        if(root == null) {
            return;
        }
        getinorder(root.left,arr);
        arr.add(root.data);
        getinorder(root.right, arr);
    }
    public static Node createBST(ArrayList<Integer> arr , int st ,int end) {
        if(st > end) {
            return null;
        }
        int mid = (st + end) / 2;
        Node root = new Node(arr.get(mid));
        root.left = createBST(arr, st, mid-1);
        root.right = createBST(arr, mid+1, end);
        return root;
    }
    public static void preOrder(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }
    public static Node mergeBSTs(Node root1,Node root2) {
        // create an ArrayList and insert all elements of a BST in inorder sequence
        ArrayList<Integer> arr1 = new ArrayList<>();
        getinorder(root1, arr1);

        ArrayList<Integer> arr2 = new ArrayList<>();
        getinorder(root2, arr2);

        /* once we have two arrayLists with inorder sequences of two BSTs then we can merge the two ArrayLists
        into a new ArrayList arranged in ascending order */
        ArrayList<Integer> finalArr = new ArrayList<>();
        int i = 0,j = 0;
        while(i < arr1.size() && j < arr2.size()) {
            if(arr1.get(i) <= arr2.get(j)) {
                finalArr.add(arr1.get(i));
                i++;
            }
            else {
                finalArr.add(arr2.get(j));
                j++;
            }
        }
        // inserting leftover elements
        while(i < arr1.size()) {
            finalArr.add(arr1.get(i));
            i++;
        }
        while (j < arr2.size()) {
            finalArr.add(arr2.get(j));
            j++;
        }
        // create BST from sorted array and return its Node
        return createBST(finalArr, 0, finalArr.size()-1);
        
    }
    public static void main(String[] args) {
        /*
            2
           / \
          1   4
           BST1
        */
        Node root1 = new Node(2);
        root1.left = new Node(1);
        root1.right = new Node(4);
        /*
             9
            / \
           3   12
            BST2
        */
        Node root2 = new Node(9);
        root2.left = new Node(3);
        root2.right = new Node(12);
        /*
             3
           /   \
          1     9
           \   / \
            2  4  12
          finalans:BST
        */
        Node root = mergeBSTs(root1, root2);
        preOrder(root);
    }
}
