package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LessonResponse {
    private Long id;
    private String lessonName;
    private String lessonTime;

    public LessonResponse(Long id, String lessonName, String lessonTime) {
        this.id = id;
        this.lessonName = lessonName;
        this.lessonTime = lessonTime;
    }
}
