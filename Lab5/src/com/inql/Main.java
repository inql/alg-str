package com.inql;

import com.inql.AL5_1.List1L;
import com.inql.AL5_3.List2L;

public class Main {

    public static void main(String[] args) {

        /*
        AL5.1, AL5.2
         */

        testAL5_1();

        /*
        AL5.3, AL5.4
         */

        testAL5_3();


    }

    public static void testAL5_1(){

        List1L list1L = new List1L("list1L:");
        System.out.println("Pusta lista:\n");
        list1L.printAll();
        for(int i =0; i<10; i++){
            list1L.insertAtEnd("Word "+i);
        }
        System.out.println("Lista po wypełnieniu danymi:\n");
        list1L.printAll();
        list1L.insert("Word 2");
        System.out.println("Lista po dodaniu słowa na początku\n");
        list1L.printAll();
        list1L.insertAtEnd("Jestem na koncu listy");
        System.out.println("Lista po dodaniu słowa na końcu\n");
        list1L.printAll();
        List1L copy = list1L.cpyWoRepetition();
        System.out.println("Lista po skopiowaniu bez powtorzen:\n");
        copy.printAll();
        list1L.remove("Word 1");
        System.out.println("Lista po usunięciu elementu:\n");
        list1L.printAll();
        List1L merged = list1L.merge(copy);
        System.out.println("Połączone listy: \n");
        merged.printAll();
    }

    public static void testAL5_3(){
        List2L list2L = new List2L("list2L");
        System.out.println("Pusta lista:\n");
        list2L.printAll();
        for(int i = 10; i>=0; i--){
            list2L.insert("Word "+i);
        }
        System.out.println("Lista po wypełnieniu danymi:\n");
        list2L.printAll();

        list2L.insert("Word 2");
        System.out.println("Lista po dodaniu słowa na początku\n");
        list2L.printAll();
        System.out.println("Lista po dodaniu słowa na początku(wydrukowana od końca\n");
        list2L.printAllFromLast();

        list2L.delete("Word 3");
        System.out.println("Lista po usunięciu elementu:\n");
        list2L.printAll();

        System.out.println("Prezentacja wyszukiwania:\n");
        System.out.println(list2L.search("Word 3")+", "+list2L.search("Word 4"));

        List2L copy = list2L.cpyWoRepetition();

        System.out.println("Lista po skopiowaniu bez powtorzen:\n");
        copy.printAll();


        List2L merged = list2L.merge(copy);

        System.out.println("Połączone listy: \n");

        merged.printAll();
    }
}
