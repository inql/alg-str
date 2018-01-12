package com.inql.binarytree;

public class Node {

    private Node leftSon, rightSon, parent;
    private int value;

    public Node(int value) {
        this.value = value;
        this.leftSon = this.rightSon = this.parent = null;
    }

    public Node getLeftSon() {
        return leftSon;
    }

    public void setLeftSon(Node leftSon) {
        this.leftSon = leftSon;
    }

    public Node getRightSon() {
        return rightSon;
    }

    public void setRightSon(Node rightSon) {
        this.rightSon = rightSon;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }
}
