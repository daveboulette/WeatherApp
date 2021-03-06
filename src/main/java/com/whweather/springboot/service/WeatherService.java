package com.whweather.springboot.service;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.whweather.springboot.model.DataCache;
import com.whweather.springboot.model.Weather;

@Component
public class WeatherService implements iWeatherService {

	DataCache dataCache = new DataCache();
	
	@Override
	public Weather retrieveWind(String zipCode) {
		return dataCache.getWeather(zipCode);
	}	
	
	@EventListener(ContextStartedEvent.class)
	private void refreshCache()
	{
		dataCache.refreshCache();
	}
}