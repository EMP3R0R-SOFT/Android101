package com.example.android101;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

public class _13_Shared_Element_Activity_Transition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._13_shared_element__transition);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().setDuration(2000);
            getWindow().getSharedElementReturnTransition().setDuration(2000)
                    .setInterpolator(new DecelerateInterpolator());
        }


        Button btnBoom = findViewById(R.id.btn_Start_anim);
        btnBoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        Intent intent = new Intent(getBaseContext(), _13_Shared_Element_Activity_Transition_2.class);
        Pair<View,String> p1 =    Pair.create(findViewById(R.id.shared_img_1),"boomboom1");
        Pair<View,String> p2 =    Pair.create(findViewById(R.id.shared_txt_1),"boomboom2");
        ActivityOptions option1 = ActivityOptions
                .makeSceneTransitionAnimation(_13_Shared_Element_Activity_Transition.this,p1,p2); // Login_Page.this , p1,p2,p3 .....);
        startActivity(intent,option1.toBundle());

        //overridePendingTransition(0,0);

            }
        });



    }

}
