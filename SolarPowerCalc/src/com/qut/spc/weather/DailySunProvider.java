package com.qut.spc.weather;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.qut.spc.postcode.PostcodeUtil;

public class DailySunProvider {
	
	
	//hours / W/m^2
	
	private static Map<String,double[]> locationToSunHoursIntensity=new HashMap<String, double[]>(){
		{
			put("4000",new double[]{7.5,1000});
			put("5000",new double[]{6.75,1000});
			put("2000",new double[]{7.33,1000});
			put("3000",new double[]{5.58,1000});
			put("2900",new double[]{7.33,1000});
			put("6000",new double[]{7.92,1000});
		}};

	
	
	public static double getDailySunByPostcode(String location) throws IllegalArgumentException {
		if(PostcodeUtil.validatePostcode(location)){
			String postcode =PostcodeUtil.transformPostcode(location);
			if(locationToSunHoursIntensity.containsKey(postcode))
				return locationToSunHoursIntensity.get(location)[0];			
		}
		
		return average(locationToSunHoursIntensity.values(),0);
	}



	private static double average(Collection<double[]> values,int index) {
		double sum=0;
		for(double[] d:values){
			if(index<d.length&&index>=0)
				sum+=d[index];
		}
		return sum/values.size();
	}



	public static double getSunIntensity(String location) {
		if(PostcodeUtil.validatePostcode(location)){
			String postcode=PostcodeUtil.transformPostcode(location);
			if(locationToSunHoursIntensity.containsKey(postcode))
				return locationToSunHoursIntensity.get(location)[1];
		}
		return average(locationToSunHoursIntensity.values(),1);
	}

}
