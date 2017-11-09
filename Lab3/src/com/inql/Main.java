package com.inql;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static int[] readFile(String path) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String currentLine;
        int arraySize = 0;
        while((currentLine = bufferedReader.readLine()) != null){
            arraySize++;
        }
        int[] A = new int[arraySize];
        fileInputStream.getChannel().position(0);
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        for(int i=0; i<arraySize; i++){
            A[i] = Integer.parseInt(bufferedReader.readLine());
        }

        return A;
    }

    public static void writeFile(String name, int[] A) throws IOException{
        File file = new File("files/"+name);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for(int i =0; i<A.length; i++){
            writer.write(Integer.toString(A[i])+"\n");
        }
        writer.flush();
        writer.close();

    }

    public static int partition(int[] A, int p, int r){
        int x = A[r];
        int i = p-1;
        for(int j=p; j<=r; j++){
            if(A[j] <= x){
                i++;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        if(i<r) return i;
        else return i-1;
    }

    public static void quicksort(int[] A,int p, int r){
        if (p<r){
            int q = partition(A,p,r);
            quicksort(A,p,q);
            quicksort(A,q+1,r);
        }
    }

    public static int[] generateRandomTable(int n){
        int[] A = new int[n];
        Random rand = new Random();
        for(int i =0; i<n; i++){
            A[i] = rand.nextInt();
        }
        return A;
    }

    public static int[] generateAscTable(int n){
        int[] A = new int[n];
        for(int i =0; i<n; i++){
            A[i] = i;
        }
        return A;
    }

    public static int[] generateDescTable(int n){
        int[] A = new int[n];
        for(int i =0; i<n; i++){
            A[i] = n-i;
        }
        return A;
    }

    public static void main(String[] args) throws IOException {

        //dla pierwszej części zadania


        //int[] A = readFile("files/input.txt");
        //quicksort(A,0,A.length-1);
        //writeFile("output.txt",A);

        //dla drugiej części zadania

        int n = 100;
        System.out.println("rozmiar tablicy N | (średni) czas - dane losowe |         czas - dane rosnące |        czas - dane malejące");
        for(n = 100; n<10000; n*=3){

            System.out.printf("%17d   ",n);
            int[] A = generateRandomTable(n);
            int[] B = generateAscTable(n);
            int[] C = generateDescTable(n);

            List<int[]> list = new ArrayList<>();
            list.add(A);
            list.add(B);
            list.add(C);

            for(int[] array: list){
                long start = System.currentTimeMillis();
                quicksort(array,0,array.length-1);
                long stop = System.currentTimeMillis();
                System.out.printf("%27d   ",(stop-start));
            }
            System.out.println();
        }
    }
}
