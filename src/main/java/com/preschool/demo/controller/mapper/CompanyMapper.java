package com.preschool.demo.controller.mapper;

import com.preschool.demo.controller.base.Converter;
import com.preschool.demo.controller.dto.CompanyDto;
import com.preschool.demo.controller.dto.SubscriptionDto;
import com.preschool.demo.controller.resource.CompanyResource;
import com.preschool.demo.controller.resource.SubscriptionResource;
import com.preschool.demo.data.entity.company.Company;
import com.preschool.demo.data.entity.subscription.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper extends Converter<CompanyDto, Company, CompanyResource> {

}
