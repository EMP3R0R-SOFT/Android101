package com.example.android101.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android101.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class toDO extends Fragment {


    public toDO() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        final EditText todo = getActivity().findViewById(R.id.input_todo);


        android.content.SharedPreferences data1 = getActivity().getSharedPreferences("datastore1",MODE_PRIVATE);
        String extracteddata1 = data1.getString("toDO_key","def");

        if (extracteddata1 == "def") {
            todo.setText("No Data Recorded.");

        }else{
            todo.setText(extracteddata1);
        }


        todo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SharedPreferences data1 = getActivity().getSharedPreferences("datastore1",MODE_PRIVATE);
                data1.edit().putString("toDO_key",todo.getText().toString()).apply();
            }
        });




    }




}
