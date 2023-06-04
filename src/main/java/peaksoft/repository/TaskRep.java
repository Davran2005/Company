package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.TaskResponse;
import peaksoft.entyti.Group;
import peaksoft.entyti.Task;

import java.util.List;

@Repository
public interface TaskRep extends JpaRepository<Task, Long> {
    @Query("select new peaksoft.dto.response.TaskResponse(t.id,t.taskName,t.taskText,t.deadLine) from Task t")
    List<TaskResponse> getAllTask();

}
