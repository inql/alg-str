package com.inql;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AL3_3 {

    private AL3_2 al3_2;

    public AL3_3() {
        this.al3_2 = new AL3_2();
    }

    public int RandomizedPartition(int[] A, int p, int r){
        int k = ThreadLocalRandom.current().nextInt(p,r+1);
        int temp = A[k];
        A[k] = A[r];
        A[r] = temp;
        return al3_2.partition(A,p,r);
    }

    public void RandomizedQuickSort(int[] A, int p, int r){
        if(p<r){
            int q = RandomizedPartition(A,p,r);
            RandomizedQuickSort(A,p,q);
            RandomizedQuickSort(A,q+1,r);
        }
    }

    public int MedianPartition(int []A, int p, int r){
        int toSwap = 0;
        if(A[p]<=A[p+((r-p)/2)] && A[p+((r-p)/2)]<= A[r])
            toSwap = p+((r-p)/2);
        else if(A[p+((r-p)/2)]<=A[p] && A[p]<=A[r])
            toSwap = p;
        else
            toSwap = r;
        int temp = A[toSwap];
        A[toSwap] = A[r];
        A[r] = temp;
        return al3_2.partition(A,p,r);
    }

    public void MedianQuickSort(int []A, int p, int r){
        if(p<r){
            int q = MedianPartition(A,p,r);
            MedianQuickSort(A,p,q);
            MedianQuickSort(A,q+1,r);
        }
    }

    public void run(){
        System.out.println("AL3_3 - alg 1 (standard) alg2 (random) alg3(mediana)");
        System.out.println("DANE LOSOWE\nrozmiar tablicy N |                  algorytm 1 |                    algorytm 2 |                    algorytm 3 |");
        for (int n = 100; n < 20000; n *= 2) {

            System.out.printf("%17d | ", n);
            int[] A = al3_2.generateRandomTable(n);
            int[] B = A.clone();
            int[] C = A.clone();
                DecimalFormat decimalFormat = new DecimalFormat("0.00000000000000000000000000");
                long start = System.nanoTime();
                al3_2.quicksort(A, 0, A.length - 1);
                long stop = System.nanoTime();
                BigDecimal bigDecimal = new BigDecimal((stop-start)/10e9);

                System.out.print(decimalFormat.format(bigDecimal)+"|   ");
                start = System.nanoTime();
                RandomizedQuickSort(B, 0, A.length - 1);
                stop = System.nanoTime();
                bigDecimal = new BigDecimal((stop-start)/10e9);
                System.out.print(decimalFormat.format(bigDecimal)+"|   ");

                start = System.nanoTime();
                MedianQuickSort(C, 0, A.length - 1);
                stop = System.nanoTime();
                bigDecimal = new BigDecimal((stop-start)/10e9);
                System.out.print(decimalFormat.format(bigDecimal)+"|   ");
            System.out.println();
        }
        System.out.println("DANE NIEKORZYSTNE\nrozmiar tablicy N |                  algorytm 1 |                    algorytm 2 |                    algorytm 3 |");
        for (int n = 100; n < 20000; n *= 2) {

            System.out.printf("%17d | ", n);
            int[] A = al3_2.generateAscTable(n);
            int[] B;
            int[] C = A.clone();
            DecimalFormat decimalFormat = new DecimalFormat("0.00000000000000000000000000");
            long start = System.nanoTime();
            al3_2.quicksort(A, 0, A.length - 1);
            long stop = System.nanoTime();
            BigDecimal bigDecimal = new BigDecimal((stop-start)/10e9);

            System.out.print(decimalFormat.format(bigDecimal)+"|   ");
            start = System.nanoTime();
            for(int i =0; i<100; i++){
                B = A.clone();
                RandomizedQuickSort(B, 0, A.length - 1);
            }
            stop = System.nanoTime();
            bigDecimal = new BigDecimal(((stop-start)/100)/10e9);
            System.out.print(decimalFormat.format(bigDecimal)+"|   ");

            start = System.nanoTime();
            MedianQuickSort(C, 0, A.length - 1);
            stop = System.nanoTime();
            bigDecimal = new BigDecimal((stop-start)/10e9);
            System.out.print(decimalFormat.format(bigDecimal)+"|   ");
            System.out.println();
        }
    }
}
