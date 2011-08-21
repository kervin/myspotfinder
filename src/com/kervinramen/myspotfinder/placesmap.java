package com.kervinramen.myspotfinder;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.kervinramen.myspotfinder.model.AndroidOverlayItems;
import com.kervinramen.myspotfinder.model.Spot;

public class placesmap extends MapActivity implements LocationListener {

	LinearLayout linearLayout;
	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	AndroidOverlayItems androidOverlay;
	Geocoder coder;
	LocationManager locationManager;

	MapController controller;

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.map);
		
		Bundle b = getIntent().getExtras();
		Spot spot = b.getParcelable("com.kervinramen.myspotfinder.model.spot");

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		controller = mapView.getController();

		mapOverlays = mapView.getOverlays();
		
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		androidOverlay = new AndroidOverlayItems(drawable);
		
		mapOverlays.clear();
		
		String[] locationvalues = spot.getLocation().split(",");
		
		int latitudeE6 = (int) (Double.parseDouble(locationvalues[0]) * 1E6);
		int longitudeE6 = (int) (Double.parseDouble(locationvalues[1]) * 1E6);

		GeoPoint point = new GeoPoint(latitudeE6,
				longitudeE6);
		OverlayItem overlayitem = new OverlayItem(point,
				spot.getName(),
				spot.getDescription());

		androidOverlay.addOverlay(overlayitem);
		
		mapOverlays.add(androidOverlay);
		
		controller.setZoom(15);
		controller.animateTo(point);
		mapView.invalidate();
		
		
		/*// Define a listener that responds to location updates
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		
		 String provider = null;
	        if (locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
	            provider = LocationManager.GPS_PROVIDER ;
	        } else {
	            Toast.makeText(this, "GPS not available", 3000).show();
	        }
	        
	        if(provider != null) {
	        	locationManager.requestLocationUpdates(provider, 1000, 100, this);
	        }*/
	        
	        
	        
	}
	
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onProviderDisabled(String provider) {
	}

	public void onLocationChanged(Location location) {

		/*showLocationOnMap(location);
		locationManager.removeUpdates(this);*/
	}
	
	private void showLocationOnMap(Location location)
	{
		
	}
	

}
