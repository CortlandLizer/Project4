package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter {
    private int sizeOfBikes;
    Context context;
    private LayoutInflater inflater;
    /**
     * takes in number of bikes to make correct size
     * @param size
     */
    public MyAdapter(int size, Context cxt){
        context = cxt;
        sizeOfBikes = size;
    }

    @Override
    public int getCount() {
        return sizeOfBikes;
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
        // only happens first time
        if (convertView == null){
            if (inflater == null){
                // instaniate inflater
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }


        }

        return null;

    }
}
