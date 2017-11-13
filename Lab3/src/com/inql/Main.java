package com.inql;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        //implementacja zadania AL3_2
        AL3_2 al3_2 = new AL3_2("files/input.txt");
        al3_2.runFirst();
        al3_2.runSecond();

        //implementacja zadania AL3_3
        AL3_3 al3_3 = new AL3_3();
        al3_3.run();

        //implementacja zadania AL3_4
        AL3_4 al3_4 = new AL3_4(4);
        al3_4.run();

        //implementacja zadania AL3_5
        AL3_5 al3_5 = new AL3_5(10);
        al3_5.run();

    }
}
