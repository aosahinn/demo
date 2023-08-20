package com.preschool.demo.controller.mapper;

import com.preschool.demo.controller.base.Converter;
import com.preschool.demo.controller.dto.UserDto;
import com.preschool.demo.controller.resource.UserResource;
import com.preschool.demo.data.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Converter<UserDto, User, UserResource> {

}
