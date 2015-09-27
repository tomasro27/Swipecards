package com.lorentzos.swipecards;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.lorentzos.swipecards.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class StatsActivity extends Activity {

    ListView statsList;
    ListCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Log.d("stats", "enteret");
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("place");
        query.orderByDescending("score");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if ( e == null)
                {
                    Log.d("stats", "inside");
                    Log.d("stats", parseObjects.size() + "   " + parseObjects.get(0).get("name"));
                    adapter = new ListCustomAdapter(parseObjects, getApplicationContext());
                    statsList = (ListView) findViewById(R.id.statsList);
                    statsList.setAdapter(adapter);
                } else
                {
                    Log.d("stats", e.toString());
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
