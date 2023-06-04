package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyInfoResponse;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entyti.Company;
import peaksoft.repository.CompanyRep;
import peaksoft.service.CompanyService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRep companyRep;

    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRep.save(company);
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCountry(),
                company.getAddress(),
                company.getPhoneNumber());
    }

    @Override
    public List<CompanyResponse> getAllCompanies() {
        return companyRep.getAllCompany();
    }

    @Override
    public CompanyResponse getCompanyById(Long companyId) {
        return companyRep.getCompanyById(companyId).
                orElseThrow(() ->
                        new NoSuchElementException("Company with id: " + companyId + "is not found!"));
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, CompanyRequest companyRequest) {
        Company company = companyRep.findById(companyId).orElseThrow(() ->
                new NoSuchElementException("Company with id: " + companyId + " is no exists"));
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRep.save(company);
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCountry(),
                company.getAddress(),
                company.getPhoneNumber());
    }

    @Override
    public SimpleResponse deleteCompanyById(Long companyId) {
        companyRep.deleteById(companyId);
        return new SimpleResponse("delete", "Успешно");
    }
    @Override
    public CompanyInfoResponse getInfo(Long id) {
        Company company = companyRep.findById(id).orElseThrow(() ->
                new NoSuchElementException("Company with id: " + id + " is no exists"));

        return CompanyInfoResponse.builder().id(company.getId())
                .name(company.getName())
                .country(company.getCountry())
                .address(company.getAddress())
                .phoneNumber(company.getPhoneNumber())
                .courseName(companyRep.getAllCourseName(id))
                .groupName(companyRep.getAllGroupName(id))
                .instructorName(companyRep.getAllInstructorName(id))
                .studentSize(companyRep.studentCount(id)).build();
    }
}
