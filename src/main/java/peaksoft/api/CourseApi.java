package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CompanyResponse;
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
    public List<CourseResponse> getAll() {
        return courseService.getAllCourses();
    }
    @PostMapping("/{companyId}")
    public SimpleResponse saveCourse(@PathVariable Long companyId, @RequestBody CourseRequest courseRequest){
        return courseService.saveCourse(companyId,courseRequest);
    }
    @GetMapping("/{courseId}")
    public CourseResponse getById(@PathVariable Long courseId) {
        return courseService.getCourseByById(courseId);
    }

    @PutMapping("/{id}")
    public SimpleResponse updateCourse(@PathVariable Long id,@RequestBody CourseRequest courseRequest){
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("/{companyId}")
    public SimpleResponse deleteById(@PathVariable Long companyId) {
        return courseService.deleteCourseById(companyId);
    }
}
