package com.example.yoyoiq.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Adapter.SquadsBAdapter;
import com.example.yoyoiq.Model.SquadsA;
import com.example.yoyoiq.PlayerPOJO.ResponsePlayer;
import com.example.yoyoiq.R;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BATFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<SquadsA> list = new ArrayList<>();
    RecyclerView recyclerView;
    String matchA, matchB, team_idA, team_idB;
    ArrayList listPlayerIdA = new ArrayList();
    String fantasy_player_ratingPlayers, pidPlayers, playing_rolePlayers, short_namePlayers, abbrA;
    String roleA;
    String substituteA;
    String role_strA;
    String playing11A;
    String nameA;
    String player_idA;

    private String mParam1;
    private String mParam2;

    public BATFragment() {
        // Required empty public constructor
    }

    public static BATFragment newInstance(String param1, String param2) {
        BATFragment fragment = new BATFragment();
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

    SquadsBAdapter squadsBAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_b_a_t, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        return root;
    }

    private void getAllPlayer() {
        list.clear();
        listPlayerIdA.clear();
        matchA = getArguments().getString("matchA");
        matchB = getArguments().getString("matchB");

        Call<ResponsePlayer> call = ApiClient
                .getInstance()
                .getApi()
                .getMatchPlaying11(getArguments().getString("match_id"));

        call.enqueue(new Callback<ResponsePlayer>() {
            @Override
            public void onResponse(Call<ResponsePlayer> call, Response<ResponsePlayer> response) {
                ResponsePlayer responsePlayer = response.body();
                if (response.isSuccessful()) {
                    String jsonArray = new Gson().toJson(responsePlayer.getResponsePlay().getTeama());
                    String SquadsA = new Gson().toJson(responsePlayer.getResponsePlay().getTeama().getSquads());

                    String jsonArray1 = new Gson().toJson(responsePlayer.getResponsePlay().getTeamb());
                    String SquadsB = new Gson().toJson(responsePlayer.getResponsePlay().getTeamb().getSquads());

                    String jsonArray2 = new Gson().toJson(responsePlayer.getResponsePlay().getTeams());
                    String jsonArray3 = new Gson().toJson(responsePlayer.getResponsePlay().getPlayers());

                    //----------------------for TeamB----------------------------
                    JSONObject jsonObjectTeamB = null;
                    try {
                        jsonObjectTeamB = new JSONObject(jsonArray1);
                        for (int i = 0; i < jsonObjectTeamB.length(); i++) {
                            team_idB = jsonObjectTeamB.getString("team_id");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //----------------------for TeamA----------------------------
                    JSONObject jsonObjectTeamA = null;
                    try {
                        jsonObjectTeamA = new JSONObject(jsonArray);
                        for (int i = 0; i < jsonObjectTeamA.length(); i++) {
                            team_idA = jsonObjectTeamA.getString("team_id");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //----------------------for TeamS----------------------------
                    JSONArray jsonArrayS = null;
                    try {
                        jsonArrayS = new JSONArray(jsonArray2);
                        for (int i = 0; i < jsonArrayS.length(); i++) {
                            JSONObject jsonObject = jsonArrayS.getJSONObject(i);
                            String tid = jsonObject.getString("tid");
                            if (tid.equals(team_idA) || tid.equals(team_idB)) {
                                abbrA = jsonObject.getString("abbr");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    JSONArray jsonArrayA = null;
                    try {
                        jsonArrayA = new JSONArray(SquadsA);
                        for (int i = 0; i < jsonArrayA.length(); i++) {
                            JSONObject jsonObject = jsonArrayA.getJSONObject(i);
                            roleA = jsonObject.getString("role");
                            substituteA = jsonObject.getString("substitute");
                            role_strA = jsonObject.getString("role_str");
                            playing11A = jsonObject.getString("playing11");
                            nameA = jsonObject.getString("name");
                            if (roleA.equals("bat")) {
                                player_idA = jsonObject.getString("player_id");
                                listPlayerIdA.add(player_idA);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    JSONObject jsonObjectPlayers = null;
                    //----------------------for Players----------------------------
                    JSONArray jsonArrayPlayers = null;
                    JSONArray teamSquadsA = null;
                    JSONArray teamSquadsB = null;
                    JSONArray teamsInformation = null;
                    try {
                        jsonArrayPlayers = new JSONArray(jsonArray3);
                        teamSquadsA = new JSONArray(SquadsA);
                        teamSquadsB = new JSONArray(SquadsB);
                        teamsInformation = new JSONArray(jsonArray2);
                        ArrayList<String> allTeamAPlayerId = new ArrayList<>();
                        ArrayList<String> allTeamBPlayerId = new ArrayList<>();
                        ArrayList<String> allTeamInformation = new ArrayList<>();
                        Map<String, String> myMap = new HashMap<String, String>();
                        String playing11 = null;

                        for (int k = 0; k < teamSquadsA.length(); k++) {
                            JSONObject xObj = teamSquadsA.getJSONObject(k);
                            playing11 = xObj.getString("playing11");
                            String player_id = xObj.getString("player_id");
                            allTeamAPlayerId.add(player_id);
                            myMap.put(player_id, playing11);
                        }

                        for (int k = 0; k < teamSquadsB.length(); k++) {
                            JSONObject xObj = teamSquadsB.getJSONObject(k);
                            playing11 = xObj.getString("playing11");
                            String player_id = xObj.getString("player_id");
                            allTeamBPlayerId.add(player_id);
                            myMap.put(player_id, playing11);
                        }

                        for (int i = 0; i < jsonArrayPlayers.length(); i++) {
                            jsonObjectPlayers = jsonArrayPlayers.getJSONObject(i);
                            pidPlayers = jsonObjectPlayers.getString("pid");
                            playing_rolePlayers = jsonObjectPlayers.getString("playing_role");
                            if (playing_rolePlayers.equals("bat")) {
                                short_namePlayers = jsonObjectPlayers.getString("short_name");
                                fantasy_player_ratingPlayers = jsonObjectPlayers.getString("fantasy_player_rating");
                                if (allTeamAPlayerId.contains(pidPlayers)) {
                                    abbrA = matchA;
                                } else if (allTeamBPlayerId.contains(pidPlayers)) {
                                    abbrA = matchB;
                                }
                                if (myMap.containsKey(pidPlayers)) {
                                    playing11A = myMap.get(pidPlayers);
                                }
                                SquadsA squadsA = new SquadsA(player_idA, roleA, substituteA, role_strA, playing11A, nameA, matchA, fantasy_player_ratingPlayers, short_namePlayers, pidPlayers, abbrA, false);
                                list.add(squadsA);
                                squadsBAdapter = new SquadsBAdapter(getContext(), list);
                            }
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(squadsBAdapter);
                        squadsBAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                }
            }

            @Override
            public void onFailure(Call<ResponsePlayer> call, Throwable t) {
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getAllPlayer();
        }
    }
}