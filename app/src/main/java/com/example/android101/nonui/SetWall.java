package com.example.android101.nonui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.example.android101.R;

import static android.content.Context.MODE_PRIVATE;

public class SetWall {


    public static void set (Context context){

        View l1 =  ((Activity) context).findViewById(R.id.listlayout);
        if (getWalpaperNum(context).equals("Wallpaper 1")){
            l1.setBackgroundResource(R.drawable.w1);
        }
        if (getWalpaperNum(context).equals("Wallpaper 2")){
            l1.setBackgroundResource(R.drawable.w2);
        }

    }

    public static String getWalpaperNum(Context context){

        SharedPreferences radio_pref = context.getSharedPreferences("datastore1",MODE_PRIVATE);
        return radio_pref.getString("wallpaperNum","Wallpaper 1");
    }
}
