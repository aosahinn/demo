package com.preschool.demo.controller.dto;

import com.preschool.demo.controller.base.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto extends AbstractDto {

    private String companyId;
    private String packageId;
    private String customerId;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

}
