package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entyti.Group;
import peaksoft.entyti.Student;
@Repository
public interface StudentRep extends JpaRepository<Student, Long> {
}
