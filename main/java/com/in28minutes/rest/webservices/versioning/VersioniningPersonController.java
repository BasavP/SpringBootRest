package com.in28minutes.rest.webservices.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioniningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
                return new  PersonV1 ("Bob Charlie");
    }


    @GetMapping("/v2/person")
    public PersonV2 getFirstVersion2OfPerson(){
        return new  PersonV2 (new Name("Bob","Charlie"));
    }

}
