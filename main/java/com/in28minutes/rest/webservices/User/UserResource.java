package com.in28minutes.rest.webservices.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

@RestController
public class UserResource {

    private UserDaoClass service;

    
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
    public ResponseEntity<Object> saveUser( @Valid @RequestBody User user)  //@Valid to perform validation as specified in the user class 
    {
        User savedUser = service.save(user);
        //to return the URI after the user is saved
         URI location  = ServletUriComponentsBuilder.fromCurrentRequest()
                 .path("/{id}")
                 .buildAndExpand(savedUser.getId())
                 .toUri();

        return ResponseEntity.created(location).build(); // returns 201
    }
    
    
    @DeleteMapping(path="/users/{id}")
    public void deleteById(@PathVariable("id") int id)
    {
    	service.deleteById(id);
    	
    	
    }

}
