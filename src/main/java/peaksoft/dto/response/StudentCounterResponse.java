package peaksoft.dto.response;

import lombok.*;
import peaksoft.entyti.Student;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCounterResponse {
    private Integer studentCount;

}
