package com.qut.spc.controller;

import java.util.ArrayList;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.qut.spc.api.BatteryFilterAPI;
import com.qut.spc.exceptions.InvalidArgumentException;
import com.qut.spc.model.Battery;
import com.qut.spc.model.BatteryContainer;
import com.qut.spc.model.Inverter;
import com.qut.spc.model.InverterContainer;
/**
 * Controller that handles requests for batteries
 * @author simen
 *
 */
@Path("/battery/")
public class BatteryController {
	private BatteryFilterAPI db;
	
	public BatteryController() {
		db = new BatteryContainer();
	}
	
	/**
	 * Returns available  batteries based on the given postcode, price range and capacity range.
	 * When indirectly accessed through a GET request to /battery, an XML representation of the batteries will be returned.
	 * When access is done via a GET request, price, capacity and postcode will default to 0 if not specified
	 * @param minPrice
	 * @param maxPrice
	 * @param minCapacity
	 * @param maxCapacity
	 * @param postcode
	 * @return
	 * @throws InvalidArgumentException
	 */
	@GET
	@Produces("application/xml")
	@Path("/")
	public BatteryFilterAPI getBatteriesByPriceCapacityLocation(
			@QueryParam("priceMin") @DefaultValue("0") double minPrice,
			@QueryParam("priceMax") @DefaultValue("0") double maxPrice,
			@QueryParam("capacityMin") @DefaultValue("0") double minCapacity,
			@QueryParam("capacityMax") @DefaultValue("0") double maxCapacity,
			@QueryParam("postcode") @DefaultValue("") String postcode)
					throws InvalidArgumentException{
		try {
			db.setMaxCapacity(maxCapacity);
			db.setMaxPrice(maxPrice);
			db.setMinPrice(minPrice);
			db.setMinCapacity(minCapacity);
			db.setPostcode(postcode);
			db.search();
		} catch (Exception e) {
			throw new InvalidArgumentException(e.getMessage());
		}
		
		
		
		return db;
	}
}
