package com.in28minutes.rest.webservices.jpa;

import com.in28minutes.rest.webservices.User.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
