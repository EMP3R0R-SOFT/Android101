package com.example.android101;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;


import com.example.android101.databinding.LoginPageBinding;

public class Login_Page extends AppCompatActivity {


    private LoginPageBinding binding1;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Define Transitions:
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        // set an exit transition
        //getWindow().setExitTransition(new Explode());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //binding1.viewLoginAnim.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding1.viewLoginAnim.setVisibility(View.VISIBLE);
                android.support.constraint.ConstraintLayout l2 = findViewById(R.id.view_login_anim);
                Animation anim2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.trans_up_fade_in_2000);
                l2.startAnimation(anim2);

            }
        },700);






        getWindow().getSharedElementEnterTransition().setDuration(800);
        getWindow().getSharedElementReturnTransition().setDuration(800).setInterpolator(new DecelerateInterpolator());






        transparentStatus();
        transparentNavigation();


//        Fade fade = new Fade();
//        fade.setDuration(3000);
//        getWindow().setEnterTransition(fade);
//
//        Slide slide = new Slide();
//        slide.setDuration(1000);
//        getWindow().setReturnTransition(slide);



//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }


        binding1 = DataBindingUtil.setContentView(this,R.layout.login_page);

        ClickListener();
        StartAnim();

    }


    private void StartAnim() {

        Animation anim_fade_in = AnimationUtils.loadAnimation(this,R.anim.fade_in_content_1);
        View container = findViewById(R.id.container1);
        EditText user = findViewById(R.id.btn_username);
        //user.startAnimation(anim_fade_in);
        container.startAnimation(anim_fade_in);

    }


    private void ClickListener() {
        FrameLayout btn_login = findViewById(R.id.btn_Login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animationButtonWidth();
                fadeOut_Text_setProgress();



            }
        });
    }


    // انیمیشن کوچیک شدن دکمه (تبدیل به دایره شدن)
    private void animationButtonWidth(){

        ValueAnimator anim1 = ValueAnimator.ofInt(binding1.btnLogin.getMeasuredWidth(),getfinalwidth());
        anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams1 = binding1.btnLogin.getLayoutParams();
                layoutParams1.width = value;
                binding1.btnLogin.requestLayout();


                // یه بکگراند دیگه به دکمه میدیم تا وقتی کوچیک شد گرد بشه
                // بک گراند قبلی radius کم داره مربع میشه...
                Drawable x = ResourcesCompat.getDrawable(getResources(), R.drawable.btn_theme_3, null);
                binding1.btnLogin.setBackground(x);

            }
        });
        anim1.setDuration(300);
        anim1.start();
    }


    private void fadeOut_Text_setProgress(){

        //binding1.btnLogin.animate().
        binding1.btnLoginText.animate().alpha(0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                showProgressDialog();
                nextAction();
            }
        });

    }

    private void showProgressDialog(){
        binding1.progressbar1.getIndeterminateDrawable().setColorFilter(Color.parseColor("#D3CCDB"), PorterDuff.Mode.SRC_IN);
        binding1.progressbar1.setVisibility(View.VISIBLE);

    }

    private void nextAction(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                binding1.progressbar1.setVisibility(View.INVISIBLE);


                Intent intent = new Intent(getBaseContext(), _0_HomePage.class);
                Pair<View,String> p1 =    Pair.create(findViewById(R.id.logo_1),"shitAnim");

                ActivityOptions option1 = ActivityOptions.makeSceneTransitionAnimation(Login_Page.this,p1); // Login_Page.this , p1,p2,p3 .....);
                startActivity(intent,option1.toBundle());

                //overridePendingTransition(0,0);
                //overridePendingTransition(R.anim.slide_in_right_500,R.anim.slide_out_right_500);

                //reveal_background();


            }
        },2000);

    }


    private void reveal_background(){

        binding1.btnLogin.setElevation(0f);
        binding1.viewReveal.setVisibility(View.VISIBLE);

        int x = binding1.viewReveal.getWidth();
        int y = binding1.viewReveal.getHeight();

        int startX =(int) (getfinalwidth() / 2 + binding1.btnLogin.getX());
        int startY =(int) (getfinalwidth() / 2 + binding1.btnLogin.getY());

        float radius = Math.max(x,y) * 1.2f;

        Animator reveal = ViewAnimationUtils.createCircularReveal(binding1.viewReveal
        ,startX,startY,getfinalwidth(),radius);
        reveal.setDuration(500);
        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Transition ts = new Explode();  //Slide(); //Explode();
                ts.setDuration(3000);
                //finish();
            }
        });

        reveal.start();

    }





    // دادن عرض دکمه
    private int getfinalwidth(){
        return (int) getResources().getDimension(R.dimen.get_width);
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
