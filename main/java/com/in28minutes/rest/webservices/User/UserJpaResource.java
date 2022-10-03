package com.in28minutes.rest.webservices.User;

import com.in28minutes.rest.webservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.jpa.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

    private UserDaoService service;
    private UserRepository repository;
    private PostRepository postRepository;


    public UserJpaResource(UserRepository repository  , PostRepository postRepository) {
        this.repository =repository;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> retriveAllUsers(){
        return repository.findAll();
    }


    //
    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<Optional<User>> retriveUser(@PathVariable ("id") Integer id) throws Exception {
        Optional<User> user = repository.findById(id);

        if (user == null ){
            throw new Exception();
        }


        //HATEOAS
        EntityModel<Optional<User>> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .retriveAllUsers()) ;
        entityModel.add(link.withRel("all-users"));

        return entityModel;


    }

    @PostMapping(path="/jpa/users")
    public ResponseEntity<Object> saveUser( @Valid @RequestBody User user)  //@Valid to perform validation as specified in the user class 
    {
        User savedUser = repository.save(user);
        //to return the URI after the user is saved
         URI location  = ServletUriComponentsBuilder.fromCurrentRequest()
                 .path("/{id}")
                 .buildAndExpand(savedUser.getId())
                 .toUri();

        return ResponseEntity.created(location).build(); // returns 201
    }


    @PostMapping(path="/jpa/users/{id}/posts")
    public ResponseEntity<Object> savePost( @PathVariable ("id") int id,@Valid @RequestBody Post post) throws Exception  //@Valid to perform validation as specified in the user class
    {
            Optional<User> user = repository.findById(id);

            if(user.isEmpty()){ throw new Exception();}

            post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location  = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
    
    
    @DeleteMapping(path="/jpa/users/{id}")
    public void deleteById(@PathVariable("id") int id)
    {
    	repository.deleteById(id);
    	
    	
    }


    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable ("id") int id) throws Exception {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty())
            throw new Exception("id:"+id);

        return user.get().getPosts();

    }

}
