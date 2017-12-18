package com.inql;

public class Utilities {

    public static String repeat(String str, int times){
        return new String(new char[times]).replace("\0", str);
    }

    public static long wordToLong(String word, int constant){
        char[] chars = word.toCharArray();
        int h = 0;
        if (chars.length > 0) {
            for (int i = 0; i < chars.length; i++) {
                h = constant * h + chars[i];
            }
        }
        return Integer.toUnsignedLong(h);

    }

}