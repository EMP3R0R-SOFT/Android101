package com.example.android101;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.example.android101.nonui.EnterSharedElementTextSizeHandler;

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






