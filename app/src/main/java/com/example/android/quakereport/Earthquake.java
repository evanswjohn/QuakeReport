package com.example.android.quakereport;

/**
 * Created by john on 8/30/16.
 * {@link Earthquake} a custom array list for earthquake data.
 * It contains the location of the earthquake, the magnitude and the date
 */
public class Earthquake {

    /** The magnitude of the earthquake */
    private double mMagnitude;

    /** The location of the earthquake */
    private String mLocation;

    /** The date of the earthquake */
    private long mTimeInMilliseconds;

    /** The url for details of the earthquake */
    private String mUrl;

    /**
     * Contsructs a new {@link Earthquake} object.
     * @param magnitude             is the magnatude (size) of the earthquake
     * @param location              location is where the earthquake happened
     * @param timeInMilliseconds    the time from the Epoch when the earthquake happened
     * @param url                   is the website URL to fine more details about the earthquake
     */
    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mLocation = location;
        mMagnitude = magnitude;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
