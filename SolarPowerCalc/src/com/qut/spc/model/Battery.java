package com.qut.spc.model;

import com.qut.spc.db.Database;

public class Battery extends SolarComponent {
	
	public Battery() {
		
	}
	
	public static Battery load(long id) {
		return Database.loadComponent(id, Battery.class);
	}
}
