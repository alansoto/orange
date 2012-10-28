package com.qut.spc.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class InverterContainerTest {
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	private Inverter inverter;
	private InverterContainer container;
	private List<Inverter> list;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		container = new InverterContainer();
		prepareData();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}
	
	private void prepareData() {
		inverter = new Inverter();
		inverter.setModel("1");
		inverter.setPrice(20.0);
		inverter.setCapacity(120.0);
		inverter.save();
		
		inverter = new Inverter();
		inverter.setModel("2");
		inverter.setPrice(40.0);
		inverter.setCapacity(140.0);
		inverter.save();
		
		inverter = new Inverter();
		inverter.setModel("3");
		inverter.setPrice(60.0);
		inverter.setCapacity(160.0);
		inverter.save();
		
		inverter = new Inverter();
		inverter.setModel("4");
		inverter.setPrice(80.0);
		inverter.setCapacity(180.0);
		inverter.save();
		
		inverter = new Inverter();
		inverter.setModel("5");
		inverter.setPrice(100.0);
		inverter.setCapacity(200.0);
		inverter.save();
	}

	@Test
	public void testSearchByPrice() {
		container.setMinPrice(41.0);
		container.setMaxPrice(100.0);
		
		list = container.search();
		
		assertEquals(3, list.size());
		assertEquals("3", list.get(2).getModel());
		assertEquals("4", list.get(1).getModel());
		assertEquals("5", list.get(0).getModel());
	}
	
	@Test
	public void testSearchByPrice_NoMin() {
		container.setMinPrice(0.0);
		container.setMaxPrice(50.0);
		
		list = container.search();
		
		assertEquals(2, list.size());
		assertEquals("1", list.get(1).getModel());
		assertEquals("2", list.get(0).getModel());
	}

	@Test
	public void testSearchByPrice_NoMax() {
		container.setMinPrice(80.0);
		container.setMaxPrice(0.0);
		
		list = container.search();
		
		assertEquals(2, list.size());
		assertEquals("4", list.get(1).getModel());
		assertEquals("5", list.get(0).getModel());
	}
	
	@Test
	public void testSearchByPrice_All() {
		container.setMinPrice(0.0);
		container.setMaxPrice(0.0);
		list = container.search();
		
		assertEquals(5, list.size());
	}
	
	@Test
	public void testSearchByCapacity() {
		container.setMinCapacity(159.9);
		container.setMaxCapacity(185.0);
		
		list = container.search();
		
		assertEquals(2, list.size());
		assertEquals("3", list.get(1).getModel());
		assertEquals("4", list.get(0).getModel());
	}
	
	@Test
	public void testSearchByCapacity_NoMin() {
		container.setMinCapacity(0.0);
		container.setMaxCapacity(120.0);
		
		list = container.search();
		
		assertEquals(1, list.size());
		assertEquals("1", list.get(0).getModel());
	}

	@Test
	public void testSearchByCapacity_NoMax() {
		container.setMinCapacity(175.0);
		container.setMaxCapacity(0.0);
		
		list = container.search();
		
		assertEquals(2, list.size());
		assertEquals("4", list.get(1).getModel());
		assertEquals("5", list.get(0).getModel());
	}
	
	@Test
	public void testSearchByCapacity_All() {
		container.setMinCapacity(0.0);
		container.setMaxCapacity(0.0);
		list = container.search();
		
		assertEquals(5, list.size());
	}
}
