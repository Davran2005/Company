package peaksoft.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDetailsResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    private List<String>groupName;
    private int studentCount;

}
