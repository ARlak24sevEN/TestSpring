//Connect user table db
package com.example.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_user")//name of table
public class User extends BaseEntity {


    //set column name and default value
    //@Column(name = "email", nullable = false, unique = true, length = 60)
    @Column(nullable = false, unique = true, length = 60)
    private String email;

    //@JsonIgnore => ignore to show
    @Column(nullable = false, length = 120)
    private String password;

    @Column(nullable = false, length = 120)
    private String name;

    private String civilId;

    //one to one foringkey
    @OneToOne(mappedBy = "user",orphanRemoval = true)
    private Social social;


    //one to many
    //fetch default is LAZY => Don't wait data is error no time session in one to many or many to one or many to many
    //fetch set EAGER is wait to call database but beware if have many data =>Database load table user table address load too
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Address> addresses;

}
