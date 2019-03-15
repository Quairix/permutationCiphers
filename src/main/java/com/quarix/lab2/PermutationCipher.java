package com.quarix.lab2;

public class PermutationCipher {
    public static String encrypt(String str) {
        char[] chars = str.toCharArray();
        int k = 0;
        for (int i = 0; i < chars.length; i += 2) {
            chars[k++] = chars[i];
        }
        for (int i = 1; i < str.length(); i += 2) {
            chars[k++] = str.charAt(i);
        }
        return String.valueOf(chars);
    }

    public static String decrypt(String str) {
        char[] chars = str.toCharArray();
        int k = 0;
        for (int i = 0; i < str.length(); i += 2) {
            chars[i] = str.charAt(k++);
        }
        for (int i = 1; i < str.length(); i += 2) {
            chars[i] = str.charAt(k++);
        }
        return String.valueOf(chars);
    }
}
