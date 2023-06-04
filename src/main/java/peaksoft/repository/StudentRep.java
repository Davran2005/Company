package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import peaksoft.dto.response.StudentResponse;

import peaksoft.entyti.Student;
import peaksoft.enums.StudyFormat;

import java.util.List;
import java.util.Optional;


public interface StudentRep extends JpaRepository<Student, Long> {
    @Query("select new peaksoft.dto.response.StudentResponse(s.id,s.firstName,s.lastName,s.phoneNumber,s.email,s.studyFormat,s.isBlocked) from Student s")
    List<StudentResponse> getAllStudent();

//    Optional<StudentResponse>getStudentById(Long id);
@Query("SELECT new peaksoft.dto.response.StudentResponse(s.id, s.firstName, s.lastName, s.email, s.phoneNumber, s.studyFormat, s.isBlocked) FROM Student s WHERE s.studyFormat='ONLINE'")
    List<StudentResponse> getFiltrOnlaine();
    @Query("SELECT new peaksoft.dto.response.StudentResponse(s.id, s.firstName, s.lastName, s.email, s.phoneNumber, s.studyFormat, s.isBlocked) FROM Student s WHERE s.studyFormat='OFFLINE'")
    List<StudentResponse> getFiltrOFFLAINE();
}
