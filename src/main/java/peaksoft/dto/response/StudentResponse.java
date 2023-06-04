package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.StudyFormat;

@Builder
@Setter
@Getter
public class StudentResponse {
    private Long id;
    private String firsName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private StudyFormat studyFormat;
    private Boolean isBlocked;

    public StudentResponse(Long id, String firsName, String lastName, String phoneNumber, String email, StudyFormat studyFormat, Boolean isBlocked) {
        this.id = id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
        this.isBlocked = isBlocked;
    }
}
