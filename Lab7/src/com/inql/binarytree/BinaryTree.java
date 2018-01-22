package com.inql.binarytree;

import com.inql.Utilities;

import java.util.ArrayList;

/**
 * @author inql
 * @version 0.0.1
 * Obiekt <code>BinaryTree</code> reprezentuje drzewo binarne. Zawiera wyłącznie referencję do obiektu korzenia.
 */

public class BinaryTree {

    /**
     * Opis pól:
     * root - korzeń drzewa
     * isCloseable - zmienna logiczna używana podczas wstawiania wartości do drzewa w przypadku napotkania powtórzenia
     * behavior - interfejs funkcyjny - wstrzykuję do niego lambdę podczas tworzenia obiektu(bądź też w trakcie) by zmienić zachowanie się drzewa podczas umieszczania wartości
     */

    private Node root;
    private boolean isCloseable;
    private Behavior behavior;

    /**
     * domyślny konstruktor
     */

    public BinaryTree() {
        this.root = null;
    }
    /**
     * Konstruktor
     * @param behavior - zachowanie podczas napotkania powtórzenia
     */

    public BinaryTree(Behavior behavior) {
        this.root = null;
        this.behavior = behavior;
    }

    /**
     * gettery & settery
     */

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean isCloseable() {
        return isCloseable;
    }

    public void setCloseable(boolean closeable) {
        isCloseable = closeable;
    }



    /**
     * Metoda <code>insertNode</code> umieszcza węzeł w argumencie jawnym do drzewa, zachowując zasady drzew binarnych
     * @param node - węzeł do umieszczenia
     *
     */


    public void insertNode(Node node) {
        setCloseable(false);
        Node current = root;
        Node nodesFather = null;
        while (current != null) {
            nodesFather = current;
            if(node.compareTo(current)==0) {
                current = behavior.operation(current,this);
                if(isCloseable)
                    return;
            }
            else if (node.compareTo(current)<0) current = current.getLeftSon();
            else if (node.compareTo(current)>0) current = current.getRightSon();
        }
        node.setParent(nodesFather);
        if (nodesFather == null)
            root = node;
        else if (node.compareTo(nodesFather)==0) nodesFather.setLeftSon(node);
        else if (node.compareTo(nodesFather)<0)
            nodesFather.setLeftSon(node);
        else
            nodesFather.setRightSon(node);
    }

    /**
     *
     * @param values - tablica węzłów do dodania
     */

    public void massInsert(ArrayList<Integer> values){
        for(int value : values){
            insertNode(new Node(value));
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
        postorderPrint(root, maxDepth());
    }

    private void postorderPrint(Node x, int counter) {
        if (x == null)
            return;
        postorderPrint(x.getLeftSon(), --counter);
        counter++;
        postorderPrint(x.getRightSon(), --counter);
        System.out.println(Utilities.repeat(" ",counter)+x.getValue()+":"+x.getQuantity());

    }

    /**
     * Metoda wypisuje zawartość drzewa w postaci inorder
     */

    public void inorderPrint() {
        inorderPrint(root, maxDepth());
    }

    private void inorderPrint(Node x, int counter) {
        if (x == null)
            return;
        inorderPrint(x.getLeftSon(), --counter);
        System.out.println(Utilities.repeat(" ",counter)+x.getValue()+":"+x.getQuantity());
        counter++;
        inorderPrint(x.getRightSon(), --counter);

    }
    /**
     * Metoda <code>treeSearch</code> wyszukuje podaną wartość w drzewie
     * @param value - wartość do wyszukania
     * @param root - korzeń poddrzewa od którego zaczynamy szukanie
     * @return - zwraca referencję do obiektu Node lub null jeśli wartość nie znajduje się w drzewie
     */

    public Node treeSearch(int value, Node root){
        Node current = root;
        while(current != null && current.getValue()!=value){
            if(value <  current.getValue())
                current = current.getLeftSon();
            else
                current = current.getRightSon();
        }
        return current;
    }

    /**
     * Metoda <code>treeSearch</code> wyszukuje podaną wartość w drzewie w wariancie z przełącznikami
     * @param value - wartość do wyszukania
     * @param occurrence - wystąpienie tej samej wartości - jeśli occurence > ilość powtórzeń to zwróci korzeń
     * @return
     */

    public Node treeSearch(int value, int occurrence){
        int counter = 0;
        Node found = root;
        Node son = null;
        while(counter!=occurrence){
            if(found!=son && son!=null)
                found = son;
            found = treeSearch(value,found);
            son = behavior.operation(found,this);
            counter++;
        }
        return found;
    }

    /**
     * Metoda <code>preTreeDelete</code> przekazuje węzeł do usunięcia do odpowiedniej instrukcji w zależności od typu drzewa
     * @param toDelete - węzeł do usunięcia
     */

    public void preTreeDelete(Node toDelete){
        if(toDelete!=null){
            if(toDelete.getQuantity()==1){
                treeDelete(toDelete);
                return;
            }
            else{
                toDelete = behavior.operation(toDelete,this);
            }
        }
    }

    /**
     * Właściwe usuwanie w sytuacji gdy chcemy pozbyć się fizycznie węzła(w przypadku gdy ilość powtarzających się kluczy jest równa 1)
     * @param toDelete - węzeł do usunięcia
     */

    private void treeDelete(Node toDelete){
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

    /**
     * podczepia poddrzewo o korzeniu node w miejsce węzła subtreeRoot
     * @param node korzeń
     * @param subtreeRoot miejsce do którego zostaje podczepiony korzeń
     */

    public void transplant(Node node, Node subtreeRoot){

        if(node.getParent()==null)
            root = subtreeRoot;
        else
        {
            if(node==node.getParent().getLeftSon())
                node.getParent().setLeftSon(subtreeRoot);
            else
                node.getParent().setRightSon(subtreeRoot);
            if (subtreeRoot!=null)
                subtreeRoot.setParent(node.getParent());
        }

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

    /**
     * Zwraca głębokość drzewa
     * @return - głębokość drzewa
     */

    public int maxDepth(){
        return maxDepth(root);
    }

    public int maxDepth(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(maxDepth(node.getLeftSon()), maxDepth(node.getRightSon()));
    }

}
