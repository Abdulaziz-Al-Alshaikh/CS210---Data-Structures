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
public class CircularDoublyLinkedList {
    public static void main(String[] args) {
        CircularDoublyLinkedList c = new CircularDoublyLinkedList();
        c.add(1);
        c.add(2);
        c.add(3);
        c.add(54);
        c.printDoublyLinkedList();
        c.addAt(2, 12);
        c.printDoublyLinkedList();
        c.printReversedList();
       
    }
    Node head, tail;
    //circural doubly linked list is similar to circular singly linked list. 
    //However there is only one difference, which is the tail's next pointer points to the head reference and head's previous pointer points to the tail.
    //Tail <-> Head <-> Node <-> Tail <-> Head <-> Node...
    //When we traverse a circular list, we always use the size field to avoid an infitie loop.
    int size;
    
    
    public void add(int value) {
        Node newNode = new Node(value);
        if(isEmpty()) {
            //O(1) time & space
            head = newNode;
            tail = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        }
        
        else{
            //O(1) time & space to insert at the end.
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
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
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
            //adding at the beggining is done in constant time & space
        }
        
        else{
            Node tempHead = head;
            for(int i = 0; i < index - 1; i++) {
                tempHead = tempHead.next;
            }
            //tempHead reference is now at the position right before where the insertion has to be done.
            tempHead.next.prev = newNode;//updating next node's previous reference to point to the newNode. 
            newNode.next = tempHead.next;//updating newNode's next reference to point to the next node. 
            newNode.prev = tempHead;//updating the previous reference of newNode to point to the node at the index right before the insertion index.
            tempHead.next = newNode;//Finally update node's next reference to point to newNode. 
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
            head.prev = tail;
        }
        else{
            Node tempHead = head;
            for(int i = 0; i < index - 1; i++)
                tempHead = tempHead.next;
            tempHead.next = tempHead.next.next;//Successfully removed the element at the given index. O(n) time & constant space
            tempHead.next.prev = tempHead;
            //The garbage collection will take care of the deleted node since there is no reference pointing to it.
            if(index == size-1)
                tail = tempHead;//In case we are removing the last element/tail we need to update the tail's reference.
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
        System.out.print("Tail <-> ");
        for(int i = 0; i < size; i++) {
            System.out.print(tempHead + " <-> ");
            tempHead = tempHead.next;
        }
        
        System.out.println("Tail <-> Head");
    }
    public void printReversedList() {
        if(isEmpty()){
            return;
        }
        Node tempTail = tail;
        for(int i = 0; i < size; i++) {
            System.out.print(tempTail + ", ");
            tempTail = tempTail.prev;
        }
        
        System.out.println("\b\b");
    }
    public static class Node{
        Node next, prev;
        int value;
        public Node(int value) {this.value = value;}
        
        @Override
        public String toString() {return String.valueOf(this.value);}
    }
}
