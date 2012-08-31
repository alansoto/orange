/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.qut.spc.db.Database;


/**
 * Wrapper class for list of SolarPanel
 * @author QuocViet
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="panels")
public class PanelContainer implements PanelDB {

	@XmlElement(name="panel")
	private List<Panel> list;
	
	public PanelContainer() {
		list = new ArrayList<Panel>();
	}
	
	public PanelContainer(List<Panel> list) {
		this.list = list;
	}
	
	public List<Panel> getList() {
		return list;
	}
	
	public void setList(List<Panel> list) {
		this.list = list;
	}
	
	@Override
	public List<Panel> getPanelsInPriceRange(double min, double max)
			throws Exception {
		list = Database.getComponentsInPrice(Panel.class, min, max);
		return list;
	}
	
	@Override
	public List<Panel> getPanelsInLocation(String location)
			throws Exception {
		// TODO
		list = new ArrayList<Panel>();
		return list;
	}

	@Override
	public List<Panel> getPanelsInCapacity(double min, double max)
			throws Exception {
		list = Database.getComponentsInCapacity(Panel.class, min, max);
		return list;
	}
}
