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

	Weather mockWeather =  new Weather("89109",4.6,135.005,"1534127239091");

	@Test
	public void retrieveWind() throws Exception {

		Mockito.when(
				weatherService.retrieveWind(Mockito.anyString())).thenReturn(mockWeather);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"http://localhost:8080/api/v1/wind/89109").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"zipCode\":\"89109\",\"windDirection\":135.005,\"windSpeed\":4.6,\"lastUpdated\":1534127239091}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

}
