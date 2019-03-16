package com.example.android101;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.android101.nonui.SetWall;


public class _0_HomePage extends AppCompatActivity {
    @Override
    protected void onResume() {

        //SetWall.set(this);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._0_activity_home_page);


        //SetWall.set(this);


        View l1 = findViewById(R.id.linearlayout1);
        Animation anim_fade_in = AnimationUtils.loadAnimation(this,R.anim.fade_in_2);
        l1.startAnimation(anim_fade_in);



        Button button1= findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), _1_SharedPreferences_example.class));
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), _2_UseClass_example.class));
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), _3_UseClass2_example.class));
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), _4_ListView_example.class));
            }
        });

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), _5_Dialog_Alert_example.class));
            }
        });

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), _7_Background_Async_Task_example.class));
            }
        });

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), _8_Dynamic_Buttons_example.class));
            }
        });

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), _9_Pass_data_using_intent_1_example.class));
            }
        });

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setContentView(R.layout._1_sharedperferences_example);

                Intent myIntent = new Intent(Intent.ACTION_SENDTO);
                myIntent.setData(Uri.fromParts("mailto","asd@lol.ir", null));
                myIntent.putExtra(Intent.EXTRA_SUBJECT, "عنوان");
                myIntent.putExtra(Intent.EXTRA_TEXT, "متن ایمیل");
                startActivity(Intent.createChooser(myIntent, "انتخاب پیام رسان"));
            }
        });

        Button button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), _11_Simple_ListView.class));
            }
        });

        Button button11 = findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), _12_Play_Video.class));
            }
        });


    }





















}