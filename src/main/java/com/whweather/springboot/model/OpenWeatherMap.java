package com.whweather.springboot.model;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OpenWeatherMap implements iWeatherInfo {
	
	double windDirection;
	double windSpeed;
	
	public OpenWeatherMap(String zipCode) {
		// retrieve weather info from service
		getWeatherInfo("https://openweathermap.org/data/2.5/weather?q="+zipCode+"&appid=b6907d289e10d714a6e88b30761fae22");
	}
	
	@Override
	public double getWindDirection() {
		return windDirection;
	}
	
	@Override
	public double getWindSpeed() {
		return windSpeed;
	}
	
	private void getWeatherInfo(String url) {

		Object resultObject=null;
		
		// grab the weather info from from url
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(url);
            
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            try {
                JSONParser parser = new JSONParser();
                resultObject = parser.parse(json);

                if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    
                    // pull out wind info from the json response
                    windDirection = Double.parseDouble(((JSONObject)obj.get("wind")).get("deg").toString());
                    windSpeed = Double.parseDouble(((JSONObject)obj.get("wind")).get("speed").toString());
                }
            } 
            catch (Exception e) {
            	System.out.println(e.toString());
            }

        } 
        catch (IOException ex) {
        	System.out.println(ex.toString());
        }
    }
}