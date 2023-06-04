package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.InstructorDetailsResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.entyti.Instructor;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRep extends JpaRepository<Instructor, Long> {
    @Query("select new peaksoft.dto.response.InstructorResponse(i.id,i.firstName,i.lastName,i.phoneNumber,i.specialization) from Instructor i")
    List<InstructorResponse> getAllInstructor();
    Optional<InstructorResponse> getInstructorById(Long instructorId);
    @Query("select (count (s)) FROM Instructor i JOIN i.courses c JOIN c.groups g JOIN g.students s WHERE i.id = :instructorId")
    int getAllCount(@Param("instructorId") Long instructorId);
    @Query("select cast( count(s) as int ) from Instructor i join i.courses c join  c.groups g join g.students s where i.id=:instructorId")
    int getAllStudentCount(Long instructorId);
    @Query("select g.groupName from Group g join g.courses c join c.instructor i where i.id = ?1")
    List<String> getAllInstructorsDetails(Long id);


}
