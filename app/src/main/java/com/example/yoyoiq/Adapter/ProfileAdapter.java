package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoyoiq.R;

public class ProfileAdapter extends BaseAdapter {
    private Context context;
    private String[] wordPositionTv;
    private int[] positionIv;

    public ProfileAdapter(Context context, String[] numberWord, int[] numberImage) {
        this.context = context;
        this.wordPositionTv = numberWord;
        this.positionIv = numberImage;
    }

    @Override
    public int getCount() {
        return wordPositionTv.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_profile, null, true);
        }
        ImageView imageView = convertView.findViewById(R.id.imageIv);
        TextView textView = convertView.findViewById(R.id.oilTrackingTv);

        imageView.setImageResource(positionIv[position]);
        textView.setText(wordPositionTv[position]);
        return convertView;
    }
}

