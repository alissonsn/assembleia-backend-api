package br.com.assembleia.backendapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Alisson Nascimento 
 *
 * http://localhost:8080/swagger-ui.html#/
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket assembleiaApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.assembleia.backendapi"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger Assembleia Backend API")
                .version("1.0")
                .description("Assembleia Backend API")
                .contact(new Contact("Alisson Nascimento", "", "alissonsn@gmail.com"))
                .license("Apache License Version 2.0")
                .build();
    }
    
}
