package com.inql;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.BiFunction;

public class AL6_3 {

    private Surname[] surnames;
    private Surname[] table;
    private final Surname del = new Surname(0,"DEL");
    int arraySize;

    public AL6_3(String path) throws IOException {
        this.surnames = readFile(path);
    }

    public Surname[] getSurnames() {
        return surnames;
    }

    public Surname[] getTable() {
        return table;
    }

    public void setTable(Surname[] table) {
        this.table = table;
    }

    private Surname[] readFile(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        Surname[] A;
        String currentLine;
        int arraySize = 0;
        while((currentLine = bufferedReader.readLine()) != null){
            arraySize++;
        }
        A = new Surname[arraySize];
        fileInputStream.getChannel().position(0);
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        for(int i=0; i<arraySize; i++){
            currentLine = bufferedReader.readLine();
            Surname item = new Surname(Integer.parseInt(currentLine.split(" ")[0]),currentLine.split(" ")[1]);
            A[i] = item;
        }
        return A;
    }

    private void hashInsert(Surname surname, HashFunction hashFunction, boolean doTests){
        int i = 0;
        int j;
        long word=0;
        if (doTests) System.out.println(surname);
        do {
            word = Utilities.wordToLong(surname.getSurname(),111);
            j=hashFunction.hash(word, table.length,i);
            if(doTests)System.out.println("Wydruk kontrolny: \n\ti="+i+"\n\tdługość tablicy: "+table.length+"\n\twartość słowa jako long: "+word+"\n\tpo hashowaniu: "+j);
            if(table[j]==null || table[j].getSurname().equals("DEL")){
                table[j] = surname;
                if(doTests) System.out.println("Wartość została umieszona na miejscu: "+j);
                return;
            }
            if(doTests) System.out.println("Ponawiam hashowanie dla większego i...\n");
            i++;
        }while(i< table.length);
        System.out.println("Błąd - brak miejsca w tablicy");
    }

    private int hashSearch(String surname, HashFunction hashFunction){
        int i =0;
        int j =0;
        do {
            j=hashFunction.hash(Utilities.wordToLong(surname,111), table.length,i);
            if (table[j].getSurname().equals(surname))
                return j;
            i++;
        }while(table[j]!= null && i< table.length);
        return -1; //nie znaleziono wartości
    }

    private void hashDelete(String surname, HashFunction hashFunction){
        int j = hashSearch(surname, hashFunction);
        if(j>=0)
            table[j] = del;
        else
            System.out.println("Brak takiej wartości w tablicy - nic nie zostało usunięte");
    }

    //testy na małej tablicy

    public void doTest(){
        arraySize=23;
        setTable(new Surname[arraySize]);
        HashFunction hashFunction = (k, m, i) -> (int) ((k % m +i)%m);
        System.out.println(Utilities.repeat("-",40)+"\nAdresowanie otwarte liniowe\nFunkcja hashująca: ((k % m) + i)% m\n\nTest hashInsert...");
        for(int i =0; i<arraySize; i++){
            System.out.println(Utilities.repeat("*",40));
            hashInsert(surnames[i],hashFunction,true);
            System.out.println(Utilities.repeat(".",40)+"\nWydruk kontrolny tablicy:\n");
            for(int j=0; j<arraySize; j++){
                System.out.println("table["+j+"] = "+table[j]);
            }
        }


    }





}
