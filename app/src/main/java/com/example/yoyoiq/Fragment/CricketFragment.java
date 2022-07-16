package com.example.yoyoiq.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.AllMatchAdapter;
import com.example.yoyoiq.Adapter.BannerAdapter;
import com.example.yoyoiq.Modal.The_Slide_Items_Model_Class;
import com.example.yoyoiq.Modal.TotalHomeData;
import com.example.yoyoiq.NewPoJo.Status;
import com.example.yoyoiq.R;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CricketFragment extends Fragment {
    ViewPager view_bannerItem;
    RecyclerView recyclerView;
    ArrayList<TotalHomeData> list = new ArrayList<>();
    private List<The_Slide_Items_Model_Class> listItems;


    public CricketFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAllMatches();
        setAutoSliderBanner();
        if (getArguments() != null) {
        }
    }


    public static CricketFragment getInstance() {
        return new CricketFragment();
    }

    BannerAdapter bannerAdapter;
    AllMatchAdapter allMatchAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cricket, container, false);
        view_bannerItem = root.findViewById(R.id.view_bannerItem);
        recyclerView = root.findViewById(R.id.recyclerViewMatchList);
        view_bannerItem.setAdapter(bannerAdapter);
        return root;
    }

    private void setAutoSliderBanner() {
        listItems = new ArrayList<>();
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner2));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner3));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner4));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner5));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner6));
        bannerAdapter = new BannerAdapter(getContext(), listItems);
        bannerAdapter.notifyDataSetChanged();
    }

    private void getAllMatches() {
        Call<Status> call = ApiClient
                .getInstance()
                .getApi()
                .getMatch();

        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                if (response.isSuccessful()) {
                    status.getResponseClass();
                    String jsonArray = new Gson().toJson(status.getResponseClass().getItems());
                    JSONArray jsonArray1 = null;
                    try {
                        jsonArray1 = new JSONArray(jsonArray);
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            JSONObject jsonObject = jsonArray1.getJSONObject(i);
                            String title = jsonObject.getString("title");
                            String match_id = jsonObject.getString("match_id");

                            JSONArray teama1 = jsonObject.getJSONArray("teama");
                            JSONArray teamb1 = jsonObject.getJSONArray("teamb");

                            JSONObject jsonObject11 = teama1.getJSONObject(0);
                            JSONObject jsonObject22 = teamb1.getJSONObject(0);

                            String logo_url_a = jsonObject11.getString("logo_url");
                            String name_a = jsonObject11.getString("name");
                            String short_name_a = jsonObject11.getString("short_name");
                            int teamIda = Integer.parseInt(jsonObject11.getString("team_id"));


                            String logo_url_b = jsonObject22.getString("logo_url");
                            String name_b = jsonObject22.getString("name");
                            String short_name_b = jsonObject22.getString("short_name");
                            int teamIdb = Integer.parseInt(jsonObject22.getString("team_id"));

                            TotalHomeData totalHomeData = new TotalHomeData(title, logo_url_a, name_a, short_name_a, logo_url_b, name_b, short_name_b);
                            list.add(totalHomeData);

                            allMatchAdapter = new AllMatchAdapter(getContext(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(allMatchAdapter);
                            allMatchAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
            }
        });
    }
}