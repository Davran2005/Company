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
    @Query("select new peaksoft.dto.response.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber) from Company c")
    List<CompanyResponse> getAllCompany();

    Optional<CompanyResponse> getCompanyById(Long id);
    @Query("select count(s.id) from Company c join c.courses cc join cc.groups g join g.students s where c.id=:companyId")
    int studentCount(@Param("companyId") Long companyId);
    @Query("select a.courseName from Company c join c.courses a where a.id = :companyId")
    List<String> getAllCourseName(Long companyId);
    @Query("select g.groupName from Company c join c.courses a join a.groups g where g.id = :companyId")
    List<String> getAllGroupName(Long companyId);
    @Query("select i.firstName from Company c join c.instructors i where i.id = :companyId")
    List<String> getAllInstructorName(Long companyId);

}
