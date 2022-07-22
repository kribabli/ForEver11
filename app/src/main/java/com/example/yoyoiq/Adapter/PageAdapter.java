package com.example.yoyoiq.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.yoyoiq.Fragment.ContestsFragment;
import com.example.yoyoiq.Fragment.MyContestsFragment;
import com.example.yoyoiq.Fragment.MyTeamsFragment;

public class PageAdapter extends FragmentPagerAdapter {
    int tabCount;
    String match_id = "";
    String matchA = "";
    String matchB = "";
    String logo_url_a = "";
    String logo_url_b = "";

    public PageAdapter(@NonNull FragmentManager fm, int behavior, String match_id, String matchA, String matchB, String logo_url_a, String logo_url_b) {
        super(fm, behavior);
        tabCount = behavior;
        this.match_id = match_id;
        this.matchA = matchA;
        this.matchB = matchB;
        this.logo_url_a = logo_url_a;
        this.logo_url_b = logo_url_b;
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
                ContestsFragment contestsFragment = new ContestsFragment();
                contestsFragment.setArguments(bundle);
                return contestsFragment;
            case 1:
                return new MyContestsFragment();
            case 2:
                return new MyTeamsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
