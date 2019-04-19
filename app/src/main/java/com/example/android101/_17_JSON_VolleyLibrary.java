package com.example.android101;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android101._0_AppController.AppController;
import com.example.android101.nonui.SetWall;

import org.json.JSONException;
import org.json.JSONObject;

public class _17_JSON_VolleyLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._17__json__volley_library);
        SetWall.set(this);


        Button btn1 = findViewById(R.id.btn_parse);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parse();
            }
        });



    }




    public void Parse() {

        final TextView txt_response = findViewById(R.id.txt_response);
        final String url = "https://api.ipify.org/?format=json";

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {


                    txt_response.setText("IP: " + response.getString("ip"));

                    progressBar.setVisibility(View.INVISIBLE);

                    Log.v("CURRENCY-0", response.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.INVISIBLE);

//                NetworkResponse networkResponse = error.networkResponse;
//                if (networkResponse != null && networkResponse.data != null) {
//                    String jsonError = new String(networkResponse.data);
//                    // Print Error!
//                    Log.v("CURRENCY-1", jsonError);
//                }else{

                    Log.v("CURRENCY-2", error.toString());
                    Toast.makeText(_17_JSON_VolleyLibrary.this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
//                }
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
