package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Setter
@Getter
public class TaskResponse {
    private Long id;
    private String taskName;
    private String taskText;
    private LocalDate deadLine;

    public TaskResponse(Long id, String taskName, String taskText, LocalDate deadLine) {
        this.id = id;
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadLine = deadLine;
    }
}
