package com.example.android101;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class _18_toDo_json extends AppCompatActivity {


    // 1)
    // یه آرایه تغریف میکنیم
    List<_18_toDo_json.ListFormat> listToDO = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._18_to_do_json);

        stage1();



    }

    private void stage1() {


        listToDO.add(new _18_toDo_json.ListFormat("Change application's wallpaper.Change application's wallpaper." +
                "Change application's wallpaper.Change application's wallpaper.Change application's wallpaper.",1));
        listToDO.add(new _18_toDo_json.ListFormat("Shutdown the app.Change application's " +
                "wallpaper.Change application's wallpaper.",2));




        // 3)
        ArrayAdapter<ListFormat> adapter = new adapter2();
        ListView listView = findViewById(R.id.list_todo);
        listView.setAdapter(adapter);

    }



    // کلاس برای فرم آیتم ها
    public class ListFormat {

        // تعریف آیتم های لیست

        private String itemText;
        private int itemID;

        // ساخت متد کلاس
        public ListFormat(String itemText, int itemID) {

            this.itemText = itemText;
            this.itemID = itemID;}

        // getter
        public String getItemText() {return itemText;}
        public int getItemID() {return itemID;}

    }






    private class adapter2 extends ArrayAdapter<_18_toDo_json.ListFormat> {




        public adapter2() {
            super(getBaseContext(), R.layout._18_entry_theme, listToDO);
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            // مطمئن مشیشم که یه view وجود داره چون ممکنه جواب null بده
            View shitView = convertView;
            if (shitView==null){
                shitView=getLayoutInflater().inflate(R.layout._18_entry_theme,parent,false);
            }

            //  پیدا کردن آیتم های لیست با فرمت اون کلاسه که ساختیم
            _18_toDo_json.ListFormat CurrentShitItem2 = listToDO.get(position);

            // حالا پر میکنیم
            TextView txt_title = shitView.findViewById(R.id.txt_todo_title);
            txt_title.setText("#" + CurrentShitItem2.getItemID());

            TextView txt_text = shitView.findViewById(R.id.txt_todo_text);
            txt_text.setText(CurrentShitItem2.getItemText());



            Button btn_delete = shitView.findViewById(R.id.btn_todo_delete);
            Button btn_edit = shitView.findViewById(R.id.btn_todo_edit);
            Button btn_com = shitView.findViewById(R.id.btn_todo_complete);

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listToDO.remove(position);
                    notifyDataSetChanged();

                }
            });


            final LinearLayout test = shitView.findViewById(R.id.todo_entry);
            btn_com.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListView listView = findViewById(R.id.list_todo);


                    //test.setAlpha(0.5f);

                    if (test.getAlpha() >= 0.41f){
                        test.animate().alpha(0.4f).setDuration(500);
                    }else{
                        test.animate().alpha(1f).setDuration(500);
                    }
                    notifyDataSetChanged();
                }

            });



            return shitView;
        }

    }

        public View getViewByPosition(int pos, ListView listView) {
            final int firstListItemPosition = listView.getFirstVisiblePosition();
            final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

            if (pos < firstListItemPosition || pos > lastListItemPosition ) {
                return listView.getAdapter().getView(pos, null, listView);
            } else {
                final int childIndex = pos - firstListItemPosition;
                return listView.getChildAt(childIndex);
            }
        }



}
