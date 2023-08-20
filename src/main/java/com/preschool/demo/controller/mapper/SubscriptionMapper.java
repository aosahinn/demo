package com.preschool.demo.controller.mapper;

import com.preschool.demo.controller.base.Converter;
import com.preschool.demo.controller.dto.SubscriptionDto;
import com.preschool.demo.controller.resource.SubscriptionResource;
import com.preschool.demo.data.entity.subscription.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper extends Converter<SubscriptionDto, Subscription, SubscriptionResource> {

}
