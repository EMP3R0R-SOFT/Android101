package com.example.android101;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
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

        transparentStatus();
        transparentNavigation();
        getWindow().setNavigationBarColor(Color.parseColor("#4D000000"));
        //SetWall.set(this);


//                View l1 = findViewById(R.id.linearlayout1);
//                Animation anim_fade_in = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fade_in_500);
//                anim_fade_in.setStartOffset(1000);
//                l1.startAnimation(anim_fade_in);


        Fade fade = new Fade();
        fade.setDuration(1000);
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setEnterTransition(fade);
        getWindow().setReturnTransition(fade);

        getWindow().getSharedElementEnterTransition().setDuration(800);
        getWindow().getSharedElementReturnTransition().setDuration(800).setInterpolator(new DecelerateInterpolator());

        //overridePendingTransition(R.anim.slide_in_right_500,R.anim.slide_out_right_500);








        final Button button1= findViewById(R.id.button1);
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
                overridePendingTransition(R.anim.slide_in_right_500,R.anim.slide_out_left_500);


            }
        });

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), _9_Pass_data_using_intent_1_example.class));
            }
        });

        // ارسال ایمیل
        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        final Button button12 = findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),_13_Shared_Element_Activity_Transition.class));
            }
        });


    }














    // بی رنگ کردن استاتوس  بار
    private void transparentStatus() {
        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    // بی رنگ کردن نویگیشن بار
    private void transparentNavigation() {
        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag( WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }



    private void setWindowFlag(final int bits, boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }










}
