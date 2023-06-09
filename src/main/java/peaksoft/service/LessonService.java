package peaksoft.service;

import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.simple.SimpleResponse;

import java.util.List;

public interface LessonService {
    LessonResponse save(Long courseId,LessonRequest lessonRequest);
    List<LessonResponse> getAllLessons();
    LessonResponse getLessonById(Long lessonId);
    LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest);
    SimpleResponse deleteLesson(Long lessonId);
}
