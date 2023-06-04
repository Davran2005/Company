package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.StudentCounterResponse;
import peaksoft.entyti.Group;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRep extends JpaRepository<Group, Long> {
    @Query("select new peaksoft.dto.response.GroupResponse(g.id,g.groupName,g.imageLink,g.description) from Group g")
    List<GroupResponse> getAllGroup();

    Optional<GroupResponse> getGroupById(Long courseId);

    @Query("select (count (s))from Group g JOIN g.students s where g.id = :id")
    int get(Long id);
}


