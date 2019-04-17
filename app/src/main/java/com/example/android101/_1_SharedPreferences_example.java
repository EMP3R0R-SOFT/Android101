package com.example.android101;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android101.nonui.SetWall;

import static com.example.android101.nonui.SetWall.getWalpaperNum;

public class _1_SharedPreferences_example extends AppCompatActivity {


    Context c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._01_sharedperferences_example);


        SetWall.set(this);

        c1=this;


        Setup_RadioButton_ClickListener(R.id.radioBTN1);
        Setup_RadioButton_ClickListener(R.id.radioBTN2);
        Setup_RadioButton_ClickListener(R.id.radioBTN3);
        Setup_RadioButton_ClickListener(R.id.radioBTN4);
        Setup_RadioButton_ClickListener(R.id.radioBTN5);
        Setup_RadioButton_ClickListener(R.id.radioBTN6);
        Setup_RadioButton_ClickListener(R.id.radioBTN7);


        // Dokme 1 (save)
        Button button1  = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText1 = findViewById(R.id.editText1);

                /** Save data
                 *  datastore1 = esme file xml ke tosh ina zakhire mishe
                 *  username = title on meghdar
                 *  edittext mishe chizi ke zakhire mishe
                 */
                SharedPreferences data1 = getSharedPreferences("datastore1",MODE_PRIVATE);
                data1.edit().putString("Username",editText1.getText().toString()).apply();

                Toast("Saved.");


            }
        });


        // Dokme 2 (Read)
        Button button2  = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView1 = findViewById(R.id.textview1);


                /** injoori zakhire mishe
                 *
                 *
                 * <map>
                 *     <string name="Username">savedstring</string>
                 * </map>
                 *
                 *
                 */


                android.content.SharedPreferences data1 = getSharedPreferences("datastore1",MODE_PRIVATE);
                String extracteddata1 = data1.getString("Username","def");

                if (extracteddata1 == "def") {
                    Toast("No Data Recorded.");

                }else{
                    textView1.setText(extracteddata1);
                }

            }
        });


        // Dokme 3
        Button button3  = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView1 = findViewById(R.id.textview1);


                android.content.SharedPreferences data1 = getSharedPreferences("datastore1",MODE_PRIVATE);
                data1.edit().clear().apply();

            }
        });


    }


    // شورت کات برای نشان دادن T
    private void Toast(String matn) {
        Toast.makeText(getBaseContext(), matn, Toast.LENGTH_SHORT).show();


    }













    private void Setup_RadioButton_ClickListener(int btnID){

        final RadioButton RadioBTN = findViewById(btnID);

        if (getWalpaperNum(this).equals(RadioBTN.getText())  ){
            RadioBTN.setChecked(true);
        }

        RadioBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save_RadioButton_Pref(RadioBTN.getText().toString());
                SetWall.set(c1);
            }
        });
    }

    private void Save_RadioButton_Pref(String btnText) {

        SharedPreferences radio_pref = getSharedPreferences("datastore1",MODE_PRIVATE);
        radio_pref.edit().putString("wallpaperNum" ,btnText).apply();
    }







}
