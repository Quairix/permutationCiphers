package com.quarix.lab2;

public class CombiCipher {
    public static String encrypt(String str, String key) {
        char[] chars = str.toCharArray();
        int k = 0;
        int m = str.length() / key.length();
        int pos =0;
        for (int i = 0; i < key.length(); i++) {
            for(int j=0; j < m; j++){
                chars[pos++]=str.charAt((Character.getNumericValue(key.charAt(k)) - 1)+ key.length()*j);
            }
            k++;
        }
        return String.valueOf(chars);
    }

    public static String decrypt(String str, String key) {
        char[] chars = str.toCharArray();
        int k = 0;
        int m = str.length() / key.length();
        int pos =0;
        for (int i = 0; i < key.length(); i++) {
            for(int j=0; j < m; j++){
                chars[(Character.getNumericValue(key.charAt(k)) - 1)+ key.length()*j]=str.charAt(pos++);
            }
            k++;
        }
        return String.valueOf(chars);
    }
}
