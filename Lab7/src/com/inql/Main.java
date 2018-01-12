package com.inql;

import com.inql.binarytree.BinaryTree;
import com.inql.binarytree.Node;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
	    AL7_3();
    }

    public static void AL7_3(){
        BinaryTree binaryTree = new BinaryTree();
        System.out.println(binaryTree.isEmpty());
        binaryTree.massInsert(new ArrayList<Node>(){{add(new Node(10));add(new Node(7));add(new Node(5));add(new Node(44));add(new Node(51));}});
        binaryTree.postorderPrint();
    }
}
