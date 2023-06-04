package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.StudentResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entyti.Group;
import peaksoft.entyti.Student;
import peaksoft.enums.StudyFormat;
import peaksoft.repository.GroupRep;
import peaksoft.repository.StudentRep;
import peaksoft.service.StudentService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRep studentRep;
    private final GroupRep groupRep;

    @Override
    public StudentResponse save(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirsName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
        studentRep.save(student);
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getIsBlocked());
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRep.getAllStudent();
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        Student student = studentRep.findById(studentId)
                .orElseThrow(() -> new NullPointerException("Student with id: " + studentId + "is not found!"));
        return new StudentResponse(student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getIsBlocked());
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        Student student = studentRep.findById(studentId
        ).orElseThrow(() -> new NullPointerException("Student with id: " + studentId + "is not found!"));
        student.setFirstName(studentRequest.getFirsName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());
        studentRep.save(student);
        return new StudentResponse(student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getStudyFormat(),
                student.getIsBlocked()
        );
    }

    @Override
    public SimpleResponse deleteStudent(Long studentId) {
        Student student = studentRep.findById(studentId)
                .orElseThrow(() -> new NullPointerException("Student with id: " + studentId + "is not found!"));
        student.setGroup(null);
        studentRep.delete(student);
        return new SimpleResponse("delete", "Успешно");
    }

    @Override
    public SimpleResponse assignStudentToGroup(Long studentId, Long groupId) {
        Group group = groupRep.findById(groupId)
                .orElseThrow(() -> new NullPointerException("Group with id: " + groupId + "is not found!"));
        Student student = studentRep.findById(studentId)
                .orElseThrow(() -> new NullPointerException("Student with id: " + studentId + "is not found!"));
        group.getStudents().add(student);
        student.setGroup(group);
        studentRep.save(student);
        groupRep.save(group);
        return  new SimpleResponse("OK", "Успешно");
    }

    @Override
    public SimpleResponse blockUnBlockStudent(Long studentId, Boolean block) {
        Student student = studentRep.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student with id:" + studentId + "is not found"));
        student.setIsBlocked(block);
        studentRep.save(student);
        return  new SimpleResponse("OK", "Успешно");
    }

    @Override
    public List<StudentResponse> getFiltr(String studyFormat) {
        Student student = new Student();
        if (studyFormat.equals("ONLINE")) return studentRep.getFiltrOnlaine();
        else if (studyFormat.equals("OFFLINE")) return studentRep.getFiltrOFFLAINE();

        return Collections.singletonList(StudentResponse.builder()
                .id(student.getId())
                .firsName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .isBlocked(student.getIsBlocked())
                .studyFormat(student.getStudyFormat())
                .build());
    }
}
