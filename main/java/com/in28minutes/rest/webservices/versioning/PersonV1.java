package com.in28minutes.rest.webservices.versioning;

public class PersonV1 {

    private String name;
    public PersonV1( String name ) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonV1{" +
                "name='" + name + '\'' +
                '}';
    }
}
