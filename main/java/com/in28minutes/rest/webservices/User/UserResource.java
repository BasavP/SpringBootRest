package com.in28minutes.rest.webservices.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    private UserDaoClass service;

    @Autowired
    public UserResource(UserDaoClass service) {
        this.service = service;
    }

    @GetMapping(path = "/users")
    public List<User> retriveAllUsers(){
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retriveUser(@PathVariable ("id") Integer id){
        return service.findOne(id);
    }

    @PostMapping(path="/users")
    public void saveUser(@RequestBody User user)
    {
        service.save(user);
    }

}
