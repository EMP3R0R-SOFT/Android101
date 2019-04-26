package com.example.android101._0_HomePage;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android101.R;
import com.example.android101._18_toDo._18_toDo_json;
import com.example.android101._20_SendReceive_Json_From_RestAPI;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class Countdown extends Fragment {


    public Countdown() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout._00_fragment_countdown, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        test();

        final ImageView me = getActivity().findViewById(R.id.countdown_page_1);
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(getContext(), _20_SendReceive_Json_From_RestAPI.class));
                startActivity(new Intent(getContext(), _18_toDo_json.class));

            }
        });


    }

    public void test() {


        final TextView txt_day = getActivity().findViewById(R.id.txt_day);
        final TextView txt_hour = getActivity().findViewById(R.id.txt_hour);
        final TextView txt_minutes = getActivity().findViewById(R.id.txt_minutes);
        final TextView txt_seconds = getActivity().findViewById(R.id.txt_seconds);
        final TextView txt_prog = getActivity().findViewById(R.id.txt_prog);

        Calendar start_calendar = Calendar.getInstance();
        Calendar end_calendar = Calendar.getInstance();


        end_calendar.set(2019, 8, 23, 0, 0); // bayad bezari : mount - 1 (mount az 0 shoro mishe yani mahe aval 0 e )


        long start_millis = start_calendar.getTimeInMillis(); //get the start time in milliseconds
        long end_millis = end_calendar.getTimeInMillis(); //get the end time in milliseconds
        long total_millis = (end_millis - start_millis); //total time in milliseconds


        // Main Timer
        CountDownTimer MainTimer = new CountDownTimer(total_millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                txt_day.setText(days + "");
                txt_hour.setText(hours + "");
                txt_minutes.setText(minutes + "");
                txt_seconds.setText(seconds + "");
            }

            @Override
            public void onFinish() {
                Toast.makeText(getContext(), "Finish", Toast.LENGTH_SHORT).show();
            }
        };
        MainTimer.start();


        // TImer for Progress
        CountDownTimer ProgressTimer = new CountDownTimer(total_millis, 60000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days);


                txt_day.setText(days + "");

            }

            @Override
            public void onFinish() {
                Toast.makeText(getContext(), "Finish", Toast.LENGTH_SHORT).show();
            }
        };
        ProgressTimer.start();


        //################################################
        // Progress %
        // 6 mah = 186 day or 4464 hour
        DecimalFormat No_Decimal = new DecimalFormat("#");
        long Hour_Remain = TimeUnit.MILLISECONDS.toHours(total_millis); // masalan = 3800
        int Progress_Percent = (int) (100.0 * ((4464.0 - Hour_Remain) / 4464.0));

        //txt_prog.setText(No_Decimal.format(Progress_Percent) + "%");
        txt_prog.setText(Progress_Percent + "%");


    }
}
