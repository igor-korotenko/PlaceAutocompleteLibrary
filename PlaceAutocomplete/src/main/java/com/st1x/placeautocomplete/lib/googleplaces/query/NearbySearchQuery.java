package com.st1x.placeautocomplete.lib.googleplaces.query;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class NearbySearchQuery extends SearchQuery {

	public enum Ranking { Prominence, Distance };
	
	public NearbySearchQuery(Location location) {
		this(location.getLatitude(), location.getLongitude());
	}

    public NearbySearchQuery(LatLng location) {
        this(location.latitude, location.longitude);
    }

	public NearbySearchQuery(double lat, double lon) {
		setLocation(lat, lon);
		setRadius(2500); // Default
	}
	
	public void setRanking(Ranking ranking)	{
		mQueryBuilder.addParameter("rankby", ranking.toString());
	}
	
	public void setKeyword(String keyword) {
		mQueryBuilder.addParameter("keyword", keyword);
	}
	
	public void setName(String name) {
		mQueryBuilder.addParameter("name", name);	
	}
	
	public void setPageToken(String pageToken) {
		mQueryBuilder.addParameter("pagetoken", pageToken);
	}

	@Override
	public String getUrl() {
		return "https://maps.googleapis.com/maps/api/place/search/json";
	}
}
