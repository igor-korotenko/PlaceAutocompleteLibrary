package com.st1x.placeautocomplete.lib.googleplaces.models;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlacesPredictions extends Result {

    private List<Prediction> mPlaces = new ArrayList<Prediction>();

    public PlacesPredictions(JSONObject jsonResponse) throws JSONException {
        super(jsonResponse);

        if (jsonResponse.has("predictions")) {
            JSONArray results = jsonResponse.getJSONArray("predictions");

            for(int i = 0; i < results.length(); i++) {
                Gson gson = new Gson();
                Prediction place = gson.fromJson(results.getJSONObject(i).toString(), Prediction.class);
                mPlaces.add(place);
            }
        }
    }

    public List<Prediction> getPredictions() {
        return mPlaces;
    }


}
