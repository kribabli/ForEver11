package com.example.yoyoiq.OnlyTeamPreView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.yoyoiq.R;
import com.example.yoyoiq.common.HelperData;
import com.google.android.flexbox.FlexboxLayout;

public class MyTeamPreview extends AppCompatActivity {
    TextView close, teamA, teamB, userNameAndTid;
    int batCount, arCount, bowlCount, wkCount = 0, teamAcount = 0, teamBCount = 0;
    FlexboxLayout LL_GroundWK;
    FlexboxLayout LL_GroundBAT;
    FlexboxLayout LL_GroundAR;
    FlexboxLayout LL_GroundBOWL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team_preview);
        initMethod();
        setAction();
        getAllRequestedData();
    }

    private void getAllRequestedData() {
        userNameAndTid.setText("" + HelperData.UserName);
        teamA.setText("" + HelperData.team1NameShort);
        teamB.setText("" + HelperData.team2NameShort);


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
}