package com.qut.spc.service;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class CompassService implements SensorEventListener {
	private float[] accValues = new float[3];
	private float[] magValues = new float[3];
	
	private float[] orientation = new float[3];
	
	private SensorManager manager;
	
	public CompassService(Context ctx) {
		// SensorManager instance
		manager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);

		registerListener();
	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// Do nothing
	}

	public void onSensorChanged(SensorEvent e) {
		// Gets the value of the sensor that has been changed
		switch (e.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			accValues = e.values.clone();
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			magValues = e.values.clone();
			break;
		}
		calculateOrientation();
	}
	
	public float[] getOrientation() {
		return orientation;
	}
	
	public void registerListener() {
		Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		Sensor magnetfield = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

		// Register the listener
		manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
		manager.registerListener(this, magnetfield, SensorManager.SENSOR_DELAY_UI);
	}
	
	public void unregisterListener() {
		manager.unregisterListener(this);
	}
	
	private void calculateOrientation() {
		float[] R = new float[9];
		float[] outR = new float[9];

		SensorManager.getRotationMatrix(R, null, accValues, magValues);
		SensorManager.remapCoordinateSystem(R, SensorManager.AXIS_X,
				SensorManager.AXIS_Z, outR);

		SensorManager.getOrientation(outR, orientation);

		// Convert from Radians to Degrees.
		orientation[0] = (float) Math.toDegrees(orientation[0]);
		orientation[1] = (float) Math.toDegrees(orientation[1]);
		orientation[2] = (float) Math.toDegrees(orientation[2]);
	}
}
