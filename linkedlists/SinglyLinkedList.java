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
public class SinglyLinkedList {
    
    public static void main(String[] args) {
        SinglyLinkedList s = new SinglyLinkedList();
        s.add(1);
        s.add(2);
        s.add(3);
        s.add(4);
        s.printSinglyLinkedList();
        s.remove(2);
        s.printSinglyLinkedList();
    }
    Node head;//The starting point of the list.
    int size;
    
    
    public void add(int value) {
        Node newNode = new Node(value);
        if(isEmpty()) {
            //O(1) Time & Space
            head = newNode;
        }
        
        else{
            Node tempHead = head;//This node copies the reference of the original head of the list.
            while(tempHead.next != null) {tempHead = tempHead.next;}
            //Now the tempHead pointer is at the end/tail of the list.
            tempHead.next = newNode;//successfully added the newNode to the end of the list.
            //O(n) time because we had to traverse the entire list in order to append the node.
            //O(1) space.
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
            //adding at the beggining of a linked list is done in constant time & space
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
            head = head.next;//O(1) time & space to remove from the beginning of a list.
        }
        else{
            Node tempHead = head;
            for(int i = 0; i < index - 1; i++)
                tempHead = tempHead.next;
            tempHead.next = tempHead.next.next;//Successfully removed the element at the given index. O(n) time & constant space
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
        while(tempHead != null) {
            System.out.print(tempHead + "-> ");
            tempHead = tempHead.next;
        }
        
        System.out.println("Null");
    }
    public static class Node{
        Node next;
        int value;
        public Node(int value) {this.value = value;}
        
        @Override
        public String toString() {return String.valueOf(this.value);}
    }
    
}
