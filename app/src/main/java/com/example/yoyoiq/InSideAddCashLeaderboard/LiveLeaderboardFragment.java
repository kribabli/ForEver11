package com.example.yoyoiq.InSideAddCashLeaderboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.yoyoiq.Model.LeaderboardPOJO;
import com.example.yoyoiq.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LiveLeaderboardFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    String match_id1, contest_id1;
    String match_id, contest_id;
    RecyclerView recyclerView;
    ArrayList<LeaderboardPOJO> listItems = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    TextView totalTeam;
    String url = "http://adminapp.tech/yoyoiq/ItsMe/all_apis.php?func=get_leaderboard_users";

    public LiveLeaderboardFragment() {
        // Required empty public constructor
    }

    public static LiveLeaderboardFragment newInstance(String param1, String param2) {
        LiveLeaderboardFragment fragment = new LiveLeaderboardFragment();
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

    LeaderBoardAdapter leaderBoardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_live_leaderboard, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        totalTeam = root.findViewById(R.id.totalTeam);
        swipeRefreshLayout = root.findViewById(R.id.swiper);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    getLeaderBoardData(match_id, contest_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return root;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            try {
                match_id1 = getArguments().getString("match_id");
                contest_id1 = getArguments().getString("contestId");
                match_id = String.valueOf(match_id1);
                contest_id = String.valueOf(contest_id1);
                getLeaderBoardData(match_id, contest_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void getLeaderBoardData(String match_id, String contest_id) throws JSONException {
        listItems.clear();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray1 = new JSONArray();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    jsonArray1 = jsonObject.getJSONArray("users");
                    totalTeam.setText("All Teams " + "( " + jsonArray1.length() + " )");
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String user_id = jsonObject1.getString("user_id");
                        String team_id = jsonObject1.getString("team_id");
                        String match_id = jsonObject1.getString("match_id");
                        String contest_id = jsonObject1.getString("contest_id");
                        String date_time = jsonObject1.getString("date_time");
                        String name = jsonObject1.getString("name");
                        String mobile = jsonObject1.getString("mobile");
                        int rank = Integer.parseInt(jsonObject1.getString("rank"));
                        int total_points = Integer.parseInt(jsonObject1.getString("total_points"));

                        LeaderboardPOJO leaderboardPOJO = new LeaderboardPOJO(id, user_id, team_id, match_id, contest_id, date_time, name, mobile, rank, total_points);
                        listItems.add(leaderboardPOJO);
                    }
                    swipeRefreshLayout.setRefreshing(false);
                    leaderBoardAdapter = new LeaderBoardAdapter(getContext(), listItems);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(leaderBoardAdapter);
                    leaderBoardAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    swipeRefreshLayout.setRefreshing(false);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("match_id", match_id);
                params.put("contest_id", contest_id);
                return params;
            }
        };
        queue.add(request);
    }


    public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.MyViewHolder> {
        Context context;
        ArrayList<LeaderboardPOJO> list;

        public LeaderBoardAdapter(Context context, ArrayList<LeaderboardPOJO> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public LeaderBoardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_user_ranking_list, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LeaderBoardAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            LeaderboardPOJO listData = list.get(position);
            holder.setIsRecyclable(false);
            holder.userName.setText(listData.getName());
            holder.userTotalPoints.setText(String.valueOf(listData.getTotal_points()));
            holder.userRank.setText("# " + String.valueOf(listData.getRank()));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView userName, userTotalPoints, userRank;
            ImageView userProfile;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                userProfile = itemView.findViewById(R.id.userProfile);
                userName = itemView.findViewById(R.id.userName);
                userRank = itemView.findViewById(R.id.userRank);
                userTotalPoints = itemView.findViewById(R.id.userTotalPoints);
            }
        }
    }

}