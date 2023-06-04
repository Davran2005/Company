package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.enums.StudyFormat;
import peaksoft.service.StudentService;

import java.util.List;
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentApi {
    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public StudentResponse saveStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    @GetMapping("/{studentId}")
    public StudentResponse getById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    public StudentResponse updateCompany(@PathVariable Long studentId, @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(studentId, studentRequest);
    }

    @DeleteMapping("/{studentId}")
    public SimpleResponse deleteCompanyById(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @PostMapping("/assignGroup/{studentId}/{groupId}")
    public SimpleResponse assignStudentAndGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        return studentService.assignStudentToGroup(studentId, groupId);
    }


    @PostMapping("/blocked/{studentId}")
    public SimpleResponse blockUnBlockStudent(@PathVariable Long studentId, @RequestParam Boolean block) {
        return studentService.blockUnBlockStudent(studentId, block);
    }

    @GetMapping("/filter/{studyFormat}")
    public List<StudentResponse> getFilteredStudentsByStudyFormat(@PathVariable String studyFormat) {
        return studentService.getFiltr(studyFormat);
    }
}
