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

        String x = getWalpaperNum(context);
        switch (x){
            case "Wallpaper 1" :
                l1.setBackgroundResource(R.drawable.w1);
                break;

            case "Wallpaper 2" :
                l1.setBackgroundResource(R.drawable.w2);
                break;

            case "Wallpaper 3" :
                l1.setBackgroundResource(R.drawable.wall_blur_1);
                break;

            case "Wallpaper 4" :
                l1.setBackgroundResource(R.drawable.wall_blur_3);
                break;

            case "Wallpaper 5" :
                l1.setBackgroundResource(R.drawable.wall_4);
                break;

            case "Wallpaper 6" :
                l1.setBackgroundResource(R.drawable.wall_5);
                break;

            case "Wallpaper 7" :
                l1.setBackgroundResource(R.drawable.wall_6);
                break;
        }


    }

    public static String getWalpaperNum(Context context){

        SharedPreferences radio_pref = context.getSharedPreferences("datastore1",MODE_PRIVATE);
        return radio_pref.getString("wallpaperNum","Wallpaper 5");
    }
}
