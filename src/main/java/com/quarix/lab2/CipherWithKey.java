package com.quarix.lab2;

public class CipherWithKey {
    public static String encrypt(String str, String key) {
        char[] chars = str.toCharArray();
        int k;
        for(int ii=0; ii<str.length()/key.length();ii++){
            k=0;
            for (int i = ii*key.length(); i < (ii+1)*key.length(); i++) {
                chars[i] = str.charAt(ii*key.length() + Character.getNumericValue(key.charAt(k++))-1);
            }
        }
        return String.valueOf(chars);
    }

    public static String decrypt(String str, String key) {
        char[] chars = str.toCharArray();
        int k;
        for(int ii=0; ii<str.length()/key.length();ii++){
            k=0;
            for (int i = ii*key.length(); i < (ii+1)*key.length(); i++) {
                chars[ii*key.length() + Character.getNumericValue(key.charAt(k++))-1] = str.charAt(i);
            }
        }
        return String.valueOf(chars);
    }
}
