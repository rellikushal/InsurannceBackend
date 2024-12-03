package com.insurance.www;

import java.util.HashSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Property Insurance REST API Documentation",
		description = "Total monolith REST API Documentation",
		version = "V2" ,
		contact = @Contact(
			name = " Ravi sir",
			email =" dummy@email.com"
		)

	)
)


public class PropertyInsuranceApplication {

	// private static int ACCESS_CONTROL_MAX_AGE_IN_SECONDS = 12 * 60 * 60;

	// private static final HashSet<String> TRUSTED_SOURCES = new HashSet<String>();

	// static {
	// 	TRUSTED_SOURCES.add("http://122.169.207.194:9093");
	// }

	// public static void setTrustedSources(final HashSet<String> sources) {
	// 	TRUSTED_SOURCES.addAll(sources);
	// }


	public static void main(String[] args) {
		SpringApplication.run(PropertyInsuranceApplication.class, args);
	}

	
	// @Override
	// public void addCorsMappings(CorsRegistry registry) {

	// 	//LOG.info(TRUSTED_SOURCES.toString());
		
	// 	registry.addMapping("/**")
	// 			// .allowedOrigins(TRUSTED_SOURCES.toArray(new String[TRUSTED_SOURCES.size()]))
	// 			.allowedOrigins("http://122.169.207.194:9093").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
	// 			.allowedHeaders("origin", "content-type", "accept", "authorization", "user-agent", "host",
	// 					"X-Forwarded-For", "X-Forwarded-Proto", "X-Forwarded-Port", "X-Redirected-Path",
	// 					"X-Redirected-Params", "X-TraceId", "X-Feature-Flags", "X-Partner-Id")
	// 			.exposedHeaders("Content-Length", "Content-Type", "Content-Disposition", "Cache-Control")
	// 			.allowCredentials(true).maxAge(ACCESS_CONTROL_MAX_AGE_IN_SECONDS);

	// }




}
