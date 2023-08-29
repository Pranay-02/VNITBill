package com.vnit.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringfoxConfiguration {

	private static final String DOCKET_GROUP_NAME = "VNIT";

	@Bean
	Docket generatedDocket() {
		return docket().groupName(DOCKET_GROUP_NAME);
	}

	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.vnit.api")).build()
				.apiInfo(apiInfo());
	}

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("VNIT REST api")
				.description("Generated documentation for VNIT APIs").termsOfServiceUrl("").version("1.0.0")
				.contact(new Contact("", "", "")).build();
	}

}
