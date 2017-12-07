package com.ng.web;

import com.ng.services.User;
import com.ng.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
public class UsersController {

    @Autowired
    protected UsersService usersService;

    protected Logger logger = Logger.getLogger(UsersController.class.getName());

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/users")
    public String goHome() {
        return "index";
    }


    @RequestMapping("/users/{login}")
    public String getByLogin(Model model, @PathVariable("login") String login) {

        User user = usersService.getByLogin(login);
        model.addAttribute("user", user);

        return "user";
    }

    @RequestMapping("/users/search/{name}")
    public String getByName(Model model, @PathVariable("name") String name) {

        User user = usersService.searchByName(name);
        model.addAttribute("user", user);

        return "user";
    }



}
