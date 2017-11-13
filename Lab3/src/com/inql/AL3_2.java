package com.inql;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AL3_2 {

    private String path;

    public  AL3_2(){

    }

    public AL3_2(String path) {
        this.path = path;
    }

    public void runFirst(){
        try{
            int[] A = readFile(path);
            quicksort(A,0,A.length-1);
            writeFile("output.txt",A);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void runSecond() {
        int n = 100;
        System.out.println("AL3_2");
        System.out.println("rozmiar tablicy N | (średni) czas - dane losowe |           czas - dane rosnące |          czas - dane malejące |");
        for (n = 100; n < 10000; n *= 2) {

            System.out.printf("%17d | ", n);
            int[] A = generateRandomTable(n);
            int[] B = generateAscTable(n);
            int[] C = generateDescTable(n);

            List<int[]> list = new ArrayList<>();
            list.add(A);
            list.add(B);
            list.add(C);

            for (int[] array : list) {
                DecimalFormat decimalFormat = new DecimalFormat("0.00000000000000000000000000");
                long start = System.nanoTime();
                quicksort(array, 0, array.length - 1);
                long stop = System.nanoTime();
                BigDecimal bigDecimal = new BigDecimal((stop-start)/10e9);
                System.out.print(decimalFormat.format(bigDecimal)+"|   ");
            }
            System.out.println();
        }
    }

    public int[] readFile(String path) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        int[] A;
        String currentLine;
        int arraySize = 0;
            while((currentLine = bufferedReader.readLine()) != null){
                arraySize++;
            }
            A = new int[arraySize];
            fileInputStream.getChannel().position(0);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            for(int i=0; i<arraySize; i++){
                A[i] = Integer.parseInt(bufferedReader.readLine());
            }
        return A;
    }

    public void writeFile(String name, int[] A) throws IOException{
        File file = new File("files/"+name);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for(int i =0; i<A.length; i++){
            writer.write(Integer.toString(A[i])+"\n");
        }
        writer.flush();
        writer.close();

    }

    public int partition(int[] A, int p, int r){
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

    public void quicksort(int[] A,int p, int r){
        if (p<r){
            int q = partition(A,p,r);
            quicksort(A,p,q);
            quicksort(A,q+1,r);
        }
    }

    public int[] generateRandomTable(int n){
        int[] A = new int[n];
        Random rand = new Random();
        for(int i =0; i<n; i++){
            A[i] = rand.nextInt();
        }
        return A;
    }

    public int[] generateAscTable(int n){
        int[] A = new int[n];
        for(int i =0; i<n; i++){
            A[i] = i;
        }
        return A;
    }

    public int[] generateDescTable(int n){
        int[] A = new int[n];
        for(int i =0; i<n; i++){
            A[i] = n-i;
        }
        return A;
    }

}
