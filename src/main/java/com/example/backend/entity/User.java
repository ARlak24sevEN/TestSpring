//Connect user table db
package com.example.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_user")//name of table
public class User extends BaseEntity {


    //set column name and default value
    //@Column(name = "email", nullable = false, unique = true, length = 60)
    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false,length = 120)
    private String password;

    @Column(nullable = false,length = 120)
    private String name;


}
