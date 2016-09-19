package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by john on 8/30/16.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    // Used to split the location into a primary and offset
    private static final String LOCATION_SEPARATOR = " of ";

    // Original location to split
    private String originalLocation;

    // New locations (primary and offset)
    private String locationOffset;
    private String primaryLocation;


    /** This context is used to inflate the layout file, and the list is the data we want
     *  to populate into the lists.
     *
     *  @param context The current context. Used to inflate the layout file.
     *  @param earthquakes A list of Earthquake objects to display in a list.
     */
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /** Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     *  @param position    The position in the list of data that should be displayed in the
     *                     list item view.
     *
     *  @param convertView The recycled view to populate.
     *
     *  @param parent      The parent ViewGroup that is used for inflation.
     *
     *  @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,
                    parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        final Earthquake currentQuake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID magnitude_view
        TextView magnitudeTextView = (TextView)listItemView.findViewById(R.id.magnitude_view);

        // Get the magnitude from the current Earthquake object and set it
        double magnitude = currentQuake.getMagnitude();
        String stringMagnitude = formatMagnitude(magnitude);
        magnitudeTextView.setText(stringMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable)magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(magnitude);

        // Set the color on the magnitude circle
        magnitudeCircle.setAlpha(magnitudeColor);

        // Split the location String into a primary and offset location
        originalLocation = currentQuake.getLocation();
        if(originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }
        else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        // Set the offset location TextView
        TextView locationOffsetTextView = (TextView)listItemView.findViewById(R.id.location_offset_view);
        locationOffsetTextView.setText(locationOffset);

        // Set the primary location TextView
        TextView locationPrimaryTextView = (TextView)listItemView.findViewById(R.id.location_primary_view);
        locationPrimaryTextView.setText(primaryLocation);

        // Get the date and time from the time string
        Date dateObject = new Date(currentQuake.getTimeInMilliseconds());

        // Set the date and time text views
        TextView dateTextView = (TextView)listItemView.findViewById(R.id.date_view);
        String date = formatDate(dateObject);
        dateTextView.setText(date);

        TextView timeTextView = (TextView)listItemView.findViewById(R.id.time_view);
        String time = formatTime(dateObject);
        timeTextView.setText(time);

        return listItemView;
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        String dateToDisplay = dateFormat.format(dateObject);
        return dateToDisplay;
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String timeToDisplay = timeFormat.format(dateObject);
        return timeToDisplay;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
