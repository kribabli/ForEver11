package com.example.yoyoiq;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Adapter.TeamPreviewAdapter;
import com.example.yoyoiq.Model.AllSelectedPlayer;

import java.util.ArrayList;

public class TeamPreviewActivity extends AppCompatActivity {
    TextView backPress;
    RecyclerView recyclerView;
    Button saveTeam, teamPreView;
    TeamPreviewAdapter teamPreviewAdapter;
    ArrayList<AllSelectedPlayer> arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_preview);
        inItMethod();
        setAction();
    }

    private void inItMethod() {
        backPress = findViewById(R.id.backPress);
        recyclerView = findViewById(R.id.selectedPlayerRecyclerView);
        saveTeam = findViewById(R.id.saveTeam);
        teamPreView = findViewById(R.id.teamPreview);
    }

    private void setAction() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(TeamPreviewActivity.this));
        teamPreviewAdapter = new TeamPreviewAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(teamPreviewAdapter);
        teamPreviewAdapter.notifyDataSetChanged();

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}