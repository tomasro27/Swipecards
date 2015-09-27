package com.lorentzos.swipecards;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MyActivity extends Activity {

    private SwipeCardsCustomAdapter adapter;
    private int i;
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<Integer> imageResources = new ArrayList<>();
    @InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);

        titles.add("Jefferson Davis");
        titles.add("UT Punter vs. OSU");
        titles.add("UT Tower");
        titles.add("PCL");
        titles.add("Cabo Bob's");
        titles.add("Big Bite");
        titles.add("GDC Sculpture");
        titles.add("Clock Knot");
        titles.add("GDC Door That's Been Broken For Months");
        titles.add("Niko");
        titles.add("J2");
        titles.add("Giant Canoe Sculpture on Speedway");

        imageResources.add(R.drawable.leff);
        imageResources.add(R.drawable.punter);
        imageResources.add(R.drawable.tower);
        imageResources.add(R.drawable.pcl);
        imageResources.add(R.drawable.cabo_bobs);
        imageResources.add(R.drawable.bigbite);
        imageResources.add(R.drawable.gdc);
        imageResources.add(R.drawable.clock);
        imageResources.add(R.drawable.gdc_door);
        imageResources.add(R.drawable.niko);
        imageResources.add(R.drawable.j2);
        imageResources.add(R.drawable.canoe);

        adapter = new SwipeCardsCustomAdapter(titles, imageResources, this);


        flingContainer.setAdapter(adapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                String temp1 = titles.get(0);
                Integer temp2 = imageResources.get(0);
                titles.remove(0);
                imageResources.remove(0);
                titles.add(temp1);
                imageResources.add(temp2);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                makeToast(MyActivity.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(MyActivity.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(MyActivity.this, "Clicked!");
            }
        });

    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.right)
    public void right() {
        /**
         * Trigger the right event manually.
         */
        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.left)
    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }




}
