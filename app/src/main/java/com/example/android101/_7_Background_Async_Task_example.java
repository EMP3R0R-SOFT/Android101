package com.example.android101;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android101.nonui.SetWall;

import java.util.Random;

public class _7_Background_Async_Task_example extends AppCompatActivity {

    private final static long Password = 1112550;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._07_background__async__task_example);

        SetWall.set(this);

    }


    public void show_status(String TheStatus) {

        TextView tv_status = findViewById(R.id.tv_status);
        tv_status.setText(TheStatus);

    }

    public void show_answer(long TheAnswer) {

        TextView tv_answer = findViewById(R.id.tv_answer);
        tv_answer.setText("Final Password = " + TheAnswer);

    }


    public void BTN_Normal(View v){

        TextView tv_answer = findViewById(R.id.tv_answer);
        tv_answer.setText("");

        final long Range = 10000000;
        final long showCountStep = 100;
        long guess = 0;
        long count = 0;
        Random RandomInt = new Random();

        while (guess != Password){

            guess = Math.abs(RandomInt.nextLong()) % Range;
            count++;

            if (count % showCountStep==0) {
                show_status("#" + count +" password checked!");
            }

        }

        show_status("Done!");
        show_answer(guess);

    }


    public void BTN_ASync(View v){

        final long Range = 50000000;
        final long showCountStep = 10000;


        Task1 xxx = new Task1();
        xxx.execute(Range,showCountStep);

//        Task2 xxx = new Task2();
//        xxx.execute();
    }




//    AsyncTask <Params, Progress, Result>

//    Params,   the type of the parameters sent to the task upon execution.
//    Progress, the type of the progress units published during the background computation.
//    Result,   the type of the result of the background computation.


    //                                    XXX   YYY    ZZZ
    private class Task1 extends AsyncTask<Long,String,Long>{

        // Send to Main UI
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            TextView tv_answer = findViewById(R.id.tv_answer);
            tv_answer.setText("...Calculating...");

        }

        // Run on Background
        @Override                   //XXX
        protected Long doInBackground(Long... longs) {

            //XXX
            long Range_fromArg = longs[0];
            long showCountStep_fromArg = longs[1];

            long guess = 0;
            long count = 0;
            Random RandomInt = new Random();

            while (guess != Password){

                guess = Math.abs(RandomInt.nextLong()) % Range_fromArg;
                count++;

                if (count % showCountStep_fromArg==0) {
                    // YYY2
                    publishProgress("#" + count +" password checked!   Last Guess: " + guess);
                }
            }

            ///   ZZZ2
            return guess;
        }


        // Send to Main UI
        @Override                   //  YYY        YYY2
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            String message = "";
            for (String str : values){
                message += str;
            }
            show_status(message);
        }

        // Send to Main UI
        @Override              //    ZZZ   ZZZ2
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            show_answer(aLong);
        }
    }





















    // android.os.AsyncTask<Params, Progress, Result>
    //                                    XXX   YYY    ZZZ
    private class Task2 extends AsyncTask<Void,String,Long>{

        // Send to Main UI
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            TextView tv_answer = findViewById(R.id.tv_answer);
            tv_answer.setText("...Calculating...");

        }



        // Run on Background
        @Override                   //XXX
        protected Long doInBackground(Void... voids) {

            //XXX
            final long Range = 10000000;
            final long showCountStep = 100;

            long guess = 0;
            long count = 0;
            Random RandomInt = new Random();

            while (guess != Password){

                guess = Math.abs(RandomInt.nextLong()) % Range;
                count++;

                if (count % showCountStep==0) {
                    // YYY2
                    publishProgress("#" + count +" password checked!   Last Guess: " + guess);
                }
            }

            ///   ZZZ2
            return guess;
        }


        // Send to Main UI
        @Override                   //  YYY        YYY2
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            String message = "";
            for (String str : values){
                message += str;
            }
            show_status(message);
        }

        // Send to Main UI
        @Override              //    ZZZ   ZZZ2
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            show_answer(aLong);
        }
    }















    // نحوه استفاده از Async Task

    /**

    private class Task1 extends AsyncTask<Long,String,Long>{
        @Override
        protected Long doInBackground(Long... longs) {
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }
    }

     **/





}
