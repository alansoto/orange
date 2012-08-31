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

import com.qut.spc.db.Database;


/**
 * Wrapper class for list of Battery
 * @author QuocViet
 */
@XmlRootElement(name="batteries")
public class BatteryContainer implements BatteryDB {
	
	@XmlElement(name="battery")
	private List<Battery> list;
	
	public BatteryContainer() {
		list = new ArrayList<Battery>();
	}
	
	public BatteryContainer(List<Battery> list) {
		this.list = list;
	}
	
	public List<Battery> getList() {
		return list;
	}
	
	public void setList(List<Battery> list) {
		this.list = list;
	}
	
	@Override
	public List<Battery> getBatteriesInPriceRange(double min, double max)
			throws Exception {
		list = Database.getComponentsInPrice(Battery.class, min, max);
		return list;
	}
	
	@Override
	public List<Battery> getBatteriesInLocation(String location)
			throws Exception {
		// TODO
		list = new ArrayList<Battery>();
		return list;
	}

	@Override
	public List<Battery> getBatteriesInCapacity(double min, double max)
			throws Exception {
		
		list = Database.getComponentsInCapacity(Battery.class, min, max);
		return list;
	}
}
