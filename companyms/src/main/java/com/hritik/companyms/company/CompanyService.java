package com.hritik.companyms.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long Id);
    void createCompany(Company company);
    boolean deleteCompany(Long Id);
    Company getCompanyById(Long Id);
} 
