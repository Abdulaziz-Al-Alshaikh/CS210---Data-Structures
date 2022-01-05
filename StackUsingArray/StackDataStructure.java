/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StackUsingArray;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 *
 * @author Abdulaziz Al-Alshaikh
 */
public class StackDataStructure {
    public static void main(String[] args) {
        StackDataStructure s = new StackDataStructure(10);
        s.push(2);
        s.push(3);
        s.push(22);
        s.push(244);
        s.pop();
        s.pop();
        s.push(2222);
        s.printStack();
    }
    
    int top;
    int [] stack;
    public StackDataStructure(int size) {
        stack = new int[size];
    }
    
    public StackDataStructure() {
        this(10);
    }
    
    public void push(int value) {
        if(top == stack.length - 1) {
            //In this case the stack is full.
            //so we can double the size of the stack and copy all elements to the new one.
            int [] temp = Arrays.copyOf(stack, stack.length);
            stack = new int[stack.length * 2];
            for(int i = 0; i < temp.length; i++)
                stack[i] = temp[i];
        }
        stack[top++] = value;
    }
    
    public int pop() {
        if(top == 0)
            throw new NoSuchElementException("The stack is empty!");
        return stack[--top];//Pre decrement is used to pop because we used post increment to push.
    }
    
    public int peek() {
        if(top == 0)
            throw new NoSuchElementException("The stack is empty!");
        return stack[top-1];
    }
    public void printStack() {
        if(top == 0) {
            System.out.println("Empty!");
            return;
        }
        
        for(int i = top-1; i > -1; i--){
            System.out.println(stack[i] + "\n------------");
        }
    }
}
