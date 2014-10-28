package bintree;

public class BinaryTreeDemo {
  // Root node pointer. Will be null for an empty tree.
  private Node root, closestMatch;
  private int diff= 0, closestDiff= 0;

  /* --Node--
   The binary tree is built using this nested node class. Each node stores one data element, and has left and right
   sub-tree pointer which may be null. The node is a "dumb" nested class -- we just use it for storage; it does not have any 
  methods. */
  private static class Node {
    Node left;
    Node right;
    int data;

    Node(int newData) {
      left = null;
      right = null;
      data = newData;
    }
  }

  /** Creates an empty binary tree -- a null root pointer. */
  public void BinaryTree() {
    root = null;
  }
 
  /** Returns true if the given target is in the binary tree. Uses a recursive helper. */
  public boolean lookup(int data) {
    resetClosestDiff(2^32);
    return(lookup(root, data));
  }
 
  /** Recursive lookup  -- given a node, recur down searching for the given data. */
  private boolean lookup(Node node, int data) {
    if (node==null) {
      return(false);
    }
    
    if(data > node.data) { diff= data - node.data; }
    else { diff= node.data - data; }
    
    if(diff <= closestDiff) {
       closestDiff= diff;
       closestMatch= node;
      }
   System.out.println("diff= "+ diff +" , ClosestDiff= "+ closestDiff);

    if (data==node.data) {
      return(true);
    }
    else if (data<node.data) {
      return(lookup(node.left, data));
    }
    else {
      return(lookup(node.right, data));
    }
  }
 
  private void resetClosestDiff(int val) {
   closestDiff= val;
  }

  /** Inserts the given data into the binary tree. Uses a recursive helper. */
  public void insert(int data) {
   root= insert(root, data);
  }
 

  /** Recursive insert -- given a node pointer, recur down and insert the given data into the tree. Returns the new node pointer 
   (the standard way to communicate a changed pointer back to the caller). */
  private Node insert(Node node, int data) {
    if (node==null) {
      node = new Node(data);
    }
    else {
      if (data <= node.data) {
        node.left = insert(node.left, data);
      }
      else {
        node.right = insert(node.right, data);
      }
    }

    return(node); // in any case, return the new pointer to the caller
  } 
  
 public void printTree() {
  printTree(root);
  System.out.println();
 }

 private void printTree(Node node) {
  if (node == null) return;

  // left, node itself, right
  printTree(node.left);
  System.out.print(node.data + "  ");
  printTree(node.right);
 }
  
  public static void main(String[] args) {
   BinaryTreeDemo btd= new BinaryTreeDemo();
   btd.BinaryTree();
   Node n1= new BinaryTreeDemo.Node(5);
   Node n2= new BinaryTreeDemo.Node(8);
   Node n3= new BinaryTreeDemo.Node(3);
   Node n4= new BinaryTreeDemo.Node(10);
   Node n5= new BinaryTreeDemo.Node(19);
   btd.insert(n1.data);
   btd.insert(n2.data);
   btd.insert(n3.data);
   btd.insert(n4.data);
   btd.insert(n5.data);
   
   System.out.println("Binary Tree elements: ");
   btd.printTree();
   System.out.println("19 found ?= "+ btd.lookup(19) +" , Closest match= "+ btd.closestMatch.data);
   System.out.println("12 found ?= "+ btd.lookup(12) +" , Closest match= "+ btd.closestMatch.data);
  }
}
  