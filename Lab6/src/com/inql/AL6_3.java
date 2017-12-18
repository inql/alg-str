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
        long word;
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

    private int hashSearch(String surname, HashFunction hashFunction, boolean doTests){
        int i =10;
        int j =0;
        long word = Utilities.wordToLong(surname,111);
        if(doTests) System.out.println("Szukanie nazwiska: "+surname);
        do {
            j=hashFunction.hash(word, table.length,i);
            if(doTests)System.out.println("Wydruk kontrolny: \n\ti="+i+"\n\tdługość tablicy: "+table.length+"\n\twartość słowa jako long: "+word+"\n\tpo hashowaniu: "+j);
            try{
                if (table[j].getSurname().equals(surname)){
                    if(doTests)System.out.println("Znaleziono wartość na pozycji: "+j);
                    return j;
                }
            }catch(NullPointerException e){ }

            if(doTests) System.out.println(surname+" != "+table[j]+" szukam dalej...");
            i++;
        }while(table[j]!= null && i< table.length);
        if(doTests) System.out.println("Nie znaleziono takiej wartości.");
        return -1; //nie znaleziono wartości
    }

    private void hashDelete(String surname, HashFunction hashFunction, boolean doTests){
        if(doTests) System.out.println("Szukanie elementu...");
        int j = hashSearch(surname, hashFunction, doTests);
        if(j>=0){
            if(doTests) System.out.println("Wartość została usunięta z indeksu: "+j);
            table[j] = del;
        }

        else
            System.out.println("Brak takiej wartości w tablicy - nic nie zostało usunięte");
    }

    //testy na małej tablicy

    public void run(){
        arraySize=29;
        doTest(arraySize,(k, m, i) -> (int) ((k % m + i)%m),"\nAdresowanie otwarte liniowe\nFunkcja hashująca: (k, m, i) ->  ((k % m +i)%m)");
        doTest(arraySize,(k, m, i) -> (int) ((k % m + (i*i))%m),"\nAdresowanie otwarte kwadratowe\nFunkcja hashująca: (k, m, i) ->  ((k % m +i^2)%m)");
        doTest(arraySize,(k, m, i) -> (int) ((k % m + i*(1+(k%(m-2))))%m),"\nAdresowanie otwarte kwadratowe\nFunkcja hashująca: (k, m, i) ->  ((k % m + i*(1+(k%(m-2))))%m)");

    }

    public void doTest(int arraySize, HashFunction hashFunction,String message){
        setTable(new Surname[arraySize]);
        System.out.println(Utilities.repeat("-",40)+message+"\n\nTest hashInsert...");
        for(int i =0; i<arraySize-6; i++){
            System.out.println(Utilities.repeat("*",40));
            hashInsert(surnames[i],hashFunction,true);
            System.out.println(Utilities.repeat(".",40)+"\nWydruk kontrolny tablicy:\n");
            for(int j=0; j<arraySize; j++){
                System.out.println("table["+j+"] = "+table[j]);
            }

        }
        System.out.println(Utilities.repeat("-",40)+"\n\nTest hashSearch...");
        System.out.println(Utilities.repeat("*",40));
        hashSearch("Kwiatkowski",hashFunction,true);
        //szukanie nieistniejącego elementu:
        System.out.println(Utilities.repeat("*",40));
        hashSearch("Bińkuś",hashFunction,true);
        System.out.println("\n"+Utilities.repeat("-",40)+"\n\nTest hashDelete...");
        hashDelete("Nowak",hashFunction,true);
        for(int j=0; j<arraySize; j++){
            System.out.println("table["+j+"] = "+table[j]);
        }
        hashDelete("Bińkuś",hashFunction,true);
    }





}
