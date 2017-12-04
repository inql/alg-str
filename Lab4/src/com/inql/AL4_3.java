package com.inql;

public class AL4_3 {

    private int min;
    private int max;
    private int[] toSort;
    private int range;

    public AL4_3(int min, int max, int[] toSort) {
        this.min = min;
        this.max = max;
        this.toSort = toSort;
        this.range = getHighestValue(this.toSort);
    }

    public void run(){
        int[] X = countingSortWithCountersOnly(toSort,range);
        System.out.println("min: " + min + ", max: " + max + ", ilość wyrazów: " + getNumbers(X,min,max) + ", range: "+range);
    }

    public int getNumbers(int[] counters, int min, int max) {
        if(min<1)
            return counters[max];
        else
            return counters[max]-counters[min-1];
        //zlozonosc czasowa O(1)
    }

    public int[] countingSortWithCountersOnly(int[] array, int range) {
        int[] counters = new int[range + 1];

        for (int i = 0; i < array.length; i++) //n
            counters[array[i]]++;
        for (int i = 1; i < range; i++) //k
            counters[i] += counters[i - 1];
        //zlozonosc czasowa O(n+k)
        return counters;
    }

    public int getHighestValue(int[] array){
        int highest = array[0];
        for(int value : array){
            if(highest<value)
                highest=value;
        }
        return highest;
    }
}
