package com.zqlc.www.util.location;

/**
 * Created by LGQ
 * Time: 2018/9/11
 * Function:
 */

public class LocationModel {

    String city;
    String cityChild;
    String error;
    double longitude;
    double latitude;

    public LocationModel(String city, String cityChild, double longitude, double latitude) {
        this.city = city;
        this.cityChild = cityChild;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocationModel(String error) {
        this.error = error;
    }

    public String getCity() {
        return city;
    }


    public String getCityChild() {
        return cityChild;
    }

    public String getError() {
        return error;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
