package com.example.gatewayservice;


import com.example.gatewayservice.Config.AuthFilter;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


@SpringBootApplication
@EnableDiscoveryClient


public class GatewayServiceApplication {





    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args

        );
        System.out.println( AuthFilter.VALUE_KEY.toLowerCase(Locale.ROOT));

    }
    @Autowired
    AuthFilter filter;
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder  ) {


        return builder.routes()

               // .route(p -> p.path("/Management/User/**").filters(f->f.filter(new AuthFilter().apply(new AuthFilter.Config()))).uri("lb://Authentification-MicroService"))

                .route(p -> p.path("/Management/portefeuil/**").uri("lb://Portefeuille-MicroService"))
                .route(p -> p.path("/Management/User/**").uri("lb://Authentification-MicroService"))
                .route(p -> p.path("/**").uri("lb://Management-operation-service"))
                .build();
    }


}
