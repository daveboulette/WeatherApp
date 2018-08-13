package com.whweather.springboot.model;

import java.util.Date;

public class Weather {
	private String zipCode;
	private double windDirection;
	private double windSpeed;
	private Date lastUpdated;

	public Weather() {

	}

	public Weather(String iZipCode) {
		zipCode=iZipCode;
		
		loadWeather();
		
		lastUpdated=new Date(System.currentTimeMillis());
	}
	
	public Weather(String inZipCode, double inWindSpeed, double inWindDirection, String inLastUpdated) {
		zipCode=inZipCode;
		windSpeed=inWindSpeed;
		windDirection=inWindDirection;
		
		lastUpdated=new Date(Long.parseLong(inLastUpdated));
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public double getWindDirection() {
		return windDirection;
	}
	
	public double getWindSpeed() {
		return windSpeed;
	}
	
	public Date getLastUpdated() {
		return lastUpdated;
	}
	
	public void loadWeather() {
		// load the weather info
		iWeatherInfo weatherInfo = new OpenWeatherMap(zipCode);
		windDirection = weatherInfo.getWindDirection();
		windSpeed = weatherInfo.getWindSpeed();
	}
	
	@Override
	public String toString() {
		return String.format(
				"Weather [zipCode=%s, windDirection=%s, windSpeed=%s, lastUpdated=%s]", zipCode, windDirection, windSpeed,
				lastUpdated);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Weather other = (Weather) obj;
		
		if (!zipCode.equals(other.zipCode))
			return false;
		
		return true;
	}

}