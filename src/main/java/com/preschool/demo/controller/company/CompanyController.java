package com.preschool.demo.controller.company;

import com.preschool.demo.controller.base.AbstractController;
import com.preschool.demo.controller.base.Converter;
import com.preschool.demo.controller.dto.CompanyDto;
import com.preschool.demo.controller.mapper.CompanyMapper;
import com.preschool.demo.controller.resource.CompanyResource;
import com.preschool.demo.controller.resource.UserResource;
import com.preschool.demo.data.entity.company.Company;
import com.preschool.demo.data.entity.user.User;
import com.preschool.demo.service.AbstractEntityService;
import com.preschool.demo.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@PreAuthorize("hasAnyAuthority('" + AuthorityType.Names.MNG_POST + "')")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/company"})
public class CompanyController extends AbstractController<CompanyDto, Company, CompanyResource, String> {

    private final CompanyMapper mapper;
    private final CompanyService service;

    protected AbstractEntityService<Company, String> getService() {
        return service;
    }

    @Override
    protected Converter<CompanyDto, Company, CompanyResource> getConverter() {
        return mapper;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        getService().delete(id);
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping
    public CompanyResource save(@RequestBody CompanyDto dto) {
        Company entity = service.save(getConverter().toEntity(dto));
        return toResource(entity);
    }

    @PutMapping("/{id}")
    public CompanyResource put(@PathVariable("id") String id, @RequestBody CompanyDto dto) {
        Company forSave = getConverter().toEntity(dto);
        Company entity = service.put(id, forSave);
        return toResource(entity);
    }

    @GetMapping
    public CompanyResource get(@RequestParam String id) {
        Company entity = service.findById(id);
        return toResource(entity);
    }

}
