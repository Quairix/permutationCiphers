package com.quarix.lab2;

public class EditString {
    public static String Clear(String str){
        return str.replaceAll("[^абвгдеёжзийклмнопрстуфхцчшщъыьэюя_., АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЭЮЯ]", "");
    }
}
