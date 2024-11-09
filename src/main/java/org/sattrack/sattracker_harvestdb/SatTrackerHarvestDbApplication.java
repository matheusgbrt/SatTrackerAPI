package org.sattrack.sattracker_harvestdb;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableRabbit
public class SatTrackerHarvestDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SatTrackerHarvestDbApplication.class, args);
	}


}
