/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;
import java.util.*;

/**
 *
 * @author Abdulaziz Al-Alshaikh
 */
public class BinaryTree {
    BinaryNode root;
    
    
    public void insert(int val) {
        insert(new BinaryNode(val));
    }
    
    private void insert(BinaryNode node) {
        //this function inserts the passed node into the first empty space using level-order traversal.
        if(this.root == null) {
            //binary tree is empty in this case.
            root = node;
        }
        else{
            //level-order traversal. O(n) time & space.
            Queue<BinaryNode> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()) {
                BinaryNode curNode = queue.poll();
                if(curNode.left == null || curNode.right == null) {
                    if(curNode.left == null){
                        curNode.left = node;
                    }
                    else{
                        curNode.right = node;
                    }
                    return;
                }
                
                
                if(curNode.left != null)
                    queue.add(curNode.left);
                if(curNode.right != null)
                    queue.add(curNode.right);
            }
        }
    }
    
    
    public BinaryNode getLastLeafNode(BinaryNode root) {
        if(root == null) throw new NoSuchElementException("The tree is empty!");
        //This function returns the last inserted node into this tree. 
        //We can get it by a level-order traversal over the tree.
        //Linear time and space.
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode lastLeafNode = null;
        while(!queue.isEmpty()) {
            lastLeafNode = queue.poll();
            if(lastLeafNode.left != null) queue.add(lastLeafNode.left);
            if(lastLeafNode.right != null) queue.add(lastLeafNode.right);
        }
        return lastLeafNode;
        
    }
    public void deleteLastLeafNode() {
        deleteLastLeafNode(root);
    }
    private void deleteLastLeafNode(BinaryNode root) {
        if(root == null) throw new NoSuchElementException("The tree is empty!");
        BinaryNode secondLastLeafNode = null, curNode = null;
        Queue<BinaryNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {//O(n) space & time.
            secondLastLeafNode = curNode;
            curNode = q.poll();
            if(curNode.left == null) {
                //In this case, curNode's right child is the last leaf node. Therefore, deleting it.
                secondLastLeafNode.right = null;
                return;
            }
            else if(curNode.right == null) {
                //in this case, curNode's left child is the last leaf node.
                curNode.left = null;
                return;
            }
            q.add(curNode.left);
            q.add(curNode.right);
        }

    }
    
    public void printTree() {
        if(root == null) throw new NoSuchElementException("The tree is empty!");
        BTreePrinter.printNode(root);
    }
    
    public void delete(int value) {
        if(root == null) throw new NoSuchElementException("The tree is empty!");
        Queue<BinaryNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            BinaryNode curNode = q.poll();
            if(curNode.val == value) {
                //We found the value to be deleted!
                //Now we need to swap the values of the node to be deleted with the last leaf node.
                BinaryNode lastLeafNode = getLastLeafNode(root);
                curNode.val = lastLeafNode.val;
                lastLeafNode.val = value;
                //finally deleting the last leaf node, which now has the value of the node we intend to delete.
                this.deleteLastLeafNode(root);
                System.out.println(value+" has been successfully deleted");
                return;
            }
            
            if(curNode.left != null) q.add(curNode.left);
            if(curNode.right != null) q.add(curNode.right);
        }
        System.out.println(value+" not found");
    }
    
    //All of the below types of traversal -> O(n) time & space.
    public void preOrderTraversal() {
        if(root == null) throw new NoSuchElementException("The tree is empty!");
        preOrderTraversal(root);
        System.out.println("\b\b");
    }
    private void preOrderTraversal(BinaryNode root) {
        if(root == null) return;
        System.out.print(root.val+", ");
        this.preOrderTraversal(root.left);
        this.preOrderTraversal(root.right);
    }
    
    
    public void inOrderTraversal() {
        if(root == null) throw new NoSuchElementException("The tree is empty!");
        inOrderTraversal(root);
        System.out.println("\b\b");
    }
    private void inOrderTraversal(BinaryNode root) {
        if(root == null) return;
        this.inOrderTraversal(root.left);
        System.out.print(root.val+", ");
        this.inOrderTraversal(root.right);
    }
    
    
    
    public void postOrderTraversal() {
        if(root == null) throw new NoSuchElementException("The tree is empty!");
        postOrderTraversal(root);
        System.out.println("\b\b");
    }
    private void postOrderTraversal(BinaryNode root) {
        if(root == null) return;
        this.postOrderTraversal(root.left);
        this.postOrderTraversal(root.right);
        System.out.print(root.val+", ");
    }
    
    public void levelOrderTraversal() {
        if(root == null) throw new NoSuchElementException("The tree is empty!");
        levelOrderTraversal(root);
        System.out.println("\b\b");
        
    }
    
    private void levelOrderTraversal(BinaryNode root) {
        Queue<BinaryNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            BinaryNode curNode = q.poll();
            System.out.print(curNode.val+", ");
            if(curNode.left != null) q.add(curNode.left);
            if(curNode.right != null) q.add(curNode.right);
        }
    }
}
