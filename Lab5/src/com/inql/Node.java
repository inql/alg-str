package com.inql;

public class Node {

    private String key;

    public Node(){}

    public Node(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Node " +
                "key='" + key + '\'';
    }
}
