package com.qut.spc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.qut.spc.model.Battery;
import com.qut.spc.model.Inverter;
import com.qut.spc.model.Panel;
import com.qut.spc.model.SolarComponent;
import com.qut.spc.task.ListRequestTask;

public class SearchResultActivity extends Activity {

	ProgressDialog progressDlg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);

		String url = getIntent().getStringExtra("url");
		String component = getIntent().getStringExtra("component");
		if (component.equals("panel")) {
			new ComponentListTask<Panel>("panel", Panel.class).execute(url);
		} else if (component.equals("inverter")) {
			new ComponentListTask<Inverter>("inverter", Inverter.class)
					.execute(url);
		} else if (component.equals("battery")) {
			new ComponentListTask<Battery>("battery", Battery.class)
					.execute(url);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_search_result, menu);
		return true;
	}

	class ComponentListTask<T extends SolarComponent> extends
			ListRequestTask<T> {

		public ComponentListTask(String componentName, Class<T> componentClass) {
			super(componentName, componentClass);
		}

		@Override
		protected void onPreExecute() {
			progressDlg = ProgressDialog.show(SearchResultActivity.this,
					"In progress", "Loading");
		}

		@Override
		protected void onPostExecute(List<T> list) {
			if (progressDlg != null) {
				progressDlg.dismiss();
				progressDlg = null;
			}
			if (list == null) {
				return;
			}

			List<HashMap<String, String>> groupData = new ArrayList<HashMap<String, String>>();
			List<LinkedList<HashMap<String, String>>> childData = new ArrayList<LinkedList<HashMap<String, String>>>();

			for (T component : list) {
				// Group item
				HashMap<String, String> item = new HashMap<String, String>();

				item.put("name", component.getManufacturer() + " - "
						+ component.getModel());
				groupData.add(item);

				// Child item
				LinkedList<HashMap<String, String>> childRow = new LinkedList<HashMap<String, String>>();

				item = new HashMap<String, String>();
				component.bindMap(item);
				childRow.add(item);
				childData.add(childRow);
			}

			String[] groupFrom = new String[] { "name" };
			int[] groupTo = new int[] { R.id.component_name };

			String[] childFrom = new String[] { "price", "capacity", "voltage", "dimensions",
					"efficiency", "warranty", "postcode" };
			int[] childTo = new int[] { R.id.price, R.id.capacity, R.id.voltage, R.id.dimensions,
					R.id.efficiency, R.id.warranty, R.id.postcode };

			ExpandableListAdapter adapter = new SimpleExpandableListAdapter(
					SearchResultActivity.this, groupData,
					R.layout.component_list, groupFrom, groupTo, childData,
					R.layout.component_detail, childFrom, childTo);

			ExpandableListView view = (ExpandableListView) SearchResultActivity.this
					.findViewById(R.id.result_view);
			view.setEmptyView(findViewById(R.id.result_empty));
			view.setAdapter(adapter);
		}
	}
}
