package com.inql.AL5_1;


import com.inql.Utilities;

public class List1L {

    private Node1L head;
    private Node1L tail;
    private String name;

    public List1L(String name) {
        this.head = null;
        this.tail = head;
        this.name = name;
    }

    public Node1L getHead() {
        return head;
    }

    public Node1L getTail() {
        return tail;
    }

    //dodaje na początek listy

//    public void insert(String key){
//        Node1L item = new Node1L(key);
//        item.setNext(head);
//        head = item;
//    }


    public void insert(Node1L item){
        if(isEmpty())
            tail = item;
        item.setNext(head);
        head = item;
    }

    //dodaje na koniec listy

    public void insertAtEnd(Node1L item){
        if(head==null)
            insert(item);
        else{
            tail.setNext(item);
            tail = item;
        }
    }

    public void printAll(){
        System.out.println(name+"\n"+Utilities.repeat("-",20)+"\n");
        if(!isEmpty()){
            Node1L item = head;
            while(item!=null){
                System.out.println(item.getKey());
                item = item.getNext();
            }
            System.out.println();
            System.out.println("head: "+getHead()+" tail: "+getTail());
        }
        else
            System.out.println("Lista jest pusta.");
        System.out.println(Utilities.repeat("-",20)+"\n");
    }

    public boolean isEmpty(){
        return head == null;
    }

    public Node1L search(String key){
        if(!isEmpty()){
            Node1L item = head;
            while (item!=null){
                if(item.getKey().equals(key))
                    return item;
                item = item.getNext();
            }

        }
        return null;
    }

    public void remove(String key){
        if(!isEmpty() && search(key)!=null)
        {
            Node1L prev = head;
            Node1L current = head;
            while(!key.equals(current.getKey())){
                if(current.getNext()==null)
                    return;
                prev = current;
                current = current.getNext();
            }
            if(current == tail)
                tail = prev;
            if(current == head){
                head = head.getNext();
            }
            else{
                prev.setNext(current.getNext());
            }

        }
        else{
            System.out.println("Nie ma takiego elementu na liście");
        }
    }

    //Zwalnianiem pamięci zajmie się garbage collector, ale węzły należy usunąć samemu :)

    public void deleteWholeList(){
        if(!isEmpty()){
            Node1L next = head;
            while(next!=null){
                head = next.getNext();
                next = next.getNext();
            }
        }
    }

    public List1L cpyWoRepetition(){

        List1L result = new List1L("copy of "+this.name);

        if(!isEmpty()){

            Node1L next = head;
            while(next!=null){
                if(result.search(next.getKey())==null){
                    Node1L toInsert = new Node1L(next.getKey());
                    result.insertAtEnd(toInsert);
                }
                next = next.getNext();

            }

        }

        return result;
    }

    public List1L merge(List1L second){

        List1L result = new List1L("merge of "+this.name+" and "+second.name);
        result.head = this.head;
        this.head = null;
        result.tail = this.tail;
        result.getTail().setNext(second.head);
        second.head = null;
        result.tail = second.tail;

        return result;

    }


}
