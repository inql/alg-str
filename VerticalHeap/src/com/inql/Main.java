package com.inql;

import com.inql.heapsort.HorizontalHeapPrinter;
import com.inql.heapsort.VerticalHeapPrinter;
import com.inql.priorityq.Node;
import com.inql.priorityq.PriorityQueue;

public class Main {

    public static void main(String[] args) {
//        priorityQueueTest();
        horizontalHeapPrint();

    }

    public static void priorityQueueTest(){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i =0; i<10; i++){
            priorityQueue.insert(new Node<>(i,(int)Math.pow(-1,i)*i));
        }
        priorityQueue.insert(new Node<>(200));
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.remove());
        System.out.println(priorityQueue);
        priorityQueue.setPriority(2,200);
        System.out.println(priorityQueue);
        priorityQueue.remove();
        System.out.println(priorityQueue);
    }

    public static void verticalHeapPrint(){
        int[] A = {10,11,12,13,14,15,16,17,18,19,20};
        int[] B = {1,2,3,4,5,6,7,8,9};

        VerticalHeapPrinter verticalHeapPrinter = new VerticalHeapPrinter(A);
        System.out.println(verticalHeapPrinter.printHeapVertically());
        verticalHeapPrinter.setA(B);
        System.out.println(verticalHeapPrinter.printHeapVertically());
    }

    public static void horizontalHeapPrint(){
        int[] A = {1,2,3,4,5,6,7,9,10,11,12,13,14,15,16,17,18,19,20};
        HorizontalHeapPrinter horizontalHeapPrinter = new HorizontalHeapPrinter(A);
        System.out.println(horizontalHeapPrinter.getValueAsString(0));
    }
}
