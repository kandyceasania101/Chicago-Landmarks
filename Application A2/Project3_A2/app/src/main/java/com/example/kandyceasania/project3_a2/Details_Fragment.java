package com.example.kandyceasania.project3_a2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Details_Fragment extends Fragment {
    private int currIndex = -1;
    private WebView siteView;
    private String[] SITES;

    //Getters and Setters
    public int getShownIndex(){
        return currIndex;
    }

    public void resetCurrentIndex() { currIndex = -1; }

    public void showSiteAtIndex(int index, String[] sites) {
        SITES = sites;

        if (index < 0 || index >= SITES.length) {
            return;
        }

        siteView.getSettings().setJavaScriptEnabled(true);

        // Set WebView client
        siteView.setWebChromeClient(new WebChromeClient());

        siteView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        siteView.loadUrl(SITES[index]);

        currIndex = index;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_, container, false);
    }

    // Set up some information about site view
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        siteView = getActivity().findViewById(R.id.site_view);

        showSiteAtIndex(currIndex, SITES);
    }
}
