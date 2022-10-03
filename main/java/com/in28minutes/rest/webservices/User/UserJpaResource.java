package com.in28minutes.rest.webservices.User;

import com.in28minutes.rest.webservices.jpa.PostRepository;
import com.in28minutes.rest.webservices.jpa.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    Logger log = LoggerFactory.getLogger(UserJpaResource.class.getName());

    private UserDaoService service;
    private UserRepository usesrRepository;
    private PostRepository postRepository;


    public UserJpaResource(UserRepository usesrRepository, PostRepository postRepository) {
        log.debug("Inside the Construtor");
        this.usesrRepository = usesrRepository;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> retriveAllUsers(){

        log.info("Inside the method retriveAllUsers ");

        return usesrRepository.findAll();
    }


    //
    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<Optional<User>> retriveUser(@PathVariable ("id") Integer id) throws Exception {
        log.info("Inside the method retriveUser ");
        log.debug("id : " + id );

        Optional<User> user = usesrRepository.findById(id);

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
        log.info("Inside the method saveUser ");

        log.debug("Request Body : " + user.toString());
        User savedUser = usesrRepository.save(user);
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
            log.info("Inside the method savePost");
            log.debug("Posting for the id :" + id);
            log.debug("Post : " + post.toString());

            Optional<User> user = usesrRepository.findById(id);

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

        log.info("Inside the method deleteById ");
        log.debug("Deleting the id : "+id);
    	usesrRepository.deleteById(id);
    	
    	
    }


    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable ("id") int id) throws Exception {

        log.info("Inside the method retrievePostsForUser ");

        log.debug("retrieving posts for the user id : "+id);

        Optional<User> user = usesrRepository.findById(id);

        if(user.isEmpty())
            throw new Exception("id:"+id);

        return user.get().getPosts();

    }

}
