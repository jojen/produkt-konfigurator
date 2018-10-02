package com.schmalz.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Solcom-Schulten on 27.04.2017.
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket apiDocumentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(Predicates.and(RequestHandlerSelectors.withClassAnnotation(RestController.class)))
                .paths(PathSelectors.any())
                .build()
                .enableUrlTemplating(false);
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                null,// url
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true,         // showRequestHeaders    => true | false
                60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
    }
}
