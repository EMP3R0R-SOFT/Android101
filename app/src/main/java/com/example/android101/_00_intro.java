package com.example.android101;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;

public class _00_intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Hide both the navigation bar and the status bar.
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);



//        SystemClock.sleep(1000);
//        Intent intent = new Intent(this, Login_Page.class);
//        startActivity(intent);
//        overridePendingTransition(0,R.anim.fade_out_500);
//        finish();


        SystemClock.sleep(1);
        Intent intent = new Intent(this, _00_intro_2.class);
        startActivity(intent);
        overridePendingTransition(0,0);
        finish();




    }
}
