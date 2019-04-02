package com.example.android101;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class _00_intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemClock.sleep(1000);
        Intent intent = new Intent(this, _0_HomePage.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right_500,R.anim.slide_out_left_500);
        finish();

    }


}
