package com.example.android101.nonui;

import android.content.Context;

public class T {

    public static void show (Context context , String text){

        android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT).show();

    }
}
