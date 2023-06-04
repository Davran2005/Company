package peaksoft.service;

import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorDetailsResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.StudentCounterResponse;
import peaksoft.dto.response.simple.SimpleResponse;

import java.util.List;

public interface InstructorService {
    InstructorResponse saveInstructor(Long courseId,InstructorRequest instructorRequest);
    List<InstructorResponse> getAllInstructors();
    InstructorResponse getInstructorById(Long instructorId);
    InstructorResponse updateInstructor(Long instructorId,InstructorRequest instructorRequest);

    SimpleResponse deleteInstructor(Long instructorId);
    SimpleResponse assignInstructorToCompany(Long instructorId, Long companyId);
    StudentCounterResponse get(Long id);
    InstructorDetailsResponse infoInstructor(Long instructorId);
}

