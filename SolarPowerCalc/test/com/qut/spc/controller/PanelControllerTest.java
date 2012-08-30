package com.qut.spc.controller;


import org.junit.Before;
import org.junit.Test;

import com.qut.spc.model.PanelDB;
import com.qut.spc.model.exceptions.InvalidArgumentException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * Tests for PanelController
 * @author Simen
 *
 */
public class PanelControllerTest {
	
	private PanelDB db;
	private PanelController controller;

	@Before
	public void setup(){
		db = mock(PanelDB.class);
		controller = new PanelController(db);		
	}

	@Test
	public void getPanelsByPrice_validInput_correctDBIsReturned(){
		PanelDB res=controller.getPanelsByPrice(0, 100);
		
		assertEquals(db, res);
	}
	
	@Test
	public void getPanelsByPrice_validInput_dbIsCalledAppropriately(){
		controller.getPanelsByPrice(1, 100);
		
		try {
			verify(db).getPanelsInPriceRange(1, 100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected=InvalidArgumentException.class)
	public void getPanelsByPrice_exception_InvalidArgumentExceptionIsThrown(){
		try {
			when(db.getPanelsInPriceRange(1, 100)).thenThrow(Exception.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller.getPanelsByPrice(1, 100);
	}
	
	@Test
	public void getPanelsByCapacity_validInput_correctDBIsReturned(){
		PanelDB res=controller.getPanelsByCapacity(0, 100);
		
		assertEquals(db, res);
	}
	
	@Test
	public void getPanelsByCapacity_validInput_dbIsCalledAppropriately(){
		controller.getPanelsByCapacity(1, 100);
		
		try {
			verify(db).getPanelsInCapacity(1, 100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected=InvalidArgumentException.class)
	public void getPanelsByCapacity_exception_InvalidArgumentExceptionIsThrown(){
		try {
			when(db.getPanelsInCapacity(1, 100)).thenThrow(Exception.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller.getPanelsByCapacity(1, 100);
	}
	
	@Test
	public void getPanelsByLocation_validInput_correctDBIsReturned(){
		PanelDB res=controller.getPanelsByLocation("4169");
		
		assertEquals(db, res);
	}
	
	@Test
	public void getPanelsByLocation_validInput_dbIsCalledAppropriately(){
		controller.getPanelsByLocation("4444");
		
		try {
			verify(db).getPanelsInLocation("4444");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected=InvalidArgumentException.class)
	public void getPanelsByLocation_exception_InvalidArgumentExceptionIsThrown(){
		try {
			when(db.getPanelsInLocation("1233")).thenThrow(Exception.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller.getPanelsByLocation("1233");
	}
	
}
