package peaksoft.dto.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class CompanyInfoResponse {
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    private List<String> courseName;
    private List<String> groupName;
    private List<String> instructorName;
    private int studentSize;

    public CompanyInfoResponse(Long id, String name, String country, String address, String phoneNumber, List<String> courseName, List<String> groupName, List<String> instructorName, int studentSize) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.courseName = courseName;
        this.groupName = groupName;
        this.instructorName = instructorName;
        this.studentSize = studentSize;
    }
}
