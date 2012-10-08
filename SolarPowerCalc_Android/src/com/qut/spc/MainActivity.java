package com.qut.spc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
	private Intent calculatorIntent, searchIntent;

	public static final int REQUEST_CODE_MAP = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabHost host = getTabHost();

		// Tab for Calculator
		TabSpec calculatorSpec = host.newTabSpec("Calculator");
		calculatorSpec.setIndicator("Calculator",
				getResources().getDrawable(R.drawable.calculator));
		calculatorIntent = new Intent(this, ElectricityProductionActivity.class);
		calculatorSpec.setContent(calculatorIntent);

		// Tab for Search
		TabSpec searchSpec = host.newTabSpec("Search");
		searchSpec.setIndicator("Search",
				getResources().getDrawable(R.drawable.search));
		searchIntent = new Intent(this, SearchActivity.class);
		searchSpec.setContent(searchIntent);
				
		// Adding all tabs
		host.addTab(calculatorSpec);
		host.addTab(searchSpec);
	}
	
	public static void showError(Context ctx, String msg) {
		AlertDialog alert = new AlertDialog.Builder(ctx).create();
		alert.setTitle("Error");
		alert.setMessage(msg);
		alert.setButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// do nothing
			}
		});
		alert.show();
	}
	
	public static void getLocationFromMap(Activity ctx) {
		Intent i = new Intent(ctx, LocationActivity.class);
		ctx.startActivityForResult(i, REQUEST_CODE_MAP);
	}
}
