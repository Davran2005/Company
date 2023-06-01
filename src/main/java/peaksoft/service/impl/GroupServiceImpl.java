package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entyti.Company;
import peaksoft.entyti.Course;
import peaksoft.entyti.Group;
import peaksoft.repository.CourseRep;
import peaksoft.repository.GroupRep;
import peaksoft.service.GroupService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRep groupRep;
    private final CourseRep courseRep;
    Course course = new Course();
//        course.setCourseName(courseRequest.getCourseName());
//        course.setDateOfStar(LocalDate.now());
//        course.setDescription(courseRequest.getDescription());
//        company.getCourses().add(course);
//        course.setCompany(company);
//        courseRep.save(course);
//        return SimpleResponse
//                .builder()
//                .status("OK")
//                .message("Save Course")
//                .build();

    @Override
    public SimpleResponse saveGroup(Long courseId, GroupRequest groupRequest) {
        Course course = courseRep.findById(courseId).orElseThrow(() -> new NullPointerException("Course with id: " + courseId + "not found"));
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setImageLink(groupRequest.getImageLink());
        group.setDescription(groupRequest.getDescription());
        group.getCourses().add(course);
        groupRep.save(group);
        return SimpleResponse
                .builder()
                .status("OK")
                .message("Save Course")
                .build();
    }

    @Override
    public List<GroupResponse> getAllGroups() {
        return null;
    }

    @Override
    public GroupResponse getGroupById(Long groupId) {
        return null;
    }

    @Override
    public SimpleResponse updateGroup(Long groupId, GroupRequest groupRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteGroup(Long groupId) {
        return null;
    }
}
