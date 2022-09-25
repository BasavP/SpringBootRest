package com.in28minutes.rest.webservices.User;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoClass {

    //will use ArrayList initially

    private static ArrayList<User> users = new ArrayList();

    static {

        users.add(new User(1,"Dwight", LocalDateTime.now().minusYears(30)));
        users.add(new User(2,"Jim", LocalDateTime.now().minusYears(28)));
        users.add(new User(3,"Michael", LocalDateTime.now().minusYears(40)));

    }

    public List<User> findAll(){
        return users;
    }


}
