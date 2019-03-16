package com.example.android101;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class _9_Pass_data_using_intent_1_example extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_data_using_intent_1_example);


        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText1 = findViewById(R.id.editText1);
                int usernum = Integer.parseInt(editText1.getText().toString());

                Intent send1 = new Intent(getBaseContext(), _10_Pass_data_using_intent_2_example.class);
                send1.putExtra("key1",usernum);
              //startActivity(send1);
                startActivityForResult(send1,12345);

            }
        });


    }

    // Javabo migire
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 12345:

                int answer = data.getIntExtra("key2",0);
                Toast.makeText(this, "Result: " + answer, Toast.LENGTH_SHORT).show();
                break;
        }
    }





}
