package com.whweather.springboot;

import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext sp = SpringApplication.run(WeatherApplication.class, args);
		
		Scanner scanner = new Scanner(System.in);
		boolean running=true;

		// let the users know what commands are available
		System.out.println("Type 'clear' to clear the cache.");
		System.out.println("Type 'exit' to shutdown.");
		
		do
		{
		    // get users input as a String
		    String command = scanner.next();
		    
		    if (command.equals("clear"))
		    {
		    	// stop and start to trigger the cache clear event
		    	sp.stop();
		    	sp.start();
		    }
		    else if (command.equals("exit"))
		    {
		    	running=false;
		    }
		    
		} while (running);
		
		// close down the app
		sp.stop();
		sp.close();
		scanner.close();
	}
}
