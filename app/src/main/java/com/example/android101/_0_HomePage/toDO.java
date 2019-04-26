package com.example.android101._0_HomePage;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.clans.fab.FloatingActionButton;

import android.support.v4.app.Fragment;


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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.android101.R;
import com.example.android101._0_AppController.AppController;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class toDO extends Fragment {

    final String TAG = "XXXXXXXX#####X";


    private ArrayList<ListFormat> toDo_List;
    RecyclerView RC1;
    RecyclerView.Adapter rcAdapter;
    RecyclerView.LayoutManager rcLayoutManager;
    ItemTouchHelper ith1;

    ;

    SmartRefreshLayout swiper;
    ProgressBar progressBar;
    boolean activity_is_Blur;
    private int progress_duration = 500;
    final String url = "http://emp3r0r.ir/api/TODO_API/index.php";


    public toDO() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout._00_todo, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        activity_is_Blur = false;


        progressBar = getActivity().findViewById(R.id._00_progressBar);
        final FloatingActionMenu fab_menu = getActivity().findViewById(R.id._00_FAB_Menu);
        FloatingActionButton fab_add = getActivity().findViewById(R.id._00_fab_add);
        FloatingActionButton fab_save = getActivity().findViewById(R.id._00_fab_save);
        FloatingActionButton fab_refresh = getActivity().findViewById(R.id._00_fab_refresh);
        FloatingActionButton fab_dummy = getActivity().findViewById(R.id._00_fab_dummy);
        swiper = getActivity().findViewById(R.id.refreshLayout);


        progressBar.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.btn_color_1),
                android.graphics.PorterDuff.Mode.SRC_IN);


//----------------------------------------------------------------------------------------------------------------


        fab_menu.setClosedOnTouchOutside(true);


        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment("", "", -1);
                fab_menu.close(true);
            }
        });

        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save_List();
                fab_menu.close(true);
            }
        });

        fab_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getList();
                fab_menu.close(true);


            }
        });

        fab_dummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ItemCount = rcAdapter.getItemCount();
                insertItem("Title " + ItemCount, "Dummy text ...", ItemCount, DateAndTime());
            }
        });


        swiper.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //refreshLayout.finishRefresh(2000);
                getList();

            }
        });




