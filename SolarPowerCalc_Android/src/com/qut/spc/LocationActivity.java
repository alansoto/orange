package com.qut.spc;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.qut.spc.service.LocationService;

public class LocationActivity extends MapActivity {
	private LocationService locationService;
	private MapView mapView;
	private GeoPoint selectedPoint;
	private static final String API_KEY_DEBUG = "0TZUBCgVFqPlFD0_tuWFPEooI6FvTfTRE6L9Gng";
	private static final String API_KEY_RELEASE = "0TZUBCgVFqPmqRiCCpX7Pwa3iV7XGzNDW0Ol62w";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// fetch the map view from the layout
		if (BuildConfig.DEBUG) {
			mapView = new MapView(this, API_KEY_DEBUG);
		} else {
			mapView = new MapView(this, API_KEY_RELEASE);
		}
		// make available zoom controls
		mapView.setBuiltInZoomControls(true);
		mapView.setClickable(true);

		setContentView(mapView);
		
		locationService = new LocationService(this) {
			@Override
			public void onLocationChanged(Location location) {
				super.onLocationChanged(location);
				updateLocation();
			}
		};
		updateLocation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_location, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.save:
			sendResultAndClose();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		locationService.updateLocation();
	}

	@Override
	protected void onPause() {
		super.onPause();
		locationService.cancelUpdateLocation();
	}

	private void sendResultAndClose() {
		Intent data = new Intent();
		
		if (selectedPoint != null) {
			data.putExtra("latitude", (double)selectedPoint.getLatitudeE6() / 1E6);
			data.putExtra("longitude", (double)selectedPoint.getLongitudeE6() / 1E6);
		}
		setResult(Activity.RESULT_OK, data);
		finish();
	}
	
	private void updateLocation() {
		if (locationService.getLatitude() != 0
				&& locationService.getLongitude() != 0) {
			selectedPoint = new GeoPoint(
					(int) (locationService.getLatitude() * 1E6),
					(int) (locationService.getLongitude() * 1E6));
	
			// get the MapController object
			MapController controller = mapView.getController();
	
			// animate to the desired point
			controller.animateTo(selectedPoint);
	
			// set the map zoom
			// zoom 1 is top world view
			controller.setZoom(12);
		}
		// Add a location marker
		LocationOverlay overlay = new LocationOverlay();
		List<Overlay> listOfOverlays = mapView.getOverlays();
		listOfOverlays.add(overlay);
		
		// invalidate the map in order to show changes
		mapView.invalidate();
	}
	
	class LocationOverlay extends Overlay {
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {
			super.draw(canvas, mapView, shadow);
			if (selectedPoint != null) {
				// ---translate the GeoPoint to screen pixels---
				Point screenPts = new Point();
				mapView.getProjection().toPixels(selectedPoint, screenPts);
	
				// ---add the marker---
				Bitmap bmp = BitmapFactory.decodeResource(getResources(),
						R.drawable.pinsmall);
			
				canvas.drawBitmap(bmp,
						screenPts.x - bmp.getWidth() / 2,
						screenPts.y - bmp.getHeight(), null);
			}
			return true;
		}

		@Override
		public boolean onTap(GeoPoint p, MapView mapView) {
			selectedPoint = p;
			return false;
		}
		
//		@Override
//		public boolean onTouchEvent(MotionEvent event, MapView mapView) {
//			if (event.getAction() == MotionEvent.ACTION_UP) {
//				selectedPoint = mapView.getProjection().fromPixels(
//						(int) event.getX(), (int) event.getY());
//			}
//			return false;
//		}
	}

}
