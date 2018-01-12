package com.inql.binarytree;

import java.util.ArrayList;

/**
 * @author inql
 * @version 0.0.1
 * Obiekt <code>BinaryTree</code> reprezentuje drzewo binarne. Zawiera wyłącznie referencję do obiektu korzenia.
 */

public class BinaryTree {

    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    /**
     * Metoda <code>insertNode</code> umieszcza węzeł w argumencie jawnym do drzewa, zachowując zasady drzew binarnych
     *
     * @param node - węzeł do dodania
     */

    public void insertNode(Node node) {
        Node current = root;
        Node nodesFather = null;
        while (current != null) {
            nodesFather = current;
            if (node.getValue() < current.getValue()) current = current.getLeftSon();
            else current = current.getRightSon();
        }
        node.setParent(nodesFather);
        if (nodesFather == null)
            root = node;
        else if (node.getValue() < nodesFather.getValue())
            nodesFather.setLeftSon(node);
        else
            nodesFather.setRightSon(node);
    }

    /**
     *
     * @param nodes - tablica węzłów do dodania
     */

    public void massInsert(ArrayList<Node> nodes){
        for(Node node : nodes){
            insertNode(node);
        }
    }

    /**
     * @return zwraca wartość true jeśli referencja do korzenia jest nullem - czyli gdy drzewo binarne jest puste
     */

    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Metoda wypisuje zawartość drzewa w postaci postorder
     */

    public void postorderPrint() {
        postorderTreeWalk(root);
    }

    public void postorderTreeWalk(Node x) {
        if (x == null)
            return;
        postorderTreeWalk(x.getLeftSon());
        postorderTreeWalk(x.getRightSon());
        System.out.println(x.getValue());
    }

    /**
     * Metoda <code>treeSearch</code> wyszukuje podaną wartość w drzewie
     * @param value - wartość do wyszukania
     * @return - zwraca referencję do obiektu Node lub null jeśli wartość nie znajduje się w drzewie
     */

    public Node treeSearch(int value){
        Node current = root;
        while(current != null && current.getValue()!=value){
            if(value <  current.getValue())
                current = current.getLeftSon();
            else
                current = current.getRightSon();
        }
        return current;
    }

    public void treeDelete(Node toDelete){
        if(toDelete!=null){
            if(toDelete.getLeftSon() == null)
                transplant(toDelete,toDelete.getRightSon());
            else if(toDelete.getRightSon() == null)
                transplant(toDelete,toDelete.getLeftSon());
            else{
                Node subMin = treeMinimum(toDelete.getRightSon());
                if(subMin.getParent() != toDelete){
                    transplant(subMin,subMin.getRightSon());
                    subMin.setRightSon(toDelete.getRightSon());
                    subMin.getRightSon().setParent(subMin);
                }
                transplant(toDelete,subMin);
                subMin.setLeftSon(toDelete.getLeftSon());
                subMin.getLeftSon().setParent(subMin);
            }
        }
    }

    public void transplant(Node node, Node subtreeRoot){

        if(node.getParent()==null)
            root = subtreeRoot;
        else if(node==node.getParent().getLeftSon())
            node.getParent().setLeftSon(subtreeRoot);
        else
            node.getParent().setRightSon(subtreeRoot);

    }

    /**
     * Metoda <code>treeMinimum</code> zwraca skrajny lewy węzeł
     * w poddrzewie o korzeniu x, czyli węzeł o najmniejszym kluczu w tym poddrzewie
     * @param x - korzeń poddrzewa
     * @return zwraca najmniejszą wartość
     */

    public Node treeMinimum(Node x){
        while(x.getLeftSon()!=null){
            x = x.getLeftSon();
        }
        return x;
    }

}
