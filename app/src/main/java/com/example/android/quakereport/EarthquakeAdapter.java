package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, List<Earthquake> earthquakes) {
        super(context, 0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item,parent,false
            );
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = listItemView.findViewById(R.id.magnitude);
        TextView locationView = listItemView.findViewById(R.id.location);
        TextView dateView = listItemView.findViewById(R.id.date);
        TextView timeView = listItemView.findViewById(R.id.time);

        magnitudeView.setText(currentEarthquake.getMagnitude());

        locationView.setText(currentEarthquake.getLocation());

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy",Locale.US);
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a",Locale.US);
        return timeFormat.format(dateObject);
    }
}
