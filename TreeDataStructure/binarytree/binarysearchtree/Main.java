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
public class Main {
    public static void main (String [] args) {
        BinarySearchTree b = new BinarySearchTree();
        b.insert(5);
        b.insert(4);
        b.insert(6);
        b.insert(10);
        b.printTree();
        //deleting the root --> case 2 (2 children)
        b.delete(5);
        b.printTree();
    }
}
