package com.in28minutes.rest.webservices.filetering;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering")
    public MappingJacksonValue filtering(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        //create a filter
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field1","field3");

        //create a filterProvider , adding the above created filter
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter"   //this filtername will be used in the annotation
                ,simpleBeanPropertyFilter);

        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping(path = "/filtering-list")
    public MappingJacksonValue filteringList(){
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeans);
        //create a filter
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field1","field3");

        //create a filterProvider , adding the above created filter
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter"   //this filtername will be used in the annotation
                        ,simpleBeanPropertyFilter);

        mappingJacksonValue.setFilters(filters);


        return mappingJacksonValue;
    }

}
