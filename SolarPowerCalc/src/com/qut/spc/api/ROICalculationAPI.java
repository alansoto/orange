package com.qut.spc.api;

public interface ROICalculationAPI {
	
//	/**
//	 * Set system output energy. E.g. 5kW
//	 * @param size Output energy in kW
//	 * @throws IllegalArgumentException if size is less than zero
//	 */
//	void setSystemSize(double size) throws IllegalArgumentException;
	/**
	 * Sets the electricity generation for the system for the specified number of days. E.g. 5kW
	 * @param electricity production in kW
	 * @param numberOfDays number of days to calculate for
	 * @throws IllegalArgumentException if size is less than zero
	 */
	void setElectricityProduction(double electricity,int numberOfDays) throws IllegalArgumentException;
	
	/**
	 * Set feed-in tariff. E.g. $0.44
	 * @param tariff Tariff in dollars
	 * @throws IllegalArgumentException if size is less than zero
	 */
	void setFeedInTariff(double tariff) throws IllegalArgumentException;
	
	/**
	 * Set cost of electricity per kW. E.g. $0.23/kW
	 * @param cost Cost in dollars
	 * @throws IllegalArgumentException if cost is less than zero
	 */
	void setCostOfElectricity(double cost) throws IllegalArgumentException;
	
	/**
	 * Set average daily household energy usage. E.g. 1750kW 
	 * @param usage Average usage in kW
	 * @throws IllegalArgumentException if usage is less than zero
	 */
	void setDailyUsage(double usage) throws IllegalArgumentException;
	
//	/**
//	 * Set daily sun hours
//	 * @param hours Average sun hours per day
//	 * @throws IllegalArgumentException if hours is less than zero
//	 */
//	void setDailySunHours(double hours) throws IllegalArgumentException;
	
	/**
	 * Set cost of the system
	 * @param cost System cost
	 * @throws IllegalArgumentException if cost is less than zero
	 */
	void setSystemCost(double cost) ;
	
	/**
	 * get the annual ROI
	 * @throws IllegalArgumentException if any of parameter haven't been set
	 */
	double getROI() throws IllegalArgumentException;
}
