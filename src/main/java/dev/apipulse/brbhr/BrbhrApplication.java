package dev.apipulse.brbhr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ai.apiverse.apisuite.mirror.agent.ApimonitorAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@ImportAutoConfiguration(ApimonitorAutoConfiguration.class)
public class BrbhrApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrbhrApplication.class, args);
	}

}
