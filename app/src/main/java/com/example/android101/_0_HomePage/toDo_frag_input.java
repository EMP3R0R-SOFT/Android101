package com.example.android101._0_HomePage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android101.R;


public class toDo_frag_input extends Fragment {

    private static final String PARAM_Title = "param1";
    private static final String PARAM_Description = "param2";
    private static final String PARAM_id = "param3";
    private String Recived_title;
    private String Recived_Description;
    private int Recived_ID;
    private OnFragmentInteractionListener mListener;

    EditText todo_frag_title;
    EditText todo_frag_Description;
    Button todo_frag_save;
    Button toto_frag_cancel;



    public toDo_frag_input() {
        // Required empty public constructor
    }


    public static toDo_frag_input newInstance(String stringTitle, String stringDescription, int intID) {
        toDo_frag_input fragment = new toDo_frag_input();
        Bundle args = new Bundle();
        args.putString(PARAM_Title, stringTitle);
        args.putString(PARAM_Description, stringDescription);
        args.putInt(PARAM_id, intID);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Recived_title = getArguments().getString(PARAM_Title);
            Recived_Description = getArguments().getString(PARAM_Description);
            Recived_ID = getArguments().getInt(PARAM_id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout._00_todo_frag_input, container, false);

        todo_frag_title = view.findViewById(R.id._00_input_title);
        todo_frag_Description = view.findViewById(R.id._00_input_description);
        todo_frag_save = view.findViewById(R.id._00_btn_save);
        toto_frag_cancel = view.findViewById(R.id._00_btn_cancel);

        //todo_frag_title.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/yekan.ttf"));
        //todo_frag_Description.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/yekan.ttf"));

        todo_frag_title.setText(Recived_title);
        todo_frag_Description.setText(Recived_Description);

//        if (Recived_ID == -1) {
//            todo_frag_Description.requestFocus();
//
//        }


        todo_frag_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String new_title = todo_frag_title.getText().toString();
                String new_Desc = todo_frag_Description.getText().toString();

                if (new_title.isEmpty() && new_Desc.isEmpty()) {

                    Toast.makeText(getContext(), "Write something bitch!", Toast.LENGTH_SHORT).show();

                } else {

                    if (Recived_ID == -1) {

                        SendBack(new_title, new_Desc, -1);
                        getActivity().onBackPressed();

                    } else {

                        SendBack(new_title, new_Desc, Recived_ID);
                        getActivity().onBackPressed();
                    }
                }

            }
        });

        toto_frag_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().onBackPressed();
            }
        });


        todo_frag_Description.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        return view;
    }


    public void SendBack(String new_title, String new_Desc, int new_id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(new_title, new_Desc, new_id);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String new_title, String new_Desc, int new_id);
    }



    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
