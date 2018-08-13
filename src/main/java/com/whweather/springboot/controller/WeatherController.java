package com.whweather.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.whweather.springboot.model.Weather;
import com.whweather.springboot.service.iWeatherService;

@RestController
public class WeatherController {

	@Autowired
	private iWeatherService weatherService;
	
	@GetMapping("api/v1/wind/{zipCode}")
	public Weather retrieveWind(@PathVariable String zipCode) {
		return weatherService.retrieveWind(zipCode);
	}
	
}
