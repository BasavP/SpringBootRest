package com.in28minutes.rest.webservices.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;



@Entity(name = "user_details")
public class User {
    @Id
    @Generated
    private Integer id;
    @JsonProperty("userName")
    @Size(min = 2, message = "name must be atleast 2 chars")
    private String name;
    
    @Past  (message = "birthDate should be in the past" )  //to ensure that the birthdate is of the past
    private LocalDateTime birthDate;

    
    public User() {}
    
    public User(Integer id, String name, LocalDateTime birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }


}
