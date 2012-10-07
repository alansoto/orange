package com.qut.spc.controller;


import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.qut.spc.api.PanelFilterAPI;
import com.qut.spc.exceptions.InvalidArgumentException;
import com.qut.spc.model.PanelContainer;


/**
 * Public entrypoint for requests to /panel/
 * @author Simen
 *
 */
@Path("/panel/")
public class PanelController {
	
	private PanelFilterAPI db;
	
	public PanelController(){
		this.db = new PanelContainer();
	}
	
	public PanelController(PanelFilterAPI db){
		this.db=db;
	}

	
	/**
	 * Returns available panels based on the given postcode, price range and capacity range.
	 * When indirectly accessed through a GET request to /panel, an XML representation of the panels will be returned.
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
	public PanelFilterAPI getPanelsByPriceCapacityLocation(@QueryParam("priceMin") @DefaultValue("0")double minPrice,
			@QueryParam("priceMax") @DefaultValue("0") double maxPrice,
			@QueryParam("capacityMin") @DefaultValue("0") double minCapacity,
			@QueryParam("capacityMax") @DefaultValue("0") double maxCapacity,
			@QueryParam("postcode") @DefaultValue("") String postcode){
		
		try{
			db.setMaxCapacity(maxCapacity);
			db.setMaxPrice(maxPrice);
			db.setMinPrice(minPrice);
			db.setMinCapacity(minCapacity);
			db.setPostcode(postcode);
			db.search();
		}catch(Exception e){
			throw new InvalidArgumentException(e.getMessage());
		}
		return db;
	}
	
}
