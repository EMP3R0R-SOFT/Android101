package com.example.android101._0_HomePage;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android101.R;

public class _00_intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemClock.sleep(500);
        Intent intent = new Intent(this, _0_HomePage_Theme.class);
        startActivity(intent);
        //overridePendingTransition(R.anim.slide_in_right_500,R.anim.slide_out_left_500);


        overridePendingTransition(R.anim.fade_in_500, R.anim.no_anim_500);
        finish();

    }




}
