package com.example.kandyceasania.project3_a2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class List_Fragment extends Fragment {
    ListView listView;
    public int currIndex = -1; //current index
    private ListSelectionListener listener;
    public boolean BACK_BUTTON = false; //used to check whether back button has been pressed

    public void resetCurrentIndex() { currIndex = -1; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_list_, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //attach list view to fragment, set click listener, and
        // update current index for later use
        listView = getView().findViewById(R.id.fragment_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listView.setItemChecked(i, true);
                listener.onListSelection(i);
                currIndex = i;
            }
        });

        if (-1 != currIndex && !BACK_BUTTON) {
            listView.setItemChecked(currIndex, true);
            listener.onListSelection(currIndex);
        }

        BACK_BUTTON = false;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            // Set the ListSelectionListener for communicating with Activities
            listener = (ListSelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }
}
