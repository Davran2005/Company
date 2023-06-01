package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entyti.Instructor;

@Repository
public interface InstructorRep extends JpaRepository<Instructor, Long> {
}
