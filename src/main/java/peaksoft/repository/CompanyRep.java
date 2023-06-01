package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entyti.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRep extends JpaRepository<Company, Long> {
    @Query("select new peaksoft.dto.response.CourseResponse(c.id,c.name,c.country,c.address,c.phoneNumber) from Company c")
    List<CompanyResponse> getAllCompany();

    Optional<CompanyResponse> getCompanyById(Long id);

}
