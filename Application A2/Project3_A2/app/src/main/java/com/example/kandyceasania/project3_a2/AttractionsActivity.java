package com.example.kandyceasania.project3_a2;

import android.app.Activity;
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


public class AttractionsActivity extends AppCompatActivity implements ListSelectionListener {
    private Details_Fragment details_fragment;
    private List_Fragment list_fragment;
    private ListView listView;
    static String[] SITES, ATTRACTIONS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        //replace action bar with newly created toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //get sites string array
        SITES = getResources().getStringArray(R.array.aSitesArray);

        //get restaurants array
        ATTRACTIONS = getResources().getStringArray(R.array.AttractionsArray);

        //get reference to Details_Fragment
        details_fragment = (Details_Fragment) getSupportFragmentManager().findFragmentById(R.id.details);
        list_fragment = (List_Fragment) getSupportFragmentManager().findFragmentById(R.id.list);

        //set Restaurants string array to List_Fragment
        listView = (ListView) findViewById(R.id.fragment_list);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, ATTRACTIONS);

        listView.setAdapter(adapter);
        resetLayout();
    }

    //updates back button variable in List_Fragment depending on whether
    //the action was caused by a back button press, then
    //reset the layout to initial config
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

    //reset the layout to initial config
    private void resetLayout() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(details_fragment);
        ft.show(list_fragment);
        ft.commit();

        // Deselects last item selected (clicked)
        listView.setItemChecked(details_fragment.getShownIndex(), false);
    }

    //set layout of fragments depending on orientation
    private void setLayout() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (getResources().getConfiguration().orientation == 1) { // Portrait Orientation
            //Log.i("TAG", "Portrait Orientation");
            ft.show(details_fragment);
            ft.hide(list_fragment);
            ft.commit();
        } else { // Landscape Orientation
            //Log.i("TAG", "Landscape Orientation");
            ft.show(details_fragment);
            ft.show(list_fragment);
            ft.commit();
        }
    }

    // Create Options Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Called when the user selects an item in the Options Menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Restaurants:
                Intent intent = new Intent(this, RestaurantsActivity.class);
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }

    // Called when the user selects an item in the List_Fragment
    @Override
    public void onListSelection(int index) {
        details_fragment.showSiteAtIndex(index, SITES);
        setLayout();
    }
}