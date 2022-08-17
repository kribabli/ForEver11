package com.example.yoyoiq.OnlyTeamPreView;

import android.os.Bundle;
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
    String CaptainName, VCName, wkName, BATName, ARName, BOWLName;
    ArrayList<OnlyTeamPreviewModel> list = new ArrayList();
    OnlyTeamPreviewAdapter onlyTeamPreviewAdapter;
    RecyclerView recyclerView;

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
        recyclerView = findViewById(R.id.recyclerView);
        teamA = findViewById(R.id.teamA);
        teamB = findViewById(R.id.teamB);
    }

    private void setAction() {
        close.setOnClickListener(v -> finish());
    }

    private void getPreViewTeamData() {
        for (int i = 0; i < HelperData.myTeamList.size(); i++) {
            if (HelperData.myTeamList.get(i).isCap() == true) {
                CaptainName = HelperData.myTeamList.get(i).getTitle();
            }
            if (HelperData.myTeamList.get(i).isVcap() == true) {
                VCName = HelperData.myTeamList.get(i).getTitle();
            }
            if (HelperData.myTeamList.get(i).getPlaying_role() == "WK") {
                wkName = HelperData.myTeamList.get(i).getTitle();
                wkCount++;
            }
            if (HelperData.myTeamList.get(i).getPlaying_role() == "BAT") {
                BATName = HelperData.myTeamList.get(i).getTitle();
                batCount++;
            }
            if (HelperData.myTeamList.get(i).getPlaying_role() == "AR") {
                ARName = HelperData.myTeamList.get(i).getTitle();
                arCount++;
            }
            if (HelperData.myTeamList.get(i).getPlaying_role() == "BOWL") {
                BOWLName = HelperData.myTeamList.get(i).getTitle();
                bowlCount++;
            }
        }

        teamA.setText(HelperData.team1NameShort);
        teamB.setText(HelperData.team2NameShort);
        userNameAndTid.setText(HelperData.UserName);

        OnlyTeamPreviewModel onlyTeamPreviewModel = new OnlyTeamPreviewModel(HelperData.team1NameShort, HelperData.team2NameShort, HelperData.matchId, HelperData.UserId,
                CaptainName, VCName, batCount, bowlCount, arCount, wkCount, CaptainName, VCName, wkName, BATName, ARName, BOWLName);
        list.add(onlyTeamPreviewModel);
        onlyTeamPreviewAdapter = new OnlyTeamPreviewAdapter(getApplicationContext(), list);
        recyclerView.setLayoutManager(new GridLayoutManager(OnlyTeamPreview.this,3));
        recyclerView.setAdapter(onlyTeamPreviewAdapter);
        onlyTeamPreviewAdapter.notifyDataSetChanged();
    }
}