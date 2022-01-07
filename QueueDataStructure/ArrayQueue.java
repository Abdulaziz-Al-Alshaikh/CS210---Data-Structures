/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QueueDataStructure;

/**
 *
 * @author Abdulaziz Al-Alshaikh
 */

import java.util.NoSuchElementException;


public class ArrayQueue {

    private Integer[] queue;
    private int front;
    private int back;

    public ArrayQueue(int capacity) {
        queue = new Integer[capacity];
    }
    
    public ArrayQueue() {
        this(10);
    }

    public void add(int value) {
        if (size() == queue.length - 1) {//In case the queue is full, we create a new array with length double the queue's length. O(n) space & time
            int numItems = size();
            Integer[] newArray = new Integer[2 * queue.length];

            System.arraycopy(queue, front, newArray, 0, queue.length - front);//copying elements to newArray starting from front till queue.length - front. Items will be added to newArray starting from index 0.
            System.arraycopy(queue, 0, newArray, queue.length - front, back);

            queue = newArray;

            front = 0;
            back = numItems;
        }


        queue[back] = value;
        if (back < queue.length - 1) {
            back++;
        }
        else {
            back = 0;
        }
    }

    public int remove() {//O(1) space & time
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        int first = queue[front];
        queue[front] = null;
        front++;
        if (size() == 0) {
            front = 0;
            back = 0;
        }
        else if (front == queue.length) {
            front = 0;
        }

        return first;
    }

    public int peek() {//O(1) space & time
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        return queue[front];
    }

    public int size() {//O(1) space & time
        if (front <= back) {
            return back - front;
        }
        else {
            return back - front + queue.length;
        }

    }
    

    public void printQueue() {//Linear time
        if (front <= back) {
            for (int i = front; i < back; i++) {
                System.out.println(queue[i]);
            }
        }
        else {//This happens when we remove elements from the queue and then adding more elements. At some point the front will exceed the back index.
            for (int i = front; i < queue.length; i++) {
                System.out.println(queue[i]);
            }
            for (int i = 0; i < back; i++) {
                System.out.println(queue[i]);
            }
        }
    }

}
