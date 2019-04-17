package com.example.android101.nonui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.android101._15_JobScheduler;


public class _15_jobService_Example extends JobService {


    private boolean jobStop = false; // واسه بررسی اینکه سروی رانه یا نه
    private static final String TAG = "job_TAG";

    SharedPreferences pref1;

    @Override
    public boolean onStartJob(JobParameters params) {




        Log.d(TAG,"onStartJob");
        Background1(params);
        return true;

    }



    @Override
    public boolean onStopJob(JobParameters params) {

        Log.d(TAG,"onStopJob");
        jobStop = true;
        return true;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { // واسه اینه که اگه برنامه بسته شد بعد از یه مدت اگه سیستم عامل رم خالی داشت سرویس رو دوباره ران کنه
        Log.v(TAG, "onStartCommand");
        return START_STICKY;
    }

    //--------------------------------------------------------------------------------------------------

    private void Background1(final JobParameters params){

        new Thread(new Runnable() {
            @Override
            public void run() {
                // do things
                for (int i = 1; i > 0 ; i++) { // یه تایمر میسازیم هر 1 قانیه کاری که میگیم رو انجام بده

                   if (jobStop){
                       return;
                   }


                   save(i);

                    Log.d(TAG,"run:" + i);

                   try {
                       Thread.sleep(1000);
                   }catch (Exception e ){
                       e.printStackTrace();
                   }
                }


                Log.d(TAG,"background 1 finish");
                jobFinished(params,false);

            }
        }).start();

    }

    private void save(int i) { // دیتا رو ذخیره کنه

        pref1 = getSharedPreferences("datastore1",MODE_PRIVATE);
        pref1.edit().putInt("service_counter",i).apply();
    }


}
