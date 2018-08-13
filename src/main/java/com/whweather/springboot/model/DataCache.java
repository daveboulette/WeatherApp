package com.whweather.springboot.model;

import java.util.Date;
import java.util.HashMap;

public class DataCache 
{
	// cache the weather info and store it in a HashMap so lookups occur in O(n) time
	private static HashMap<String,Weather> weatherCache;

	public DataCache()
	{
		weatherCache = new HashMap<String,Weather>();
	}
	
	public Weather getWeather(String zipCode)
	{
		Date now = new Date();
		
		// check the cache
		Weather zipWeather = weatherCache.get(zipCode);
		
		// if we didn't find it in the cache
		if (zipWeather==null) {
			// load the weather info
			Weather weather = new Weather(zipCode);
			zipWeather=weather;
			
			// store it in the cache for next time
			weatherCache.put(zipCode, weather);
			
		}
	
		// if the data is older then 15 minutes refresh it
		if (now.getTime() - zipWeather.getLastUpdated().getTime() >= 15*60*1000)
			zipWeather.loadWeather();
		
		return zipWeather;
	}
	
	public void refreshCache() {
		// clear out all saved values in the hashMap
	    weatherCache = new HashMap<String,Weather>();
	    System.out.println("The cache has been cleared.");
	}
}
