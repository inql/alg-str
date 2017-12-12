package com.inql.AL5_1;


import com.inql.Utilities;
import com.sun.deploy.util.StringUtils;

public class List1L {

    private Node1L head;
    private String name;

    public List1L(String name) {
        this.head = null;
        this.name = name;
    }

    public Node1L getHead() {
        return head;
    }

//dodaje na początek listy

    public void insert(String key){
        Node1L item = new Node1L(key);
        item.setNext(head);
        head = item;
    }

    //dodaje na koniec listy

    public void insertAtEnd(String key){
        if(head==null)
            insert(key);
        else{
            Node1L item = new Node1L(key);
            Node1L next = head;
            while(next.getNext()!=null){
                next = next.getNext();
            }
            next.setNext(item);
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

            if(current == head)
                head = head.getNext();
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
                    result.insertAtEnd(next.getKey());
                }
                next = next.getNext();

            }

        }

        return result;
    }

    public List1L merge(List1L second){

        List1L result = new List1L("merge of "+this.name+" and "+second.name);
        Node1L next = head;
        //kopiujemy wszystko z pierwszej listy (tej z której wywoływana była metoda)
        while(next!=null){
            result.insertAtEnd(next.getKey());
            next = next.getNext();
        }
        //kopiujemy wszystko z drugiej listy (argumentu)
        next = second.getHead();
        while(next!=null){
            result.insertAtEnd(next.getKey());
            next = next.getNext();
        }
        //usuwamy obie listy
        this.deleteWholeList();
        second.deleteWholeList();

        return result;

    }


}
