package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;
    @GetMapping
    public List<CourseResponse> getAllCourse() {
        return courseService.getAllCourses();
    }
    @PostMapping("/{companyId}")
    public CourseResponse saveCourse(@PathVariable Long companyId, @RequestBody CourseRequest courseRequest){
        return courseService.saveCourse(companyId,courseRequest);
    }
    @GetMapping("/{courseId}")
    public CourseResponse getById(@PathVariable Long courseId) {
        return courseService.getCourseByById(courseId);
    }

    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id,@RequestBody CourseRequest courseRequest){
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("/{courseId}")
    public SimpleResponse deleteById(@PathVariable Long courseId) {
        return courseService.deleteCourseById(courseId);
    }
    @GetMapping("/sort")
    public Object sort(@RequestBody String ascOrDesc , @RequestBody CourseRequest courseRequest){
        return courseService.getSort(ascOrDesc);
    }
}
