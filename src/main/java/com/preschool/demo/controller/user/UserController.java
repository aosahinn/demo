package com.preschool.demo.controller.user;

import com.preschool.demo.controller.base.AbstractController;
import com.preschool.demo.controller.base.Converter;
import com.preschool.demo.controller.dto.UserDto;
import com.preschool.demo.controller.mapper.UserMapper;
import com.preschool.demo.controller.resource.UserResource;
import com.preschool.demo.data.entity.user.User;
import com.preschool.demo.service.AbstractEntityService;
import com.preschool.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@PreAuthorize("hasAnyAuthority('" + AuthorityType.Names.MNG_POST + "')")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/users"})
public class UserController extends AbstractController<UserDto, User, UserResource, String> {

    private final UserMapper mapper;
    private final UserService service;

    protected AbstractEntityService<User, String> getService() {
        return service;
    }

    @Override
    protected Converter<UserDto, User, UserResource> getConverter() {
        return mapper;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        getService().delete(id);
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping
    public UserResource save(@RequestBody UserDto dto) {
        User entity = service.save(getConverter().toEntity(dto));
        return toResource(entity);
    }

    @PutMapping("/{id}")
    public UserResource put(@PathVariable("id") String id, @RequestBody UserDto dto) {
        User forSave = getConverter().toEntity(dto);
        User entity = service.put(id, forSave);
        return toResource(entity);
    }

    @GetMapping
    public UserResource get(@RequestParam String id) {
        User entity = service.findById(id);
        return toResource(entity);
    }

}
