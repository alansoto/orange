package com.qut.spc;

import java.util.Arrays;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.qut.spc.task.XmlRequestTask;

public class CalculationResultActivity extends Activity {
	private TextView tvElectricityProduction, tvReturnOnInvestment,
		tvRebates;
	
	private static final String[] DURATIONS = {
			"week",
			"month",
			"year",
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculation_result);

		tvElectricityProduction = (TextView) findViewById(R.id.electricity_production);
		tvReturnOnInvestment = (TextView) findViewById(R.id.return_on_investment);
		tvRebates = (TextView) findViewById(R.id.rebates);
		
		String url = getIntent().getStringExtra("url");
		new CalculationResultTask().execute(url);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_calculation_result, menu);
		return true;
	}
	
	class CalculationResultTask extends XmlRequestTask {
		private int depth;
		private String category;
		private String type;
		
		private double[] electricityProduction;
		private double[] returnOnInvestment;
		private double[] rebates;
		
		private ProgressDialog progressDlg;
		
		public CalculationResultTask() {
			electricityProduction = new double[DURATIONS.length];
			returnOnInvestment = new double[DURATIONS.length];
			rebates = new double[DURATIONS.length];
		}

		@Override
		protected boolean onXmlTag(XmlPullParser parser, int eventType) {
			String name;
			
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				depth = 0;
				break;
				
			case XmlPullParser.START_TAG:
				++depth;
				name = parser.getName();
				if (depth == 2) {
					category = name;
					type = null;
				} else if (depth == 3) {
					type = name;
				}
				break;
			case XmlPullParser.END_TAG:
				if (depth == 2) {
					category = null;
					type = null;
				} else if (depth == 3) {
					type = null;
				}
				--depth;
				break;
			case XmlPullParser.TEXT:
				if (depth == 3) {
					setValue(category, type, parser.getText());
				}
				break;
			}
			return true;
		}
		
		@Override
		protected void onPreExecute() {
			progressDlg = ProgressDialog.show(CalculationResultActivity.this,
					null, "Loading...");
		}
		
		@Override
		protected void onPostExecute(XmlPullParser parser) {
			if (progressDlg != null) {
				progressDlg.dismiss();
				progressDlg = null;
			}
			// Display in years only
			tvElectricityProduction.setText(String.valueOf(electricityProduction[2]));
			tvReturnOnInvestment.setText(String.valueOf(returnOnInvestment[2]));
			tvRebates.setText(String.valueOf(rebates[2]));
		}
		
		private void setValue(String category, String type, String value) {
			if (category == null || type == null) {
				return;
			}
			int durationIdx = Arrays.asList(DURATIONS).indexOf(type);
			
			if (durationIdx >= 0 && durationIdx < DURATIONS.length) {
				double calValue;
				
				try {
					calValue = Double.parseDouble(value);
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
					calValue = 0.0;
				}
				if (category.equals("electricityProduction")) {
					electricityProduction[durationIdx] = calValue;
				} else if (category.equals("returnOnInvestment")) {
					returnOnInvestment[durationIdx] = calValue;
				} else if (category.equals("governmentRebates")) {
					rebates[durationIdx] = calValue;
				}
			}
		}
	}
}
