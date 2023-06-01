package peaksoft.service;

import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.simple.SimpleResponse;

import java.util.List;

public interface CourseService {
    SimpleResponse saveCourse(Long companyId,CourseRequest courseRequest);
    List<CourseResponse> getAllCourses();

    CourseResponse getCourseByById(Long courseId);

    SimpleResponse updateCourse(Long courseId, CourseRequest courseRequest);

    SimpleResponse deleteCourseById(Long courseId);
}
