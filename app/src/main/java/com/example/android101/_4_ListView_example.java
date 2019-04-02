package com.example.android101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android101.nonui.SetWall;

import java.util.ArrayList;
import java.util.List;


public class _4_ListView_example extends AppCompatActivity {

    // 1)
    // یه آرایه تغریف میکنیم
    List<MakeListClass> mylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_example);

        SetWall.set(this);


        ijadeList();       //////
        clickListener();
    }


    // کلاس برای فرم آیتم ها
    public class MakeListClass {

        // تعریف آیتم های لیست
        private String itemTitle;
        private String itemDescription;

        // ساخت متد کلاس
        public MakeListClass(String itemTitle, String itemDescription) {
            this.itemTitle = itemTitle;
            this.itemDescription = itemDescription; }

        // getter
        public String getItemTitle() {return itemTitle;}
        public String getItemDescription() {return itemDescription;}

    }



    //  2)
    // یه adaptor میسازیم (سخته ساختنش)
    // به اسم adaptor1

    private class adapter1 extends ArrayAdapter<MakeListClass>{

        // alt + enter  میزنیم پایینی رو میسازه
        public adapter1() {
            super(getBaseContext(), R.layout.list_item_layout,mylist);
        }

        // alt = ins
        // override method
        // getView
        // تا پایینی رو بسازه بعد اضافی هارو پاک میکنیم
        @Override
        public View getView(int position,  View convertView, ViewGroup parent) {

            // مطمئن مشیشم که یه view وجود داره چون ممکنه جواب null بده
            View shitView = convertView;
            if (shitView==null){
                shitView=getLayoutInflater().inflate(R.layout.list_item_layout,parent,false);
            }

            //  پیدا کردن آیتم های لیست با فرمت اون کلاسه که ساختیم
            MakeListClass CurrentShitItem = mylist.get(position);

            // حالا پر میکنیم
            TextView textView1 = shitView.findViewById(R.id.item_textView1);
            textView1.setText(CurrentShitItem.getItemTitle());

            TextView textView2 = shitView.findViewById(R.id.item_textView2);
            textView2.setText(CurrentShitItem.getItemDescription());

            return shitView;
        }

    }





    private void ijadeList() {

        // 2)
        // یه لیست آرایه توسط کلاسی که ساختیم میسازیم
        mylist.add(new MakeListClass("Wallpaper","Change application's wallpaper."));
        mylist.add(new MakeListClass("Exit","Shutdown the app."));

        // 3)
        ArrayAdapter<MakeListClass> adapter = new adapter1();
        ListView listView = findViewById(R.id.list1);
        listView.setAdapter(adapter);
    }


    // کلیک خور کردن ایتم ها
    private void clickListener() {

        ListView listView = findViewById(R.id.list1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){

                    Toast.makeText(_4_ListView_example.this, "wallpaper", Toast.LENGTH_SHORT).show();

                }
                if (position == 1){
                    Toast.makeText(_4_ListView_example.this, "exit", Toast.LENGTH_SHORT).show();

                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                    homeIntent.addCategory( Intent.CATEGORY_HOME );
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                    //finish();

                }
            }
        });

    }






}

