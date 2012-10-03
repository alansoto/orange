package com.qut.spc.service;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationService implements LocationListener {

	private double latitude;
	private double longtude;
	
	private LocationManager manager;

	/**
	 * 
	 * @param currentActivity
	 *            the activity that you use the class from (general activity)
	 */
	public LocationService(Activity currentActivity) {

		manager = (LocationManager) currentActivity
				.getSystemService(Context.LOCATION_SERVICE);
		Criteria crit = new Criteria();
		// we don't need high accuracy.. better save the battery
		crit.setAccuracy(Criteria.ACCURACY_COARSE);
		String provider = manager.getBestProvider(crit, true);

		// this will not connect to GPS.. just Last Known Location
		setLocation(manager.getLastKnownLocation(provider));
	}

	public boolean updateLocation() {
		boolean enabled;
		
		try {
			enabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
			enabled = false;
		}
		if (enabled) {
			manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				this);
			return true;
		}
		try {
			enabled = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
			enabled = false;
		}
		if (enabled) {
			manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
				this);
			return true;
		}
		return false;
	}
	
	public void cancelUpdateLocation() {
		manager.removeUpdates(this);
	}
	
	public void onLocationChanged(Location location) {
		// Called when a new location is found after the location
		// manager request
		// the provider update
		manager.removeUpdates(this);
		setLocation(location);
	}

	public void onStatusChanged(String provider, int status,
			Bundle extras) {
		// do nothing
	}

	public void onProviderEnabled(String provider) {
		// do nothing
	}

	public void onProviderDisabled(String provider) {
		// do nothing
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongtude() {
		return longtude;
	}
	
	protected void setLocation(Location loc) {
		if (loc != null) {
			latitude = loc.getLatitude();
			longtude = loc.getLongitude();
		}
	}

}
