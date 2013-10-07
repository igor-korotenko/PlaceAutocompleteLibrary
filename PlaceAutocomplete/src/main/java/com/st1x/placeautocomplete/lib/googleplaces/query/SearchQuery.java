package com.st1x.placeautocomplete.lib.googleplaces.query;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public abstract class SearchQuery extends Query {

	private StringBuilder mTypes = new StringBuilder();
	
	public void setLocation(double latitude, double longitude) {
		String location = Double.toString(latitude) + "," + Double.toString(longitude);
		mQueryBuilder.addParameter("location", location);
	}
	
	public void setLocation(Location location) {
		setLocation(location.getLatitude(), location.getLongitude());
	}

    public void setLocation(LatLng location) {
        setLocation(location.latitude, location.longitude);
    }
	
	public void setRadius(int radius) {
		mQueryBuilder.addParameter("radius", Integer.toString(radius));
	}
	
	public void setKey(String key) {
		mQueryBuilder.addParameter("key", key);
	}

	public void addType(String type) {
		mTypes.append(type);
		mTypes.append("|");
	}
	
	@Override
	public String toString() {
		return (getUrl() + mQueryBuilder.toString());
	}

    public void setTypes(String[] types) {
        for (String type : types) {
            addType(type);
        }
        mTypes.deleteCharAt(mTypes.length() - 1);
        mQueryBuilder.removeParameter("types");
        mQueryBuilder.addParameter("types", mTypes.toString());
    }
}
