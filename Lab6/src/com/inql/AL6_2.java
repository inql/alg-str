package com.inql;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class AL6_2 {

    private String[] words;
    private int[] T;
    private int constant;

    public AL6_2(String path, int constant) {
        try {
            this.words = readFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.constant = constant;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }

    public String[] readFile(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String[] A;
        String currentLine;
        int arraySize = 0;
        while ((currentLine = bufferedReader.readLine()) != null) {
            arraySize++;
        }
        A = new String[arraySize];
        fileInputStream.getChannel().position(0);
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        for (int i = 0; i < arraySize; i++) {
            A[i] = bufferedReader.readLine();
        }
        return A;
    }


    public void doTest(int arraySize) {
        T = new int[arraySize];
        for (int i = 0; i < 2 * arraySize; i++) {
            int j = hash(Utilities.wordToLong(words[i], constant), arraySize);
            T[j]++;
        }
        int zero_count = 0;
        int max = 0;
        double avgNonZeroes = 0;
        for (int value : T) {
            if (value == 0)
                zero_count++;
            else {
                if (value > max)
                    max = value;
                avgNonZeroes += value;
            }
        }
        avgNonZeroes /= (arraySize - zero_count);
        System.out.println(Utilities.repeat("-", 20));
        System.out.println("Test: Rozmiar tablicy: " + arraySize);
        System.out.println("Ilość wstawionych kluczy: " + 2 * arraySize);
        System.out.println("Ilość zerowych pozycji w tablicy T: " + zero_count);
        System.out.println("Maksymalna wartość w T: " + max);
        System.out.println("Średnia wartość pozycji niezerowych: " + avgNonZeroes);
    }

    public int hash(long k, int m) {
        long result = k % m;
        return (int) result;
    }

}
