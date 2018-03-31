package com.example.kandyceasania.project3_a2;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class RestaurantsActivity extends AppCompatActivity implements ListSelectionListener {
    private Details_Fragment details_fragment;
    private List_Fragment list_fragment;
    private ListView listView;
    static String[] SITES, RESTAURANTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        //replace action bar with newly created toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(myToolbar);

        //get sites string array
        SITES =

                getResources().

                        getStringArray(R.array.rSitesArray);

        //get restaurants array
        RESTAURANTS =

                getResources().

                        getStringArray(R.array.RestaurantsArray);

        //get reference to Details_Fragment
        details_fragment = (Details_Fragment)

                getSupportFragmentManager().

                        findFragmentById(R.id.details);

        list_fragment = (List_Fragment)

                getSupportFragmentManager().

                        findFragmentById(R.id.list);

        //set Restaurants string array to List_Fragment
        listView = (ListView)

                findViewById(R.id.fragment_list);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, RESTAURANTS);

        listView.setAdapter(adapter);

        resetLayout();

    }

    //updates back button variable in List_Fragment depending on whether
    //the action was caused by a back button press
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if(details_fragment.getShownIndex() == -1){
                finishAffinity();
                moveTaskToBack(true);
            }
            list_fragment.BACK_BUTTON = true;
            resetLayout();
            list_fragment.resetCurrentIndex();
            details_fragment.resetCurrentIndex();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void resetLayout() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.hide(details_fragment);
        ft.show(list_fragment);
        ft.commit();

        listView.setItemChecked(details_fragment.getShownIndex(), false);
    }

    private void setLayout() {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (getResources().getConfiguration().orientation == 1) {
            ft.show(details_fragment);
            ft.hide(list_fragment);
            ft.commit();
        } else {
            ft.show(details_fragment);
            ft.show(list_fragment);
            ft.commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Attractions:
                Intent intent = new Intent(this, AttractionsActivity.class);
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onListSelection(int index) {
        details_fragment.showSiteAtIndex(index, SITES);
        setLayout();
    }

}