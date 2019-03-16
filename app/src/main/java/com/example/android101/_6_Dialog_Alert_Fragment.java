package com.example.android101;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.TextView;

public class _6_Dialog_Alert_Fragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        DialogInterface.OnClickListener Listener1 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){




                TextView tv1 =  getActivity().findViewById(R.id.textViewDialog);
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        tv1.setText("PASS");
                        tv1.setTextColor(getResources().getColor(R.color.MyGreen));
                        tv1.setTextSize(72);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        tv1.setText("FAIL");
                        tv1.setTextColor(getResources().getColor(R.color.MyRED));
                        tv1.setTextSize(72);
                        break;

                }

            }

        };



        AlertDialog.Builder shit = new AlertDialog.Builder(getActivity());
        shit.setTitle("Hey yaa..");
        shit.setMessage("Really wanna do this shit !?");
        shit.setPositiveButton("yeah man", Listener1);
        shit.setNegativeButton("NO! Get me out of here...", Listener1);
        return shit.create();
    }
}
