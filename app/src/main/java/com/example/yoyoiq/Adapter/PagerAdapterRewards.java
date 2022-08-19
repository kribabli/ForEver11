package com.example.yoyoiq.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.yoyoiq.InSideRewardsFragments.MyRewardsFragment;
import com.example.yoyoiq.InSideRewardsFragments.RewardShopFragment;

public class PagerAdapterRewards extends FragmentPagerAdapter {
    int tabCount;

    public PagerAdapterRewards(@NonNull FragmentManager fm, int behavior) {
        super(fm);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RewardShopFragment();
            case 1:
                return new MyRewardsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}