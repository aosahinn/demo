package com.preschool.demo.controller.mapper;

import com.preschool.demo.controller.base.Converter;
import com.preschool.demo.controller.dto.SubscriptionDto;
import com.preschool.demo.controller.resource.SubscriptionResource;
import com.preschool.demo.data.entity.subscription.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper extends Converter<SubscriptionDto, Subscription, SubscriptionResource> {

    @Mappings({
            @Mapping(source = "companyId", target = "company.identifier"),
            @Mapping(source = "packageId", target = "pack.identifier"),
            @Mapping(source = "customerId", target = "customer.identifier")
    })
    @Override
    Subscription toEntity(SubscriptionDto dto);
}
