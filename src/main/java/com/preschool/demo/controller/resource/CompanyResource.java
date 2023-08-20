package com.preschool.demo.controller.resource;

import com.preschool.demo.controller.base.AbstractResource;
import com.preschool.demo.utils.enums.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResource extends AbstractResource {

    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private CompanyType type;

}
