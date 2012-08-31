package com.qut.spc.controller;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qut.spc.exceptions.InvalidArgumentException;
import com.qut.spc.model.PanelContainer;
import com.qut.spc.model.PanelDB;


/**
 * Public entrypoint for requests to /panel/
 * @author Simen
 *
 */
@Path("/panel/")
public class PanelController {
	
	private PanelDB db;
	
	public PanelController(){
		this.db=new PanelContainer();
	}
	
	public PanelController(PanelDB db){
		this.db=db;
	}

	/**
	 * Returns a XML representation of SolarPanels with a cost between min and max
	 * @param min Minimum cost of Solar Panel
	 * @param max Maximum cost of Solar Panel
	 * @return 
	 */
	@GET
	@Produces("application/xml")
	@Path("/price/{min}/{max}")
	public PanelDB getPanelsByPrice(@PathParam("min") double min, @PathParam("max") double max) throws InvalidArgumentException{
		try {
			db.getPanelsInPriceRange(min, max);
		} catch (Exception e) {
			throw new InvalidArgumentException(e.getMessage());
		}
		return db;
	}
	
	/**
	 * Returns a XML representation of SolarPanels with a capacity between min and max
	 * @param min Minimum cost of Solar Panel
	 * @param max Maximum cost of Solar Panel
	 * @return 
	 */
	@GET
	@Produces("application/xml")
	@Path("/capacity/{min}/{max}")
	public PanelDB getPanelsByCapacity(@PathParam("min") double min, @PathParam("max") double max) throws InvalidArgumentException{		
			try {
				db.getPanelsInCapacity(min, max);
			} catch (Exception e) {
				throw new InvalidArgumentException(e.getMessage());
			}
		return db;
	}
	
	/**
	 * Returns a XML representation of SolarPanels available at the given location
	 * @param min Minimum cost of Solar Panel
	 * @param max Maximum cost of Solar Panel
	 * @return 
	 */
	@GET
	@Produces("application/xml")
	@Path("/location/{postcode}")
	public PanelDB getPanelsByLocation(@PathParam("postcode")String postcode) throws InvalidArgumentException{		
			try {
				db.getPanelsInLocation(postcode);
			} catch (Exception e) {
				throw new InvalidArgumentException(e.getMessage());
			}
		return db;
	}
	


}
