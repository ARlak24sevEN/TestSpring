//Connect user table db
package com.example.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_address")//name of table
public class Address extends BaseEntity {


    //set column name and default value
    //@Column(name = "email", nullable = false, unique = true, length = 60)
    @Column(length = 120)
    private String line1;

    //@JsonIgnore => ignore to show
    @Column(length = 120)
    private String line2;

    @Column(length = 120)
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "m_user_id",nullable = false)
    private User user;

}
