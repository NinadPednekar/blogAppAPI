package com.blog.api.utils;

public class LL {

    Node head;
    class Node {
        String data;
        Node next;

        Node(String data){
            this.data = data;
            this.next = null;
        }
    }

    public void add(String data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void printList(){
        Node currentNode = head;
        while (currentNode != null){
            System.out.print(currentNode.data + " -> ");
            currentNode = currentNode.next;
        }
        System.out.println("null");
    }
    public static void main(String[] args){
        LL list = new LL();
        list.add("World");
        list.add("Hello");
        list.printList();
    }
}
