package com.example.android101;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.example.android101.nonui.EnterSharedElementTextSizeHandler;
import com.example.android101.nonui.TextSizeTransition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _13_Shared_Element_Activity_Transition_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__13__shared__element___transition_2);


        // تغیر سرعت افکت shared element
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().setDuration(2000);
            getWindow().getSharedElementReturnTransition().setDuration(2000)
                    .setInterpolator(new DecelerateInterpolator());
        }


        EnterSharedElementTextSizeHandler handler = new EnterSharedElementTextSizeHandler(this);
        handler.addTextViewSizeResource((TextView) findViewById(R.id.shared_txt_2),R.dimen.startTest,R.dimen.endTest );

    }
}






