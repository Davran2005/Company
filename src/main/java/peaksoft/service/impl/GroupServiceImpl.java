package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.StudentCounterResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entyti.Company;
import peaksoft.entyti.Course;
import peaksoft.entyti.Group;
import peaksoft.repository.CourseRep;
import peaksoft.repository.GroupRep;
import peaksoft.service.GroupService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRep groupRep;
    private final CourseRep courseRep;

    @Override
    public GroupResponse saveGroup(Long courseId, GroupRequest groupRequest) {
        Course course = courseRep.findById(courseId).orElseThrow(() -> new NullPointerException("Course with id: " + courseId + "not found"));
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        course.getGroups().add(group);
        groupRep.save(group);
        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getImageLink(),
                group.getDescription());
    }

    @Override
    public List<GroupResponse> getAllGroups() {
        return groupRep.getAllGroup();
    }

    @Override
    public GroupResponse getGroupById(Long groupId) {
        return groupRep.getGroupById(groupId).orElseThrow(() -> new NullPointerException("Group with id: " + groupId + "not found"));
    }

    @Override
    public GroupResponse updateGroup(Long groupId, GroupRequest groupRequest) {
        Group group = groupRep.findById(groupId).orElseThrow(() -> new NullPointerException("Group with id: " + groupId + "not found"));
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        groupRep.save(group);
        return new GroupResponse(
                group.getId(),
                group.getGroupName(),
                group.getImageLink(),
                group.getDescription());

    }


    @Override
    public SimpleResponse deleteGroup(Long groupId) {
        Group group = groupRep.findById(groupId).orElseThrow(() -> new NullPointerException("Group with id: " + groupId + "not found"));
        List<Course> courses = group.getCourses();
        for (Course cours : courses) {
            cours.getGroups().remove(group);
        }
        group.getCourses().clear();
        groupRep.delete(group);
        return SimpleResponse.builder()
                .status("OK").message("Update Course").build();
    }

    @Override
    public StudentCounterResponse get(Long id) {
        return StudentCounterResponse.builder()
                .studentCount(groupRep.get(id))
                .build();
    }

}
