package com.example.android101;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android101.nonui.SetWall;

public class _5_Dialog_Alert_example extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example__dialog__alert);

        SetWall.set(this);

        SetupAlertDialog_UsingBuilder();

    }


    private void SetupAlertDialog_UsingBuilder() {

        final DialogInterface.OnClickListener Listener1 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                TextView tv1 = findViewById(R.id.textViewDialog);
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        tv1.setText("PASS");
                        tv1.setTextColor(getResources().getColor(R.color.MyGreen));
                        tv1.setTextSize(72);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        tv1.setText("FAIL");
                        tv1.setTextColor(getResources().getColor(R.color.MyRED));
                        tv1.setTextSize(72);
                        break;
                }
            }
        };


        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(_5_Dialog_Alert_example.this)

                        .setTitle("Hey yaa..")
                        .setMessage("Really wanna do this shit !?")
                        .setPositiveButton("yeah man", Listener1)
                        .setNegativeButton("NO! Get me out of here...", Listener1)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });


    }
}
