package com.example.android101;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android101.nonui.SetWall;
import com.example.android101.nonui.T;


public class _11_Simple_ListView extends AppCompatActivity {

    String weekdays[] = {"شنبه","یکشنبه","دوشنبه","سه شنبه","چهارشنبه","پنج شنبه","جمعه"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._11__simple__list_view);

        SetWall.set(this);

        makelist();

    }

    private void makelist() {

        ListView L1 =  findViewById(R.id.listXXX);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this
                ,android.R.layout.simple_list_item_1,weekdays);
        L1.setAdapter(adapter1);
        L1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                String aaa = (String) parent.getItemAtPosition(position);
//                T.makeText(getBaseContext(),    aaa + " - " + position, T.LENGTH_SHORT).show();
//
                TextView zzz = (TextView) view;
                Toast.makeText(_11_Simple_ListView.this, "" + zzz.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}
