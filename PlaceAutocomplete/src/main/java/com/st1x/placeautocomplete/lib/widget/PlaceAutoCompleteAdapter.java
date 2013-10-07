package com.st1x.placeautocomplete.lib.widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.st1x.placeautocomplete.lib.googleplaces.GooglePlaces;
import com.st1x.placeautocomplete.lib.googleplaces.models.PlacesPredictions;
import com.st1x.placeautocomplete.lib.googleplaces.models.Prediction;
import com.st1x.placeautocomplete.lib.googleplaces.query.AutoCompleteSearchQuery;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by st1x on 10/7/13.
 */

public class PlaceAutoCompleteAdapter extends ArrayAdapter<Prediction> {

    private List<Prediction> mPlaces;
    private LayoutInflater mInflater;
    private int mResource;
    private AutoCompleteSearchQuery mQuery;
    private GooglePlaces mGooglePlaces;

    public PlaceAutoCompleteAdapter(Context context, GooglePlaces googlePlaces, int textViewResourceId, AutoCompleteSearchQuery placeQuery) {
        super(context, textViewResourceId);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResource = textViewResourceId;
        mQuery = placeQuery;
        mGooglePlaces = googlePlaces;
    }

    @Override
    public int getCount() {
        return mPlaces.size();
    }

    @Override
    public Prediction getItem(int index) {
        return mPlaces.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        View view;
        TextView text;

        if (convertView == null) {
            view = mInflater.inflate(mResource, parent, false);
        } else {
            view = convertView;
        }
        text = (TextView) view;
        Prediction item = getItem(position);
        if (text != null) {
            text.setText(item.getDescription());
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    // Retrieve the autocomplete results.
                    mPlaces = autocompletePlace(constraint.toString());
                    // Assign the data to the FilterResults
                    filterResults.values = mPlaces;
                    filterResults.count = mPlaces.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }

            }
        };
        return filter;
    }

    private List<Prediction> autocompletePlace(String constraint) {
        List<Prediction> places = new ArrayList<Prediction>();
        try {
            mQuery.setInput(constraint);
            PlacesPredictions result = mGooglePlaces.getPredictions(mQuery);
            if (result.requestSucceeded()) {
                places = result.getPredictions();
            } else {
                Log.w("PlacesAPI", result.getStatusCode().name());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return places;
    }

}
