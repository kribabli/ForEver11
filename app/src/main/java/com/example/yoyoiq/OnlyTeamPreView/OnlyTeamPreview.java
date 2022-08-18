package com.example.yoyoiq.OnlyTeamPreView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.R;
import com.example.yoyoiq.common.HelperData;

import java.util.ArrayList;

public class OnlyTeamPreview extends AppCompatActivity {
    TextView close, teamA, teamB, userNameAndTid;
    int batCount, arCount, bowlCount, wkCount = 0;
    LinearLayout LL_GroundWK;
    LinearLayout LL_GroundBAT;
    LinearLayout LL_GroundAR;
    LinearLayout LL_GroundBOWL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_team_preview);
        initMethod();
        setAction();
        getPreViewTeamData();

    }

    private void initMethod() {
        close = findViewById(R.id.close);
        userNameAndTid = findViewById(R.id.userNameAndTid);
        teamA = findViewById(R.id.teamA);
        teamB = findViewById(R.id.teamB);
        LL_GroundWK = findViewById(R.id.LL_GroundWK);
        LL_GroundBAT = findViewById(R.id.LL_GroundBAT);
        LL_GroundAR = findViewById(R.id.LL_GroundAR);
        LL_GroundBOWL = findViewById(R.id.LL_GroundBOWL);
    }

    private void setAction() {
        close.setOnClickListener(v -> finish());
    }

    private void getPreViewTeamData() {
        for (int i = 0; i < HelperData.myTeamList.size(); i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View tv = inflater.inflate(R.layout.single_grounder, null);
            View tvKalu = inflater.inflate(R.layout.black_ground, null);


            TextView nameTxt = tv.findViewById(R.id.nameTxt);
            TextView nameTxtKalu = tvKalu.findViewById(R.id.nameTxtKalu);

            TextView badgeTxtGoru = tv.findViewById(R.id.badgeTxtGoru);
            TextView badgeTxtKalu = tvKalu.findViewById(R.id.badgeTxtKalu);


            if (HelperData.myTeamList.get(i).isCap()) {
                badgeTxtGoru.setText("C");
                badgeTxtGoru.setVisibility(View.VISIBLE);

                badgeTxtKalu.setText("C");
                badgeTxtKalu.setVisibility(View.VISIBLE);
            }

            if (HelperData.myTeamList.get(i).isVcap()) {
                badgeTxtGoru.setText("Vc");
                badgeTxtGoru.setVisibility(View.VISIBLE);

                badgeTxtKalu.setText("Vc");
                badgeTxtKalu.setVisibility(View.VISIBLE);
            }

            String one = "" + HelperData.myTeamList.get(i).getTitle();
            String two = "";

            nameTxt.setText("" + one);
            nameTxtKalu.setText("" + one);

            try {
                String currentString = "" + HelperData.myTeamList.get(i).getTitle();
                String[] separated = currentString.split(" ");
                one = separated[0];
                two = separated[1];
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    nameTxt.setText("" + HelperData.myTeamList.get(i).getTitle().substring(0, 10));
                    nameTxtKalu.setText("" + HelperData.myTeamList.get(i).getTitle().substring(0, 10));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (HelperData.team1NameShort.equalsIgnoreCase(HelperData.myTeamList.get(i).getCountry())) {
                nameTxt.setText("" + one.substring(0, 1) + ". " + two + "");
            } else {
                nameTxtKalu.setText("" + one.substring(0, 1) + ". " + two + "");
            }

            if (HelperData.team1NameShort.length() == 0) {
                HelperData.team1NameShort = HelperData.myTeamList.get(0).getCountry();
            }

            if (HelperData.myTeamList.get(i).getPlaying_role().equalsIgnoreCase("wk")) {
                assert LL_GroundWK != null;
                LL_GroundWK.setBackgroundColor(Color.TRANSPARENT);
                if (HelperData.team1NameShort.equalsIgnoreCase(HelperData.myTeamList.get(i).getCountry())) {
                    LL_GroundWK.addView(tv);
                } else {
                    LL_GroundWK.addView(tvKalu);
                }
            } else if (HelperData.myTeamList.get(i).getPlaying_role().equalsIgnoreCase("bat")) {
                LL_GroundBAT.setBackgroundColor(Color.TRANSPARENT);
                if (HelperData.team1NameShort.equalsIgnoreCase(HelperData.myTeamList.get(i).getCountry())) {
                    LL_GroundBAT.addView(tv);
                } else {
                    LL_GroundBAT.addView(tvKalu);
                }
            } else if (HelperData.myTeamList.get(i).getPlaying_role().equalsIgnoreCase("all")) {
                LL_GroundAR.setBackgroundColor(Color.TRANSPARENT);
                if (HelperData.team1NameShort.equalsIgnoreCase(HelperData.myTeamList.get(i).getCountry())) {
                    LL_GroundAR.addView(tv);
                } else {
                    LL_GroundAR.addView(tvKalu);
                }
            } else if (HelperData.myTeamList.get(i).getPlaying_role().equalsIgnoreCase("bowl")) {
                LL_GroundBOWL.setBackgroundColor(Color.TRANSPARENT);
                if (HelperData.team1NameShort.equalsIgnoreCase(HelperData.myTeamList.get(i).getCountry())) {
                    LL_GroundBOWL.addView(tv);
                } else {
                    LL_GroundBOWL.addView(tvKalu);
                }

            }

        }
    }
}