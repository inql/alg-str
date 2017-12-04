package com.inql;

public class Quicksort {
    public static char getMedian(int a, int b, int c)
    {
        return (char)Math.max(Math.min(a,b), Math.min(Math.max(a,b),c));
    }

    public static int medianPartition(String[] array, int p, int r, int letter)
    {
        char x = getMedian(array[p].length()>letter ? array[p].charAt(letter) : (char)0,array[r].length()>letter ? array[r].charAt(letter) : (char)0, array[(p+r)/2].length()>letter ? array[(p+r)/2].charAt(letter):(char)0); //element wyznaczający podział
        int j,i = p-1;
        for(j=p;j<=r;j++)
            if((array[j].length()>letter ? array[j].charAt(letter):0)<=x)
            {
                i++;
                String temp = array[i];
                array[i]=array[j];
                array[j]=temp;
            }
        if(i<r) return i;
        else return i-1;
    }
    public static void medianQuicksort(String[] array, int p, int r, int letter)
    {
        if (p<r)
        {
            int q = medianPartition(array,p,r,letter);
            medianQuicksort(array,p,q,letter);
            medianQuicksort(array,q+1,r,letter);
        }
    }

    public static void Quicksort(String[] array, int letter) {
        medianQuicksort(array,0,array.length-1, letter);
    }
}
