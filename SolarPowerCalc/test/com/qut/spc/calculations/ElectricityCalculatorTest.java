package com.qut.spc.calculations;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class ElectricityCalculatorTest {
	
	ElectricityCalculator calculator;

	@Before
	public void setUp()  {
		calculator = new ElectricityCalculator();
	}
	
	
	
	@Test
	public void getElectricityProduction_sunPowerOver () {
		double electricity = calculator.getElectricityProduction(100.0,0.8,0.7,120.0,4.0,1.0);
		//System.out.println(electricity);
		assertEquals(256.0,electricity,0.01);
	}
	
	@Test
	public void getElectricityProduction_solarPanelOver () {
		double electricity = calculator.getElectricityProduction(120.0,0.8,0.7,100.0,4.0,1.0);
		assertEquals(307.20,electricity,0.01);
	}
	
	@Test
	public void getElectricityProduction_solarSunPowerEqual () {
		double electricity = calculator.getElectricityProduction(100.0,0.8,0.7,100.0,4.0,1.0);
		assertEquals(256.0,electricity,0.01);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_negativeDailySun () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		calculator.getElectricityProduction(-100.0,0.8,0.7,100.0,4.0,1.0);
	}
	
	@Test
	public void restrictInput_zeroDailySun () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		assertEquals(0.0, calculator.getElectricityProduction(0.0,0.8,0.7,100.0,4.0,1.0), 0.01);
	}
	
	@Test
	public void restrictInput_normalDailySun () {
		double electricity = calculator.getElectricityProduction(100.0,0.8,0.7,120.0,4.0,1.0);
		assertEquals(256.0,electricity,0.01);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_negativeinverterEfficiency () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		calculator.getElectricityProduction(100.0,-0.8,0.7,100.0,4.0,1.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_overOneinverterEfficiency () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		calculator.getElectricityProduction(100.0,1.2,0.7,100.0,4.0,1.0);
	}
	
	@Test
	public void restrictInput_oneinverterEfficiency () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		assertEquals(320.0, calculator.getElectricityProduction(100.0,1,0.7,100.0,4.0,1.0), 0.01);
	}
	
	@Test
	public void restrictInput_zeroinverterEfficiency () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		assertEquals(0.0, calculator.getElectricityProduction(100.0,0.0,0.7,100.0,4.0,1.0), 0.01);
	}
	
	@Test
	public void restrictInput_normalinverterEfficiency () {
		double electricity = calculator.getElectricityProduction(100.0,0.8,0.7,120.0,4.0,1.0);
		assertEquals(256,electricity,0.01);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_negativeSolarPanelEfficiency () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		calculator.getElectricityProduction(100.0,0.8,-0.7,100.0,4.0,1.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_overOneSolarPanelEfficiency () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		calculator.getElectricityProduction(100.0,0.8,1.2,100.0,4.0,1.0);
	}
	
	@Test
	public void restrictInput_oneSolarPanelEfficiency () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		assertEquals(256.0, calculator.getElectricityProduction(100.0,0.8,1.0,100.0,4.0,1.0),0.01);
	}
	
	@Test
	public void restrictInput_zeroSolarPanelEfficiency () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		assertEquals(256.0, calculator.getElectricityProduction(100.0,0.8,0.0,100.0,4.0,1.0),0.01);
	}
	
	@Test
	public void restrictInput_normalSolarPanelEfficiency () {
		double electricity = calculator.getElectricityProduction(100.0,0.8,0.7,120.0,4.0,1.0);
		assertEquals(256.0,electricity,0.01);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_negativeSolarPowerOutput () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,-100.0,4.0,1.0);
	}
	
	@Test
	public void restrictInput_zeroSolarPowerOutput () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		assertEquals(0.0, calculator.getElectricityProduction(100.0,0.8,0.7,0.0,4.0,1.0),0.01);
	}
	
	@Test
	public void restrictInput_normalSolarPowerOutput () {
		double electricity = calculator.getElectricityProduction(100.0,0.8,0.7,120.0,4.0,1.0);
		assertEquals(256.0,electricity,0.01);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_negativeDailyHours () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,100.0,-4.0,1.0);
	}
	
	@Test
	public void restrictInput_zeroDailyHours () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		assertEquals(0.0, calculator.getElectricityProduction(100.0,0.8,0.7,0.0,0.0,1.0),0.01);
	}
	
	@Test
	public void restrictInput_normalDailyHours () {
		double electricity = calculator.getElectricityProduction(100.0,0.8,0.7,120.0,4.0,1.0);
		assertEquals(256.0,electricity,0.01);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_negativeTimespan () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,100.0,4.0,-1.0);
	}
	
	@Test
	public void restrictInput_zeroTimespan () {
		ElectricityCalculator calculator = new ElectricityCalculator();
		assertEquals(0.0, calculator.getElectricityProduction(100.0,0.8,0.7,0.0,0.0,0.0),0.01);
	}
	
	@Test
	public void restrictInput_normalTimespan () {
		double electricity = calculator.getElectricityProduction(100.0,0.8,0.7,120.0,4.0,1.0);
		assertEquals(256.0,electricity,0.01);
	}
	
	
	

}
