/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.qut.spc.model.db.Database;


/**
 * Wrapper class for list of Inverters
 * @author QuocViet
 */
@XmlRootElement(name="inverters")
public class InverterContainer implements InverterDB {
	
	@XmlElement(name="inverter")
	private List<Inverter> list;
	
	public InverterContainer() {
		list = new ArrayList<Inverter>();
	}
	
	public InverterContainer(List<Inverter> list) {
		this.list = list;
	}
	
	public List<Inverter> getList() {
		return list;
	}
	
	public void setList(List<Inverter> list) {
		this.list = list;
	}
	
	@Override
	public List<Inverter> getInvertersInPriceRange(double min, double max)
			throws Exception {
		list = Database.getComponentsInPrice(Inverter.class, min, max);
		return list;
	}
	
	@Override
	public List<Inverter> getInvertersInLocation(String location)
			throws Exception {
		// TODO
		list = new ArrayList<Inverter>();
		return list;
	}

	@Override
	public List<Inverter> getInvertersInCapacity(double min, double max)
			throws Exception {
		list = Database.getComponentsInCapacity(Inverter.class, min, max);
		return list;
	}
}
