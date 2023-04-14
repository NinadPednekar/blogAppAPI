package com.blog.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKey(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContexts(){
        return SecurityContext.builder().securityReferences(securityReferences()).build();
    }

    private List<SecurityReference> securityReferences(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    @Bean
    public Docket api(){
      return new Docket(DocumentationType.SWAGGER_2)
              .apiInfo(getInfo())
              .securityContexts(Arrays.asList(securityContexts()))
              .securitySchemes(Arrays.asList(apiKey()))
              .select()
              .apis(RequestHandlerSelectors.any())
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
