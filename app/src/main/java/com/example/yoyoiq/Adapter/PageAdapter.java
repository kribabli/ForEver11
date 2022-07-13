package com.example.yoyoiq.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.yoyoiq.Fragment.ContestsFragment;
import com.example.yoyoiq.Fragment.MyContestsFragment;
import com.example.yoyoiq.Fragment.MyTeamsFragment;

public class PageAdapter extends FragmentPagerAdapter {
    int tabCount;

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ContestsFragment();
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
