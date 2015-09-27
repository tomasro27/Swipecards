package com.lorentzos.swipecards;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tomasrodriguez on 9/27/15.
 */
public class SwipeCardsCustomAdapter extends BaseAdapter {

    ArrayList<String> titles;
    ArrayList<Integer> images;
    Context context;
    private LayoutInflater mInflater;

    public SwipeCardsCustomAdapter(ArrayList<String> titles, ArrayList<Integer> images, Context context) {
        this.titles = titles;
        this.images = images;
        this.context = context;
        Log.d("swipecardsadapter", context.getApplicationInfo().toString());
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parentView) {
        View view = convertView;
        Log.d("getView", "convertView: " + convertView);
        if(view == null) {

            Log.d("getView", "view is null");
            //LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Log.d("getView", "layoutinflater vi: " + mInflater.toString());
            view = mInflater.inflate(R.layout.item, parentView, false);
            Log.d("getView", "view: " + view.toString());

            ImageView imgView = (ImageView) view.findViewById(R.id.imageView);
            Log.d("getView", "imgView ID: " + imgView.getId());
            TextView title = (TextView) view.findViewById(R.id.helloText);
            Log.d("getView", "textView ID: " + title.getText());


            title.setText(this.titles.get(position));
            imgView.setImageResource(this.images.get(position));
            Log.d("getView" ,  "asdfasdf");
        }






        return view;
    }
}
