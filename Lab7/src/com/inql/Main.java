package com.inql;

import com.inql.binarytree.Behavior;
import com.inql.binarytree.BinaryTree;
import com.inql.binarytree.Node;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        AL7_3();
        AL7_4();
        AL7_5();
    }


    public static void AL7_3(){
        Behavior doNothing = (target, binaryTree) -> {binaryTree.setCloseable(true); return target;};
        BinaryTree binaryTree = new BinaryTree(doNothing);
//        System.out.println(binaryTree.isEmpty());
        binaryTree.massInsert(new ArrayList<Integer>(){{add(18);add(11);add(6);add(30);add(21);add(19);add(8);add(22);add(23);add(5);add(20);add(26);add(17);}});
//        binaryTree.inorderPrint();
        binaryTree.postorderPrint();
        System.out.println("\n\n\n\n");
        binaryTree.preTreeDelete(binaryTree.treeSearch(8,binaryTree.getRoot()));
        binaryTree.preTreeDelete(binaryTree.treeSearch(30,binaryTree.getRoot()));
        binaryTree.preTreeDelete(binaryTree.treeSearch(18,binaryTree.getRoot()));
        binaryTree.preTreeDelete(binaryTree.treeSearch(11,binaryTree.getRoot()));
        binaryTree.postorderPrint();
//        binaryTree.inorderPrint();
//        System.out.println(binaryTree.maxDepth());
    }

    public static void AL7_4(){
        Behavior increaseQuantity = (Node target, BinaryTree binaryTree) -> {target.increaseQuantity(); binaryTree.setCloseable(true); return target;};
        BinaryTree binaryTree = new BinaryTree(increaseQuantity);
        binaryTree.massInsert(new ArrayList<Integer>(){{add(2);add(2);add(2);add(1);add(1);add(10);add(7);add(5);add(4);add(5);}});
        binaryTree.inorderPrint();
        Behavior descreaseQuantity = (Node target, BinaryTree binaryTree1) -> {target.decreaseQuantity(); return target;};
        binaryTree.setBehavior(descreaseQuantity);
        binaryTree.preTreeDelete(binaryTree.treeSearch(2,binaryTree.getRoot()));
        System.out.println("XD");
        binaryTree.inorderPrint();
    }

    public static void AL7_5(){

        Behavior putNode = (Node target, BinaryTree binaryTree) -> {
            if(target.getRightSon()==null) target = target.getLeftSon();
            else target = target.getRightSon();
            return target;
        };
        BinaryTree binaryTree = new BinaryTree(putNode);
        binaryTree.massInsert(new ArrayList<Integer>(){{add(17);add(18);add(11);add(6);add(30);add(18);add(19);add(18);add(17);add(22);add(17);add(23);add(18);add(18);add(26);add(17);}});
        binaryTree.postorderPrint();
        Node found = binaryTree.treeSearch(17,2);
        System.out.println(found.getValue()+found.toString() + " "+found.getParent().getValue()+found.getParent().toString()+"\n\n\n");
        binaryTree.preTreeDelete(binaryTree.treeSearch(18,1));
        binaryTree.postorderPrint();
    }
}

