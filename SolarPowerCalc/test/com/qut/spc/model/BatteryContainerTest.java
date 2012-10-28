package com.qut.spc.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class BatteryContainerTest {
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	private Battery battery;
	private BatteryContainer container;
	private List<Battery> list;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		container = new BatteryContainer();
		prepareData();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}
	
	private void prepareData() {
		battery = new Battery();
		battery.setModel("1");
		battery.setPrice(20.0);
		battery.setCapacity(120.0);
		battery.save();
		
		battery = new Battery();
		battery.setModel("2");
		battery.setPrice(40.0);
		battery.setCapacity(140.0);
		battery.save();
		
		battery = new Battery();
		battery.setModel("3");
		battery.setPrice(60.0);
		battery.setCapacity(160.0);
		battery.save();
		
		battery = new Battery();
		battery.setModel("4");
		battery.setPrice(80.0);
		battery.setCapacity(180.0);
		battery.save();
		
		battery = new Battery();
		battery.setModel("5");
		battery.setPrice(100.0);
		battery.setCapacity(200.0);
		battery.save();
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
