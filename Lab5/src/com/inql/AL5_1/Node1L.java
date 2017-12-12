package com.inql.AL5_1;

import com.inql.Node;

public class Node1L extends Node{

    private Node1L next;

    public Node1L(String key) {
        super(key);
    }

    public Node1L getNext() {
        return next;
    }

    public void setNext(Node1L next) {
        this.next = next;
    }
}
