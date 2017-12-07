package com.ng.services;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * User DTO
 */

@JsonRootName("User")
public class User {

    protected Long id;
    protected String name;
    protected String login;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    protected void setLogin(String login) {
        this.login = login;
    }
}
