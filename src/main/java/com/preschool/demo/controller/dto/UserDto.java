package com.preschool.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends AbstractDto {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean active;

}
