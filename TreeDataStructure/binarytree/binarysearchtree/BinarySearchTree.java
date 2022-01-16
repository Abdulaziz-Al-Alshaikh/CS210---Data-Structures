/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;

/**
 *
 * @author Abdulaziz Al-Alshaikh
 */
import java.util.*;
public class BinarySearchTree {
    BinaryNode root;
    
    public BinarySearchTree() {
        this.root = null;
    }

    
    public void insert(int value) {
        root = insert(root, value);
    }
    private BinaryNode insert(BinaryNode root, int value) {
        if(root == null) {
            BinaryNode newNode = new BinaryNode();
            newNode.val = value;
            return newNode;
        }
        else if(value > root.val) {
            root.right = insert(root.right, value);
        }
        else{
            root.left = insert(root.left, value);
        }
        return root;
    }
    
    
    public void delete(int value) {
        root = deleteNode(root, value);
    }
    
    private BinaryNode deleteNode(BinaryNode root, int key) {
        if(root == null)
            return null;
        if(root.val > key) 
            root.left = deleteNode(root.left, key);
        
        else if(key > root.val) 
            root.right = deleteNode(root.right, key);
        
        else{
            if(root.left != null && root.right != null) {
                //case of a node with two children
                BinaryNode tempRoot = root;
                BinaryNode successor = getSuccessor(tempRoot.right);
                root.val = successor.val;
                root.right = deleteNode(root.right, successor.val);
            }
            //case of a node with one child
            else if(root.left != null) 
                root = root.left;
            
            else if(root.right != null) 
                root = root.right;
            
            else//case of a leaf node
                root = null;
            
        }
        return root;
    }
    
    public void preOrderTraversal(BinaryNode root) {
        if(root == null)
            return;
        System.out.print(root.val + " ");
        this.preOrderTraversal(root.left);
        this.preOrderTraversal(root.right);
    }
    
    public int minDepth(BinaryNode root) {
        if(root == null)
            return 0;
        Queue<BinaryNode> q = new LinkedList<BinaryNode>();
        int count = 0;
        q.add(root);
        while(!q.isEmpty()) {
            count++;
            BinaryNode curNode = q.poll();
            if(curNode.left == null && curNode.right == null) {
                return count;
            }
            if(curNode.left != null)
                q.add(curNode.left);
            if(curNode.right != null)
                q.add(curNode.right);
        }
        return count;
    }
    
    public void inOrderTraversal(BinaryNode root) {
        if(root == null)
            return;
        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }  
    
    public void postOrderTraversal(BinaryNode root) {
        if(root == null)
            return;
        this.postOrderTraversal(root.left);
        this.postOrderTraversal(root.right);
        System.out.print(root.val + " ");
    }
    
    public void levelOrderTraversal(BinaryNode root) {
        if(root == null)
            return;
        Queue<BinaryNode> q = new LinkedList<BinaryNode>();
        q.add(root);
        while(!q.isEmpty()) {
            BinaryNode curNode = q.remove();
            System.out.print(curNode.val + " ");
            if(curNode.left != null) q.add(curNode.left);
            if(curNode.right != null) q.add(curNode.right);
        }
    }
    
    public BinaryNode getSuccessor(BinaryNode node) {
        if(node == null) {
            return null;
        }
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    
    public int height(BinaryNode root) {
        if(root == null)
            return 0;
        int r = height(root.right);
        int left = height(root.left);
        return Math.max(r, left) + 1;
        
    }

    

   

    
    public boolean searchNode(int value, BinaryNode root) {
        if(root == null) {
            System.out.println("the value has not found!");
            return false;
        }
        if(root.val == value) {
            System.out.println("the value has found!");
            return true;
        }
        if(root.val > value) {
            return searchNode(value, root.left);
        }
        else{
            return searchNode(value, root.right);
        }
        
    }
    
    
    public void printTree() {
        if(root == null)
            throw new NoSuchElementException("BST is empty!");
        BTreePrinter.printNode(root);
        
    }
    
}
