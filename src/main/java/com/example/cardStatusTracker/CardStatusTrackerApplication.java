package com.example.cardStatusTracker;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImp")
@EnableScheduling
@OpenAPIDefinition(
		info = @Info(
				title = "Card Status Tracker API's",
				description = "This services provides an API's for getting card status by taking card number or mobile number",
				version = "v1",
				contact = @Contact(
						name = "Arjunagi A Rehman",
						email = "abdul123arj@gmail.com"
				),
				license =@License(
						name = "Apache"
				)
		)
)
public class CardStatusTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardStatusTrackerApplication.class, args);
	}

}
