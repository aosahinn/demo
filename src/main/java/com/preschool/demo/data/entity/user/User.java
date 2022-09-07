package com.preschool.demo.data.entity.user;

import com.preschool.demo.data.entity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name= "users")
@Data
public class User extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", length = USERNAME_LENGTH, nullable = false)
    private String username;

    @NotNull
    private String password;

    private String email;

    private boolean active;
}
