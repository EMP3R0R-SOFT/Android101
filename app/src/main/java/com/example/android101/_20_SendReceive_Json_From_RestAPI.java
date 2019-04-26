package com.example.android101;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android101._0_AppController.AppController;

import java.util.HashMap;
import java.util.Map;

public class _20_SendReceive_Json_From_RestAPI extends AppCompatActivity {

    Button _20_send;
    Button _20_receive;

    TextView textView1;
    EditText editText1;
    ProgressBar progressBar;

    final String url = "http://emp3r0r.ir/api/20_RestAPI/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._20__send_receive__json__from__rest_api);


        _20_send = findViewById(R.id._20_send);
        _20_receive = findViewById(R.id._20_Recive);
        textView1 = findViewById(R.id._20_txt1);
        editText1 = findViewById(R.id._20_editor);
        progressBar= findViewById(R.id.progressBar);


        _20_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send();
            }
        });


        _20_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get();
            }
        });


    }


    private void Get() {
        textView1.setText("");
        progressBar.setVisibility(View.VISIBLE);
        StringRequest RequestGet = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressBar.setVisibility(View.INVISIBLE);
                            textView1.setText("< Server Response >\n" + response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressBar.setVisibility(View.INVISIBLE);
                        textView1.setText("<onErrorResponse>\n"+error);
                    }
                }
        ) {

            // باهاش اطلاعات رو وارد میکنیم
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("type", "get");
                params.put("key", "5050");
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(RequestGet);
    }


    private void send() {
        textView1.setText("");

        progressBar.setVisibility(View.VISIBLE);
        StringRequest RequestSendData = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            textView1.setText("< Server Response >\n" + response);
                            progressBar.setVisibility(View.INVISIBLE);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressBar.setVisibility(View.INVISIBLE);
                        textView1.setText("<onErrorResponse>\n"+error);
                    }
                }
        ) {

            // باهاش اطلاعات رو وارد میکنیم
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("type", "send");
                params.put("key", "5050");
                params.put("matn", editText1.getText().toString());
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(RequestSendData);
    }


}
