package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorDetailsResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.StudentCounterResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entyti.Company;
import peaksoft.entyti.Course;
import peaksoft.entyti.Instructor;
import peaksoft.repository.CompanyRep;
import peaksoft.repository.CourseRep;
import peaksoft.repository.InstructorRep;
import peaksoft.service.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRep instructorRep;
    private final CompanyRep companyRep;
    private final CourseRep courseRep;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public InstructorResponse saveInstructor(Long courseId, InstructorRequest instructorRequest) {
        Course course = courseRep.findById(courseId).orElseThrow(() -> new NullPointerException("Course with id: " + courseId + "is not found"));
        Instructor instructors = new Instructor();
        instructors.setFirstName(instructorRequest.getFirstName());
        instructors.setLastName(instructorRequest.getLastName());
        instructors.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructors.setSpecialization(instructorRequest.getSpecialization());
        course.setInstructor(instructors);
        instructors.setCourses(List.of(course));
        instructorRep.save(instructors);
        return new InstructorResponse(
                instructors.getId(),
                instructors.getFirstName(),
                instructors.getLastName(),
                instructors.getPhoneNumber(),
                instructors.getSpecialization());
    }

    @Override
    public List<InstructorResponse> getAllInstructors() {
        return instructorRep.getAllInstructor();
    }

    @Override
    public InstructorResponse getInstructorById(Long instructorId) {
        return instructorRep.getInstructorById(instructorId).orElseThrow(() -> new NullPointerException("Instructor with id: " + instructorId + "not found"));
    }

    @Override
    public InstructorResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest) {
        Instructor instructors = instructorRep.findById(instructorId).orElseThrow(() -> new NullPointerException("Instructor with id: " + instructorId + "not found"));
        instructors.setFirstName(instructorRequest.getFirstName());
        instructors.setLastName(instructorRequest.getLastName());
        instructors.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructors.setSpecialization(instructorRequest.getSpecialization());
        instructorRep.save(instructors);
        return new InstructorResponse(
                instructors.getId(),
                instructors.getFirstName(),
                instructors.getLastName(),
                instructors.getPhoneNumber(),
                instructors.getSpecialization());
    }


    @Override
    public SimpleResponse deleteInstructor(Long instructorId) {
        Instructor instructors = instructorRep.findById(instructorId).orElseThrow(() -> new NullPointerException("Group with id: " + instructorId + "not found"));
        List<Company> companies = instructors.getCompanies();
        for (Company c : companies) {
            c.getInstructors().remove(instructors);
        }
        instructors.getCompanies().clear();
        instructorRep.delete(instructors);
        return SimpleResponse.builder().status("OK").message("Успешно").build();
    }

    @Override
    public SimpleResponse assignInstructorToCompany(Long instructorId, Long companyId) {
        Instructor instructor = instructorRep.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor with id " + instructorId + " is not found!"));
        Company company = companyRep.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id " + companyId + " is not found!"));
        company.getInstructors().add(instructor);
        instructor.getCompanies().add(company);
        instructorRep.save(instructor);
        companyRep.save(company);
        return SimpleResponse
                .builder()
                .status("OK")
                .message("Успешно")
                .build();
    }

    @Override
    public StudentCounterResponse get(Long id) {
        return StudentCounterResponse.builder()
                .studentCount(instructorRep.getAllCount(id))
                .build();
    }

    @Override
    public InstructorDetailsResponse infoInstructor(Long instructorId) {
        InstructorResponse i = instructorRep.getInstructorById(instructorId).orElseThrow(() -> new NullPointerException("Instructor with id: " + instructorId + "is not found!"));
        return InstructorDetailsResponse.builder()
                .id(i.getId())
                .firstName(i.getFirstName())
                .lastName(i.getLastName())
                .specialization(i.getSpecialization())
                .phoneNumber(i.getPhoneNumber())
                .groupName(instructorRep.getAllInstructorsDetails(instructorId))
                .studentCount(instructorRep.getAllStudentCount(instructorId)).build();

    }
}