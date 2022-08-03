package com.example.yoyoiq;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.yoyoiq.Adapter.PageAdapterPlayer;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateTeamActivity extends AppCompatActivity {
    TextView backPress;
    Button continueBtn;
    String match_id, matchA, matchB, logo_url_a, logo_url_b, date_start, date_end;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem tabItem1, tabItem2, tabItem3, tabItem4;
    PageAdapterPlayer pageAdapterPlayer;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    CircleImageView imageViewA, imageViewB;
    TextView textViewA, textViewB;
    private String EVENT_DATE_TIME = "2022-07-23 18:08:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private TextView tv_days, tv_done;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team_actvity);
        initMethod();
        setAction();
        countDownStart();
        linearLayout1.setVisibility(View.VISIBLE);

        pageAdapterPlayer = new PageAdapterPlayer(getSupportFragmentManager(), tabLayout.getTabCount(), match_id, matchA, matchB, logo_url_a, logo_url_b);
        viewPager.setAdapter(pageAdapterPlayer);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                    pageAdapterPlayer.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);
                    pageAdapterPlayer.notifyDataSetChanged();
                } else if (tab.getPosition() == 2) {
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.GONE);
                    pageAdapterPlayer.notifyDataSetChanged();
                } else if (tab.getPosition() == 3) {
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    pageAdapterPlayer.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void initMethod() {
        linearLayout1 = findViewById(R.id.linerLayout1);
        linearLayout2 = findViewById(R.id.linerLayout2);
        linearLayout3 = findViewById(R.id.linerLayout3);
        linearLayout4 = findViewById(R.id.linerLayout4);

        tv_days = findViewById(R.id.tv_days);
        tv_done = findViewById(R.id.done);

        backPress = findViewById(R.id.backPress);
        continueBtn = findViewById(R.id.Continue);

        match_id = getIntent().getStringExtra("match_id");
        matchA = getIntent().getStringExtra("matchA");
        matchB = getIntent().getStringExtra("matchB");
        logo_url_a = getIntent().getStringExtra("logo_url_a");
        logo_url_b = getIntent().getStringExtra("logo_url_b");
        date_start = getIntent().getStringExtra("date_start");
        date_end = getIntent().getStringExtra("date_end");

        tabLayout = findViewById(R.id.tabLayout);
        tabItem1 = findViewById(R.id.WK);
        tabItem2 = findViewById(R.id.BAT);
        tabItem3 = findViewById(R.id.AR);
        tabItem4 = findViewById(R.id.BOWL);
        viewPager = findViewById(R.id.viewPager);

        imageViewA = findViewById(R.id.im_Team1);
        imageViewB = findViewById(R.id.im_Team2);
        textViewA = findViewById(R.id.tvHead_TeamOneName);
        textViewB = findViewById(R.id.tvHead_TeamTwoName);
    }

    private void setAction() {
        backPress.setOnClickListener(view -> onBackPressed());

        textViewA.setText(matchA);
        textViewB.setText(matchB);

        Glide.with(this)
                .load(logo_url_a)
                .into(imageViewA);

        Glide.with(this)
                .load(logo_url_b)
                .into(imageViewB);
    }

    private void countDownStart() {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    Date event_date = dateFormat.parse(date_start);
                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;
                        tv_days.setText(String.format("%02d", Days) + " " + String.format("%02d", Hours) + "h " + String.format("%02d", Minutes) + "m " + String.format("%02d", Seconds) + "s " + " left");
                    } else {
                        tv_days.setVisibility(View.GONE);
                        tv_done.setVisibility(View.VISIBLE);
                        tv_done.setText("Done!");
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}