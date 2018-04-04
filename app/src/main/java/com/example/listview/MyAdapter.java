package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    private int sizeOfBikes;
    Context context;
    private LayoutInflater inflater;
    ViewHolder myVH;
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

            convertView = inflater.inflate(R.layout.listview_row_layout, null);

            myVH = new ViewHolder();

            // might need a few more of these
            myVH.imageView1 = (ImageView)convertView.findViewById(R.id.imageView1);
            myVH.model = (TextView)convertView.findViewById(R.id.Model);
            myVH.price = (TextView)convertView.findViewById(R.id.Price);
            myVH.description = (TextView)convertView.findViewById(R.id.Description);


            convertView.setTag(myVH);
        }

        myVH = (ViewHolder)convertView.getTag();
        //myVH.imageView1.setImage();
        //mH.model.setText();

        return convertView;

    }
    private class ViewHolder{

        ImageView imageView1;
        TextView model;
        TextView price;
        TextView description;



    }
}

