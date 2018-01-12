package com.inql.heapsort;

public class HorizontalHeapPrinter {

    private int[] A;

    public HorizontalHeapPrinter(int[] a) {
        A = a;
    }

    public StringBuilder build(StringBuilder lineBuilder, boolean isTail, StringBuilder sb, int index, int counter) {
        int rightsonIndex = 2*index+1;
        int leftsonIndex = 2*index+2;
        StringBuilder newLineBuilder;
        if(rightsonIndex<A.length) {
            newLineBuilder = new StringBuilder();
            newLineBuilder.append(lineBuilder);
            if(counter==0 || !isTail)
                newLineBuilder.append("    ");
            else
                newLineBuilder.append("|   ");
            build(newLineBuilder, false, sb,rightsonIndex,counter++);
        }
        if(index == 0)
            sb.append(lineBuilder).append("    "+A[index]).append("\n");
        else
            sb.append(lineBuilder).append("+-- ").append(A[index]).append("\n");
        if(leftsonIndex<A.length) {
            newLineBuilder = new StringBuilder();
            newLineBuilder.append(lineBuilder);
            if(counter==0 || isTail)
                newLineBuilder.append("    ");
            else
                newLineBuilder.append("|   ");
            build(newLineBuilder, true, sb,leftsonIndex,counter++);
        }
        return sb;
    }

    public String getValueAsString(int index) {
        return this.build(new StringBuilder(), true, new StringBuilder(),index,0).toString();
    }
}
