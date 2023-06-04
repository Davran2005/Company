package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.LessonService;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonApi {
    private final LessonService lessonService;
    @GetMapping
    public List<LessonResponse> getAllLesson() {
        return lessonService.getAllLessons();
    }

    @PostMapping("/{courseId}")
    public LessonResponse saveCourse(@PathVariable Long courseId, @RequestBody LessonRequest lessonRequest) {
        return lessonService.save(courseId, lessonRequest);
    }

    @GetMapping("/{lessonId}")
    public LessonResponse getById(@PathVariable Long lessonId) {
        return lessonService.getLessonById(lessonId);
    }

    @PutMapping("/{id}")
    public LessonResponse updateCourse(@PathVariable Long id, @RequestBody LessonRequest lessonRequest) {
        return lessonService.updateLesson(id, lessonRequest);
    }

    @DeleteMapping("/{lessonId}")
    public SimpleResponse deleteById(@PathVariable Long lessonId) {
        return lessonService.deleteLesson(lessonId);
    }
}
