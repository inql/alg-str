package com.inql.AL5_3;

import com.inql.Node;

public class Node2L extends Node {

    private Node2L prev;
    private Node2L next;

    public Node2L(){}

    public Node2L(String key) {
        super(key);
    }

    public Node2L getPrev() {
        return prev;
    }

    public void setPrev(Node2L prev) {
        this.prev = prev;
    }

    public Node2L getNext() {
        return next;
    }

    public void setNext(Node2L next) {
        this.next = next;
    }
}
