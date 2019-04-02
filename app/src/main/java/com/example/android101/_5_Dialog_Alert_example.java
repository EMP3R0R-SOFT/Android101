package com.example.android101;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android101.nonui.SetWall;

public class _5_Dialog_Alert_example extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example__dialog__alert);

        SetWall.set(this);

        SetupAlertDialog_UsingFragment();





    }


    private void SetupAlertDialog_UsingFragment(){
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager fuck = getSupportFragmentManager();
                _6_Dialog_Alert_Fragment Dialog = new _6_Dialog_Alert_Fragment();
                Dialog.show(fuck,"just do it :|");

            }
        });
    }



}
