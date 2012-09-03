package com.qut.spc.controller;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.qut.spc.api.BatteryFilterAPI;
import com.qut.spc.exceptions.InvalidArgumentException;
import com.qut.spc.model.BatteryContainer;

@Path("/battery/")
public class BatteryController {
	private BatteryFilterAPI db;
	
	public BatteryController() {
		db = new BatteryContainer();
	}
	
	@GET
	@Produces("application/xml")
	@Path("/")
	public BatteryFilterAPI getBatteriesByPriceCapacityLocation(
			@QueryParam("priceMin") @DefaultValue("0") double minPrice,
			@QueryParam("priceMax") @DefaultValue("0") double maxPrice,
			@QueryParam("capacityMin") @DefaultValue("0") double minCapacity,
			@QueryParam("capacityMax") @DefaultValue("0") double maxCapacity,
			@QueryParam("postcode") @DefaultValue("0000") String postcode)
					throws InvalidArgumentException{
		try {
			db.setMaxCapacity(maxCapacity);
			db.setMaxPrice(maxPrice);
			db.setMinPrice(minPrice);
			db.setMinCapacity(minCapacity);
			db.setPostcode(postcode);
			db.search();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;
	}
}
