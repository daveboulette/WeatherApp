package com.whweather.springboot.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
		// return null if the zip is invalid
		if (!validZip(zipCode)) {
			return null;
        }
		
		return weatherService.retrieveWind(zipCode);
	}
	
	private boolean validZip(String zipCode) {
		// use a regex to verify the zipcode
		String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
		 
		Pattern pattern = Pattern.compile(regex);
		 
		Matcher matcher = pattern.matcher(zipCode);

		return matcher.matches();
	}
	
}
