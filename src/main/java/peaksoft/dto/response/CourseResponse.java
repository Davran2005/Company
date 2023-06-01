package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Setter
@Getter
public class CourseResponse {
    private Long id;
    private String courseName;
    private String description;

    public CourseResponse(Long id, String courseName, String description) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
    }
}
