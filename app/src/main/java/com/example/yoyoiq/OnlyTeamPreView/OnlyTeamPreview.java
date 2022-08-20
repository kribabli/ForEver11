package com.example.yoyoiq.OnlyTeamPreView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.R;
import com.example.yoyoiq.common.HelperData;
import com.google.android.flexbox.FlexboxLayout;

public class OnlyTeamPreview extends AppCompatActivity {
    TextView close, teamA, teamB, userNameAndTid;
    int batCount, arCount, bowlCount, wkCount = 0, teamAcount = 0, teamBCount = 0;
    FlexboxLayout LL_GroundWK;
    FlexboxLayout LL_GroundBAT;
    FlexboxLayout LL_GroundAR;
    FlexboxLayout LL_GroundBOWL;

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
        userNameAndTid.setText("" + HelperData.UserName);
        teamA.setText("" + HelperData.team1NameShort);
        teamB.setText("" + HelperData.team2NameShort);

        for (int i = 0; i < HelperData.myTeamList.size(); i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View tv = inflater.inflate(R.layout.single_grounder, null);
            TextView nameTxt = tv.findViewById(R.id.nameTxt);
            TextView playerPts = tv.findViewById(R.id.pointsTxt);
            TextView badgeTxtGoru = tv.findViewById(R.id.badgeTxtGoru);
            TextView VCnameTv = tv.findViewById(R.id.VCnameTv);

            if (HelperData.myTeamList.get(i).isCap()) {
                badgeTxtGoru.setText("C");
                badgeTxtGoru.setVisibility(View.VISIBLE);
            }

            if (HelperData.myTeamList.get(i).isVcap()) {
                VCnameTv.setText("VC");
                VCnameTv.setVisibility(View.VISIBLE);
            }

            String one = "" + HelperData.myTeamList.get(i).getTitle();
            String two = "" + HelperData.myTeamList.get(i).getPoints();
            nameTxt.setText("" + one);
            playerPts.setText("" + two);

            try {
                String currentString = "" + HelperData.myTeamList.get(i).getTitle();
                String[] separated = currentString.split(" ");
                one = separated[0];
                two = separated[1];
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    nameTxt.setText("" + HelperData.myTeamList.get(i).getTitle().substring(0, 10));
                    playerPts.setText("" + HelperData.myTeamList.get(i).getPoints());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            if (HelperData.myTeamList.get(i).getPlaying_role().equalsIgnoreCase("wk")) {
                assert LL_GroundWK != null;
                LL_GroundWK.setBackgroundColor(Color.TRANSPARENT);
                if (HelperData.team1NameShort.equalsIgnoreCase(HelperData.myTeamList.get(i).getCountry())) {
                    LL_GroundWK.addView(tv);
                    teamAcount++;
                } else {
                    LL_GroundWK.addView(tv);
                    teamBCount++;
                }
            } else if (HelperData.myTeamList.get(i).getPlaying_role().equalsIgnoreCase("bat")) {
                LL_GroundBAT.setBackgroundColor(Color.TRANSPARENT);
                if (HelperData.team1NameShort.equalsIgnoreCase(HelperData.myTeamList.get(i).getCountry())) {
                    LL_GroundBAT.addView(tv);
                    teamAcount++;
                } else {
                    LL_GroundBAT.addView(tv);
                    teamBCount++;
                }
            } else if (HelperData.myTeamList.get(i).getPlaying_role().equalsIgnoreCase("ar")) {
                LL_GroundAR.setBackgroundColor(Color.TRANSPARENT);
                if (HelperData.team1NameShort.equalsIgnoreCase(HelperData.myTeamList.get(i).getCountry())) {
                    LL_GroundAR.addView(tv);
                    teamAcount++;
                } else {
                    LL_GroundAR.addView(tv);
                    teamBCount++;
                }

            } else if (HelperData.myTeamList.get(i).getPlaying_role().equalsIgnoreCase("bowl")) {
                LL_GroundBOWL.setBackgroundColor(Color.TRANSPARENT);
                if (HelperData.team1NameShort.equalsIgnoreCase(HelperData.myTeamList.get(i).getCountry())) {
                    LL_GroundBOWL.addView(tv);
                    teamAcount++;
                } else {
                    LL_GroundBOWL.addView(tv);
                    teamBCount++;
                }
            }
        }
    }
}