package com.in28minutes.rest.webservices.User;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoClass {

    //will use ArrayList initially

    private static ArrayList<User> users = new ArrayList();
    private static int userCount =0;
    static {

        users.add(new User(++userCount,"Dwight", LocalDateTime.now().minusYears(30)));
        users.add(new User(++userCount,"Jim", LocalDateTime.now().minusYears(28)));
        users.add(new User(++userCount,"Michael", LocalDateTime.now().minusYears(40)));

    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id) {
      Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }


    public User save (User user ){
        user.setId(++userCount);
        users.add(user);
        return user;
    }
}
