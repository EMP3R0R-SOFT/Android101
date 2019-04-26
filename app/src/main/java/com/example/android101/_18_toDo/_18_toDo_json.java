package com.example.android101._18_toDo;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android101.R;
import com.example.android101._0_AppController.AppController;
import com.example.android101.nonui.SetWall;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class _18_toDo_json extends AppCompatActivity implements _19_ToDo_Frag_input.OnFragmentInteractionListener {


    // 1)
    // یه آرایه تغریف میکنیم

    private ArrayList<ListFormat> toDo_List;
    RecyclerView RC1;
    RecyclerView.Adapter rcAdapter;
    RecyclerView.LayoutManager rcLayoutManager;
    ItemTouchHelper ith1;

    Button btn_new_toDo;
    Button btn_check;
    Button btn_saveList;
    Button btn_LoadList;
    ProgressBar progressBar;
    boolean activity_is_Blur;
    private int progress_duration = 500;
    final String url = "http://emp3r0r.ir/api/TODO_API/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._18_to_do_json);
        SetWall.set(this);


        activity_is_Blur = false;

        btn_new_toDo = findViewById(R.id._18__btn_newToDo);
        btn_check = findViewById(R.id._18__btn_check);
        btn_saveList = findViewById(R.id._18__btn_savelist);
        btn_LoadList = findViewById(R.id._18__btn_loadlist);
        progressBar = findViewById(R.id._18_progressBar);

        progressBar.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.btn_color_1),
                android.graphics.PorterDuff.Mode.SRC_IN);

        btn_new_toDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFragment("", "", -1);


            }
        });

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ItemCount = rcAdapter.getItemCount();
                insertItem("Title " + ItemCount
                        , "Dummy text ...", ItemCount);
            }
        });


        btn_saveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Save_List();

            }
        });

        btn_LoadList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getList();

            }
        });


        getList();
    }

    private void getList() {

        progressBar.animate().alpha(1f).setDuration(progress_duration);


        StringRequest RequestGet = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressBar.animate().alpha(0f).setDuration(progress_duration);
                            Log.d("######", "<onErrorResponse>\n" + response);

                            if (response != null) {
                                stage1_CreateList(response);
                            } else {
                                Toast.makeText(_18_toDo_json.this, "Null Response", Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressBar.animate().alpha(0f).setDuration(progress_duration);
                        Log.d("######", "<onErrorResponse>\n" + error);
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

    private void stage1_CreateList(String response) {


        //-------------------------------------------------------------------------------------------------------------------
        // Read From SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("TODO_List", Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString("List1", null);
//        Type type = new TypeToken<ArrayList<ListFormat>>() {
//        }.getType();
//
//        toDo_List = gson.fromJson(json, type);
//
//
//        if (toDo_List == null) {
//            toDo_List = new ArrayList<>();
//        }

        //-------------------------------------------------------------------------------------------------------------------


        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ListFormat>>() {}.getType();

        toDo_List = gson.fromJson(response, type);


        if (toDo_List == null) {
            toDo_List = new ArrayList<>();
        }


        RC1 = findViewById(R.id._18_todo_rview);
        RC1.setHasFixedSize(true);
        rcLayoutManager = new LinearLayoutManager(this);
        rcAdapter = new rc_adapter(toDo_List);
        RC1.setLayoutManager(rcLayoutManager);
        RC1.setAdapter(rcAdapter);



        // recycler view ro be Touch helper attache mikonim...
        ith1 = new ItemTouchHelper(TouchHelper);
        ith1.attachToRecyclerView(RC1);

    }


    private void insertItem(final String title, final String Description, final int position) {


        toDo_List.add(position, new _18_toDo_json.ListFormat(title, Description, false));
        rcAdapter.notifyItemInserted(position);
        Save_List();


    }


    private void removeItem(int position) {
        toDo_List.remove(position);
        rcAdapter.notifyItemRemoved(position);
        Save_List();


    }

    private void Modify_Alpha() {

        RelativeLayout RL = findViewById(R.id.todo_layout_for_alpha);

        if (activity_is_Blur) {
            activity_is_Blur = false;
            RL.animate().alpha(1f).setDuration(250);

        } else {
            activity_is_Blur = true;
            RL.animate().alpha(0f).setDuration(250);

        }

    }



    public void openFragment(String Title, String Description, int ID) {


        Log.d("TAG", "open Fragment " + activity_is_Blur);

        _19_ToDo_Frag_input fragment = _19_ToDo_Frag_input.newInstance(Title, Description, ID);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in_250, R.anim.fade_out_250, R.anim.fade_in_250, R.anim.fade_out_250);
        transaction.addToBackStack(null); // وقتی تو فرگمنت دکمه بک بزنیم فقط فرمگمن بسته شه نه اکتیویتی
        transaction.add(R.id._18_input_fragment_container_, fragment, "TAG").commit();

        Modify_Alpha();
    }


    @Override
    public void onFragmentInteraction(String new_title, String new_Desc, int new_id) {

        int ItemCount = rcAdapter.getItemCount();
        String correct_title;

        // 1
        // چک میکنیم اگه اطلاعات خالی بود toodo جدید نسازه
        // 2
        // چک میکنیم اگه title نداشت بهش یه عنوان بده بر حسب ID


        if (new_id == -1) {

            if (new_title.equals("") && new_Desc.equals("")) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            } else {


                if (new_title.equals("")) {
                    correct_title = "ToDO #" + (ItemCount + 1);

                } else {
                    correct_title = new_title;
                }


                insertItem(correct_title, new_Desc, ItemCount);
            }


        } else {

            if (new_title.equals("")) {
                correct_title = "ToDO #" + (new_id + 1);

            } else {
                correct_title = new_title;
            }

            TextView tit1 = RC1.findViewHolderForAdapterPosition(new_id).itemView.findViewById(R.id.txt_todo_title);
            TextView des1 = RC1.findViewHolderForAdapterPosition(new_id).itemView.findViewById(R.id.txt_todo_text);
            tit1.setText(correct_title);
            des1.setText(new_Desc);

        }


        onBackPressed();
    }


    private void Save_List() {

        //---------------------------------------------------------------------------------------------------------------------------
        // Save ba shared Preferences
//        SharedPreferences sharedPreferences = getSharedPreferences("TODO_List", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        Gson gson = new Gson();
//        String json = gson.toJson(toDo_List);
//        editor.putString("List1", json);
//        editor.apply();
        //---------------------------------------------------------------------------------------------------------------------------

        progressBar.animate().alpha(1f).setDuration(progress_duration);
        StringRequest RequestGet = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressBar.animate().alpha(0f).setDuration(progress_duration);
                            Log.d("######", "<onErrorResponse>\n" + response);

                            if (response != null) {
                                //Toast.makeText(_18_toDo_json.this, response, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(_18_toDo_json.this, "Null Response", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressBar.animate().alpha(0f).setDuration(progress_duration);
                        Log.d("######", "<onErrorResponse>\n" + error);
                    }
                }
        ) {

            // باهاش اطلاعات رو وارد میکنیم
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                Gson gson = new Gson();
                String json = gson.toJson(toDo_List);

                params.put("type", "send");
                params.put("key", "5050");
                params.put("matn", json);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(RequestGet);


    }


    //####################################################################################################################################################################
    //####################################################################################################################################################################
    //####################################################################################################################################################################


    //####################################################################################################################################################################
    //####################################################################################################################################################################
    //####################################################################################################################################################################



    // کلاس برای حرکت آیتم ها با تاچ
    private ItemTouchHelper.Callback TouchHelper = new ItemTouchHelper.Callback() {
        View v;


        @Override
        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {


            try {
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {

                    // start drag
                    // bozorg kon
                    v = viewHolder.itemView;
                    v.animate().scaleX(1.05f).scaleY(1.05f).setDuration(250);
                    v.animate().alpha(0.8f).setDuration(250);

                } else if (actionState == ItemTouchHelper.ACTION_STATE_IDLE) {

                    // end of drag
                    // koochik kon
                    v.animate().scaleX(1f).scaleY(1f).setDuration(250);
                    v.animate().alpha(1f).setDuration(250);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            super.onSelectedChanged(viewHolder, actionState);
        }

        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            int currentPos = viewHolder.getAdapterPosition();
            int targetPos = target.getAdapterPosition();

            Collections.swap(toDo_List, currentPos, targetPos);
            rcAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());    //  notify
            Save_List(); // bad az move,chon position taghir karde, bayad save kone
            return true;

        }


        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //TODO
        }


        //defines the enabled move directions in each state (idle, swiping, dragging).
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                    ItemTouchHelper.DOWN | ItemTouchHelper.UP);
        }


    };


    // کلاس برای فرم آیتم ها
    private class ListFormat {

        // تعریف آیتم های لیست

        private String itemText;
        private String itemTitle;
        private boolean isDone;

        // ساخت متد کلاس
        private ListFormat(String itemTitle, String itemText, boolean isDone) {
            this.itemText = itemText;
            this.itemTitle = itemTitle;
            this.isDone = isDone;
        }

        // getter
        private String getItemText() {
            return itemText;
        }

        private String getItemTitle() {
            return itemTitle;
        }

        public boolean getIisDone() {
            return isDone;
        }
    }


    /**
     * in adaptor bayad to ye classe dige bashe
     * rc_adapter = esme class baraye formate itema
     * toDo_List = esme
     */
    public class rc_adapter extends RecyclerView.Adapter<rc_adapter.RC_ViewHolder> {


        private class RC_ViewHolder extends RecyclerView.ViewHolder {

            // tarif mikonim
            LinearLayout todo_entry;
            TextView txt_title;
            TextView txt_text;
            Button btn_delete;
            Button btn_edit;
            android.support.v7.widget.AppCompatCheckBox btn_com;
            LinearLayout btn_com_layout;



            private RC_ViewHolder(@NonNull View itemView) {
                super(itemView);

                // tarif mikonim

                todo_entry = itemView.findViewById(R.id.todo_entry);
                txt_title = itemView.findViewById(R.id.txt_todo_title);
                txt_text = itemView.findViewById(R.id.txt_todo_text);

                btn_delete = itemView.findViewById(R.id.btn_todo_delete);
                btn_edit = itemView.findViewById(R.id.btn_todo_edit);
                btn_com = itemView.findViewById(R.id.btn_todo_complete);
                btn_com_layout = itemView.findViewById(R.id.btn_todo_complete_layout);



                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            removeItem(position);
                        }

                    }
                });


                // واسه سنتر کردن چک باکس از لاینر لایوت استفاده کردیم
                // خود لایاوت رو هم کلیک خور میکنیم تا رو چک باکس کلیک کنه
                btn_com_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_com.performClick();
                    }
                });


                btn_com.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {


                            if (toDo_List.get(position).isDone) {

                                toDo_List.get(position).isDone = false;
                                todo_entry.animate().alpha(1f).setDuration(500);

                            } else {

                                toDo_List.get(position).isDone = true;
                                todo_entry.animate().alpha(0.3f).setDuration(500);

                            }

                        }

                    }
                });

                btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int E_ID = getAdapterPosition();

                        openFragment(txt_title.getText().toString(), txt_text.getText().toString(), E_ID);

                    }
                });

