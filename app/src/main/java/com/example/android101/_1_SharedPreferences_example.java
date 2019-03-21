package com.example.android101;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android101.nonui.SetWall.getWalpaperNum;

public class _1_SharedPreferences_example extends AppCompatActivity {









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._1_sharedperferences_example);




        View a1 = findViewById(R.id.rel1);
        Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.trans_up_fade_in_1000);
        a1.startAnimation(anim1);







        SetupRadioButtonClickListener(R.id.radioBTN1);
        SetupRadioButtonClickListener(R.id.radioBTN2);


        // Dokme 1
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


        // Dokme 2
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
               // data1.edit().remove("Username").apply();

                data1.edit().clear().apply();

            }
        });


    }


    // شورت کات برای نشان دادن T
    private void Toast(String matn) {
        Toast.makeText(getBaseContext(), matn, Toast.LENGTH_SHORT).show();


    }




























    private void SetupRadioButtonClickListener (int btnID){

        final RadioButton RadioBTN = findViewById(btnID);
        if (getWalpaperNum(this).equals(RadioBTN.getText())  ){
            RadioBTN.setChecked(true);
        }

        RadioBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save_RadioButton_Pref(RadioBTN.getText().toString());
                //T(RadioBTN.getText() + "");
            }
        });
    }

    private void Save_RadioButton_Pref(String btnText) {

        SharedPreferences radio_pref = getSharedPreferences("datastore1",MODE_PRIVATE);
        radio_pref.edit().putString("wallpaperNum" ,btnText).apply();
    }







}
