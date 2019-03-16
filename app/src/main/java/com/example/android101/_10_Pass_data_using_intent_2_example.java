package com.example.android101;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class _10_Pass_data_using_intent_2_example extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_data_using_intent_2_example);

        Intent get1 = getIntent();
        final int recived_int = get1.getIntExtra("key1",0);
        TextView txt2 = findViewById(R.id.txt2);
        txt2.setText("" + recived_int);


        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int answer = recived_int * 2;
                Intent back1 = new Intent();
                back1.putExtra("key2",answer);
                setResult(Activity.RESULT_OK,back1);  // bayad ino bezarim ta javab bede :|
                finish();

            }
        });


    }





}
