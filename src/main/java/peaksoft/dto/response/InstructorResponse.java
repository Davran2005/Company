package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class InstructorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;

    public InstructorResponse(Long id, String firstName, String lastName, String phoneNumber, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}
