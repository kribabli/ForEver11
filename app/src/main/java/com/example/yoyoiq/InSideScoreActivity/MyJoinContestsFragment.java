package com.example.yoyoiq.InSideScoreActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.yoyoiq.LeaderboardActivity;
import com.example.yoyoiq.OnlyMyContestPOJO.MyContest1;
import com.example.yoyoiq.OnlyMyContestPOJO.TotalJoinContestsData;
import com.example.yoyoiq.R;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SessionManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyJoinContestsFragment extends Fragment {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<TotalJoinContestsData> list = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public MyJoinContestsFragment() {
        // Required empty public constructor
    }

    public static MyJoinContestsFragment newInstance(String param1, String param2) {
        MyJoinContestsFragment fragment = new MyJoinContestsFragment();
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

    MyJoinContestsAdapter myJoinContestsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_join_contests, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        swipeRefreshLayout = root.findViewById(R.id.swiper);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllJoinContests();
            }
        });
        return root;
    }

    private void getAllJoinContests() {
        list.clear();
        Call<MyContest1> call = ApiClient
                .getInstance()
                .getApi()
                .getJoinContestList(HelperData.UserId, HelperData.matchId);

        call.enqueue(new Callback<MyContest1>() {
            @Override
            public void onResponse(Call<MyContest1> call, Response<MyContest1> response) {
                MyContest1 status = response.body();
                if (response.isSuccessful()) {
                    String jsonArray = new Gson().toJson(status.getResponse());
                    JSONArray jsonArray1 = null;
                    try {
                        jsonArray1 = new JSONArray(jsonArray);
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            try {
                                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                                JSONArray jsonArray2 = jsonObject1.getJSONArray("contest");
                                for (int j = 0; j < jsonArray2.length(); j++) {
                                    JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
                                    String contest_description = jsonObject2.getString("contest_description");
                                    String contest_id = jsonObject2.getString("contest_id");
                                    String contest_name = jsonObject2.getString("contest_name");
                                    String first_price = jsonObject2.getString("first_price");
                                    String match_id = jsonObject2.getString("match_id");
                                    String prize_pool = jsonObject2.getString("prize_pool");
                                    String total_team = jsonObject2.getString("total_team");
                                    String join_team = jsonObject2.getString("join_team");
                                    String price_contribution = jsonObject2.getString("price_contribution");

                                    TotalJoinContestsData totalJoinContestsData = new TotalJoinContestsData(contest_description, contest_id, contest_name, first_price, match_id, prize_pool, total_team, join_team, price_contribution);
                                    list.add(totalJoinContestsData);
                                    myJoinContestsAdapter = new MyJoinContestsAdapter(getContext(), list);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerView.setAdapter(myJoinContestsAdapter);
                                    myJoinContestsAdapter.notifyDataSetChanged();
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                                swipeRefreshLayout.setRefreshing(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    } catch (JSONException e) {
                        swipeRefreshLayout.setRefreshing(false);
                        e.printStackTrace();
                    }
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<MyContest1> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getAllJoinContests();
        }
    }

    //Adapter for join ContestsList
    public class MyJoinContestsAdapter extends RecyclerView.Adapter<MyJoinContestsAdapter.MyViewHolder> {
        Context context;
        ArrayList<TotalJoinContestsData> list;
        SessionManager sessionManager;

        public MyJoinContestsAdapter(Context context, ArrayList<TotalJoinContestsData> list) {
            this.context = context;
            this.list = list;
            sessionManager = new SessionManager(context.getApplicationContext());
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_contest_list, parent, false);
            return new MyViewHolder(view);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            TotalJoinContestsData listData = list.get(position);
            if (list.size() > 0) {
                holder.contestName.setText(listData.getContest_name());
                holder.totalSports.setText(listData.getTotal_team() + "  Spots");
                holder.first_price.setText(listData.getFirst_price());
                holder.userName.setText(HelperData.UserName + "  T1");

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, LeaderboardActivity.class);
                        intent.putExtra("contest_description", listData.getContest_description());
                        intent.putExtra("contest_id", listData.getContest_id());
                        intent.putExtra("match_id", listData.getMatch_id());
                        intent.putExtra("price_contribution", listData.getPrice_contribution());
                        intent.putExtra("contest_name", listData.getContest_name());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView userName, getTotalPoints, userRank, first_price, contestName, totalSports;
            CardView cardView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                contestName = itemView.findViewById(R.id.contestName);
                totalSports = itemView.findViewById(R.id.totalSports);
                first_price = itemView.findViewById(R.id.first_price);
                userName = itemView.findViewById(R.id.userName);
                getTotalPoints = itemView.findViewById(R.id.getTotalPoints);
                userRank = itemView.findViewById(R.id.userRank);
                cardView = itemView.findViewById(R.id.cardView);
            }
        }
    }
}