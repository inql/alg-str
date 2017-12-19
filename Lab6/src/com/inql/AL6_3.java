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
    int numberOfAttempts;

    public AL6_3(String path) throws IOException {
        this.surnames = readFile(path);
        this.numberOfAttempts=0;
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
                numberOfAttempts+=(i+1);
                if(doTests) System.out.println("Wartość została umieszona na miejscu: "+j);
                return;
            }
            if(doTests) System.out.println("Ponawiam hashowanie dla większego i...\n");
            i++;
        }while(i< table.length);
        System.out.println("Błąd - brak miejsca w tablicy");
    }

    private int hashSearch(String surname, HashFunction hashFunction, boolean doTests){
        int i =0;
        int j =0;
        long word = Utilities.wordToLong(surname,111);
        if(doTests) System.out.println("Szukanie nazwiska: "+surname);
        do {
            j=hashFunction.hash(word, table.length,i);
            if(doTests)System.out.println("Wydruk kontrolny: \n\ti="+i+"\n\tdługość tablicy: "+table.length+"\n\twartość słowa jako long: "+word+"\n\tpo hashowaniu: "+j);
            try{
                if (table[j].getSurname().equals(surname)){
                    if(doTests)System.out.println("Znaleziono wartość na pozycji: "+j);
                    numberOfAttempts+=(i+1);
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
        arraySize=2039;
        //testowanie działania funkcji
//        doTest(arraySize,(k, m, i) -> (int) ((k % m + i)%m),"\nAdresowanie otwarte liniowe\nFunkcja hashująca: (k, m, i) ->  ((k % m +i)%m)");
//        doTest(arraySize,(k, m, i) -> (int) ((k % m + (i*i))%m),"\nAdresowanie otwarte kwadratowe\nFunkcja hashująca: (k, m, i) ->  ((k % m +i^2)%m)");
//        doTest(arraySize,(k, m, i) -> (int) ((k % m + i*(1+(k%(m-2))))%m),"\nAdresowanie otwarte kwadratowe\nFunkcja hashująca: (k, m, i) ->  ((k % m + i*(1+(k%(m-2))))%m)");

        //pomiary
        arraySize=2039;
        doEfficiencyTest(arraySize,(k, m, i) -> (int) ((k % m + i)%m),"\nAdresowanie otwarte liniowe\nFunkcja hashująca: (k, m, i) ->  ((k % m +i)%m)");


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

    public void doEfficiencyTest(int arraySize, HashFunction hashFunction, String message){
        System.out.println(Utilities.repeat("-",40)+"\nTesty pomiarowe, arraySize: "+arraySize);
        setTable(new Surname[arraySize]);
        //wypełnienie tablicy do 50%
        insertTest(hashFunction,0.5);
        clearTable();
        //wypełnienie tablicy do 70%
        insertTest(hashFunction, 0.7);
        clearTable();
        insertTest(hashFunction, 0.9);
        clearTable();
        //pomiar szukania
        int i;
        for(i = 0; i<(int)(arraySize*0.8); i++){
            hashInsert(surnames[i],hashFunction,false);
        }
        int range = i-20;
        while(i>=range){
            i--;
            searchTest(hashFunction,i);
        }
        //pomiar usuwania
        clearTable();
        for(i = 0; i<(int)(arraySize*0.8); i++){
            hashInsert(surnames[i],hashFunction,false);
        }
        range = i/2;

        while(i>=range){
            i--;
            hashDelete(surnames[i].getSurname(),hashFunction,false);
        }
        int placesTaken = 0;
        for(Surname surname : table){
            if(surname!=null && surname != del){
                placesTaken++;
            }
//            System.out.println(surname);
        }
//        System.out.println(placesTaken);
        for(i=placesTaken; i<(int)(arraySize*0.8);i++){
            hashInsert(surnames[i+range],hashFunction,false);
        }
        int delCount = 0;
        for(Surname surname : table){
            if(surname==del)
                delCount++;
//            System.out.println(surname);
        }
        System.out.println("Ilość wartości DEL: "+delCount);



    }

    private void insertTest(HashFunction hashFunction, double perc){
        int numberOfWordsToInsert = (int)(arraySize * perc);
        for(int i =0; i<numberOfWordsToInsert; i++){
            hashInsert(surnames[i],hashFunction,false);
        }
        double avg = (double)numberOfAttempts/numberOfWordsToInsert;
        System.out.println("\nIlość słów do wstawienia: "+numberOfWordsToInsert+"\nIlość prób: "+numberOfAttempts+"\nŚrednia: "+avg);
        numberOfAttempts=0;
    }

    private void searchTest(HashFunction hashFunction, int index){
        System.out.println("Szukam..."+surnames[index]);
        hashSearch(surnames[index].getSurname(),hashFunction,false);
        System.out.println("Ilość prób: "+numberOfAttempts);
        numberOfAttempts=0;
    }

    private void clearTable(){
        for(int i = 0; i<table.length; i++){
            table[i] = null;
        }
    }



}
