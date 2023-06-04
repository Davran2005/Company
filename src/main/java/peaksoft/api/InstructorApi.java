package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorDetailsResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.StudentCounterResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorApi {
    private final InstructorService instructorService;

    @GetMapping
    public List<InstructorResponse> getAllInstructor() {
        return instructorService.getAllInstructors();
    }

    @PostMapping("/{courseId}")
    public InstructorResponse saveInstructor(@PathVariable Long courseId, @RequestBody InstructorRequest instructorRequest) {
        return instructorService.saveInstructor(courseId, instructorRequest);
    }

    @GetMapping("/{instructorId}")
    public InstructorResponse getById(@PathVariable Long instructorId) {
        return instructorService.getInstructorById(instructorId);
    }

    @PutMapping("/{id}")
    public InstructorResponse updateInstructor(@PathVariable Long id, @RequestBody InstructorRequest instructorRequest) {
        return instructorService.updateInstructor(id, instructorRequest);
    }


    @DeleteMapping("/{instructorId}")
    public SimpleResponse deleteById(@PathVariable Long instructorId) {
        return instructorService.deleteInstructor(instructorId);
    }

    @PostMapping("/assignCompany/{instructorId}/{companyId}")
    public SimpleResponse assignInstructorToCompany(@PathVariable Long instructorId, @PathVariable Long companyId) {
        return instructorService.assignInstructorToCompany(instructorId, companyId);
    }

    @GetMapping("/count/{id}")
    public StudentCounterResponse count(@PathVariable Long id) {
        return instructorService.get(id);
    }
    @GetMapping("/get/{id}")
    public InstructorDetailsResponse info(@PathVariable Long id) {
        return instructorService.infoInstructor(id);
    }

}