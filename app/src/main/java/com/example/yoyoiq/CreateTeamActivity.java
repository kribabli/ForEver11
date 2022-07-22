package com.example.yoyoiq;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.yoyoiq.Adapter.PageAdapterPlayer;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateTeamActivity extends AppCompatActivity {
    TextView backPress;
    Button continueBtn;
    String match_id, matchA, matchB, logo_url_a, logo_url_b;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem tabItem1, tabItem2, tabItem3, tabItem4;
    PageAdapterPlayer pageAdapterPlayer;
    LinearLayout linearLayout;
    CircleImageView imageViewA, imageViewB;
    TextView textViewA, textViewB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team_actvity);
        initMethod();
        setAction();
        linearLayout = findViewById(R.id.linerLayout1);

        pageAdapterPlayer = new PageAdapterPlayer(getSupportFragmentManager(), tabLayout.getTabCount(), match_id, matchA, matchB, logo_url_a, logo_url_b);
        viewPager.setAdapter(pageAdapterPlayer);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3) {
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
        backPress = findViewById(R.id.backPress);
        continueBtn = findViewById(R.id.Continue);

        match_id = getIntent().getStringExtra("match_id");
        matchA = getIntent().getStringExtra("matchA");
        matchB = getIntent().getStringExtra("matchB");
        logo_url_a = getIntent().getStringExtra("logo_url_a");
        logo_url_b = getIntent().getStringExtra("logo_url_b");

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

}