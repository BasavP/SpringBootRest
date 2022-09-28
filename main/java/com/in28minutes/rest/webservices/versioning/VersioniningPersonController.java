package com.in28minutes.rest.webservices.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioniningPersonController {


    //Versioning with URI

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
                return new  PersonV1 ("Bob Charlie");
    }


    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new  PersonV2 (new Name("Bob","Charlie"));
    }


    //versioning with requestParams
    @GetMapping(path = "/person" ,  params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter(){
        return new  PersonV1 ("Bob Charlie");
    }

    @GetMapping(path = "/person" ,  params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter(){
        return new  PersonV2 (new Name("Bob","Charlie"));
    }


    //Versioning with headers


    @GetMapping(path = "/person/header" ,  headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonHeader(){
        return new  PersonV1 ("Bob Charlie");
    }

    @GetMapping(path = "/person/header" ,  headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonHeader(){
        return new  PersonV2 (new Name("Bob","Charlie"));
    }

    //acceptHeader Versioning  to be written here


}
