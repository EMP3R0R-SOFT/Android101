package com.example.android101;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android101.nonui.SetWall;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class _14_Miladi_be_Shamsi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._14__miladi_be__shamsi);
        SetWall.set(this);


        set_current_time();

        Button btn_convert = findViewById(R.id.convert);
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                convert();

            }
        });

    }



    private void SaveFile(String matn) {

        try {

            final File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "date.txt");

            path.createNewFile();
            FileOutputStream out = new FileOutputStream(path);
            out.write(matn.getBytes());
            out.close();
        } catch (Exception e) {
            Toast.makeText(this, "Error - " + e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }


    public void convert(){


        final TextView txt_y = findViewById(R.id.txt_y);
        final TextView txt_m = findViewById(R.id.txt_m);
        final TextView txt_d = findViewById(R.id.txt_d);
        final TextView txt_weak = findViewById(R.id.txt_weak);
        final EditText my = findViewById(R.id.input_m_year);
        final EditText mm = findViewById(R.id.input_m_month);
        final EditText md = findViewById(R.id.input_m_day);

        try {
            int my_int =  Integer.parseInt(my.getText().toString());
            int mm_int =  Integer.parseInt(mm.getText().toString());
            int md_int =  Integer.parseInt(md.getText().toString());

            int Result[] = gregorian_to_jalali(my_int,mm_int,md_int);

            txt_y.setText(Result[0] + "");
            txt_m.setText(Result[1] + "");
            txt_d.setText(Result[2] + "");

            txt_weak.setText(weakDay(Result[1]) + "");


            String matn = Result[0] + "-" + Result[1] + "-" + Result[2] + "-" + weakDay(Result[1]) ;
            SaveFile(matn);

        }catch (Exception e){




        }

    }


    public static int[] gregorian_to_jalali(int gy, int gm, int gd){
        int[] g_d_m = {0,31,59,90,120,151,181,212,243,273,304,334};
        int jy;
        if(gy>1600){
            jy=979;
            gy-=1600;
        }else{
            jy=0;
            gy-=621;
        }
        int gy2 = (gm > 2)?(gy + 1):gy;
        int days = (365 * gy) + ((int)((gy2 + 3) / 4)) - ((int)((gy2 + 99) / 100)) + ((int)((gy2 + 399) / 400)) - 80 + gd + g_d_m[gm - 1];
        jy += 33 * ((int)(days / 12053));
        days %= 12053;
        jy += 4 * ((int)(days / 1461));
        days %= 1461;
        if(days > 365){
            jy+=(int)((days-1)/365);
            days=(days-1)%365;
        }
        int jm = (days < 186)?1 + (int)(days / 31):7 + (int)((days - 186) / 30);
        int jd = 1 + ((days < 186)?(days % 31):((days - 186) % 30));
        int[] out = {jy,jm,jd};
        return out;
    }

    public static int[] jalali_to_gregorian(int jy, int jm, int jd){
        int gy;
        if(jy>979){
            gy=1600;
            jy-=979;
        }else{
            gy=621;
        }
        int days = (365 * jy) + (((int)(jy / 33)) * 8) + ((int)(((jy % 33) + 3) / 4)) + 78 + jd + ((jm < 7)?(jm - 1) * 31:((jm - 7) * 30) + 186);
        gy += 400 * ((int)(days / 146097));
        days %= 146097;
        if(days > 36524){
            gy += 100 * ((int)(--days / 36524));
            days %= 36524;
            if (days >= 365)days++;
        }
        gy += 4 * ((int)(days / 1461));
        days %= 1461;
        if(days > 365){
            gy += (int)((days - 1) / 365);
            days = (days - 1) % 365;
        }
        int gd = days + 1;
        int[] sal_a = {0,31,((gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0))?29:28,31,30,31,30,31,31,30,31,30,31};
        int gm;
        for(gm = 0;gm < 13;gm++){
            int v = sal_a[gm];
            if(gd <= v)break;
            gd -= v;
        }
        int[] out = {gy,gm,gd};
        return out;
    }


    public static String weakDay(int month){

        switch (month){
            case 1:
                return "فروردین";
            case 2:
                return "اردیبهشت";
            case 3:
                return "خرداد";
            case 4:
                return "تیر";
            case 5:
                return "مرداد";
            case 6:
                return "شهریور";
            case 7:
                return "مهر";
            case 8:
                return "آبان";
            case 9:
                return "آذر";
            case 10:
                return "دی";
            case 11:
                return "بهمن";
            case 12:
                return "اسفند";

            default:
                return "خطا";
        }

    }



    public void set_current_time(){


        EditText my = findViewById(R.id.input_m_year);
        EditText mm = findViewById(R.id.input_m_month);
        EditText md = findViewById(R.id.input_m_day);

        Calendar currentTime = Calendar.getInstance();

        String Current_Year = String.valueOf(currentTime.get(Calendar.YEAR));
        String Current_Month = String.valueOf(currentTime.get(Calendar.MONTH) + 1);
        String Current_Day = String.valueOf(currentTime.get(Calendar.DAY_OF_MONTH));

        my.setText(Current_Year);
        mm.setText(Current_Month);
        md.setText(Current_Day);

    }






}
