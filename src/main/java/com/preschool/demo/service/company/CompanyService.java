package com.preschool.demo.service.company;

import com.preschool.demo.common.exceptions.CompanyNotFoundException;
import com.preschool.demo.common.exceptions.UserNotFoundException;
import com.preschool.demo.controller.mapper.CompanyMapper;
import com.preschool.demo.data.entity.company.Company;
import com.preschool.demo.data.entity.user.User;
import com.preschool.demo.data.repository.BaseRepository;
import com.preschool.demo.data.repository.company.CompanyRepository;
import com.preschool.demo.service.AbstractEntityService;
import com.preschool.demo.utils.enums.CompanyType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class CompanyService extends AbstractEntityService<Company, String> {

    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    @Override
    public BaseRepository<Company, String> getRepository() {
        return repository;
    }

    @Override
    protected Company verifySave(Company entity) {
        return super.verifySave(entity);
    }

    @Override
    protected Company verifyPut(Company theReal, Company forSave) {
        return super.verifyPut(theReal, forSave);
    }

    public Company findById(String id) {
        Optional<Company> company = repository.findById(id);
        return company.stream().findFirst().orElseThrow(() -> new CompanyNotFoundException("Company Bulunamadı."));
    }

    public List<Company> findByType(CompanyType companyType) {
        return repository.findByType(companyType);
    }


}
