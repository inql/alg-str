package com.inql;

public class AL4_3 {

    private int min;
    private int max;
    private int[] toSort;

    public AL4_3(int min, int max, int[] toSort) {
        this.min = min;
        this.max = max;
        this.toSort = toSort;
    }

    public void run(){
        int[] X = CountingSort.countingSortCounters(toSort,12);
        System.out.println("min: " + min + ", max: " + max + ", ilość wyrazów: " + Ex4_3.exercise(X,min,max));
    }
}
