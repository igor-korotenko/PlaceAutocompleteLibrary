package com.st1x.placeautocomplete.lib.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.maps.model.LatLng;
import com.st1x.placeautocomplete.lib.googleplaces.GooglePlaces;
import com.st1x.placeautocomplete.lib.googleplaces.query.AutoCompleteSearchQuery;

/**
 * View represents AutoComplete functionality with Google API Places as suggestions;
 *
 * Created by Igor Korotenko on 10/7/13.
 */


public class PlaceAutoCompleteTextView extends AutoCompleteTextView {

    private final int THRESHOLD = 3;
    private final static int DELAY = 750;
    private String apiKey;
    private GooglePlaces googlePlaces;
    private AutoCompleteSearchQuery query = new AutoCompleteSearchQuery();

    // DELAY
    private Handler mHandler = new Handler();
    private int mDelay;
    private Runnable mRunnable;


    public PlaceAutoCompleteTextView(Context context) {
        super(context, null);
    }

    public PlaceAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO get apiKey from xml res
        googlePlaces = new GooglePlaces(/*apiKey*/"AIzaSyBM3Ov7SmGwG8WbBJQDdGZSNXKDjUk0ySE");
        setDelay(DELAY);
        setThreshold(THRESHOLD);
        setAdapter(new PlaceAutoCompleteAdapter(context, googlePlaces, android.R.layout.simple_spinner_dropdown_item, query));
    }

    // PUBLIC METHODS
    @Override
    protected void performFiltering(final CharSequence text, final int keyCode) {
        mHandler.removeCallbacks(mRunnable);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                PlaceAutoCompleteTextView.super.performFiltering(text, keyCode);
            }
        };

        // restart handler
        mHandler.postDelayed(mRunnable, mDelay);
    }

    public void setDelay(int delay) {
        mDelay = delay;
    }

    public void setLocation(LatLng location) {
        query.setLocation(location);
    }

    public void setTypes(String[] types) {
        query.setTypes(types);
    }

}
