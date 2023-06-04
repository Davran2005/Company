package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.StudentCounterResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupApi {
    private final GroupService groupService;
    @GetMapping
    public List<GroupResponse> getAllGroup() {
        return groupService.getAllGroups();
    }

    @PostMapping("/{courseId}")
    public GroupResponse saveGroup(@PathVariable Long courseId, @RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(courseId, groupRequest);
    }

    @GetMapping("/{groupId}")
    public GroupResponse getById(@PathVariable Long groupId) {
        return groupService.getGroupById(groupId);
    }

    @PutMapping("/{id}")
    public GroupResponse updateGroup(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return groupService.updateGroup(id, groupRequest);
    }

    @DeleteMapping("/{groupId}")
    public SimpleResponse deleteById(@PathVariable Long groupId) {
        return groupService.deleteGroup(groupId);
    }
    @GetMapping("/count/{id}")
    public StudentCounterResponse count(@PathVariable Long id){
        return groupService.get(id);
    }
}
