package com.hritik.companyms.company.implement;

import com.hritik.companyms.company.Company;
import com.hritik.companyms.company.CompanyRepository;
import com.hritik.companyms.company.CompanyService;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public boolean updateCompany(Company company, Long Id){
        Optional<Company> companyOptional = companyRepository.findById(Id);

        if(companyOptional.isPresent()){
            Company comp = companyOptional.get();
            
            comp.setName(company.getName());
            comp.setDescription(company.getDescription());
            // comp.setJobs(company.getJobs());
            companyRepository.save(comp);
            return true;
        }
        return false;
    }

    public void createCompany(Company company){
        companyRepository.save(company);
    }

    public boolean deleteCompany(Long Id){
        if(companyRepository.existsById(Id)){
            companyRepository.deleteById(Id);
            return true;
        }
        return false;
    }

    public Company getCompanyById(Long Id){
        return companyRepository.findById(Id).orElse(null);
    }
}
