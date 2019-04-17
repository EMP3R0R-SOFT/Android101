package com.example.android101;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android101.nonui._15_jobService_Example;

public class _15_JobScheduler extends Activity {
    CountDownTimer timer1;
    private static final String TAG = "job_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._15__job_scheduler);

        // چک کنه وضعیت سرویس رو که رانه یا نه
        setNum();



    }


    public void btn_start(View v) {

        ComponentName comp1 = new ComponentName(this, _15_jobService_Example.class);
        JobInfo info = new JobInfo.Builder(5050, comp1)
                .setPersisted(true) // در بک گراند بمونه
                .setPeriodic(15 * 60 * 1000) // تضمین میکنه هر ربع ساعت سرویس ران بشه
                .build();
        JobScheduler job1 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        job1.schedule(info);
        int resultCode = job1.schedule(info);


        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "job Scheduled. (btn start)");

        } else {
            Log.d(TAG, "Job Scheduling faild. (btn start)");
        }

        setNum();

    }


    public void btn_stop(View v) {

        JobScheduler job1 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        job1.cancel(5050);
        Log.d(TAG, "job Cancelled. (btn stop)");

        setNum();

    }


    public boolean Check_ServiceStatus() {

        JobScheduler job1 = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        for (JobInfo jobInfo : job1.getAllPendingJobs()) {
            if (jobInfo.getId() == 5050) {
                return true;
            }
        }
        return false;
    }


    public void setNum() {
        final TextView txt_status = findViewById(R.id.txt_status);
        final TextView txt_counter = findViewById(R.id.txt_counter);


        if (Check_ServiceStatus()) { // اگع سرویس ران بود

            txt_status.setText("Service is Running");
            txt_status.setTextColor(Color.parseColor("#FF07CA00"));


            // یه تایمر که هر 1 ثانیه اطلاعات shared pref رو بخونه و بذاره تو تکس
            timer1 = new CountDownTimer(60 * 60 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.d(TAG, "timer Tick " + millisUntilFinished / 1000);

                    SharedPreferences pref1 = getSharedPreferences("datastore1", MODE_PRIVATE);
                    int num = pref1.getInt("service_counter", 0);
                    if (num == 0) {

                        txt_counter.setText("-");

                    } else {

                        txt_counter.setText(String.valueOf(num));
                    }
                }

                @Override
                public void onFinish() {

                }
            };

            timer1.start();
            Log.d(TAG, "1");

        } else {

            txt_status.setText("Service is Stopped");
            txt_status.setTextColor(Color.parseColor("#FFFF0000"));
            txt_counter.setText("-");

            if (timer1 != null){
                timer1.cancel();
            }

            Log.d(TAG, "2");

        }


    }


}