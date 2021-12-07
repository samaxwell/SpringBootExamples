package com.sean.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class PersonEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    public PersonEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
