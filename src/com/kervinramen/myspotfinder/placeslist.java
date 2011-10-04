package com.kervinramen.myspotfinder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ParseException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kervinramen.myspotfinder.model.Spot;
import com.kervinramen.myspotfinder.model.Spots;

public class placeslist extends ListActivity implements LocationListener {

    public static final String KEY_USERNAME = "username";

    private LayoutInflater mInflater;
    private Vector<RowData> data;
    private RowData rd;
    private Drawable[] imgid;
    private String userName;
    private Spots spots;
    private LocationManager locationManager;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mInflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (userName == null) {
            // Bundle extras = getIntent().getExtras();
            userName = getIntent().getStringExtra("username");
        }

        // Define a listener that responds to location updates
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String provider = null;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else {
            provider = LocationManager.NETWORK_PROVIDER;
        }

        if (provider != null) {
            locationManager.requestLocationUpdates(provider, 1000, 100, this);
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Spot spot = spots.getSpots().get(position);
        // on click, open Google Maps Activity
        Intent i = new Intent(this, placesmap.class);
        Bundle b = new Bundle();
        b.putParcelable("com.kervinramen.myspotfinder.model.spot", spot);
        i.putExtras(b);
        startActivity(i);
    }

    private void listPlaces(String username, Location location) {
        data = new Vector<RowData>();

        // Get places of interest
        spots = Spots.getSpotsFromWS(username, location.getLatitude(), location.getLongitude());

        if (spots != null) {

            imgid = new Drawable[spots.getSpots().size()];

            for (int i = 0; i < spots.getSpots().size(); i++) {

                try {
                    rd = new RowData(i, spots.getSpots().get(i).getName(), spots.getSpots().get(i).getDescription());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                data.add(rd);

                String imageUrl = "http://myspotfinder.appspot.com/media/" + spots.getSpots().get(i).getImage();
                Drawable drawable = getImageFromURL(this, imageUrl, spots.getSpots().get(i).getImage());
                imgid[i] = drawable;

            }

            CustomAdapter adapter = new CustomAdapter(this, R.layout.list, R.id.title, data);
            setListAdapter(adapter);
        }

    }

    private Drawable getImageFromURL(Context ctx, String url, String saveFilename) {
        try {
            InputStream is = (InputStream) this.fetch(url);
            Drawable d = Drawable.createFromStream(is, "src");
            return d;
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public Object fetch(String address) throws MalformedURLException, IOException {
        URL url = new URL(address);
        Object content = url.getContent();
        return content;
    }

    private class RowData {
        protected int mId;
        protected String mTitle;
        protected String mDetail;

        RowData(int id, String title, String detail) {
            mId = id;
            mTitle = title;
            mDetail = detail;
        }

        @Override
        public String toString() {
            return mId + " " + mTitle + " " + mDetail;
        }
    }

    private class CustomAdapter extends ArrayAdapter<RowData> {

        public CustomAdapter(Context context, int resource, int textViewResourceId, List<RowData> objects) {
            super(context, resource, textViewResourceId, objects);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            TextView title = null;
            TextView detail = null;
            ImageView i11 = null;
            RowData rowData = getItem(position);
            if (null == convertView) {
                convertView = mInflater.inflate(R.layout.list, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();
            title = holder.gettitle();
            title.setText(rowData.mTitle);
            detail = holder.getdetail();
            detail.setText(rowData.mDetail);
            i11 = holder.getImage();
            i11.setImageDrawable(imgid[rowData.mId]);
            return convertView;
        }

        private class ViewHolder {
            private View mRow;
            private TextView title = null;
            private TextView detail = null;
            private ImageView i11 = null;

            public ViewHolder(View row) {
                mRow = row;
            }

            public TextView gettitle() {
                if (null == title) {
                    title = (TextView) mRow.findViewById(R.id.title);
                }
                return title;
            }

            public TextView getdetail() {
                if (null == detail) {
                    detail = (TextView) mRow.findViewById(R.id.detail);
                }
                return detail;
            }

            public ImageView getImage() {
                if (null == i11) {
                    i11 = (ImageView) mRow.findViewById(R.id.img);
                }
                return i11;
            }
        }
    }

    public void onLocationChanged(Location location) {
        locationManager.removeUpdates(this);
        
        listPlaces(userName, location);
    }

    public void onProviderDisabled(String provider) {

    }

    public void onProviderEnabled(String provider) {

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

}
