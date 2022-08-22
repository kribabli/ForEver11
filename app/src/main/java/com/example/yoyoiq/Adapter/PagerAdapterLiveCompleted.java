package com.example.yoyoiq.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.yoyoiq.InSideMyMatches.CompletedMatchFragment;
import com.example.yoyoiq.InSideMyMatches.LiveMatchFragment;
import com.example.yoyoiq.InSideMyMatches.UpcomingMatchFragment;

public class PagerAdapterLiveCompleted extends FragmentPagerAdapter {
    int tabCount;

    public PagerAdapterLiveCompleted(@NonNull FragmentManager fm, int behavior) {
        super(fm);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new UpcomingMatchFragment();
            case 1:
                return new LiveMatchFragment();
            case 2:
                return new CompletedMatchFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}