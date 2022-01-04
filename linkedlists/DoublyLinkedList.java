/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlists;

/**
 *
 * @author Abdulaziz Al-Alshaikh
 */
public class DoublyLinkedList {
    
    public static void main(String[] args) {
        DoublyLinkedList d = new DoublyLinkedList();
        d.add(1);
        d.add(2);
        d.add(3);
        d.add(4);
        d.printDoublyLinkedList();
       

    }
    Node head, tail;//Note that using a tail referece would allow us to insert/delete a node at the end of the list in constant time! 
    int size;
    
    
    public void add(int value) {
        Node newNode = new Node(value);
        if(isEmpty()) {
            //O(1) Time & Space
            head = newNode;
            tail = newNode;
        }
        
        else{
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
            //inserting at at the end of the list in constant time & space
        }
        size++;
    }
    
    public void addAt(int index, int value) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Inserting at "+index+" is not valid given the size is "+size);
        }
        
        Node newNode = new Node(value);
        if(index == size){
            this.add(value);//We have already implemeted add(int value) which adds the value to the end of the list.
        }
        else if(index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            //adding at the beggining of a linked list is done in constant time & space
        }
        
        else{
            Node tempHead = head;
            for(int i = 0; i < index - 1; i++) {
                tempHead = tempHead.next;
            }
            //tempHead reference is now at the position right before where the insertion has to be done.
            tempHead.next.prev = newNode;//updating next node's previous refrence to point to the newNode. 
            newNode.next = tempHead.next;//updating newNode's next reference to point to the next node. 
            newNode.prev = tempHead;//updating the previous refrence of newNode to point to the node at the index right before the insertion index.
            tempHead.next = newNode;//Finally update node's next reference to point to newNode. 
            //in this case, adding at a specified index is done in O(n) time & O(1) space.
        }
        size++;
    }
    
    public void remove(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Size is "+size+"!");
        if(index == 0){
            //O(1) time & space to remove from the beginning of a list.
            head.next.prev = null;
            head = head.next;
            head.prev = null;
        }
        else if(index == size-1) {
            //O(1) time & space to remove from the end 
            tail = tail.prev;
            tail.next = null;
        }
        else{
            Node tempHead = head;
            for(int i = 0; i < index - 1; i++)
                tempHead = tempHead.next;
            tempHead.next = tempHead.next.next;//Successfully removed the element at the given index. O(n) time & constant space
            tempHead.next.prev = tempHead;
            //The garbage collection will take care of the deleted node since there is no reference pointing to it.
        }
        size--;
    }
    
    
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void printDoublyLinkedList() {
        if(isEmpty()){
            return;
        }
        
        Node tempHead = head;
        while(tempHead != null) {
            System.out.print(tempHead + " <-> ");
            tempHead = tempHead.next;
        }
        
        System.out.println("\b\b\b\b-> Null");
    }
    public static class Node{
        Node next, prev;
        int value;
        public Node(int value) {this.value = value;}
        
        @Override
        public String toString() {return String.valueOf(this.value);}
    }
}
    