//----------------------------------------------------------------------------------------------------------------------------------


        getList();
        Log.d(TAG, "onActivityCreated");


    }


    private void getList() {

        progressBar.animate().alpha(1f).setDuration(progress_duration);


        StringRequest RequestGet = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            swiper.finishRefresh(2000);
                            progressBar.animate().alpha(0f).setDuration(progress_duration);

                            JsonElement jsonElement = new JsonParser().parse(response);
                            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
                            String json = gson.toJson(jsonElement);
                            Log.d("######", "<GET Response>\n" + json);


                            if (response != null) {
                                stage1_CreateList(response);
                            } else {
                                Toast.makeText(getContext(), "Null Response", Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        swiper.finishRefresh(2000);
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

    private void Save_List() {

        progressBar.animate().alpha(1f).setDuration(progress_duration);
        StringRequest RequestGet = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            swiper.finishRefresh(2000);
                            progressBar.animate().alpha(0f).setDuration(progress_duration);
                            Log.d("######", "<onResponse>\n" + response);

                            if (response != null) {
                                //Toast.makeText(_18_toDo_json.this, response, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Null Response", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swiper.finishRefresh(2000);
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


    private void stage1_CreateList(String response) {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ListFormat>>() {
        }.getType();

        toDo_List = gson.fromJson(response, type);


        if (toDo_List == null) {
            toDo_List = new ArrayList<>();
        }


        RC1 = getActivity().findViewById(R.id._00_todo_rc);
        RC1.setHasFixedSize(true);
        rcLayoutManager = new LinearLayoutManager(getContext());
        rcAdapter = new rc_adapter(toDo_List);
        RC1.setLayoutManager(rcLayoutManager);
        RC1.setAdapter(rcAdapter);


        // recycler view ro be Touch helper attache mikonim...
        // faghat 1 bar attach kon! (ke pull to refresh bug nashe)
        if (ith1 == null) {
            ith1 = new ItemTouchHelper(TouchHelper);
            ith1.attachToRecyclerView(RC1);
        }


    }


    private void insertItem(final String title, final String Description, final int position, final String Date) {
        toDo_List.add(position, new ListFormat(title, Description, false, Date));
        rcAdapter.notifyItemInserted(position);
        Save_List();
    }


    private void removeItem(int position) {
        toDo_List.remove(position);
        rcAdapter.notifyItemRemoved(position);
        Save_List();


    }

    private void completeItem(int position) {


    }


    public void Modify_Alpha() {

        RelativeLayout RL = getActivity().findViewById(R.id._00_todo_layout_for_alpha);

        if (activity_is_Blur) {
            activity_is_Blur = false;
            RL.animate().alpha(1f).setDuration(250);
        } else {
            activity_is_Blur = true;
            RL.animate().alpha(0f).setDuration(250);
        }

    }


    private void openFragment(String Title, String Description, int ID) {
        ((_0_HomePage_Theme) getActivity()).openInput(Title, Description, ID);
        Modify_Alpha();
    }


    public void feedback_from_mainActivity(String new_title, String new_Desc, int new_id) {


        int ItemCount = rcAdapter.getItemCount();

        String correct_title;

        // 1
        // چک میکنیم اگه اطلاعات خالی بود toodo جدید نسازه
        // 2
        // چک میکنیم اگه title نداشت بهش یه عنوان بده بر حسب ID


        if (new_id == -1) {

            if (new_title.equals("") && new_Desc.equals("")) {
                // Empty result
            } else {


                if (new_title.equals("")) {
                    correct_title = "ToDO #" + (ItemCount + 1);

                } else {
                    correct_title = new_title;
                }


                insertItem(correct_title, new_Desc, ItemCount, DateAndTime());
            }


        } else {

            if (new_title.equals("")) {
                correct_title = "ToDO #" + (new_id + 1);

            } else {
                correct_title = new_title;
            }


            toDo_List.get(new_id).itemTitle = correct_title;
            toDo_List.get(new_id).itemText = new_Desc;
            rcAdapter.notifyItemChanged(new_id);
            Save_List();

            //removeItem(new_id);
            //insertItem(correct_title, new_Desc, new_id);

//            TextView tit1 = RC1.findViewHolderForAdapterPosition(new_id).itemView.findViewById(R.id.txt_todo_title);
//            TextView des1 = RC1.findViewHolderForAdapterPosition(new_id).itemView.findViewById(R.id.txt_todo_text);
//            tit1.setText(correct_title);
//            des1.setText(new_Desc);

        }


    }


    //####################################################################################################################################################################
    //####################################################################################################################################################################
    //####################################################################################################################################################################


    private static String DateAndTime() {

        Calendar currentTime = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("h:mm a");

        int gy = currentTime.get(Calendar.YEAR);
        int gm = (currentTime.get(Calendar.MONTH) + 1);
        int gd = currentTime.get(Calendar.DAY_OF_MONTH);

        String time = df.format(currentTime.getTime());

        int[] g_d_m = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int jy;
        if (gy > 1600) {
            jy = 979;
            gy -= 1600;
        } else {
            jy = 0;
            gy -= 621;
        }
        int gy2 = (gm > 2) ? (gy + 1) : gy;
        int days = (365 * gy) + ((int) ((gy2 + 3) / 4)) - ((int) ((gy2 + 99) / 100)) + ((int) ((gy2 + 399) / 400)) - 80 + gd + g_d_m[gm - 1];
        jy += 33 * ((int) (days / 12053));
        days %= 12053;
        jy += 4 * ((int) (days / 1461));
        days %= 1461;
        if (days > 365) {
            jy += (int) ((days - 1) / 365);
            days = (days - 1) % 365;
        }
        int jm = (days < 186) ? 1 + (int) (days / 31) : 7 + (int) ((days - 186) / 30);
        int jd = 1 + ((days < 186) ? (days % 31) : ((days - 186) % 30));

        return jy + "/" + jm + "/" + jd + "-" + time;
    }


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
                    if (v.getAlpha() != 0.3f) {
                        v.animate().alpha(0.8f).setDuration(250);
                    }


                    swiper.setEnabled(false); // vaghti darim item jabeja mikonim, pull to refresh ro disable kon ta refresh nakone.

                } else if (actionState == ItemTouchHelper.ACTION_STATE_IDLE) {

                    // end of drag
                    // koochik kon
                    v.animate().scaleX(1f).scaleY(1f).setDuration(250);
                    if (v.getAlpha() != 0.3f) {
                        v.animate().alpha(1f).setDuration(250);
                    }

                    swiper.setEnabled(true);
                    Save_List(); // bad az move,chon position taghir karde, bayad save kone
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
        private String Date;

        // ساخت متد کلاس
        private ListFormat(String itemTitle, String itemText, boolean isDone, String Date) {
            this.itemText = itemText;
            this.itemTitle = itemTitle;
            this.isDone = isDone;
            this.Date = Date;
        }

        // getter
        private String getItemText() {
            return itemText;
        }

        private String getItemTitle() {
            return itemTitle;
        }

        private boolean getIisDone() {
            return isDone;
        }

        private String getDate() {
            return Date;
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
            TextView txt_date;
            TextView txt_time;


            Button btn_delete;
            Button btn_edit;
            //android.support.v7.widget.AppCompatCheckBox btn_com;
            RadioButton btn_com;
            LinearLayout btn_com_layout;


            private RC_ViewHolder(@NonNull View itemView) {
                super(itemView);

                // tarif mikonim

                todo_entry = itemView.findViewById(R.id.todo_entry);
                txt_title = itemView.findViewById(R.id.txt_todo_title);
                txt_text = itemView.findViewById(R.id.txt_todo_text);
                txt_date = itemView.findViewById(R.id.txt_todo_date);
                txt_time = itemView.findViewById(R.id.txt_todo_time);

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
                        int lastpos = (rcAdapter.getItemCount() - 1);


                        if (position != RecyclerView.NO_POSITION) {

                            if (toDo_List.get(position).isDone) {

                                toDo_List.get(position).isDone = false;
                                todo_entry.animate().alpha(1f);
                                Save_List();

                            } else {

                                toDo_List.get(position).isDone = true;
                                todo_entry.setAlpha(0.3f);
                                Collections.swap(toDo_List, position, lastpos);
                                rcAdapter.notifyItemMoved(position, lastpos);

                            }


                            Save_List();
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


            try {

                String[] Date0Time1 = currentItem.getDate().split("-");
                rc_viewHolder.txt_date.setText(Date0Time1[0]);
                rc_viewHolder.txt_time.setText(Date0Time1[1]);

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext(), "error: " + e.toString(), Toast.LENGTH_SHORT).show();
            }


            if (currentItem.getIisDone()) {
                rc_viewHolder.todo_entry.setAlpha(0.3f);
                rc_viewHolder.btn_com.setChecked(true);
            }

            //rc_viewHolder.txt_title.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/yekan.ttf"));
            //rc_viewHolder.txt_text.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/yekan.ttf"));
        }


        @Override
        public int getItemCount() {
            return toDo_List.size();
        }
    }

//
//    /**
//     * Case 1: User doesn't have permission
//     * Case 2: User has permission
//     *
//     * Case 3: User has never seen the permission Dialog
//     * Case 4: User has denied permission once but he din't clicked on "Never Show again" check box
//     * Case 5: User denied the permission and also clicked on the "Never Show again" check box.
//     * Case 6: User has allowed the permission
//     */
//
//    public void handlePermission() {
//        if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            Log.d(TAG,"1");
//            // This is Case 1. Now we need to check further if permission was shown before or not
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                // This is Case 4.
//                Log.d(TAG,"4");
//                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
//
//            } else {
//                Log.d(TAG,"3");
//                // This is Case 3. Request for permission here
//                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
//            }
//
//        } else {
//            Log.d(TAG,"2");
//            // This is Case 2. You have permission now you can do anything related to it
//        }
//
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//
//        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Log.d(TAG,"22");
//            // This is Case 2 (Permission is now granted)
//
//            FilltoDo();
//
//
//        } else {
//            // This is Case 1 again as Permission is not granted by user
//            Log.d(TAG,"11");
//
//            //Now further we check if used denied permanently or not
//            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                // case 4 User has denied permission but not permanently
//                Log.d(TAG,"44");
//
//            } else {
//                Log.d(TAG,"55");
//                // case 5. Permission denied permanently.
//                // You can open Permission setting's page from here now.
//
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Uri uri = Uri.fromParts("package",getActivity().getPackageName(), null);
//                intent.setData(uri);
//                startActivity(intent);
//
//
//            }
//
//        }
//    }


}
