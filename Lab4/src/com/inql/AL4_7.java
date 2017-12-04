package com.inql;

import java.io.*;

public class AL4_7 {

    private String path;
    private String[] input;

    public AL4_7(String path) throws IOException {
        this.path = path;
        this.input = readFile(this.path);
    }

    public String[] readFile(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String[] A;
        String currentLine;
        int arraySize = 0;
        while((currentLine = bufferedReader.readLine()) != null){
            arraySize++;
        }
        A = new String[arraySize];
        fileInputStream.getChannel().position(0);
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        for(int i=0; i<arraySize; i++){
            A[i] = bufferedReader.readLine().replaceAll("[^A-Za-z]","");
        }
        return A;
    }

    public void writeFile(String name, String[] A) throws IOException{
        File file = new File("files/"+name);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for(int i =0; i<A.length; i++){
            writer.write(A[i]+"\n");
        }
        writer.flush();
        writer.close();

    }

    public void run() throws IOException {
        long start, stop;
        double time;


        AL4_6 al4_6 = new AL4_6(input);
        start = System.nanoTime();
        al4_6.doSort();
        stop = System.nanoTime();
        String[] A = al4_6.getSorted();
        time = (stop-start)/(10e10);
        System.out.format("Czas dla counting sort: %.10f\n",time);
        start = System.nanoTime();
        String[] B = RadixSort.radixSortQS(input,al4_6.getMax());
        stop = System.nanoTime();
        time = (stop-start)/(10e10);
        System.out.format("Czas dla quick sort:    %.10f\n",time);
        writeFile("radixsorted",A);
        writeFile("quicksorted",B);

    }
}
