package com.example.android101;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android101.nonui.SetWall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class _16_ReadWrite_storage extends AppCompatActivity {

    final String TAG = "XXXXXXXXX";
    final File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "test.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._16__read_write_storage);
        SetWall.set(this);
    }




    private void WriteFile(String matn) {

        try {

            // create file
            if(!path.exists()){
                path.createNewFile();
            }

            // write
            FileOutputStream out = new FileOutputStream(path);
            out.write(matn.getBytes());
            out.close();

        } catch (Exception e) {

            handlePermission();
            e.printStackTrace();
        }


    }




    private String ReadFile() {

        StringBuilder txt = new StringBuilder();
        try {


            //Read text from file
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            while ((line = br.readLine()) != null) {
                txt.append(line);
                txt.append('\n');
            }
            br.close();


        } catch (Exception e) {
            handlePermission();
            e.printStackTrace();
        }

        String readed_text = txt.toString();
        return readed_text;
    }






    private void save__android_date(){


        try {

            // noktash hamin khat painie
            File dir = getExternalFilesDir("dir_test_externalPrivate");
            File f1 = new File(dir,"external_private.txt");

            FileOutputStream out = new FileOutputStream(f1);
            out.write("data".getBytes());
            out.close();

        } catch (Exception e) {

            handlePermission();
            e.printStackTrace();
        }

    }












































    /**
     * Case 1: User doesn't have permission
     * Case 2: User has permission
     *
     * Case 3: User has never seen the permission Dialog
     * Case 4: User has denied permission once but he din't clicked on "Never Show again" check box
     * Case 5: User denied the permission and also clicked on the "Never Show again" check box.
     * Case 6: User has allowed the permission
     */

    public void handlePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // This is Case 1. Now we need to check further if permission was shown before or not

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // This is Case 4.


             ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);

            } else {

                // This is Case 3. Request for permission here
                //if activity:
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
                //if fragment
                //requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
            }

        } else {

            // This is Case 2. You have permission now you can do anything related to it
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            // This is Case 2 (Permission is now granted)




        } else {
            // This is Case 1 again as Permission is not granted by user


            //Now further we check if used denied permanently or not
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // case 4 User has denied permission but not permanently


            } else {

                // case 5. Permission denied permanently.
                // You can open Permission setting's page from here now.

                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",this.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);


            }

        }
    }

}
