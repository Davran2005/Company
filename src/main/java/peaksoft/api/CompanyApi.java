package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyApi {
    private final CompanyService companyService;

    @Autowired
    public CompanyApi(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> getAll() {
        return companyService.getAllCompanies();
    }

    @PostMapping
    public SimpleResponse saveCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.saveCompany(companyRequest);
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getById(@PathVariable Long companyId) {
        return companyService.getCompanyById(companyId);
    }

    @PutMapping("/{companyId}")
    public SimpleResponse updateCompany(@PathVariable Long companyId, @RequestBody CompanyRequest companyRequest) {
        return companyService.updateCompany(companyId, companyRequest);
    }

    @DeleteMapping("/{companyId}")
    public SimpleResponse deleteCompanyById(@PathVariable Long companyId) {
        return companyService.deleteCompanyById(companyId);
    }

}
