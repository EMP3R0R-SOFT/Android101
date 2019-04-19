package com.example.android101._18_toDo;


import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.annotation.NonNull;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android101.R;
import com.example.android101.nonui.SetWall;

import java.util.ArrayList;
import java.util.Collections;

public class _18_toDo_json extends AppCompatActivity implements _19_ToDo_Frag_input.OnFragmentInteractionListener {


    // 1)
    // یه آرایه تغریف میکنیم
    ArrayList<_18_toDo_json.ListFormat> Entry_list;

    private ArrayList<ListFormat> toDo_List;

    RecyclerView RC1;
    RecyclerView.Adapter rcAdapter;
    RecyclerView.LayoutManager rcLayoutManager;
    Button btn_new_toDo;
    Button btn_check;
    boolean activity_is_Blur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._18_to_do_json);

        SetWall.set(this);
        stage1_CreateList();

        activity_is_Blur = false;


        Log.d("TAG", "oncreate " + activity_is_Blur);
        btn_new_toDo = findViewById(R.id.toDO_btn_newToDo);
        btn_check = findViewById(R.id.toDO_btn_check);


        btn_new_toDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFragment("", "", -1);

            }
        });


    }

    private void stage1_CreateList() {

        Entry_list = new ArrayList<>();


        RC1 = findViewById(R.id.todo_rview);
        RC1.setHasFixedSize(true);
        rcLayoutManager = new LinearLayoutManager(this);
        rcAdapter = new rc_adapter(Entry_list);

        RC1.setLayoutManager(rcLayoutManager);
        RC1.setAdapter(rcAdapter);


        // recycler view ro be Touch helper attache mikonim...
        ItemTouchHelper ith1 = new ItemTouchHelper(TouchHelper);
        ith1.attachToRecyclerView(RC1);

    }


    private void insertItem(final String title, final String Description, final int position) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                SystemClock.sleep(250);
                Entry_list.add(position, new _18_toDo_json.ListFormat(title, Description, false));
                rcAdapter.notifyItemInserted(position);

            }
        }).start();


    }


    private void removeItem(int position) {
        Entry_list.remove(position);
        rcAdapter.notifyItemRemoved(position);


    }

    private void Modify_Alpha() {

        RelativeLayout RL = findViewById(R.id.todo_layout_for_alpha);

        if(activity_is_Blur){
            activity_is_Blur= false;
            RL.animate().alpha(1f).setDuration(500);

        }else {
            activity_is_Blur= true;
            RL.animate().alpha(0f).setDuration(500);

        }

    }

    @Override
    public void onBackPressed() {
        Modify_Alpha();

        super.onBackPressed();
    }


    public void openFragment(String Title, String Description, int ID) {


        Log.d("TAG", "open Fragment " + activity_is_Blur);

        _19_ToDo_Frag_input fragment = _19_ToDo_Frag_input.newInstance(Title, Description, ID);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in_250, R.anim.fade_out_250, R.anim.fade_in_250, R.anim.fade_out_250);
        transaction.addToBackStack(null); // وقتی تو فرگمنت دکمه بک بزنیم فقط فرمگمن بسته شه نه اکتیویتی
        transaction.add(R.id.todo_fragment_container, fragment, "TAG").commit();

        Modify_Alpha();
    }




    @Override
    public void onFragmentInteraction(String new_title, String new_Desc, int new_id) {


        if (new_id == -1) {

            if (new_title.equals("") && new_Desc.equals("")){
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }else{
                int ItemCount = rcAdapter.getItemCount();
                insertItem(new_title, new_Desc, ItemCount);
            }
        } else {

            TextView tit1 = RC1.findViewHolderForAdapterPosition(new_id).itemView.findViewById(R.id.txt_todo_title);
            TextView des1 = RC1.findViewHolderForAdapterPosition(new_id).itemView.findViewById(R.id.txt_todo_text);
            tit1.setText(new_title);
            des1.setText(new_Desc);

        }

        onBackPressed();
    }


    //####################################################################################################################################################################
    //####################################################################################################################################################################
    //####################################################################################################################################################################


    // کلاس برای حرکت آیتم ها
    private ItemTouchHelper.Callback TouchHelper = new ItemTouchHelper.Callback() {


        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            int currentPos = viewHolder.getAdapterPosition();
            int targetPos = target.getAdapterPosition();

            Collections.swap(Entry_list, currentPos, targetPos); // get the viewHolder's and target's positions in your adapter data, swap them
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
            Button btn_goup;
            Button btn_godown;


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
                btn_goup = itemView.findViewById(R.id.btn_todo_go_up);
                btn_godown = itemView.findViewById(R.id.btn_todo_go_down);


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

                btn_goup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        int fromPosition = getAdapterPosition();
                        int toPosition = fromPosition - 1;


                        if (0 <= toPosition && toPosition < toDo_List.size()) {


                            Collections.swap(toDo_List, fromPosition, fromPosition - 1);
                            notifyItemMoved(fromPosition, toPosition);
                        }


                    }
                });


                btn_godown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        int fromPosition = getAdapterPosition();
                        int toPosition = fromPosition + 1;

                        if (0 <= toPosition && toPosition < toDo_List.size()) {


                            Collections.swap(toDo_List, fromPosition, fromPosition + 1);
                            notifyItemMoved(fromPosition, toPosition);
                        }


                    }
                });


            }

            public  void testt(){

            }
        }


        private rc_adapter(ArrayList<ListFormat> list1) {
            toDo_List = list1;
        }

        @NonNull
        @Override
        public RC_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            // تعریف قالب کلی Recycler view
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._18_entry_theme, viewGroup, false);
            return new RC_ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RC_ViewHolder rc_viewHolder, int position) {

            Log.d("TAG","############################################################################### onBindViewHolder");
            // مقدار دهی میکنیم
            ListFormat currentItem = toDo_List.get(position);
            rc_viewHolder.txt_title.setText(currentItem.getItemTitle());
            rc_viewHolder.txt_text.setText(currentItem.getItemText());


            rc_viewHolder.txt_title.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/yekan.ttf"));
            rc_viewHolder.txt_text.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/yekan.ttf"));
        }


        @Override
        public int getItemCount() {
            return toDo_List.size();
        }
    }


}
