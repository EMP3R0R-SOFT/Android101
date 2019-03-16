package com.example.android101;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class _00_intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SystemClock.sleep(500);
        Intent intent = new Intent(this, Login_Page.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_2,R.anim.fade_out_2);
        finish();
    }
}
