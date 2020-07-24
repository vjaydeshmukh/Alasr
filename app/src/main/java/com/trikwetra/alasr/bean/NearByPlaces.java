package com.trikwetra.alasr.bean;

import com.google.android.gms.maps.model.LatLng;

import java.util.Comparator;

public class NearByPlaces implements Comparator<LatLng> {
    LatLng currentLoc;

    public NearByPlaces(LatLng current){
        currentLoc = current;
    }

    @Override
    public int compare(LatLng o1, LatLng o2) {
        double lat1 = o1.latitude;
        double lon1 = o1.longitude;
        double lat2 = o2.latitude;
        double lon2 = o2.longitude;

        double distanceToPlace1 = distance(currentLoc.latitude, currentLoc.longitude, lat1, lon1);
        double distanceToPlace2 = distance(currentLoc.latitude, currentLoc.longitude, lat2, lon2);
        return (int) (distanceToPlace1 - distanceToPlace2);
    }

    public double distance(double fromLat, double fromLon, double toLat, double toLon) {
        double radius = 6378137;   // approximate Earth radius, *in meters*
        double deltaLat = toLat - fromLat;
        double deltaLon = toLon - fromLon;
        double angle = 2 * Math.asin( Math.sqrt(
                Math.pow(Math.sin(deltaLat/2), 2) +
                        Math.cos(fromLat) * Math.cos(toLat) *
                                Math.pow(Math.sin(deltaLon/2), 2) ) );
        return radius * angle;
    }

}