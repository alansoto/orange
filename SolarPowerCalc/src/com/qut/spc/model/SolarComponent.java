/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.appengine.api.datastore.Key;
import com.qut.spc.EMF;

/**
 * Common interface for each component in solar system.
 * 
 * @author QuocViet
 */
@Entity
@MappedSuperclass
public class SolarComponent {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;

	// TODO: Create class Manufacture
	private String manufacture;
	
	private double price;
	
	private double efficiencyDecrease;
	
	public Key getKey() {
		return key;
	}
	
	public SolarComponent() {
		manufacture = "";
		price = 0.0;
		efficiencyDecrease = 0.0;
	}
	
	/**
	 * @return The name of manufacture
	 */
	public String getManufacture() {
		return this.manufacture;
	}
	
	/**
	 * @param efficiencyDecrease The efficiency to set
	 */
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
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
	 * @return The efficiency decrease linearly by each year
	 */
	public double getEfficiencyDecrease() throws Exception {
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
	
	public void save() {
		EntityManager em = EMF.get().createEntityManager();
		try {
			em.persist(this);
		} finally {
			em.close();
		}
	}
}
