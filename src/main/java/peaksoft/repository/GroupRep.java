package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.GroupResponse;
import peaksoft.entyti.Group;

import java.util.List;

@Repository
public interface GroupRep extends JpaRepository<Group, Long> {
    @Query("select new peaksoft.dto.response.GroupResponse(g.id,g.groupName,g.imageLink,g.description) from Group g")
    List<GroupResponse> getAllGroup();
}

