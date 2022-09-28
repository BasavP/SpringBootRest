package com.in28minutes.rest.webservices.filetering;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering")
    public SomeBean filtering(){
        return  new SomeBean("value1" , "value2" , "value3");
    }

    @GetMapping(path = "/filtering-list")
    public List<SomeBean> filteringList(){
        return Arrays.asList(new SomeBean("value1" , "value2" , "value3") ,
                new SomeBean("value4" , "value5" , "value6"));
    }

}
