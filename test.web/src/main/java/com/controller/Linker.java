package com.controller;

/**
 * Created by Administrator on 2015/12/2.
 */
public class Linker {

    private static Node head;

    public static void main(String[] args){
        head = initNode(5);
        printList(head);

        System.out.println();
        Node reversedHead = reverseList(head);
        printList(reversedHead);

        printList(reverseList(reversedHead));

    }

    private static Node reverseList(Node head){
        Node next = null;
        Node pre = null;
        while(head != null){
            next = head.next;

            head.next = pre;
            pre = head;

            head = next;
        }
        return pre;
    }


    private static Node initNode(int length){
        if(length == 0){
            throw new IllegalArgumentException();
        }
        Node head = new Node();
        head.data = "value0";
        if(length == 1){
            return head;
        }

        Node pre = head;
        Node node;
        for(int i = 1; i < length;i++){
            node = new Node();
            node.data = "value" + i;
            pre.next = node;
            pre = node;
        }
        return head;
    }

    public static void printList(Node head){
        Node cur = head;
        while(cur != null){
            System.out.println(cur.data);
            cur = cur.next;
        }
    }

}

class Node{

    public String data;

    public Node next;

}
