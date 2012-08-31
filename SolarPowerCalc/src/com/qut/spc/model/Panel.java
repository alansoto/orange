/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;

import com.qut.spc.db.Database;

@Entity
public class Panel extends SolarComponent {

	@XmlElement
	private double operatingCurrent = 0.0;
	
	public Panel() {
	}
	
	/**
	 * Get max power (Operating current) in A
	 */
	public double getOperatingCurrent() {
		return operatingCurrent;
	}

	/**
	 * @param outputEnergy The output energy to set
	 */
	public void setOperatingCurrent(double operatingCurrent) throws Exception {
		if (operatingCurrent < 0.0) {
			throw new Exception("Operating current must not be negative");
		}
		this.operatingCurrent = operatingCurrent;
	}
	
	public static Panel load(long id) {
		return Database.loadComponent(id, Panel.class);
	}
}
