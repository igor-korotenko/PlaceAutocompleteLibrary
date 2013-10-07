package com.st1x.placeautocomplete.lib.googleplaces.query;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

class QueryBuilder {
	
	private List<NameValuePair> mParameters = new ArrayList<NameValuePair>();
	
	public void addParameter(String name, String value) {
		removeParameter(name);
		mParameters.add(new BasicNameValuePair(name,value));
	}
	
	public void removeParameter(String name) {
        NameValuePair pairToRemove = null;
        for (NameValuePair pair : mParameters) {
            if (pair.getName().equals(name)) {
                pairToRemove = pair;
                break;
            }
        }
        if (pairToRemove != null && mParameters.contains(pairToRemove)) {
            mParameters.remove(pairToRemove);
        }
	}
	
	public void clearParameters() {
		mParameters.clear();
	}
	
	public String toString() {
		StringBuilder query = new StringBuilder();
		NameValuePair parameter = null;
		query.append("?");

        for (NameValuePair mParameter : mParameters) {
            parameter = mParameter;
            query.append(parameter.getName());
            query.append("=");
            query.append(parameter.getValue());
            query.append("&");
        }
		
		return encode(query.toString());
	}
	
	private String encode(String query) {
		String encodedQuery = query.replace("|", "%7C");
		encodedQuery = encodedQuery.replace(' ', '+');
		
		return encodedQuery;
	}
}
