package com.qut.spc.model;

import javax.xml.bind.annotation.XmlElement;

import com.qut.spc.db.Database;

public class Inverter extends SolarComponent {
	
	@XmlElement
	private String batteryVoltageRange = "";
	
	@XmlElement
	private String outputVoltage = "";
	
	@XmlElement
	private String outputFrequency = "";
	
	public Inverter() {
		
	}

	/**
	 * Get battery voltage range, e.g. 10.5 - 17
	 * Unit: V
	 */
	public String getBatteryVoltageRange() {
		return batteryVoltageRange;
	}

	/**
	 * Voltage range format: MIN - MAX. e.g. 10.5 - 17
	 * Unit: V
	 */
	public void setBatteryVoltageRange(String batteryVoltageRange) {
		this.batteryVoltageRange = batteryVoltageRange;
	}

	/**
	 * Get output voltage. e.g 240 AC +/- 4%  
	 * Unit V
	 */
	public String getOutputVoltage() {
		return outputVoltage;
	}

	public void setOutputVoltage(String outputVoltage) {
		this.outputVoltage = outputVoltage;
	}

	/**
	 * Get output frequency. e.g 50 +/- 0.1%  
	 * Unit Hz
	 */
	public String getOutputFrequency() {
		return outputFrequency;
	}

	public void setOutputFrequency(String outputFrequency) {
		this.outputFrequency = outputFrequency;
	}
	
	public static Inverter load(long id) {
		return Database.loadComponent(id, Inverter.class);
	}
}
