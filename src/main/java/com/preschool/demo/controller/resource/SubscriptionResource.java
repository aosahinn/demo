package com.preschool.demo.controller.resource;

import com.preschool.demo.controller.base.AbstractResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionResource extends AbstractResource {

    private String companyId;
    private String packageId;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

}
