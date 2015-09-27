package com.lorentzos.swipecards;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomasrodriguez on 9/27/15.
 */
public class ListCustomAdapter extends BaseAdapter {

    List<ParseObject> objs;
    Context context;
    private LayoutInflater mInflater;

    public ListCustomAdapter(List<ParseObject> objects, Context context) {
        this.objs = objects;
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return objs.size();
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
        if(view == null) {

            view = mInflater.inflate(R.layout.list_item, parentView, false);
            Log.d("getView", "view: " + view.toString());

            TextView imgView = (TextView) view.findViewById(R.id.leftText);
            Log.d("getView", "imgView ID: " + imgView.getId());
            TextView title = (TextView) view.findViewById(R.id.rightText);
            Log.d("getView", "textView ID: " + title.getText());


            imgView.setText(this.objs.get(position).getString("name"));
            title.setText(String.valueOf(this.objs.get(position).getNumber("score")));
            Log.d("getView" ,  "asdfasdf");
        }






        return view;
    }
}
