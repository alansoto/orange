package com.qut.spc.task;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * http://maps.google.com/maps/geo?ll=-27.46197644877817,153.0120849609375&output=xml
 *
 */
public class LocationTask extends XmlRequestTask {
	protected String address = "";
	protected String postcode = "";
	
	public static final String MAP_URL = "http://maps.google.com/maps/geo?output=xml";
	
	public static String buildUrl(double latitude, double longitude) {
		return String.format(LocationTask.MAP_URL + "&ll=%f,%f",
				latitude, longitude);
	}
	
	@Override
	protected boolean onXmlTag(XmlPullParser parser, int eventType)
			throws IOException, XmlPullParserException {
		if (eventType != XmlPullParser.START_TAG) {
			return true;
		}
		String name = parser.getName();
		
		if (name == "Placemark") {
			if (address.length() == 0) {
				address = findText(parser, "address");
			}
			if (postcode.length() == 0) {
				postcode = findText(parser, "PostalCodeNumber");
			}
		}
		if (address.length() > 0 && postcode.length() > 0) {
			return false;
		}
		return true;
	}
	
	@Override
	protected void onPostExecute(XmlPullParser parser) {
		onPostExecute(postcode.length() > 0);
	}
	
	protected void onPostExecute(boolean postcodeFound) {
		// Sub class should override this
	}
}
