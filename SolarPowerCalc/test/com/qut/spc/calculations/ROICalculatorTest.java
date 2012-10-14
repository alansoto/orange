/**
 * 
 */
package com.qut.spc.calculations;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Zuojun Chen
 *
 */
public class ROICalculatorTest {
	ROICalculator ROICal, ROICalulator;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ROICal = new ROICalculator();
	}

	/**
	 * Test method for {@link com.qut.spc.calculations.ROICalculator#ROICalculator()}.
	 */
	@Test
	public void test_ROICalculator() {
		ROICal = new ROICalculator();
	}

	/**
	 * Test method for {@link com.qut.spc.calculations.ROICalculator#ROICalculator(double, double, double, double, double)}.
	 */
	@Test
	public void test_ROICalculatorParameters() {
		ROICalulator = new ROICalculator(5,365, 0.23, 0.44, 10, 1000);
	}
	@Test
	public void test_ROICalculatorParameters_electricity_zero() {
		ROICalulator = new ROICalculator(0, 365,0.23, 0.44, 10, 1000);
	}
	@Test (expected = IllegalArgumentException.class)
	public void test_ROICalculatorParameters_electricity_negative() {
		ROICalulator = new ROICalculator(-5, 365,0.23, 0.44, 10, 1000);
	}
	@Test
	public void test_ROICalculatorParameters_electricity_positive() {
		ROICalulator = new ROICalculator(5, 365,0.23, 0.44, 10, 1000);
	}
	@Test
	public void test_ROICalculatorParameters_feedInTariff_zero() {
		ROICalulator = new ROICalculator(5, 365,0, 0.44, 10, 1000);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_ROICalculatorParameters_feedInTariff_negative() {
		ROICalulator = new ROICalculator(5, 365,-0.23, 0.44, 10, 1000);
	}
	@Test
	public void test_ROICalculatorParameters_feedInTariff_positive() {
		ROICalulator = new ROICalculator(5,365, 0.23, 0.44, 10, 1000);
	}
	@Test
	public void test_ROICalculatorParameters_costOfElectricity_zero() {
		ROICalulator = new ROICalculator(5,365, 0.23, 0, 10, 1000);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_ROICalculatorParameters_costOfElectricity_negative() {
		ROICalulator = new ROICalculator(5,365, 0.23, -0.44, 10, 1000);
	}
	@Test
	public void test_ROICalculatorParameters_costOfElectricity_positive() {
		ROICalulator = new ROICalculator(5,365, 0.23, 0.44, 10, 1000);
	}
	@Test
	public void test_ROICalculatorParameters_dailyUsage_zero() {
		ROICalulator = new ROICalculator(5,365, 0.23, 0.44, 0, 1000);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_ROICalculatorParameters_dailyUsage_negative() {
		ROICalulator = new ROICalculator(5,365, 0.23, 0.44, -10, 1000);
	}
	@Test
	public void test_ROICalculatorParameters_dailyUsage_positive() {
		ROICalulator = new ROICalculator(5,365, 0.23, 0.44, 10, 1000);
	}
	@Test
	public void test_ROICalculatorParameters_systemCost_zero() {
		ROICalulator = new ROICalculator(5,365, 0.23, 0.44, 10, 0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_ROICalculatorParameters_systemCost_negative() {
		ROICalulator = new ROICalculator(5,365, 0.23, 0.44, 10, -1000);
	}
	@Test
	public void test_ROICalculatorParameters_systemCost_positive() {
		ROICalulator = new ROICalculator(5,365, 0.23, 0.44, 10, 1000);
	}

	
	/**
	 * Test method for {@link com.qut.spc.calculations.ROICalculator#setElectricityProduction(double)}.
	 */
	@Test
	public void test_SetAnnualElectricityProduction_zero() {
		ROICal.setElectricityProduction(0,365);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_SetAnnualElectricityProduction_negative() {
		ROICal.setElectricityProduction(-100,365);
	}
	@Test
	public void test_SetAnnualElectricityProduction_positive() {
		ROICal.setElectricityProduction(100,365);
	}

	/**
	 * Test method for {@link com.qut.spc.calculations.ROICalculator#setFeedInTariff(double)}.
	 */
	@Test
	public void test_SetFeedInTariff_zero() {
		ROICal.setFeedInTariff(0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_SetFeedInTariff_negative() {
		ROICal.setFeedInTariff(-0.23);
	}
	@Test
	public void test_SetFeedInTariff_positive() {
		ROICal.setFeedInTariff(0.23);
	}

	/**
	 * Test method for {@link com.qut.spc.calculations.ROICalculator#setCostOfElectricity(double)}.
	 */
	@Test
	public void test_SetCostOfElectricity_zero() {
		ROICal.setCostOfElectricity(0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_SetCostOfElectricity_negative() {
		ROICal.setCostOfElectricity(-0.44);
	}
	@Test
	public void test_SetCostOfElectricity_positive() {
		ROICal.setCostOfElectricity(0.44);
	}

	/**
	 * Test method for {@link com.qut.spc.calculations.ROICalculator#setDailyUsage(double)}.
	 */
	@Test
	public void test_SetDailyUsage_zero() {
		ROICal.setDailyUsage(0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_SetDailyUsage_negative() {
		ROICal.setDailyUsage(-10);
	}
	@Test
	public void test_SetDailyUsage_positive() {
		ROICal.setDailyUsage(10);
	}

	/**
	 * Test method for {@link com.qut.spc.calculations.ROICalculator#setSystemCost(double)}.
	 */
	@Test
	public void test_SetSystemCost_zero() {
		ROICal.setSystemCost(0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_SetSystemCost_negative() {
		ROICal.setSystemCost(-1000);
	}
	@Test
	public void test_SetSystemCost_positive() {
		ROICal.setSystemCost(1000);
	}

	/**
	 * Test method for {@link com.qut.spc.calculations.ROICalculator#getROI()}.
	 */
	@Test
	public void test_GetAnnualROI() {
		ROICalulator = new ROICalculator(5,365, 0.23, 0.44, 10, 10000);
		assertEquals(0.08,ROICalulator.getROI(),0.01);
	}

}
