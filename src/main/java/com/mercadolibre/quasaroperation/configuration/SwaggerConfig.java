package com.mercadolibre.quasaroperation.configuration;

import com.mercadolibre.quasaroperation.controller.ApiInfoConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    @SuppressWarnings("unchecked")
    public Docket productApi(ApiInfoConfig appConfig) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.mercadolibre.quasaroperation"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title(appConfig.getTitle())
                        .version("1.0.0")
                        .description(appConfig.getDescription())
                        .contact(new Contact(appConfig.getNameContact(),
                                appConfig.getMailContact(),
                                appConfig.getMailContact()))
                        .build());
    }
}
