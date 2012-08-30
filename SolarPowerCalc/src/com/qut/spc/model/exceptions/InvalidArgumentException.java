package com.qut.spc.model.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.Responses;

public class InvalidArgumentException extends WebApplicationException{
	public InvalidArgumentException(){
		super(Responses.notAcceptable().build());
	}
	
	public InvalidArgumentException(String message){
		super(Response.status(500).entity(message).type("text/plain").build());
	}

}
