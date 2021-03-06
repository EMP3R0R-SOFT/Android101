package com.example.android101._0_HomePage;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android101.R;
import com.example.android101._6_Login_Page_Splash;
import com.example.android101._11_Simple_ListView;
import com.example.android101._12_Play_Video;
import com.example.android101._13_Shared_Element_Activity_Transition;
import com.example.android101._14_Miladi_be_Shamsi;
import com.example.android101._15_JobScheduler;
import com.example.android101._17_JSON_VolleyLibrary;
import com.example.android101._18_toDo._18_toDo_json;
import com.example.android101._19_Notification;
import com.example.android101._1_SharedPreferences_example;
import com.example.android101._20_SendReceive_Json_From_RestAPI;
import com.example.android101._2_UseClass_example;
import com.example.android101._3_UseClass2_example;
import com.example.android101._4_ListView_example;
import com.example.android101._5_Dialog_Alert_example;
import com.example.android101._7_Background_Async_Task_example;
import com.example.android101._8_Dynamic_Buttons_example;
import com.example.android101._9_Pass_data_using_intent_1_example;
import com.example.android101.nonui.SetWall;


public class _0_HomePage_Theme extends AppCompatActivity implements toDo_frag_input.OnFragmentInteractionListener {

    ViewPager viewPager;
    Myadapter myadapter;
    TabLayout tabLayout;



    //-----------------------------------------------------------------------------------------------------------------------
    public void openInput(String Title, String Description, int ID) {

        toDo_frag_input fragment_input = toDo_frag_input.newInstance(Title, Description, ID);
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction_input = fragManager.beginTransaction();
        transaction_input.setCustomAnimations(R.anim.fade_in_250, R.anim.fade_out_250, R.anim.fade_in_250, R.anim.fade_out_250);
        transaction_input.addToBackStack(null); // وقتی تو فرگمنت دکمه بک بزنیم فقط فرمگمن بسته شه نه اکتیویتی
        transaction_input.add(R.id._00_input_fragment_container_, fragment_input, "TAG");
        transaction_input.commit();

    }


    @Override
    public void onFragmentInteraction(String new_title, String new_Desc, int new_id) {

        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + 2);
        toDO ff = (toDO) page;
        ff.feedback_from_mainActivity(new_title, new_Desc, new_id);

    }


    @Override
    public void onBackPressed() {


        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();

            Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + 2);
            toDO ff = (toDO) page;
            ff.Modify_Alpha();

        }

    }



    //-----------------------------------------------------------------------------------------------------------------------
    public class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0:
                    return new functions();

                case 1:
                    return new Countdown();

                default:
                    return new toDO();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Functions";
                case 1:
                    return "CountDown";
                default:
                    return "toDo";
            }
        }
    }

    public static class Myfragment extends Fragment {

        public static Fragment getfragment(int position) {
            Myfragment myfragment = new Myfragment();
            Bundle args = new Bundle();
            args.putInt("pos", position);
            myfragment.setArguments(args);
            return myfragment;
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
        setContentView(R.layout._00_act_main);


        // new content
        //------------------------------------------------------------------------------------------------------------------
        viewPager = findViewById(R.id.pager);
        myadapter = new Myadapter(getSupportFragmentManager());
        tabLayout = findViewById(R.id.tabber);
        viewPager.setOffscreenPageLimit(2); // vase inke vaghti tab click kardi az aval fragmento reload nakone ( 2 = tedade page ha)
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(myadapter);

        viewPager.setCurrentItem(1); // وقنی برنامه باز شد کدوم صفحه به صورت پیشفرض نمایش داده بشه (صفحه دوم)

        // tanzime icon e tabbar
        int[] tabIcons = {
                R.drawable.ic_functions_24dp,
                R.drawable.ic_countdown_clock_24dp,
                R.drawable.ic_todo_check_24dp
        };
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);


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


    }


    public void btn_Click(View v) {


        switch (v.getId()) {

            case R.id.button1:
                startActivity(new Intent(this, _1_SharedPreferences_example.class));
                break;

            case R.id.button2:
                startActivity(new Intent(this, _2_UseClass_example.class));
                break;

            case R.id.button3:
                startActivity(new Intent(this, _3_UseClass2_example.class));
                break;

            case R.id.button4:
                startActivity(new Intent(this, _4_ListView_example.class));
                break;

            case R.id.button5:
                startActivity(new Intent(this, _5_Dialog_Alert_example.class));
                break;

            case R.id.button6:
                startActivity(new Intent(this, _7_Background_Async_Task_example.class));
                break;

            case R.id.button7:
                startActivity(new Intent(this, _8_Dynamic_Buttons_example.class));
                //overridePendingTransition(R.anim.slide_in_right_500, R.anim.slide_out_left_500);
                break;

            case R.id.button8:
                startActivity(new Intent(this, _9_Pass_data_using_intent_1_example.class));
                break;

            case R.id.button9:
                Toast.makeText(this, "non-UI function", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(Intent.ACTION_SENDTO);
                myIntent.setData(Uri.fromParts("mailto", "asd@lol.ir", null));
                myIntent.putExtra(Intent.EXTRA_SUBJECT, "عنوان");
                myIntent.putExtra(Intent.EXTRA_TEXT, "متن ایمیل");
                startActivity(Intent.createChooser(myIntent, "انتخاب پیام رسان"));
                break;

            case R.id.button10:
                startActivity(new Intent(this, _11_Simple_ListView.class));
                break;

            case R.id.button11:
                startActivity(new Intent(this, _12_Play_Video.class));
                break;

            case R.id.button12:
                startActivity(new Intent(this, _13_Shared_Element_Activity_Transition.class));
                break;

            case R.id.button13:
                startActivity(new Intent(this, _6_Login_Page_Splash.class));
                break;

            case R.id.button14:
                startActivity(new Intent(this, _14_Miladi_be_Shamsi.class));
                break;

            case R.id.button15:
                startActivity(new Intent(this, _15_JobScheduler.class));
                break;

            case R.id.button16:
                Toast.makeText(this, "non-UI Function", Toast.LENGTH_LONG).show();
                break;
            case R.id.button17:
                startActivity(new Intent(this, _17_JSON_VolleyLibrary.class));
                break;

            case R.id.button18:
                startActivity(new Intent(this, _18_toDo_json.class));
                break;

            case R.id.button19:
                startActivity(new Intent(this, _19_Notification.class));
                break;

            case R.id.button20:
                startActivity(new Intent(this, _20_SendReceive_Json_From_RestAPI.class));
                break;

            default:
                break;
        }

    }

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
