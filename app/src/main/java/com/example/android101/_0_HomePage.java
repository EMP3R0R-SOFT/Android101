package com.example.android101;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.example.android101.fragments.Dashboard;
import com.example.android101.fragments.functions;
import com.example.android101.fragments.toDO;
import com.example.android101.nonui.SetWall;


public class _0_HomePage extends AppCompatActivity {




    public class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    Dashboard t1 = new Dashboard();
                    return  t1;
                case 1:
                    toDO t2 = new toDO();
                    return t2;

                    default:
                        functions t3 = new functions();
                        return t3;
            }

            //return Myfragment.getfragment(position+1);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0 : return "Dashboard";
                default: return "Functions";
                case 1 : return "toDo";
            }
        }
    }
    public static class Myfragment extends Fragment{

        public static Fragment getfragment(int position)
        {
            Myfragment myfragment = new Myfragment();
            Bundle args = new Bundle();
            args.putInt("pos",position);
            myfragment.setArguments(args);
            return myfragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            int i = getArguments().getInt("pos");
            if (i==1)
            {

                View view = inflater.inflate(R.layout.fragment_dashboard,container,false);
                return view;
            }
            else if (i==2){

                View view = inflater.inflate(R.layout.fragment_to_do,container,false);
                return view;

            }
            else
            {

                View view = inflater.inflate(R.layout._0_activity_home_page,container,false);
                return view;
            }
        }
    }

    @Override
    protected void onResume() {

        SetWall.set(this);
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._0_act_main);


        // new content
        //------------------------------------------------------------------------------------------------------------------
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        Myadapter myadapter = new Myadapter(getSupportFragmentManager());
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabber);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(myadapter);


        //------------------------------------------------------------------------------------------------------------------







        SetWall.set(this);

        //transparentStatus();
        //transparentNavigation();


//        // clear FLAG_TRANSLUCENT_STATUS flag:
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//
//        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//        // finally change the color
//        int statusColor = Color.parseColor("#FF0000");
//        getWindow().setStatusBarColor(statusColor);
//        getWindow().setNavigationBarColor(statusColor);







        Fade fade = new Fade();
        fade.setDuration(1000);
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setEnterTransition(fade);
        getWindow().setReturnTransition(fade);

        getWindow().getSharedElementEnterTransition().setDuration(800);
        getWindow().getSharedElementReturnTransition().setDuration(800).setInterpolator(new DecelerateInterpolator());


        View p1 = findViewById(R.id.scrollView1);
        setTypeFace_findViews(p1);


        //Setup_buttomNav();


        //change navbutton and toolbar color (match to status bar color)
        //setNavColors();



//        final Button button1 = findViewById(R.id.button1);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(getBaseContext(), _1_SharedPreferences_example.class));
//            }
//        });
//
//        Button button2 = findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), _2_UseClass_example.class));
//            }
//        });
//
//        Button button3 = findViewById(R.id.button3);
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), _3_UseClass2_example.class));
//            }
//        });
//
//        Button button4 = findViewById(R.id.button4);
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), _4_ListView_example.class));
//            }
//        });
//
//        Button button5 = findViewById(R.id.button5);
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), _5_Dialog_Alert_example.class));
//            }
//        });
//
//        Button button6 = findViewById(R.id.button6);
//        button6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), _7_Background_Async_Task_example.class));
//            }
//        });
//
//        Button button7 = findViewById(R.id.button7);
//        button7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), _8_Dynamic_Buttons_example.class));
//                overridePendingTransition(R.anim.slide_in_right_500, R.anim.slide_out_left_500);
//
//
//            }
//        });
//
//        Button button8 = findViewById(R.id.button8);
//        button8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), _9_Pass_data_using_intent_1_example.class));
//            }
//        });
//
//        // ارسال ایمیل
//        Button button9 = findViewById(R.id.button9);
//        button9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent myIntent = new Intent(Intent.ACTION_SENDTO);
//                myIntent.setData(Uri.fromParts("mailto", "asd@lol.ir", null));
//                myIntent.putExtra(Intent.EXTRA_SUBJECT, "عنوان");
//                myIntent.putExtra(Intent.EXTRA_TEXT, "متن ایمیل");
//                startActivity(Intent.createChooser(myIntent, "انتخاب پیام رسان"));
//            }
//        });
//
//        Button button10 = findViewById(R.id.button10);
//        button10.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), _11_Simple_ListView.class));
//            }
//        });
//
//        Button button11 = findViewById(R.id.button11);
//        button11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), _12_Play_Video.class));
//            }
//        });
//
//        final Button button12 = findViewById(R.id.button12);
//        button12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), _13_Shared_Element_Activity_Transition.class));
//            }
//        });
//
//
//        final Button button13 = findViewById(R.id.button13);
//        button13.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(), _Login_Page_Splash.class));
//            }
//        });


    }

//    private void setNavColors() {
//
//        //getWindow().getStatusBarColor();
//
//        LinearLayout Layoutheader1 = findViewById(R.id.Layoutheader1);
//        BottomNavigationView BottomNav1 = findViewById(R.id.BottomNav1);
//
//        Layoutheader1.setBackgroundColor(getWindow().getStatusBarColor());
//        BottomNav1.setBackgroundColor(getWindow().getStatusBarColor());
//    }



//    private void Setup_buttomNav() {
//        final LinearLayout navdraw1 = findViewById(R.id.navdraw1);
//        final RelativeLayout homepage_screen = findViewById(R.id.homepage_screen);
//        BottomNavigationView BottomNav1 = findViewById(R.id.BottomNav1);
//
//
//        final float transX = homepage_screen.getTranslationX();
//        //final int anim_duration_1 = 500;
//        final Animation animNav = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in_1000);
//        BottomNav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//                switch (menuItem.getItemId()) {
//
//                    case R.id.menu_developer:
//
//                        homepage_screen.animate().translationX(0);
//                        navdraw1.animate().translationX(0);
//                        navdraw1.startAnimation(animNav);
//                        break;
//
//                    case R.id.menu_home:
//
//                        homepage_screen.animate().translationX(transX);
//                        navdraw1.animate().translationX(transX);
//
//                        break;
//
//                    case R.id.menu_todo:
//
//                        Toast.makeText(_0_HomePage.this, "toDO Clicked!", Toast.LENGTH_SHORT).show();
//                        break;
//
//                }
//                return true;
//            }
//        });
//    }


    // بی رنگ کردن استاتوس  بار
    private void transparentStatus() {
        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    // بی رنگ کردن نویگیشن بار
    private void transparentNavigation() {
        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }


    private void setWindowFlag(final int bits, boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    // انتخاب تمام textView با دادن parent
    public void setTypeFace_findViews(View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    // recursively call this method
                    setTypeFace_findViews(child);
                }
            } else if (v instanceof TextView) {
                //do whatever you want ...
                ((TextView) v).setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/yekan.ttf"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
