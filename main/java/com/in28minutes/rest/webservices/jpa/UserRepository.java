package com.in28minutes.rest.webservices.jpa;

import com.in28minutes.rest.webservices.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
