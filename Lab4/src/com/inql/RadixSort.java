package com.inql;

import java.util.Arrays;

public class RadixSort {

    public static String[] radixSort(String[] strings, int maxLength, boolean doPrint) {
        for(int i = maxLength - 1 ; i >= 0 ; i--)
        {
            strings=CountingSort.countingSort(strings,i);
            if(doPrint)
            {
                System.out.println("Wydruk kontrolny - i = "+i);
                System.out.println(Arrays.toString(strings));
            }

        }

        return strings;
    }

    public static String[] radixSortQS(String[] strings, int maxLength) {
        for(int i = maxLength - 1 ; i >= 0 ; i--)
            Quicksort.Quicksort(strings,i);
        return strings;
    }
}
