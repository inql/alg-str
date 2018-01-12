package com.inql.priorityq;

public class PriorityQueue<T> {

    private Node<T> head, tail;

    public PriorityQueue() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void insert(Node<T> node){

        node.setNext(null);
        if(tail != null){
            tail.setNext(node);
        }
        tail = node;
        if(head == null){
            head = tail;
        }
    }

    public Node<T> remove(){
        if(isEmpty())
            return null;
        Node<T> result = head;
        Node<T> prev = head;
        Node<T> toCompare = head.getNext();
        while(toCompare!=null){
            if(result.compareTo(toCompare)>0){
                result = toCompare;
                while(prev.getNext()!=result && prev.getNext()!=null)
                    prev = prev.getNext();
            }
            toCompare = toCompare.getNext();

        }
        if(result == tail)
            tail = prev;
        if(result == head){
            head = head.getNext();
        }else{
            prev.setNext(result.getNext());
        }
        return result;
    }

    public void setPriority(int id, int priority){
        Node<T> node = findNode(id);
        if(node!=null){
            node.setPriority(priority);
        }
        else{
            System.out.println("Nie ma takiej wartości w tablicy");
        }
    }

    private Node<T> findNode(int id){
        if(isEmpty())
            return null;
        Node<T> result = head;
        while(result!=null){
            if(result.getId()==id)
                return result;
            result = result.getNext();
        }
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PriorityQueue{");
        sb.append("head=").append(head);
        sb.append(", tail=").append(tail);
        sb.append("}\n");
        sb.append("Preview of PriorityQueue:\n");
        Node node = head;
        while(node!=null){
            sb.append(node+" ");
            node = node.getNext();
        }
        sb.append("\n");
        return sb.toString();
    }
}
