package com.qut.spc.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qut.spc.model.Panel;

@Path("/populate/")
public class DBPopulator {

	@GET
	@Produces("text/html")
	@Path("/{num}")
	public String populateDB(@PathParam("num") int num){
		generatePanels(num);
		return "Database Populated with "+num+ " panels";
	}
	
	@GET
	@Produces("text/html")
	@Path("/")
	public String populateDB(){
		generatePanels(100);
		return "Database Populated with 100 panels";
		
	}
	
	
	private void generatePanels(int numPanels){
		for(int i=0;i<numPanels;i++){
			try {
				Panel p=new Panel();
				p.setManufacture("M "+((int)Math.random()*10));
				p.setPrice(Math.random()*1337);
				p.setCapacity(Math.random()*1800);
				p.setDescription("Panel number: "+i);
				p.setModel("ABCD"+i%6);
				p.setName("PANEL "+i);
				p.setWarranty(24);
				p.save();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
