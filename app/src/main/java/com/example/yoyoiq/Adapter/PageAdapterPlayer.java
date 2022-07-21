package com.example.yoyoiq.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.yoyoiq.Fragment.ARFragment;
import com.example.yoyoiq.Fragment.BATFragment;
import com.example.yoyoiq.Fragment.BOWLFragment;
import com.example.yoyoiq.Fragment.WKFragment;

public class PageAdapterPlayer extends FragmentPagerAdapter {
    int tabCount;
    String match_id = "";

    public PageAdapterPlayer(@NonNull FragmentManager fm, int behavior, String match_id) {
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
                WKFragment wkFragment = new WKFragment();
                wkFragment.setArguments(bundle);
                return wkFragment;
            case 1:
                Bundle bundle1 = new Bundle();
                bundle1.putString("match_id", match_id);
                BATFragment batFragment = new BATFragment();
                batFragment.setArguments(bundle1);
                return batFragment;
            case 2:
                Bundle bundle2 = new Bundle();
                bundle2.putString("match_id", match_id);
                ARFragment arFragment = new ARFragment();
                arFragment.setArguments(bundle2);
                return arFragment;
            case 3:
                Bundle bundle3 = new Bundle();
                bundle3.putString("match_id", match_id);
                BOWLFragment bowlFragment = new BOWLFragment();
                bowlFragment.setArguments(bundle3);
                return bowlFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
