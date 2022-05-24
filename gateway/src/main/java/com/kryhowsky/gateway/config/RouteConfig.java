package com.kryhowsky.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    public static final String ROUTE_URI = "localhost:8989";

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("BASKET-SERVICE", predicateSpec -> predicateSpec.host(ROUTE_URI)
                        .and()
                        .path("/api/baskets")
                        .uri("http://localhost:8383"))
                .route("PRODUCT-SERVICE", predicateSpec -> predicateSpec.host(ROUTE_URI)
                        .and()
                        .path("/api/products")
                        .uri("http://localhost:8282"))
                .route("USER-SERVICE", predicateSpec -> predicateSpec.host(ROUTE_URI)
                        .and()
                        .path("/api/users")
                        .uri("http://localhost:8181"))
                .build();
    }

}
