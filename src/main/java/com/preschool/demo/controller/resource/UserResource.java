package com.preschool.demo.controller.resource;

import com.preschool.demo.controller.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResource extends AbstractDto {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean active;

}
