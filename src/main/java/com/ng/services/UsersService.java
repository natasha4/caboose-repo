package com.ng.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.AccountNotFoundException;

/**
 * Hide the access to the microservice inside this local service.
 *
 * Created by natalia on 22/11/17.
 */
@Service
public class UsersService {

    @Autowired        // NO LONGER auto-created by Spring Cloud (see below)
    @LoadBalanced     // Explicitly request the load-balanced template
    // with Ribbon built-in
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    public UsersService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public User getByLogin(String login) {
        User user = restTemplate.getForObject(serviceUrl+ "/users/{login}", User.class, login);

        if (user == null)
            throw new RuntimeException("User Not found " + login);
        else
            return user;
    }

    public User searchByName(String name) {
        User user = restTemplate.getForObject(serviceUrl+ "/users/{name}", User.class, name);

        if (user == null)
            throw new RuntimeException("User Not found " + name);
        else
            return user;
    }
}