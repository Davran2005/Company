package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entyti.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRep extends JpaRepository<Course, Long> {
    @Query("select new peaksoft.dto.response.CourseResponse(c.id,c.courseName,c.description) from Course c")
    List<CourseResponse> getAllCourse();
    Optional<CourseResponse> getCourseById(Long courseId);
}
