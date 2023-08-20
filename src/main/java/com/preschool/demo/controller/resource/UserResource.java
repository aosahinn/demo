package com.preschool.demo.controller.resource;

import com.preschool.demo.controller.base.AbstractResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResource extends AbstractResource {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean active;

}
