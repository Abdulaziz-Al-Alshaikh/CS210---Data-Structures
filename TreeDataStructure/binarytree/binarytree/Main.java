/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

/**
 *
 * @author Abdulaziz Al-Alshaikh
 */
public class Main {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        for(int i = 0; i <= 10; i++)
            binaryTree.insert(i);
        //Checking insertion and deleteion methods.
        binaryTree.printTree();
        binaryTree.delete(0);//Deleting the root of the tree.
        binaryTree.printTree();
        //Traversing the tree:
        binaryTree.preOrderTraversal();
        binaryTree.inOrderTraversal();
        binaryTree.postOrderTraversal();
        binaryTree.levelOrderTraversal();
        
        
        
    }
    
}
