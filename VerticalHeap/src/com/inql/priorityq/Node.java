package com.inql.priorityq;

import java.util.Objects;
import java.util.Random;

public class Node<T> implements Comparable<Node<T>>{

    private static int nextId;
    private int id;
    private Node<T> next;
    private T value;
    private int priority;

    static
    {
        nextId = 1;
    }

    {
        id = nextId;
        nextId++;
    }

    public Node(T value) {
        this.value = value;
        this.priority = 0;
    }

    public Node(T value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Node<T> o) {
        if(o!=null)
            return o.priority-this.priority;
        else
            return 1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("id=").append(id);
        sb.append(", value=").append(value);
        sb.append(", priority=").append(priority);
        sb.append('}');
        return sb.toString();
    }
}
