package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entyti.Group;
import peaksoft.entyti.Lesson;
@Repository
public interface LessonRep extends JpaRepository<Lesson, Long> {
}
