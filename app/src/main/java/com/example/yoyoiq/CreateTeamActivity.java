package com.example.yoyoiq;

import static com.example.yoyoiq.common.HelperData.newTeamMaking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.yoyoiq.Adapter.PageAdapterPlayer;
import com.example.yoyoiq.common.HelperData;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateTeamActivity extends AppCompatActivity {
    TextView backPress;
    Button continueBtn, teamPreview;
    String match_id, matchA, matchB, logo_url_a, logo_url_b, date_start, date_end;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem tabItem1, tabItem2, tabItem3, tabItem4;
    PageAdapterPlayer pageAdapterPlayer;
    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    CircleImageView imageViewA, imageViewB;
    TextView textViewA, textViewB, tv_TotalSelectedPlayer, tv_TotalCredit, tv_TeamOneSize, tv_TeamTwoSize;
    private String EVENT_DATE_TIME = "2022-07-23 18:08:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private TextView tv_days, tv_done;
    private Handler handler = new Handler();
    private Runnable runnable;
    public static String addedPlayerIds;

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

        if (!HelperData.teamEdt) {
            newTeamMaking();
        }

        Log.d("Amit","Value 111"+HelperData.myTeamList);

        HelperData.conty1 = new MutableLiveData<>();
        HelperData.conty2 = new MutableLiveData<>();
        HelperData.conty1.setValue(0);
        HelperData.conty2.setValue(0);
        playerCounter();
        creditCounter();
        playerSectionCounter();

        HelperData.wk.observe(this, e -> {
            if (e == null) {
                tabLayout.getTabAt(0).setText("WK(0)");
                return;
            }
            tabLayout.getTabAt(0).setText("WK(" + e + ")");
        });

        HelperData.bat.observe(this, e -> {
            if (e == null) {
                tabLayout.getTabAt(1).setText("BAT(0)");
                return;
            }
            tabLayout.getTabAt(1).setText("BAT(" + e + ")");
        });

        HelperData.ar.observe(this, e -> {
            if (e == null) {
                tabLayout.getTabAt(2).setText("AR(0)");
                return;
            }
            tabLayout.getTabAt(2).setText("AR(" + e + ")");
        });

        HelperData.bowl.observe(this, e -> {
            if (e == null) {
                tabLayout.getTabAt(3).setText("BOWL(0)");
                return;
            }
            tabLayout.getTabAt(3).setText("BOWL(" + e + ")");
        });
    }

    private void initMethod() {
        linearLayout1 = findViewById(R.id.linerLayout1);
        linearLayout2 = findViewById(R.id.linerLayout2);
        linearLayout3 = findViewById(R.id.linerLayout3);
        linearLayout4 = findViewById(R.id.linerLayout4);

        tv_days = findViewById(R.id.tv_days);
        tv_done = findViewById(R.id.done);

        backPress = findViewById(R.id.backPress);
        teamPreview = findViewById(R.id.teamPreview);
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

        tv_TotalSelectedPlayer = findViewById(R.id.tv_TotalSelectedPlayer);
        tv_TotalCredit = findViewById(R.id.tv_TotalCredit);
        tv_TeamOneSize = findViewById(R.id.tv_TeamOneSize);
        tv_TeamTwoSize = findViewById(R.id.tv_TeamTwoSize);
    }

    private void setAction() {
        backPress.setOnClickListener(view -> onBackPressed());

        textViewA.setText(matchA);
        textViewB.setText(matchB);
        HelperData.team1NameShort = matchA;
        HelperData.team2NameShort = matchB;

        Glide.with(this)
                .load(logo_url_a)
                .into(imageViewA);

        Glide.with(this)
                .load(logo_url_b)
                .into(imageViewB);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateTeamActivity.this, TeamPreviewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void playerSectionCounter() {
        HelperData.wk.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer >= 0) {
                    tabLayout.getTabAt(0).setText("" + integer);
                }
            }
        });
        HelperData.bat.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer >= 0) {
                    tabLayout.getTabAt(1).setText("" + integer);
                }
            }
        });
        HelperData.ar.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer >= 0) {
                    tabLayout.getTabAt(2).setText("" + integer);
                }
            }
        });
        HelperData.bowl.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer >= 0) {
                    tabLayout.getTabAt(3).setText("" + integer);
                }
            }
        });
    }

    private void playerCounter() {
        HelperData.playerCounter.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int limit = 0;
                if (HelperData.type_selected.equalsIgnoreCase("Cricket")) {
                    limit = 11;
                } else {
                    limit = 5;
                }
                tv_TotalSelectedPlayer.setText("" + integer + "/" + limit);
            }
        });
    }

    private void creditCounter() {
        tv_TeamOneSize = findViewById(R.id.tv_TeamOneSize);
        tv_TeamTwoSize = findViewById(R.id.tv_TeamTwoSize);

        HelperData.creditCounter.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double integer) {
                tv_TotalCredit.setText("" + integer + "/100");
            }
        });

        HelperData.conty1.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv_TeamOneSize.setText("" + integer);
            }
        });

        HelperData.conty2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv_TeamTwoSize.setText("" + integer);
            }
        });
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