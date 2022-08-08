package com.example.yoyoiq;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Adapter.TeamPreviewAdapter;
import com.example.yoyoiq.ContestPOJO.Contests;
import com.example.yoyoiq.Model.AllSelectedPlayer;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.common.HelperData;

import org.json.JSONArray;
import org.json.JSONException;
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
    private Handler handler = new Handler();
    private Runnable runnable;
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static boolean captainSelected = false;
    public static boolean viceCaptainSelected = false;

    public static void makeAllCaptainFalse(int no) {
        captainSelected = false;
        for (int i = 0; i < HelperData.myTeamList.size(); i++) {
            if (no != HelperData.myTeamList.get(i).getPid()) {
                HelperData.myTeamList.get(i).setCap(false);
            } else {
                HelperData.myTeamList.get(i).setCap(true);
                captainSelected = true;
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

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        saveTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TeamPreviewActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
                Log.d("Amit","Value Check ");

                JSONArray jsonArray=new JSONArray();
                JSONObject Myteamobjects=new JSONObject();
                for(int i=0;i<HelperData.myTeamList.size();i++){
                    try {
                        Myteamobjects.put("playername",HelperData.myTeamList.get(i).getTitle());
                        Myteamobjects.put("country",HelperData.myTeamList.get(i).getCountry());
                        Myteamobjects.put("fantasy_rating",HelperData.myTeamList.get(i).getFantasy_player_rating());
                        Myteamobjects.put("role",HelperData.myTeamList.get(i).getPlaying_role());
                        Myteamobjects.put("cap",HelperData.myTeamList.get(i).isCap());
                        Myteamobjects.put("vcap",HelperData.myTeamList.get(i).isVcap());
                        Myteamobjects.put("point",HelperData.myTeamList.get(i).getPoints());
                        jsonArray.put(Myteamobjects);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("Amit","Value check this "+jsonArray);
                Call<JSONObject> call= ApiClient.getInstance().getApi().Send_myteam_list_Server(HelperData.UserId,HelperData.matchId,HelperData.contestId,"Team1",jsonArray);
                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        if(response.isSuccessful()){
                            Log.d("Amit","Value 11 "+response.body());

                        }
                    }
                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {

                    }
                });
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