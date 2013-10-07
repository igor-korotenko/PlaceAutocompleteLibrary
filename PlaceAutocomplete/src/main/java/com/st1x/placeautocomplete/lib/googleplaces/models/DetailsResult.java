package com.st1x.placeautocomplete.lib.googleplaces.models;

import org.json.JSONException;
import org.json.JSONObject;


public class DetailsResult extends Result {

	private Place mDetails;
	
	public DetailsResult(JSONObject jsonResponse) throws JSONException {
		super(jsonResponse);
		
		if (jsonResponse.has("result")) {
			JSONObject result = jsonResponse.getJSONObject("result");
			mDetails = new Place(result);
		}
	}

	public Place getPlace() {
		return mDetails;
	}
}
