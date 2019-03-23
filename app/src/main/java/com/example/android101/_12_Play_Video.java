package com.example.android101;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.android101.nonui.SetWall;

public class _12_Play_Video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._12__play__video);

        SetWall.set(this);

/*


        Button btn_play = findViewById(R.id.btn_play);
        final VideoView player1 = findViewById(R.id.videoPlayer);
        MediaController m1 = new MediaController(getBaseContext());
        player1.setMediaController(m1);

        Uri u1 = Uri.parse(("android.resource://"+getPackageName()+"/"+R.raw.video_1));

        player1.setVideoURI(u1);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                player1.start();


            }
        });


*/




    }

}
