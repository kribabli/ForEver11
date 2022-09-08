package com.example.yoyoiq.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.yoyoiq.InSideScoreActivity.LiveMyTeamFragment;
import com.example.yoyoiq.InSideScoreActivity.MyJoinContestsFragment;

public class PageAdapterScores extends FragmentPagerAdapter {
    int tabCount;
    String match_id = "";
    String matchA = "";
    String matchB = "";

    public PageAdapterScores(@NonNull FragmentManager fm, int behavior, String match_id, String matchA, String matchB) {
        super(fm, behavior);
        tabCount = behavior;
        this.match_id = match_id;
        this.matchA = matchA;
        this.matchB = matchB;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Bundle bundle1 = new Bundle();
                bundle1.putString("match_id", match_id);
                bundle1.putString("matchA", matchA);
                bundle1.putString("matchB", matchB);
                MyJoinContestsFragment myJoinContestsFragment = new MyJoinContestsFragment();
                myJoinContestsFragment.setArguments(bundle1);
                return myJoinContestsFragment;
            case 1:
                Bundle bundle = new Bundle();
                bundle.putString("match_id", match_id);
                bundle.putString("matchA", matchA);
                bundle.putString("matchB", matchB);
                LiveMyTeamFragment liveMyTeamFragment = new LiveMyTeamFragment();
                liveMyTeamFragment.setArguments(bundle);
                return liveMyTeamFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}