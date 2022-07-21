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

    public PageAdapter(@NonNull FragmentManager fm, int behavior, String match_id) {
        super(fm, behavior);
        tabCount = behavior;
        this.match_id = match_id;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putString("match_id", match_id);
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
