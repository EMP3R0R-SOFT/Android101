package com.example.android101._18_toDo;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android101.R;


public class _19_ToDo_Frag_input extends Fragment {


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


    public _19_ToDo_Frag_input() {
        // Required empty public constructor
    }


    public static _19_ToDo_Frag_input newInstance(String stringTitle, String stringDescription, int intID) {
        _19_ToDo_Frag_input fragment = new _19_ToDo_Frag_input();
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
        View view = inflater.inflate(R.layout.fragment__19__to_do__frag_input, container, false);

        todo_frag_title = view.findViewById(R.id.todo_frag_input_title);
        todo_frag_Description = view.findViewById(R.id.todo_frag_input_description);
        todo_frag_save = view.findViewById(R.id.toDO__frag_btn_save);
        toto_frag_cancel = view.findViewById(R.id.toDO__frag_btn_cancel);

        todo_frag_title.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/yekan.ttf"));
        todo_frag_Description.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/yekan.ttf"));
        todo_frag_title.setText(Recived_title);
        todo_frag_Description.setText(Recived_Description);

        if (Recived_ID == -1) {
            todo_frag_Description.requestFocus();

        }


        todo_frag_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String new_title = todo_frag_title.getText().toString();
                String new_Desc = todo_frag_Description.getText().toString();

                if (Recived_ID == -1) {


                    SendBack(new_title, new_Desc, -1);

                }else{
                    SendBack(new_title, new_Desc, Recived_ID);

                }


            }
        });

        toto_frag_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }


    public void SendBack(String new_title, String new_Desc,int new_id ){
        if (mListener != null) {
            mListener.onFragmentInteraction(new_title, new_Desc,new_id);
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
        void onFragmentInteraction(String new_title, String new_Desc,int new_id);
    }
}
