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
public class CircularSinglyLinkedList {
   
    Node head, tail;
    //circural singly linked list is similar to singly linked list. 
    //However there is only one difference, which is the tail's next pointer points to the head reference.
    //Head -> Node -> Tail -> Head -> Node...
    //When we traverse a circular list, we always use the size field to avoid an infitie loop.
    int size;
    
    
    public void add(int value) {
        Node newNode = new Node(value);
        if(isEmpty()) {
            //O(1) time & space
            head = newNode;
            tail = newNode;
        }
        
        else{
            //O(1) time & space to insert at the end.
            newNode.next = head;
            tail.next = newNode;
            tail = newNode;
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
            head = newNode;
            //adding at the beggining is done in constant time & space
        }
        
        else{
            Node tempHead = head;
            for(int i = 0; i < index - 1; i++) {
                tempHead = tempHead.next;
            }
            //tempHead reference is now at the position right before where the insertion has to be done.
            newNode.next = tempHead.next;
            tempHead.next = newNode;
            //in this case, adding at a specified index is done in O(n) time & O(1) space.
        }
        size++;
    }
    
    public void remove(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Size is "+size+"!");
        if(index == 0){
            head = head.next;//O(1) time & space to remove from the beginning of a list
            tail.next = head;
        }
        else{
            Node tempHead = head;
            for(int i = 0; i < index - 1; i++)
                tempHead = tempHead.next;
            tempHead.next = tempHead.next.next;//Successfully removed the element at the given index. O(n) time & constant space
            if(index == size-1)
                tail = tempHead;//In case we are removing the last element/tail we need to update the tail's reference.
        }
        size--;
    }
    
    
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void printSinglyLinkedList() {
        if(isEmpty()){
            return;
        }
        Node tempHead = head;
        for(int i = 0; i < size; i++) {
            System.out.print(tempHead + "-> ");
            tempHead = tempHead.next;
        }
        
        System.out.println("Head");
    }
    public static class Node{
        Node next;
        int value;
        public Node(int value) {this.value = value;}
        
        @Override
        public String toString() {return String.valueOf(this.value);}
    }
}
