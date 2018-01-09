package com.inql;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        al6_2();
        al6_3();
    }

    public static void al6_2() {
        AL6_2 al6_2 = new AL6_2("/home/inql/IdeaProjects/alg-str/Lab6/resources/3700.txt", 111);
        System.out.println("Testy korzystne:");
        al6_2.doTest(1039);
        al6_2.doTest(1187);
        al6_2.doTest(1319);
        al6_2.doTest(1471);
        al6_2.doTest(1699);
        System.out.println("\n\nTesty niekorzystne:");
        al6_2.doTest(1000);
        al6_2.doTest(1100);
        al6_2.doTest(1300);
        al6_2.doTest(1400);
        al6_2.doTest(1600);


    }

    public static void al6_3() throws IOException {
        AL6_3 al6_3 = new AL6_3("/home/inql/IdeaProjects/alg-str/Lab6/resources/nazwiskaASCII.txt");
        al6_3.run();
    }
}
