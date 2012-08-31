/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import com.google.appengine.api.datastore.Key;
import com.qut.spc.EMF;
import com.qut.spc.db.Database;

/**
 * Common interface for each component in solar system.
 * 
 * @author QuocViet
 */
@Entity
@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class SolarComponent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement (name="id")
	private Key key;

	@XmlElement
	private String name = "";
	
	@XmlElement
	private String model = "";
	
	// TODO: Create class Manufacture
	@XmlElement
	private String manufacturer = "";
	
	@XmlElement
	private double price = 0.0;
	
	@XmlElement
	private double capacity = 0.0;
	
	@XmlElement
	private double voltage = 0.0;
	
	@XmlElement
	private String dimensions = "";
	
	@XmlElement
	private String description = "";
	
	@XmlElement
	private int warranty = 0;
	
	@XmlElement
	private double efficiencyDecrease = 0.0;
	
	
	public SolarComponent() {
	}

	/**
	 * @return Id of this component in database
	 */
	public long getId() {
		if (key != null) {
			return key.getId();
		}
		return -1;
	}
	/**
	 * @return The name of this component
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return The model of this component
	 */
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * @return The name of manufacture
	 */
	public String getManufacture() {
		return this.manufacturer;
	}
	
	/**
	 * @param efficiencyDecrease The efficiency to set
	 */
	public void setManufacture(String manufacture) {
		this.manufacturer = manufacture;
	}
	
	/**
	 * @return The price of this component
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price The price to set
	 * @throws Exception If price is negative
	 */
	public void setPrice(double price) throws Exception {
		if (price < 0.0) {
			throw new Exception("Price must not be negative");
		}
		this.price = price;
	}
	
	/**
	 * Get capacity (W or Ah)
	 * @return
	 */
	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) throws Exception {
		if (capacity < 0.0) {
			throw new Exception("Capacity must not be negative");
		}
		this.capacity = capacity;
	}

	/**
	 * Get voltage (V)
	 * @return
	 */
	public double getVoltage() {
		return voltage;
	}

	public void setVoltage(double voltage) throws Exception {
		if (voltage < 0.0) {
			throw new Exception("Voltage must not be negative");
		}
		this.voltage = voltage;
	}
	
	/**
	 * @return The efficiency decrease linearly by each year
	 */
	public double getEfficiencyDecrease() {
		return efficiencyDecrease;
	}

	/**
	 * @param efficiencyDecrease The efficiency to set
	 * @throws Exception If efficiency less than 0 or greater than 100
	 */
	public void setEfficiencyDecrease(double efficiencyDecrease) throws Exception {
		if (efficiencyDecrease < 0.0 || efficiencyDecrease > 100.0) {
			throw new Exception("Efficiency must be from 0 to 100");
		}
		this.efficiencyDecrease = efficiencyDecrease;
	}
	
	/**
	 * Get warranty time in months
	 */
	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) throws Exception {
		if (warranty < 0) {
			throw new Exception("Warranty must not be negative");
		}
		this.warranty = warranty;
	}

	/**
	 * Get dimensions in mm
	 * @return Format LxWxH
	 */
	public String getDimensions() {
		return dimensions;
	}

	/**
	 * Set dimensions in mm, format LxWxH
	 */
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get the list of efficiency by years
	 * @param years Number of years to retrieve
	 * @return list of efficiency
	 */
	public double[] getEfficiencyByYear(int years) throws Exception {
		if (years < 0) {
			throw new Exception("Years must not be negative");
		}
		double listEff[] = new double[years];
		double eff = 100.0;
		
		for (int i = 0; i < years; ++i) {
			listEff[i] = eff;
			eff -= efficiencyDecrease;
		}
		return listEff;
	}
	
	/**
	 * Store this component to database
	 */
	public void save() {
		Database.saveComponent(this);
	}
	
	
	
	


}
