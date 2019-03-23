package com.example.android101;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android101.nonui.Calculation_Class;
import com.example.android101.nonui.SetWall;

import java.util.Locale;

/**
 *  PASS DATA to Another Activity
 */
public class _2_UseClass_example extends AppCompatActivity {

    // از کلاسی که ساختیم استفاده میکنیم
    private Calculation_Class callclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_class_example);

        SetWall.set(this);

        // یه اینستنس از کلاسی که ساختیم تعریف میکنیم
        callclass = Calculation_Class.getInstance();

        Button btnCalculate = findViewById(R.id.btn_calculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText getuserdata = findViewById(R.id.inputdata);

                int userdata = Integer.parseInt(getuserdata.getText().toString()); // تبدیل چیزی که کاربر نوشته به عدد
                callclass.setTheUserData(userdata); // استفاده از Setter
                String msg = String.format(Locale.getDefault(),"%d",callclass.getTheUserData()); //حالا داده رو از کلاس میگیریم و به String تبدیل میکنیم
                Toast.makeText(getBaseContext(),msg, Toast.LENGTH_SHORT).show();

            }
        });












    }


}
