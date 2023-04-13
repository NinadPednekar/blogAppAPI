package com.blog.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
      return new Docket(DocumentationType.SWAGGER_2)
              .apiInfo(getInfo()).select().apis(RequestHandlerSelectors.any())
              .paths(PathSelectors.any()).build();
    };

    public ApiInfo getInfo(){
        return new ApiInfo("BlogAPI",
                "This project is developed by Dev",
                "1.0",
                "Terms of Service",
                new Contact("Ninad", "localhost:8090", "ninadp610@gmail.com"),
                "License of APIs",
                "licenseurl.com",
                Collections.emptyList());
    }
}
