package com.inql;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        zadanie4_3();
        zadanie4_4();
        zadanie4_5();
        zadanie4_6();
        zadanie4_7();

    }

    public static void zadanie4_3() {
        int[] array = {10 ,4 ,2 ,5, 7, 6, 1 ,5, 7, 8, 4, 12, 0, 2, 2, 2};
        AL4_3 al4_3 = new AL4_3(0,1,array);
        al4_3.run();

    }

    public static void  zadanie4_4() {
        String[] array = {"synowa", "mama", "brat", "wujek", "dziadek", "babcia", "syn", "bratowa", "ciocia", "dziecko", "stryjek", "szwagier", "tata"};
        AL4_4 al4_4 = new AL4_4(array);
        System.out.println(al4_4);
    }
    public static void  zadanie4_5() {
        String[] array = {
                "bedroom",
                "whisper",
                "command",
                "overall",
                "terrace",
                "prevent",
                "sulphur",
                "storage",
                "monarch",
                "species",
                "section",
                "variety",
                "venture",
                "biology",
                "calorie",
                "dribble",
                "endorse",
                "qualify",
                "dilemma",
                "retiree"
        };
        AL4_5 al4_5 = new AL4_5(array);
        System.out.println(al4_5);
    }
    public static void  zadanie4_6() {
        String[] array = {
                "alarm",
                "salon",
                "ratio",
                "shave",
                "cherry",
                "sheet",
                "zero",
                "regard",
                "cluster",
                "opponent",
                "mud",
                "eye",
                "engine",
                "make",
                "observer",
                "projection",
                "fleet",
                "appointment",
                "incredible",
                "gaffe"
        };
        AL4_6 al4_6 = new AL4_6(array);
        al4_6.doSort();
        System.out.println(al4_6);
    }
    public static void  zadanie4_7() throws IOException {
        AL4_7 al4_7 = new AL4_7("/home/inql/Dokumenty/Studia/alg-str/Lab4/files/nazwiskaASCII.txt");
        al4_7.run();
    }
}
