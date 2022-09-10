package com.example.yoyoiq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Adapter.TeamPreviewAdapter;
import com.example.yoyoiq.InSideContestActivityFragments.myAllTeamRequest;
import com.example.yoyoiq.Model.AllSelectedPlayer;
import com.example.yoyoiq.OnlyTeamPreView.OnlyTeamPreview;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.UpComingMatchPOJO.ShortSquadsUploadingPojoClass;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SessionManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamPreviewActivity extends AppCompatActivity {
    TextView backPress, leftTime, tv_done;
    String leftTime1;
    RecyclerView recyclerView;
    Button saveTeam, teamPreView;
    TeamPreviewAdapter teamPreviewAdapter;
    ArrayList<AllSelectedPlayer> arrayList = new ArrayList();
    ArrayList<ShortSquadsUploadingPojoClass> shortSquads = new ArrayList();
    private Handler handler = new Handler();
    private Runnable runnable;
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static boolean captainSelected = false;
    public static boolean viceCaptainSelected = false;
    SessionManager sessionManager;
    String CaptainName, VCName;
    int batCount, arCount, bowlCount, wkCount = 0;

    public static void makeAllCaptainFalse(int no) {
        captainSelected = false;
        for (int i = 0; i < HelperData.myTeamList.size(); i++) {
            if (no != HelperData.myTeamList.get(i).getPid()) {
                HelperData.myTeamList.get(i).setCap(false);
            } else {
                HelperData.myTeamList.get(i).setCap(true);
                captainSelected = true;
                HelperData.Selectedcap.setValue(HelperData.Selectedcap.getValue() + 1);
            }
            if (HelperData.myTeamList.get(i).isCap()) {
                if (HelperData.myTeamList.get(i).isVcap()) {
                    makeAllViceCaptainFalse(0);
                }
            }
        }
    }

    public static void makeAllViceCaptainFalse(int no) {
        viceCaptainSelected = false;
        for (int i = 0; i < HelperData.myTeamList.size();
             i++) {
            if (no != HelperData.myTeamList.get(i).getPid()) {
                HelperData.myTeamList.get(i).setVcap(false);
            } else {
                HelperData.myTeamList.get(i).setVcap(true);
                viceCaptainSelected = true;
                HelperData.selectedVcap.setValue(HelperData.selectedVcap.getValue() + 1);
            }
            if (HelperData.myTeamList.get(i).isVcap()) {
                if (HelperData.myTeamList.get(i).isCap()) {
                    makeAllCaptainFalse(0);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_preview);
        inItMethod();
        setAction();
        countDownStart();
        sessionManager=new SessionManager(getApplicationContext());
    }

    private void saveTeamLocally() {
        for (int i = 0; i < HelperData.myTeamList.size(); i++) {
            if (HelperData.myTeamList.get(i).isCap() == true) {
                CaptainName = HelperData.myTeamList.get(i).getTitle();
            }
            if (HelperData.myTeamList.get(i).isVcap() == true) {
                VCName = HelperData.myTeamList.get(i).getTitle();
            }
            if (HelperData.myTeamList.get(i).getPlaying_role() == "WK") {
                wkCount++;
            }
            if (HelperData.myTeamList.get(i).getPlaying_role() == "BAT") {
                batCount++;
            }
            if (HelperData.myTeamList.get(i).getPlaying_role() == "AR") {
                arCount++;
            }
            if (HelperData.myTeamList.get(i).getPlaying_role() == "BOWL") {
                bowlCount++;
            }
        }
        ShortSquadsUploadingPojoClass dataholderClassnew = new ShortSquadsUploadingPojoClass("","T" + HelperData.TeamCount.getValue(), HelperData.matchId, HelperData.UserId, CaptainName,
                VCName, HelperData.team1NameShort, HelperData.team2NameShort, batCount, bowlCount, arCount, wkCount, HelperData.conty1.getValue(), HelperData.conty2.getValue(),false);
        HelperData.myCountyPlayer.add(dataholderClassnew);
        shortSquads.add(dataholderClassnew);
    }

    private void inItMethod() {
        backPress = findViewById(R.id.backPress);
        recyclerView = findViewById(R.id.selectedPlayerRecyclerView);
        saveTeam = findViewById(R.id.saveTeam);
        teamPreView = findViewById(R.id.teamPreview);
        leftTime = findViewById(R.id.leftTime);
        tv_done = findViewById(R.id.done);
        leftTime1 = getIntent().getStringExtra("date_start");
    }

    private void setAction() {
        arrayList = (HelperData.myTeamList);
        teamPreviewAdapter = new TeamPreviewAdapter(getApplicationContext(), arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(TeamPreviewActivity.this));
        recyclerView.setAdapter(teamPreviewAdapter);
        teamPreviewAdapter.notifyDataSetChanged();

        backPress.setOnClickListener(view -> onBackPressed());

        saveTeam.setOnClickListener(view -> {
            saveTeamLocally();
            Handle_And_UploadTeamOnServer();
        });

        teamPreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamPreviewActivity.this, OnlyTeamPreview.class);
                startActivity(intent);
            }
        });
    }

    private void Handle_And_UploadTeamOnServer() {
        if (HelperData.Selectedcap.getValue() >= 1) {
            if (HelperData.selectedVcap.getValue() >= 1) {
                Gson gson = new Gson();
                String data = gson.toJson(arrayList);
                String shortData = gson.toJson(shortSquads);
//                String check =HelperData.myTeamList.toString();
                Call<JSONObject> call = ApiClient.getInstance().getApi().Send_myteam_list_Server(sessionManager.getUserData().getUser_id(), HelperData.matchId, data, shortData);
                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(TeamPreviewActivity.this, "Your Team Created Successfully...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TeamPreviewActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            HelperData.TeamCount.setValue(HelperData.TeamCount.getValue() + 1);
                            HelperData.myTeam.setValue(HelperData.TeamCount.getValue() + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                    }
                });
            } else {
                Toast.makeText(this, "Please Select Your Vice Captain", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please Select Your Captain", Toast.LENGTH_SHORT).show();
        }
    }

    private void countDownStart() {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    Date event_date = dateFormat.parse(leftTime1);
                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;
                        leftTime.setText(String.format("%02d", Days) + " " + String.format("%02d", Hours) + "h " + String.format("%02d", Minutes) + "m " + String.format("%02d", Seconds) + "s " + " left");
                    } else {
                        leftTime.setVisibility(View.GONE);
                        tv_done.setVisibility(View.VISIBLE);
                        tv_done.setText("Finish!");
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