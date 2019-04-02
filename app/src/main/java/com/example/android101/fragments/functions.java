package com.example.android101.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android101.R;
import com.example.android101._11_Simple_ListView;
import com.example.android101._12_Play_Video;
import com.example.android101._13_Shared_Element_Activity_Transition;
import com.example.android101._1_SharedPreferences_example;
import com.example.android101._2_UseClass_example;
import com.example.android101._3_UseClass2_example;
import com.example.android101._4_ListView_example;
import com.example.android101._5_Dialog_Alert_example;
import com.example.android101._7_Background_Async_Task_example;
import com.example.android101._8_Dynamic_Buttons_example;
import com.example.android101._9_Pass_data_using_intent_1_example;
import com.example.android101._Login_Page_Splash;

public class functions extends Fragment {


    public functions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        return inflater.inflate(R.layout._0_activity_home_page, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        final Button button1 = getActivity().findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "asd", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), _1_SharedPreferences_example.class));
            }
        });

        Button button2 = getActivity().findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), _2_UseClass_example.class));
            }
        });

        Button button3 = getActivity().findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), _3_UseClass2_example.class));
            }
        });

        Button button4 = getActivity().findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), _4_ListView_example.class));
            }
        });

        Button button5 = getActivity().findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), _5_Dialog_Alert_example.class));
            }
        });

        Button button6 = getActivity().findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), _7_Background_Async_Task_example.class));
            }
        });

        Button button7 = getActivity().findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), _8_Dynamic_Buttons_example.class));
                //overridePendingTransition(R.anim.slide_in_right_500, R.anim.slide_out_left_500);


            }
        });

        Button button8 = getActivity().findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), _9_Pass_data_using_intent_1_example.class));
            }
        });

        // ارسال ایمیل
        Button button9 = getActivity().findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(Intent.ACTION_SENDTO);
                myIntent.setData(Uri.fromParts("mailto", "asd@lol.ir", null));
                myIntent.putExtra(Intent.EXTRA_SUBJECT, "عنوان");
                myIntent.putExtra(Intent.EXTRA_TEXT, "متن ایمیل");
                startActivity(Intent.createChooser(myIntent, "انتخاب پیام رسان"));
            }
        });

        Button button10 = getActivity().findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), _11_Simple_ListView.class));
            }
        });

        Button button11 = getActivity().findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), _12_Play_Video.class));
            }
        });

        final Button button12 = getActivity().findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), _13_Shared_Element_Activity_Transition.class));
            }
        });


        final Button button13 = getActivity().findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), _Login_Page_Splash.class));
            }
        });

    }
}
