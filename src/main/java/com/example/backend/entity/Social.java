//Connect user table db
package com.example.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_social")//name of table
public class Social extends BaseEntity {


    //set column name and default value
    //@Column(name = "email", nullable = false, unique = true, length = 60)
    @Column(length = 120)
    private String faceook;

    //@JsonIgnore => ignore to show
    @Column(length = 120)
    private String line;

    @Column(length = 120)
    private String instagram;

    @Column(length = 120)
    private  String tiktok;

    @OneToOne
    @JoinColumn(name = "m_user_id",nullable = false)
    private User user;
}
