package com.example.yoyoiq.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.yoyoiq.InSideContestActivityFragments.ContestsFragment;
import com.example.yoyoiq.InSideContestActivityFragments.MyContestsFragment;
import com.example.yoyoiq.InSideContestActivityFragments.MyTeamsFragment;

public class PageAdapter extends FragmentPagerAdapter {
    int tabCount;
    String match_id = "";
    String matchA = "";
    String matchB = "";
    String logo_url_a = "";
    String logo_url_b = "";
    String date_start = "";
    String date_end = "";

    public PageAdapter(@NonNull FragmentManager fm, int behavior, String match_id, String matchA, String matchB, String logo_url_a, String logo_url_b, String date_start, String date_end) {
        super(fm, behavior);
        tabCount = behavior;
        this.match_id = match_id;
        this.matchA = matchA;
        this.matchB = matchB;
        this.logo_url_a = logo_url_a;
        this.logo_url_b = logo_url_b;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putString("match_id", match_id);
                bundle.putString("matchA", matchA);
                bundle.putString("matchB", matchB);
                bundle.putString("logo_url_a", logo_url_a);
                bundle.putString("logo_url_b", logo_url_b);
                bundle.putString("date_start", date_start);
                bundle.putString("date_end", date_end);
                ContestsFragment contestsFragment = new ContestsFragment();
                contestsFragment.setArguments(bundle);
                return contestsFragment;
            case 1:
                return new MyContestsFragment();
            case 2:
                Bundle bundle1 = new Bundle();
                bundle1.putString("match_id", match_id);
                bundle1.putString("matchA", matchA);
                bundle1.putString("matchB", matchB);
                bundle1.putString("logo_url_a", logo_url_a);
                bundle1.putString("logo_url_b", logo_url_b);
                bundle1.putString("date_start", date_start);
                bundle1.putString("date_end", date_end);
                MyTeamsFragment myTeamsFragment = new MyTeamsFragment();
                myTeamsFragment.setArguments(bundle1);
                return myTeamsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
