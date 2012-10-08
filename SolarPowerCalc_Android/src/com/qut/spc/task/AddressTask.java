package com.qut.spc.task;

import android.widget.EditText;
import android.widget.TextView;

public class AddressTask extends LocationTask {
	private TextView tvAddress;
	private EditText etPostcode;
	
	public AddressTask(TextView tvAddress, EditText etPostcode) {
		super();
		this.tvAddress = tvAddress;
		this.etPostcode = etPostcode;
	}
	
	public void execute(double latitude, double longitude) {
		String url = LocationTask.buildUrl(latitude, longitude);
		execute(url);
	}
	
	@Override
	protected void onPreExecute() {
		tvAddress.setText("Finding location...");
	}
	
	@Override
	protected void onPostExecute(boolean postcodeFound) {
		if (postcodeFound) {
			etPostcode.setText(postcode);
			tvAddress.setText("Current address: " + address);
		} else {
			tvAddress.setText("Unable to find your location");
		}
	}
	
}
