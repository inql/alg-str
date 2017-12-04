package com.inql;

public class CountingSort {



    public static String[] countingSort(String[] strings, int letter) {
        int range = ('z' - ('a'-1)) + ('9' - ('0'-1)) + 1;
        int[] counters = new int[range];
        String[] result = new String[strings.length];


        for (int i = 0 ; i < strings.length ; i++)
            if(strings[i].length()>letter)
            {
                char c = Character.toLowerCase(strings[i].charAt(letter));
                if(c >= 'a' && c <= 'z')
                    counters[c - 'a' + 11]++;
                else if(c >= '0' && c <= '9')
                    counters[c - '0' + 1]++;
                else
                    counters[0]++; //pole 0 dla wszystkich innych znaków
            }

            else
                counters[0]++;

        for(int i = 1 ; i < range ; i++)
            counters[i] += counters[i-1];

        for(int i = strings.length - 1 ; i >= 0 ; i--) {
            if (strings[i].length() > letter){
                char c = Character.toLowerCase(strings[i].charAt(letter));
                if (c >= 'a' && c <= 'z') {
                    result[counters[c - 'a' + 11] - 1] = strings[i];
                    counters[c - 'a' + 11]--;
                } else if (c >= '0' && c <= '9') {
                    result[counters[c - '0' + 1] - 1] = strings[i];
                    counters[c - '0' + 1]--;
                } else {
                    result[counters[0] - 1] = strings[i];
                    counters[0]--;
                }
            }
            else {
                result[counters[0] - 1] = strings[i];
                counters[0]--;
            }
        }
        return result;
    }
}
