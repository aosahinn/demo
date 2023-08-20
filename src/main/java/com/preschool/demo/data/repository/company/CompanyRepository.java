package com.preschool.demo.data.repository.company;

import com.preschool.demo.data.entity.company.Company;
import com.preschool.demo.data.repository.BaseRepository;
import com.preschool.demo.utils.enums.CompanyType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends BaseRepository<Company, String> {

    List<Company> findByType(CompanyType companyId);
}