//                btn_goup.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//                        int fromPosition = getAdapterPosition();
//                        int toPosition = fromPosition - 1;
//
//
//                        if (0 <= toPosition && toPosition < toDo_List.size()) {
//
//
//                            Collections.swap(toDo_List, fromPosition, fromPosition - 1);
//                            notifyItemMoved(fromPosition, toPosition);
//                        }
//
//
//                    }
//                });
//
//
//                btn_godown.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//                        int fromPosition = getAdapterPosition();
//                        int toPosition = fromPosition + 1;
//
//                        if (0 <= toPosition && toPosition < toDo_List.size()) {
//
//
//                            Collections.swap(toDo_List, fromPosition, fromPosition + 1);
//                            notifyItemMoved(fromPosition, toPosition);
//                        }
//
//
//                    }
//                });


            }

            public void testt() {

            }
        }


        private rc_adapter(ArrayList<ListFormat> list1) {
            toDo_List = list1;
        }

        @NonNull
        @Override
        public RC_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            // تعریف قالب کلی Recycler view
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._00_todo_entry_theme, viewGroup, false);
            return new RC_ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RC_ViewHolder rc_viewHolder, int position) {


            // مقدار دهی میکنیم
            ListFormat currentItem = toDo_List.get(position);
            rc_viewHolder.txt_title.setText(currentItem.getItemTitle());
            rc_viewHolder.txt_text.setText(currentItem.getItemText());


            //rc_viewHolder.txt_title.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/yekan.ttf"));
            //rc_viewHolder.txt_text.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/yekan.ttf"));
        }


        @Override
        public int getItemCount() {
            return toDo_List.size();
        }
    }


}
