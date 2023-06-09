package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.SortResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entyti.Company;
import peaksoft.entyti.Course;
import peaksoft.repository.CompanyRep;
import peaksoft.repository.CourseRep;
import peaksoft.service.CourseService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRep courseRep;
    private final CompanyRep companyRep;

    @Override
    public CourseResponse saveCourse(Long companyId, CourseRequest courseRequest) {
        Company company = companyRep.findById(companyId).orElseThrow(() -> new NullPointerException("Company with id: " + companyId + "not found"));
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStar(LocalDate.now());
        course.setDescription(courseRequest.getDescription());
        company.getCourses().add(course);
        course.setCompany(company);
        courseRep.save(course);
        return new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDescription()
                );

    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRep.getAllCourse();
    }

    @Override
    public CourseResponse getCourseByById(Long courseId) {
        return courseRep.getCourseById(courseId).
                orElseThrow(() ->
                        new NoSuchElementException("Company with id: " + courseId + "is not found!"));
    }

    @Override
    public CourseResponse updateCourse(Long courseId, CourseRequest courseRequest) {
        Course course = courseRep.findById(courseId).orElseThrow(() -> new RuntimeException("Course with id: " + courseId + "not found!"));
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        courseRep.save(course);
        return  new CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getDescription()
        );
    }

    @Override
    public SimpleResponse deleteCourseById(Long courseId) {
        Course course = courseRep.findById(courseId).orElseThrow(() -> new RuntimeException("Course with id: " + courseId + "not found!"));
        course.setCompany(null);
        courseRep.delete(course);
        return SimpleResponse.builder().status("OK").message("Успешно").build();
    }

    @Override
    public Object getSort(String askOrDesc) {
        Course course = new Course();
        List<String> allSortAsk;
        if (askOrDesc.equalsIgnoreCase("asc")){
            allSortAsk = courseRep.getAllSortAsk(askOrDesc);
        }else if(askOrDesc.equalsIgnoreCase("desc")) {
            allSortAsk = courseRep.getAllSortDesc();
        }else {
            return SimpleResponse.builder()
                    .status("BAD_REQUEST")
                    .message("Parameter must accept ASC or DESC")
                    .build();
        }
        return SortResponse.builder().id(course.getId()).list(allSortAsk).build();
    }
}
