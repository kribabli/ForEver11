package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.yoyoiq.Model.The_Slide_Items_Model_Class;
import com.example.yoyoiq.R;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    Context context;
    List<The_Slide_Items_Model_Class> bannerListImages;
    //This Adapter is use for Banner

    public BannerAdapter(Context context, List<The_Slide_Items_Model_Class> bannerListImages) {
        this.context = context;
        this.bannerListImages = bannerListImages;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout = inflater.inflate(R.layout.banner_layout, null);
        ImageView allSliderBannerImages = sliderLayout.findViewById(R.id.allSliderBannerImages);
        allSliderBannerImages.setImageResource(bannerListImages.get(position).getUri());
        container.addView(sliderLayout);
        return sliderLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return bannerListImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
