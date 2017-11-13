package com.inql;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class AL3_4 {

    private int c;
    private AL3_2 al3_2;

    public AL3_4(int c) {
        this.c = c;
        this.al3_2 = new AL3_2();
    }

    public void quicksort(int[] A, int p, int r){
        if (p<r){
            if(!(r-p+1<c)){
                int q = al3_2.partition(A,p,r);
                quicksort(A,p,q);
                quicksort(A,q+1,r);
            }
        }
    }

    public void insertionsort(int A[]) {
        int i,j,v;

        for (i=1;i<A.length;i++) {
            j=i;
            v=A[i];
            while ((j>0) && (A[j-1]>v)) {
                A[j]=A[j-1];
                j--;
            }
            A[j]=v;
        }
    }

    public void run(){
        System.out.println("AL3_4 - alg 1 (standard) alg 2 (uszkodzony standard + przez wstawianie na koniec)");
        System.out.println("DANE LOSOWE\nrozmiar tablicy N |                  algorytm 1 |                    algorytm 2 |");
        for(int n =100; n<20000; n*=2){
            System.out.printf("%17d | ", n);
            int[] A = al3_2.generateRandomTable(n);
            int[] B = A.clone();
            DecimalFormat decimalFormat = new DecimalFormat("0.00000000000000000000000000");
            long start = System.nanoTime();
            al3_2.quicksort(A, 0, A.length - 1);
            long stop = System.nanoTime();
            BigDecimal bigDecimal = new BigDecimal((stop-start)/10e9);

            System.out.print(decimalFormat.format(bigDecimal)+"|   ");
            start = System.nanoTime();
            quicksort(B, 0, A.length - 1);
            insertionsort(B);
            stop = System.nanoTime();
            bigDecimal = new BigDecimal((stop-start)/10e9);
            System.out.print(decimalFormat.format(bigDecimal)+"|   ");
            System.out.println();

        }
        System.out.println("DANE NIEKORZYSTNE\nrozmiar tablicy N |                  algorytm 1 |                    algorytm 2 |");
        for(int n =100; n<20000; n*=2){
            System.out.printf("%17d | ", n);
            int[] A = al3_2.generateDescTable(n);
            int[] B = A.clone();
            DecimalFormat decimalFormat = new DecimalFormat("0.00000000000000000000000000");
            long start = System.nanoTime();
            al3_2.quicksort(A, 0, A.length - 1);
            long stop = System.nanoTime();
            BigDecimal bigDecimal = new BigDecimal((stop-start)/10e9);

            System.out.print(decimalFormat.format(bigDecimal)+"|   ");
            start = System.nanoTime();
            quicksort(B, 0, A.length - 1);
            insertionsort(B);
            stop = System.nanoTime();
            bigDecimal = new BigDecimal((stop-start)/10e9);
            System.out.print(decimalFormat.format(bigDecimal)+"|   ");
            System.out.println();

        }
    }



}
