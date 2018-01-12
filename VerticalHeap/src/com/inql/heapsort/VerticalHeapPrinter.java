package com.inql.heapsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VerticalHeapPrinter {

    private int[] A;
    StringBuilder result;
    private Short maxNumLength;

    public VerticalHeapPrinter(int[] a) {
        A = a;
        result = new StringBuilder();
    }

    public void setA(int[] a) {
        A = a;
    }

    private short getMaxLen(){
        int max = A[0];
        for(int i =1; i<A.length; i++){
            max = Math.max(max,A[i]);
        }
        return (short) Integer.toString(max).length();
    }

    public String printHeapVertically() {
        int maxLevel = log(2, A.length) + 1;
        maxNumLength = getMaxLen();
        return printHeapVertically(Collections.singletonList(0), 1, maxLevel).toString();
    }

    private StringBuilder printHeapVertically(List<Integer> toPrint, int level, int maxLevel) {

        if (toPrint.isEmpty())
            return result.append("");

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        result.append(repeat(" ", firstSpaces));

        List<Integer> nextToPrint = new ArrayList<>();
        for (int index : toPrint) {
            if (index != -1) {
                result.append(A[index]);
                int l = 2 * index + 1;
                int r = 2 * index + 2;
                if (l < A.length)
                    nextToPrint.add(l);
                if (r < A.length)
                    nextToPrint.add(r);
            }
            result.append(repeat(" ", betweenSpaces));
        }
        result.append("\n");

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < toPrint.size(); j++) {
                result.append(repeat(" ", firstSpaces - i));
                if (2 * toPrint.get(j) + 1 < A.length) {
                    result.append("/");
                } else {
                    result.append(" ");
                }
                result.append(repeat(" ", i + i - 1));
                if (2 * toPrint.get(j) + 2 < A.length) {
                    result.append("\\");
                } else {
                    result.append(" ");
                }
                result.append(repeat(" ", edgeLines + edgeLines - i));

            }
            result.append("\n");
        }
        printHeapVertically(nextToPrint, level + 1, maxLevel);
        return result;
    }

    private String repeat(String str, int times) {
        if (times > 0)
            return new String(new char[times]).replace("\0", str);
        else
            return "";
    }

    private int log(int base, int value) {
        return (int) (Math.log10(value) / Math.log10(base));
    }
}
