package com.example.yoyoiq.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Adapter.ContestsListAdapter;
import com.example.yoyoiq.ContestPOJO.ContestsListPOJO;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class ContestsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String match_id;
    String matchA, matchB;
    ArrayList<ContestsListPOJO> list = new ArrayList<>();
    RecyclerView recyclerViewContest;
    private String mParam1;
    private String mParam2;

    public ContestsFragment() {
        // Required empty public constructor
    }

    public static ContestsFragment newInstance(String param1, String param2) {
        ContestsFragment fragment = new ContestsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getAllContests();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ContestsListAdapter contestsListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contests, container, false);
        if (getArguments() != null) {
            match_id = getArguments().getString("match_id");
        }

        recyclerViewContest = root.findViewById(R.id.contestsList);
        return root;
    }

//    private void getAllContests() {
//        matchA = getArguments().getString("matchA");
//        matchB = getArguments().getString("matchB");
//
//        Call<Contests> call = ApiClient
//                .getInstance()
//                .getApi()
//                .getContestsList(getArguments().getString("match_id"));
//
//        call.enqueue(new Callback<Contests>() {
//            @Override
//            public void onResponse(Call<Contests> call, Response<Contests> response) {
//                Contests contests = response.body();
//                if (response.isSuccessful()) {
//                    String jsonArray = new Gson().toJson(contests.getResponse());
//                    Log.d("TAG", "onResponse2222: " + jsonArray);
//
//                    //----------------------for Contests----------------------------
//                    JSONArray jsonArray1Contest = null;
//                    try {
//                        jsonArray1Contest = new JSONArray(jsonArray);
//                        for (int i = 0; i < jsonArray1Contest.length(); i++) {
//                            JSONObject jsonObjectContest = jsonArray1Contest.getJSONObject(i);
//                            String contest_id = jsonObjectContest.getString("contest_id");
//                            String contest_name = jsonObjectContest.getString("contest_name");
//                            String entry = jsonObjectContest.getString("entry");
//                            String join_team = jsonObjectContest.getString("join_team");
//                            String prize_pool = jsonObjectContest.getString("prize_pool");
//                            String total_team = jsonObjectContest.getString("total_team");
//                            String winners = jsonObjectContest.getString("winners");
//
////                            ContestsListPOJO contestsListPOJO = new ContestsListPOJO(contest_id, contest_name, entry, join_team, prize_pool, total_team, winners);
////                            list.add(contestsListPOJO);
////                            contestsListAdapter = new ContestsListAdapter(getContext(), list);
////                            recyclerViewContest.setLayoutManager(new LinearLayoutManager(getContext()));
////                            recyclerViewContest.setAdapter(contestsListAdapter);
////                            contestsListAdapter.notifyDataSetChanged();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                } else {
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Contests> call, Throwable t) {
//            }
//        });
//
//    }
}