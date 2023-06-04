package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.LessonResponse;
import peaksoft.entyti.Group;
import peaksoft.entyti.Lesson;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRep extends JpaRepository<Lesson, Long> {
    @Query("select new peaksoft.dto.response.LessonResponse(l.id,l.lessonName,l.lessonTime) from Lesson l")
    List<LessonResponse> getAllLesson();
    Optional<LessonResponse> getLessonById(Long lessonId);
}
