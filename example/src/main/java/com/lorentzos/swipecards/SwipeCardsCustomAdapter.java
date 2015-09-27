package com.lorentzos.swipecards;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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


            imgView.setImageBitmap(
                    decodeSampledBitmapFromResource(context.getResources(), this.images.get(position), 100, 100));
            title.setText(this.titles.get(position));
            //imgView.setImageResource(this.images.get(position));
            Log.d("getView" ,  "asdfasdf");
        }






        return view;
    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
