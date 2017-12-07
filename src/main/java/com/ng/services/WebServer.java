package com.ng.services;

import com.ng.web.HomeController;
import com.ng.web.UsersController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Created by natalia on 23/11/17.
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters=false)  // Disable component scanner
public class WebServer {

    // Case insensitive
    public static final String USERS_SERVICE_URL
            = "http://users-management-service";

    public static void main(String[] args) {
        // Will configure using web-server.yml
        System.setProperty("spring.config.name", "web-server");
        SpringApplication.run(WebServer.class, args);
    }

    @LoadBalanced    // Make sure to create the load-balanced template
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Account service calls microservice internally using provided URL.
     */
    @Bean
    public UsersService usersService() {
        return new UsersService(USERS_SERVICE_URL);
    }

    @Bean
    public UsersController usersController() {
        return new UsersController(usersService());  // plug in account-service
    }

    @Bean
    public HomeController homeController() {
        return new HomeController();
    }
}