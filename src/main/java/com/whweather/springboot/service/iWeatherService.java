package com.whweather.springboot.service;

import com.whweather.springboot.model.Weather;

public interface iWeatherService {

	public Weather retrieveWind(String zipCode);
}
