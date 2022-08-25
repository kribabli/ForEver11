package com.example.yoyoiq.InSideScoreActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.yoyoiq.CreateTeamActivity;
import com.example.yoyoiq.CreatedTeamPOJO.CreatedTeamResponse;
import com.example.yoyoiq.InSideContestActivityFragments.AllSelectedPlayerFromServer;
import com.example.yoyoiq.InSideContestActivityFragments.myAllTeamRequest;
import com.example.yoyoiq.OnlyTeamPreView.MyTeamPreview;
import com.example.yoyoiq.R;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SessionManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveMyTeamFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<myAllTeamRequest> list = new ArrayList();
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    private String mParam1;
    private String mParam2;

    public LiveMyTeamFragment() {
        // Required empty public constructor
    }

    public static LiveMyTeamFragment newInstance(String param1, String param2) {
        LiveMyTeamFragment fragment = new LiveMyTeamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    MyLiveTeamAdapter myLiveTeamAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_live_my_team, container, false);
        recyclerView = root.findViewById(R.id.myAllTeamList);
        swipeRefreshLayout = root.findViewById(R.id.swiper);
        swipeRefreshLayout.setOnRefreshListener(() -> getMyAllLiveTeam());
        return root;
    }

    private void getMyAllLiveTeam() {
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
                    JSONArray squads = null;
                    JSONArray jsonArray = null;
                    String CreatedTeamId;
                    try {
                        jsonArray = new JSONArray(totalData);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            try {
                                CreatedTeamId = jsonObject.getString("id");

                                squads = jsonObject.getJSONArray("squads");

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
                                        myAllTeamRequest myAllTeamRequest = new myAllTeamRequest(CreatedTeamId, TeamName, match_id, user_id, captain, vicecaptain, teamAName, teamBName, batsman, boller, allrounder, wkeeper, teamAcount, teamBcount, false, squads, "", "", "", "");
                                        list.add(myAllTeamRequest);
                                        myLiveTeamAdapter = new MyLiveTeamAdapter(getContext(), list);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerView.setAdapter(myLiveTeamAdapter);
                                        myLiveTeamAdapter.notifyDataSetChanged();
                                        swipeRefreshLayout.setRefreshing(false);
                                    } catch (Exception e) {
                                        swipeRefreshLayout.setRefreshing(false);
                                        e.printStackTrace();
                                    }
                                }
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getMyAllLiveTeam();
        }
    }

    //Adapter for live TeamPreViewList
    public class MyLiveTeamAdapter extends RecyclerView.Adapter<MyLiveTeamAdapter.MyViewHolder> {
        Context context;
        ArrayList<myAllTeamRequest> list;
        ArrayList<AllSelectedPlayerFromServer> listdata;
        SessionManager sessionManager;

        public MyLiveTeamAdapter(Context context, ArrayList<myAllTeamRequest> list) {
            this.context = context;
            this.list = list;
            sessionManager = new SessionManager(context.getApplicationContext());
        }

        @NonNull
        @Override
        public MyLiveTeamAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_my_created_team_list, parent, false);
            return new MyLiveTeamAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyLiveTeamAdapter.MyViewHolder holder, int position) {
            myAllTeamRequest allTeamRequest = list.get(position);
            if (list.size() > 0) {
                holder.CName.setText(allTeamRequest.getCaptain());
                holder.VCname.setText(allTeamRequest.getVicecaptain());
                holder.wkTv.setText(String.valueOf(allTeamRequest.getWkeeper()));
                holder.batTv.setText(String.valueOf(allTeamRequest.getBatsman()));
                holder.arTv.setText(String.valueOf(allTeamRequest.getAllrounder()));
                holder.bowlTv.setText(String.valueOf(allTeamRequest.getBoller()));
                holder.teamACount.setText(String.valueOf(allTeamRequest.getTeamAcount()));
                holder.teamBCount.setText(String.valueOf(allTeamRequest.getTeamBcount()));
                holder.userNameAndTid.setText(sessionManager.getUserData().getUserName() + "(T" + (position + 1) + ")");
                holder.teamA.setText(allTeamRequest.getTeamAName());
                holder.teamB.setText(allTeamRequest.getTeamBName());
                holder.linearLayout1.setOnClickListener(view -> {
                    Intent intent = new Intent(context, MyTeamPreview.class);
                    intent.putExtra("position", position);
                    intent.putExtra("listdata", list.get(position).getSquads().toString());
                    intent.putExtra("selectedPosition", position);
                    intent.putExtra("TeamName", sessionManager.getUserData().getUserName() + "(T" + (position + 1) + ")");
                    HelperData.team1NameShort = list.get(position).getTeamAName();
                    HelperData.team2NameShort = list.get(position).getTeamBName();
                    context.startActivity(intent);
                });
                holder.editImg.setOnClickListener(view -> {
                    Intent intent = new Intent(context, CreateTeamActivity.class);
                    intent.putExtra("matchId", list.get(position).getMatch_id());
                    intent.putExtra("matchA", list.get(position).getTeamAName());
                    intent.putExtra("matchB", list.get(position).getTeamBName());
                    context.startActivity(intent);
                });
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView teamA, teamB, wkTv, batTv, arTv, bowlTv, CName, VCname, teamACount, teamBCount, userNameAndTid, editImg;
            CardView cardView1;
            LinearLayout linearLayout1;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                teamA = itemView.findViewById(R.id.teamA);
                teamB = itemView.findViewById(R.id.teamB);
                wkTv = itemView.findViewById(R.id.wkTv);
                batTv = itemView.findViewById(R.id.batTv);
                arTv = itemView.findViewById(R.id.arTv);
                bowlTv = itemView.findViewById(R.id.bowlTv);
                CName = itemView.findViewById(R.id.CName);
                VCname = itemView.findViewById(R.id.VCname);
                teamACount = itemView.findViewById(R.id.teamACount);
                teamBCount = itemView.findViewById(R.id.teamBCount);
                userNameAndTid = itemView.findViewById(R.id.userNameAndTid);
                cardView1 = itemView.findViewById(R.id.cardView1);
                editImg = itemView.findViewById(R.id.editImg);
                linearLayout1 = itemView.findViewById(R.id.linearLayout1);
            }
        }
    }
}