package io.avec.outputtracker.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any()) // everything
//                .paths(PathSelectors.any()) // everything
//                .apis(RequestHandlerSelectors.basePackage("io.avec.outputtracker.output"))
                .paths(PathSelectors.ant("/api/v1/*"))
                .build();
    }
}
