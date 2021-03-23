package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class DemoSpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringCloudGatewayApplication.class, args);
    }

    @GetMapping("/hello")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().build();
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/get")
                        .uri("http://ghibliapi.herokuapp.com:80/films"))
                .build();
    }

}
