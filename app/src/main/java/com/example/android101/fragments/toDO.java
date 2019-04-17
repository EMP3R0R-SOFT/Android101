package com.example.android101.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android101.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * A simple {@link Fragment} subclass.
 */
public class toDO extends Fragment {

    final String TAG = "XXXXXXXXX";
    final File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "toDO.txt");

    public toDO() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout._00_fragment_to_do, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        final EditText todo = getActivity().findViewById(R.id.input_todo);

        FilltoDo();


        todo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                SaveFile(todo.getText().toString());

            }
        });


    }

    private void FilltoDo(){
        EditText todoBox = getActivity().findViewById(R.id.input_todo);
        todoBox.setText(toDoString());


    }
    private void SaveFile(String matn) {

        try {


            if(!path.exists()){
                path.createNewFile();
            }

            FileOutputStream out = new FileOutputStream(path);
            out.write(matn.getBytes());
            out.close();

        } catch (Exception e) {
            Log.d(TAG,"0");
            handlePermission();
            e.printStackTrace();
        }


    }


    private String toDoString() {

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

        String toDoText = txt.toString();
        return toDoText;
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
        if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"1");
            // This is Case 1. Now we need to check further if permission was shown before or not

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // This is Case 4.
                Log.d(TAG,"4");
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);

            } else {
                Log.d(TAG,"3");
                // This is Case 3. Request for permission here
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
            }

        } else {
            Log.d(TAG,"2");
            // This is Case 2. You have permission now you can do anything related to it
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"22");
            // This is Case 2 (Permission is now granted)

            FilltoDo();


        } else {
            // This is Case 1 again as Permission is not granted by user
            Log.d(TAG,"11");

            //Now further we check if used denied permanently or not
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // case 4 User has denied permission but not permanently
                Log.d(TAG,"44");

            } else {
                Log.d(TAG,"55");
                // case 5. Permission denied permanently.
                // You can open Permission setting's page from here now.

                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",getActivity().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);


            }

        }
    }


}
