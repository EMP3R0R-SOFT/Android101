package com.example.android101;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.android101.nonui.SetWall;


public class _8_Dynamic_Buttons_example extends AppCompatActivity {

    private static final int Row_Count = 5;
    private static final int Colum_Count = 7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic__buttons_example);


        SetWall.set(this);


        MakeButtons();
    }

    private void MakeButtons() {





        TableLayout tab1 = findViewById(R.id.TabLayout1);


        for (int row = 0; row < Row_Count; row++ ){


            TableRow tableRow1 = new TableRow(this);
            tab1.addView(tableRow1);

            for (int colum = 0; colum < Colum_Count; colum++){

                final int f_row = row;
                final int f_col = colum;

                Button btn = new Button(this);
                btn.setText(colum + "," + row);
                btn.setTextColor(Color.argb(255,130,130,130));
                btn.setBackgroundResource(R.drawable.btn_theme_1);



                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(_8_Dynamic_Buttons_example.this, f_col +","+ f_row +" Clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                tableRow1.addView(btn);
            }

        }


    }



    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_500,R.anim.slide_out_right_500);
    }

}
