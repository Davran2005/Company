package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entyti.Group;
import peaksoft.entyti.Task;
@Repository
public interface TaskRep extends JpaRepository<Task, Long> {

}
