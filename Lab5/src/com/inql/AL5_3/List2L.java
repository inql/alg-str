package com.inql.AL5_3;

import com.inql.Utilities;

public class List2L {

    private Node2L sentinel;
    private String name;

    public List2L(String name) {
        sentinel = new Node2L();
        this.sentinel.setPrev(sentinel);
        this.sentinel.setNext(sentinel);
        this.sentinel.setKey("wartownik");
        this.name = name;
    }

    public void insert(String key){
        Node2L node2L = new Node2L(key);
        node2L.setNext(sentinel.getNext());
        sentinel.getNext().setPrev(node2L);
        sentinel.setNext(node2L);
        node2L.setPrev(sentinel);

    }

    public boolean isEmpty(){
        return sentinel.getNext() == sentinel && sentinel.getPrev() == sentinel;
    }

    public void printAll(){
        System.out.println(name+"\n"+Utilities.repeat("-",20)+"\n");
        if(!isEmpty()){
            Node2L item = sentinel.getNext();
            while(item!=sentinel){
                System.out.println(item.getKey());
                item = item.getNext();
            }
            System.out.println();
        }
        else
            System.out.println("Lista jest pusta.");
        System.out.println(Utilities.repeat("-",20)+"\n");
    }

    public void printAllFromLast(){
        System.out.println(name+"\n"+ Utilities.repeat("-",20)+"\n");
        if(!isEmpty()){
            Node2L item = sentinel.getPrev();
            while(item!=sentinel){
                System.out.println(item.getKey());
                item = item.getPrev();
            }
        }else
            System.out.println("Lista jest pusta");
        System.out.println(Utilities.repeat("-",20)+"\n");
    }

    public Node2L search(String key){
        Node2L item = sentinel.getNext();
        while(item!=sentinel && !item.getKey().equals(key)){
            item = item.getNext();
        }
        return item;
    }

    public void delete(String key){
        if(!isEmpty() && search(key)!=sentinel){
            Node2L toDelete = search(key);
            toDelete.getPrev().setNext(toDelete.getNext());
            toDelete.getNext().setPrev(toDelete.getPrev());
        }else
            System.out.println("Nie ma takiej pozycji na liście");
    }

    public void deleteWholeList(){
        if(!isEmpty()){
            Node2L toDelete = sentinel.getNext();
            while(toDelete!=sentinel){
                delete(toDelete.getKey());
                toDelete = sentinel.getNext();
            }
        }
    }

    public List2L cpyWoRepetition(){

        List2L result = new List2L("copy of "+name);

        if(!isEmpty()){
            Node2L toCopy = sentinel.getPrev();
            while(toCopy!=sentinel){
                if(result.search(toCopy.getKey())==result.sentinel)
                    result.insert(toCopy.getKey());
                toCopy = toCopy.getPrev();
            }
        }
        return result;
    }

    public List2L merge(List2L second){

        List2L result = new List2L("merge of "+name+" and "+second.name);

        Node2L toCopy = this.sentinel.getPrev();
        //kopiujemy wszystko z pierwszej listy
        while(toCopy!=this.sentinel){
            result.insert(toCopy.getKey());
            toCopy = toCopy.getPrev();
        }
        //kopiujemy wszystko z drugiej listy
        toCopy = second.sentinel.getPrev();
        while (toCopy.getPrev()!=second.sentinel){
            result.insert(toCopy.getKey());
            toCopy = toCopy.getPrev();
        }
        //usuwamy obie listy
        this.deleteWholeList();
        second.deleteWholeList();

        return result;


    }
}
