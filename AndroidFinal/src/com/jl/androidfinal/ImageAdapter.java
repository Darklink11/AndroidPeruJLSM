package com.jl.androidfinal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
             int width = 50;
            int  height = 50;
            imageView.setLayoutParams(new GridView.LayoutParams(width, height));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            int padding = 8;
            imageView.setPadding(padding,padding,padding,padding);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab,
            R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab,
            R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab,
            R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.pieza1 ,R.drawable.pieza2 , R.drawable.tab ,R.drawable.tab , R.drawable.tab,
            R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.pieza2 ,R.drawable.pieza1 , R.drawable.tab ,R.drawable.tab , R.drawable.tab,
            R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab,
            R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab,
            R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab ,R.drawable.tab , R.drawable.tab
    };
}