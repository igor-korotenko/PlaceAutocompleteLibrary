package com.st1x.placeautocomplete.lib.googleplaces.query;

/**
 * Package: nl.d_tt.glimsys.api.googleplaces.query
 * Author: Igor Korotenko
 * E-mail: i.am.igor.korotenko@gmail.com
 * Date: 9/11/13 19 : 45
 */

public class AutoCompleteSearchQuery extends SearchQuery {

    public void setInput(String input) {
        mQueryBuilder.addParameter("input", input);
    }

    @Override
    public String getUrl() {
        return "https://maps.googleapis.com/maps/api/place/autocomplete/json";
    }
}
