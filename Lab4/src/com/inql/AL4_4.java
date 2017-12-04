package com.inql;

import java.util.Arrays;

public class AL4_4 {

    private String[] preSorted;
    private String[] sorted;
    private int letter = 0;

    public AL4_4(String[] preSorted) {
        this.preSorted = preSorted;
        this.sorted = CountingSort.countingSort(preSorted,this.letter);
    }

    @Override
    public String toString() {
        return "AL4_4 {" +
                "\npreSorted=" + Arrays.toString(preSorted) +
                "\n sorted=" + Arrays.toString(sorted) +
                "\n letter=" + letter +
                "\n}";
    }
}
