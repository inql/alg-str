package com.inql.binarytree;

public class Node implements Comparable<Node>{

    private Node leftSon, rightSon, parent;
    private int value;
    private int quantity;

    public Node(int value) {
        this.value = value;
        this.leftSon = this.rightSon = this.parent = null;
        this.quantity = 1;
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

    public void setValue(int value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity(){
        this.quantity--;
    }

    @Override
    public int compareTo(Node o) {
        if(o!=null) return this.getValue() - o.getValue();
        return -this.getValue();
    }
}
