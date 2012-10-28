package com.qut.spc.api;

import javax.persistence.EntityNotFoundException;

import com.qut.spc.model.Battery;
import com.qut.spc.model.Inverter;
import com.qut.spc.model.Panel;


public interface SystemCalculationAPI {
	
	/**
	 * Set panel for the system using id
	 */
	void setPanelId(long id) throws EntityNotFoundException;

	/**
	 * Set array of panels for the system
	 */
	void setPanelId(long[] ids);
	
	/**
	 * Set inverter for the system using id
	 */
	void setInverterId(long id)throws EntityNotFoundException;
	
	/**
	 * Set battery for the system using id
	 */
	void setBatteryId(long id)throws EntityNotFoundException;
	
	/**
	 * Set time span in months
	 */
	void setTimespan(double d); //months or day?
	
	/**
	 * Set user location
	 */
	void setLocation(String postcode) throws IllegalArgumentException;
	
	/**
	 * Get efficiency factor
	 */
	double getSystemEfficiency();
	
	/**
	 * Get cost of the whole system
	 */
	double getTotalCost();
	
	/**
	 * Get total output energy
	 */
	double getElectricityProduction();
	
	/**
	 * Get return of investment
	 */
	double getROI();

	void setBattery(Battery battery);

	void setPanel(Panel panel);

	void setInverter(Inverter inverter);

	void setpanelCount(int panelCount);

	void setSystemCost(double systemCost);

	String getLocation();

	Panel getPanel();

}
