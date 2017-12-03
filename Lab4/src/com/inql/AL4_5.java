package com.inql;

import java.util.Arrays;

public class AL4_5 {

    private String[] preSorted;
    private String[] sorted;
    private int letter = 7;
    private boolean doPrint = true;

    public AL4_5(String[] preSorted) {
        this.preSorted = preSorted;
        this.sorted = RadixSort.radixSortNLetters(preSorted,this.letter,doPrint);
    }

    @Override
    public String toString() {
        return "AL4_5{" +
                "\npreSorted=" + Arrays.toString(preSorted) +
                "\nsorted=" + Arrays.toString(sorted) +
                "\nletter=" + letter +
                "\n}";
    }
}
