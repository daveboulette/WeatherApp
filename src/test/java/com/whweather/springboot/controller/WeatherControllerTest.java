package com.whweather.springboot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.whweather.springboot.model.Weather;
import com.whweather.springboot.service.WeatherService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WeatherController.class, secure = false)
public class WeatherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService weatherService;

	Weather mockWeather =  new Weather("90210",140.001,4.5,"1534101920517");

	@Test
	public void retrieveWind() throws Exception {

		Mockito.when(
				weatherService.retrieveWind(Mockito.anyString())).thenReturn(mockWeather);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"api/v1/wind/90210").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"zipCode\":\"90210\",\"wind\":\"{\\\"deg\\\":140.001,\\\"speed\\\":4.5}\",\"lastUpdated\":1534101920517}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

}
