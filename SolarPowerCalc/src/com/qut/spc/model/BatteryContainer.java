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

import com.qut.spc.api.BatteryFilterAPI;


/**
 * Wrapper class for list of Battery
 * @author QuocViet
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="batteries")
public class BatteryContainer extends ComponentContainer<Battery>
		implements BatteryFilterAPI {
	
	@XmlElement(name="battery")
	private List<Battery> list;
	
	public BatteryContainer() {
		list = new ArrayList<Battery>();
	}
	
	public List<Battery> getList() {
		return list;
	}

	@Override
	public void setList(List<Battery> list) {
		this.list=list;
	}

	@Override
	public Class<? extends SolarComponent> getComponentClass() {
		return Battery.class;
	}


}
