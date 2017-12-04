package com.inql;

import java.util.Arrays;

public class AL4_6 {

    private String[] preSorted;
    private String[] sorted;
    private int letter = 0;
    private int max;
    private final boolean doPrint = false;

    public AL4_6(String[] preSorted) {
        this.preSorted = preSorted;
        for(int i=0 ; i<this.preSorted.length ; i++)
            if(this.preSorted[i].length()>this.letter)
                this.letter=this.preSorted[i].length();
        this.max = this.letter;
    }

    @Override
    public String toString() {
        return "AL4_6{" +
                "\npreSorted=" + Arrays.toString(preSorted) +
                "\n sorted=" + Arrays.toString(sorted) +
                "\n}";
    }

    public void doSort(){
        this.sorted = RadixSort.radixSort(this.preSorted,this.letter,this.doPrint);
    }

    public String[] getSorted() {
        return sorted;
    }

    public int getMax() {
        return max;
    }
}
