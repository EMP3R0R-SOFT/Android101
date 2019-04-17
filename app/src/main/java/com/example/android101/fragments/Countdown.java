package com.example.android101.fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android101.R;

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



    }

    public void test(){


        final TextView txt_day = getActivity().findViewById(R.id.txt_day);
        final TextView txt_hour = getActivity().findViewById(R.id.txt_hour);
        final TextView txt_minutes = getActivity().findViewById(R.id.txt_minutes);
        final TextView txt_seconds = getActivity().findViewById(R.id.txt_seconds);

        Calendar start_calendar = Calendar.getInstance();
        Calendar end_calendar = Calendar.getInstance();

        end_calendar.set(2019, 8, 23 , 0,0); // bayad bezari : mount - 1 (mount az 0 shoro mishe yani mahe aval 0 e )
        long start_millis = start_calendar.getTimeInMillis(); //get the start time in milliseconds
        long end_millis = end_calendar.getTimeInMillis(); //get the end time in milliseconds
        long total_millis = (end_millis - start_millis); //total time in milliseconds


        CountDownTimer cdt = new CountDownTimer(total_millis, 1000) {
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
        cdt.start();


    }
}
