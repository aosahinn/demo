package com.preschool.demo.controller.dto;

import com.preschool.demo.controller.base.AbstractDto;
import com.preschool.demo.utils.enums.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto extends AbstractDto {

    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private CompanyType type;

}
