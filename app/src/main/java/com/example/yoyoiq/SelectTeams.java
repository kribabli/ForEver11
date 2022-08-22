package com.example.yoyoiq;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.yoyoiq.Adapter.SelectTeamsAdapter;
import com.example.yoyoiq.CreatedTeamPOJO.CreatedTeamResponse;
import com.example.yoyoiq.InSideContestActivityFragments.myAllTeamRequest;
import com.example.yoyoiq.JoinContest.JoinContestsResponse;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.common.HelperData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectTeams extends AppCompatActivity {
    TextView backPress, team, selectAll;
    Button joinBtn;
    RecyclerView recyclerView;
    ArrayList<myAllTeamRequest> list = new ArrayList();
    SwipeRefreshLayout swipeRefreshLayout;
    SelectTeamsAdapter selectTeamsAdapter;
    public static Integer count=0;
    String contest_id;
    String Entryfee;
    String Upto;
   public static String Teamname;


    public static boolean allSelectedTeam = false;
    public static String selectedSingleTeam;


    public static void selected_single_Team(String teamId) {
        for (int i = 0; i < HelperData.myCountyPlayer.size(); i++) {
            if(HelperData.myCountyPlayer.get(i).isSlected()==true){
               SelectTeams.Teamname=HelperData.myCountyPlayer.get(i).getTeamName();
               Log.d("Amit","Value "+teamId);


            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_teams);
        initMethod();
        setAction();
        getMyAllCreatedTeam();
        selectedTeamCounter();

    }

    private void JoinContestData() {
       Call<JoinContestsResponse>call=ApiClient.getInstance().getApi().getJoinContestResponse(HelperData.UserId,HelperData.matchId,contest_id,Teamname);
       call.enqueue(new Callback<JoinContestsResponse>() {
           @Override
           public void onResponse(Call<JoinContestsResponse> call, Response<JoinContestsResponse> response) {
               if(response.isSuccessful()){
                   String Data =new Gson().toJson(response.body());

               }
           }

           @Override
           public void onFailure(Call<JoinContestsResponse> call, Throwable t) {

           }
       });
    }

    private void selectedTeamCounter() {
        HelperData.selectSingleTeamCounter.observe(this, integer -> team.setText("" + integer));
    }

    private void initMethod() {
        backPress = findViewById(R.id.backPress);
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swiper);
        joinBtn = findViewById(R.id.JoinTeam);
        team = findViewById(R.id.team);
        selectAll = findViewById(R.id.selectAll);
        contest_id=getIntent().getStringExtra("Contest_id");
        Entryfee=getIntent().getStringExtra("EntryFee");
        Upto=getIntent().getStringExtra("upto");
     }

    private void setAction() {
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMyAllCreatedTeam();
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JoinContestData();
            }
        });
    }

    private void getMyAllCreatedTeam() {
        list.clear();
        Call<CreatedTeamResponse> call = ApiClient
                .getInstance()
                .getApi()
                .getUserTeamCreated(HelperData.UserId, HelperData.matchId);

        call.enqueue(new Callback<CreatedTeamResponse>() {
            @Override
            public void onResponse(Call<CreatedTeamResponse> call, Response<CreatedTeamResponse> response) {
                CreatedTeamResponse createdTeamResponse = response.body();
                if (response.isSuccessful()) {
                    String totalData = new Gson().toJson(createdTeamResponse.getResponse());

                    //----------------------for CreatedTeam(Per User)----------------------------
                    JSONArray short_squads = null;
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(totalData);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            try {
                                short_squads = jsonObject.getJSONArray("short_squads");
                                for (int j = 0; j < short_squads.length(); j++) {
                                    try {
                                        JSONObject jsonObjectSquads = short_squads.getJSONObject(0);
                                        String TeamName = jsonObjectSquads.getString("TeamName");
                                        int allrounder = Integer.parseInt(jsonObjectSquads.getString("allrounder"));
                                        int batsman = Integer.parseInt(jsonObjectSquads.getString("batsman"));
                                        int boller = Integer.parseInt(jsonObjectSquads.getString("boller"));
                                        String captain = jsonObjectSquads.getString("captain");
                                        String match_id = jsonObjectSquads.getString("match_id");
                                        int teamAcount = Integer.parseInt(jsonObjectSquads.getString("teamAcount"));
                                        int teamBcount = Integer.parseInt(jsonObjectSquads.getString("teamBcount"));
                                        String user_id = jsonObjectSquads.getString("user_id");
                                        String vicecaptain = jsonObjectSquads.getString("vicecaptain");
                                        int wkeeper = Integer.parseInt(jsonObjectSquads.getString("wkeeper"));
                                        String teamAName = jsonObjectSquads.getString("teamAName");
                                        String teamBName = jsonObjectSquads.getString("teamBName");

                                        myAllTeamRequest myAllTeamRequest = new myAllTeamRequest(TeamName, match_id, user_id, captain, vicecaptain, teamAName, teamBName, batsman, boller, allrounder, wkeeper, teamAcount, teamBcount,false);
                                        list.add(myAllTeamRequest);
                                        HelperData.myCountyPlayer.add(myAllTeamRequest);
                                        selectTeamsAdapter = new SelectTeamsAdapter(SelectTeams.this, list);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(SelectTeams.this));
                                        recyclerView.setAdapter(selectTeamsAdapter);
                                        selectTeamsAdapter.notifyDataSetChanged();
                                        swipeRefreshLayout.setRefreshing(false);
                                    } catch (Exception e) {
                                        swipeRefreshLayout.setRefreshing(false);
                                        e.printStackTrace();
                                    }
                                }
                                selectAll.setText("Select All (" + list.size() + ")");
                            } catch (Exception e) {
                                swipeRefreshLayout.setRefreshing(false);
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        swipeRefreshLayout.setRefreshing(false);
                        e.printStackTrace();
                    }
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<CreatedTeamResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}