package com.example.yoyoiq.InSideMyMatches;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.yoyoiq.Adapter.AllMatchAdapter;
import com.example.yoyoiq.CompletedMatchPOJO.CompletedMatchPOJO;
import com.example.yoyoiq.ContestActivity;
import com.example.yoyoiq.InSideAddCashLeaderboard.LeaderboardFragment;
import com.example.yoyoiq.Model.LeaderboardPOJO;
import com.example.yoyoiq.Model.TotalHomeData;
import com.example.yoyoiq.R;
import com.example.yoyoiq.common.HelperData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CompletedMatchFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    String user_id, user_id1;
    ArrayList<CompletedMatchPOJO> listItems = new ArrayList<>();
    String url = "https://adminapp.tech/yoyoiq/ItsMe/all_apis.php?func=users_completed_matches";

    public CompletedMatchFragment() {
        // Required empty public constructor
    }

    public static CompletedMatchFragment newInstance(String param1, String param2) {
        CompletedMatchFragment fragment = new CompletedMatchFragment();
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

    CompletedMatchAdapter completedMatchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_completed_match, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        swipeRefreshLayout = root.findViewById(R.id.swiper);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    getAllCompletedMatch(user_id);
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
                user_id1 = HelperData.UserId;
                user_id = String.valueOf(user_id1);
                getAllCompletedMatch(user_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void getAllCompletedMatch(String user_id) throws JSONException {
        listItems.clear();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray1 = new JSONArray();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    jsonArray1 = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                        String status = jsonObject1.getString("status");
                        String title = jsonObject1.getString("title");
                        String status_note = jsonObject1.getString("status_note");
                        String short_title = jsonObject1.getString("short_title");
                        String teama = jsonObject1.getString("teama");
                        String teamb = jsonObject1.getString("teamb");

//                         int team_id;
//                         String name;
//                         String short_name;
//                         String logo_url;
//                         String thumb_url;
//                         String scores_full;
//                         String scores;
//                         String overs;

                        CompletedMatchPOJO completedMatchPOJO = new CompletedMatchPOJO(status, title, status_note, short_title);
                        listItems.add(completedMatchPOJO);
                    }
                    swipeRefreshLayout.setRefreshing(false);
                    completedMatchAdapter = new CompletedMatchAdapter(getContext(), listItems);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(completedMatchAdapter);
                    completedMatchAdapter.notifyDataSetChanged();

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
                params.put("user_id", user_id);
                return params;
            }
        };
        queue.add(request);
    }

    //CompletedMath  Adapter
    public class CompletedMatchAdapter extends RecyclerView.Adapter<CompletedMatchAdapter.MyViewHolder> {
        Context context;
        ArrayList<CompletedMatchPOJO> list;

        public CompletedMatchAdapter(Context context, ArrayList<CompletedMatchPOJO> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public CompletedMatchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_list, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CompletedMatchAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            CompletedMatchPOJO listData = list.get(position);
            holder.setIsRecyclable(false);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ContestActivity.class);

//                    intent.putExtra("shortNameA", listData.getShort_name_a());
//                    intent.putExtra("shortNameB", listData.getShort_name_b());
//                    intent.putExtra("match_id", listData.getMatch_id());
//                    intent.putExtra("logo_url_a", listData.getLogo_url_a());
//                    intent.putExtra("logo_url_b", listData.getLogo_url_b());
//                    intent.putExtra("date_start", listData.getDate_start());
//                    intent.putExtra("date_end", listData.getDate_end());
//                    HelperData.matchId = listData.getMatch_id();

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
            holder.textViewTitle.setText(list.get(position).getTitle());
            holder.shortNameA.setText(listData.getShort_title());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textViewTitle, matchATv, matchBTv, shortNameA, shortNameB, leftTime, done, lineUp;
            ImageView matchAImage, matchBImage;
            CardView cardView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.matchTitle);
                lineUp = itemView.findViewById(R.id.lineUp);
                matchATv = itemView.findViewById(R.id.matcha);
                matchBTv = itemView.findViewById(R.id.matchb);
                shortNameA = itemView.findViewById(R.id.shortNameA);
                shortNameB = itemView.findViewById(R.id.shortNameB);
                leftTime = itemView.findViewById(R.id.leftTime);
                done = itemView.findViewById(R.id.done);
                matchAImage = itemView.findViewById(R.id.matchaImage);
                matchBImage = itemView.findViewById(R.id.matchbImage);
                cardView = itemView.findViewById(R.id.cardViewMatchList);
            }
        }
    }
}