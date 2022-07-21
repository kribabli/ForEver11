package com.example.yoyoiq.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.yoyoiq.CreateTeamActivity;
import com.example.yoyoiq.R;

public class ContestsFragment extends Fragment {
    TextView matchList, createTeam;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String match_id;


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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contests, container, false);
        matchList = root.findViewById(R.id.matchList);
        createTeam = root.findViewById(R.id.createTeam);

        //get bundle values in Fragment
        //Bundle bundle=getArguments();
        //String match_id = bundle.getString("match_id");

        if (getArguments() != null) {
            match_id = getArguments().getString("match_id");
        }

        createTeam.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), CreateTeamActivity.class);
            intent.putExtra("match_id", getArguments().getString("match_id"));
            startActivity(intent);

        });

        return root;
    }
}