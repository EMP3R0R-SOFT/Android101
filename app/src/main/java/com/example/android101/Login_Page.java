package com.example.android101;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;


import com.example.android101.databinding.LoginPageBinding;

public class Login_Page extends AppCompatActivity {


    private LoginPageBinding binding1;
    final int anim_duration = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Define Transitions:
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        // set an exit transition
        getWindow().setExitTransition(new Explode());


        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        binding1 = DataBindingUtil.setContentView(this,R.layout.login_page);

        ClickListener();
        StartAnim();

    }


    private void StartAnim() {

        Animation anim_fade_in = AnimationUtils.loadAnimation(this,R.anim.fade_in_1);
        View container = findViewById(R.id.container1);
        EditText user = findViewById(R.id.btn_username);
        //user.startAnimation(anim_fade_in);
        container.startAnimation(anim_fade_in);

    }


    private void ClickListener() {
        FrameLayout btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animationButtonWidth();
                fadeOut_Text_setProgress();
//                Intent intent = new Intent(getBaseContext(), _0_HomePage.class);
//                startActivity(intent);
//                finish();
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
            }
        });
        anim1.setDuration(anim_duration);
        anim1.start();
    }


    private void fadeOut_Text_setProgress(){

        binding1.btnLoginText.animate().alpha(0f).setDuration(anim_duration).setListener(new AnimatorListenerAdapter() {
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

                Intent intent = new Intent(getBaseContext(), _0_HomePage.class);
                //ActivityOptions trans1 = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_2,R.anim.fade_out_2);


                //reveal_background();
            }
        },1500);

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




                finish();
            }
        });

        reveal.start();

    }




    // دادن عرض دکمه
    private int getfinalwidth(){

        return (int) getResources().getDimension(R.dimen.get_width);
    }


}
