package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.LessonResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entyti.*;
import peaksoft.repository.CompanyRep;
import peaksoft.repository.CourseRep;
import peaksoft.repository.LessonRep;
import peaksoft.service.LessonService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRep lessonRep;
    private final CourseRep courseRep;


    @Override
    public LessonResponse save(Long courseId, LessonRequest lessonRequest) {
        Course course = courseRep.findById(courseId).orElseThrow(() -> new NullPointerException("Course with id: " + courseId + "not found"));
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setLessonTime(lessonRequest.getLessonTime());
        course.setLesson(lesson);
        lessonRep.save(lesson);
        return new LessonResponse(lesson.getId(), lesson.getLessonName(), lesson.getLessonTime());
    }

    @Override
    public List<LessonResponse> getAllLessons() {
        return lessonRep.getAllLesson();
    }

    @Override
    public LessonResponse getLessonById(Long lessonId) {
        return lessonRep.getLessonById(lessonId).orElseThrow(() -> new NullPointerException("Lesson with id: " + lessonId + "not found"));
    }

    @Override
    public LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest) {
        Lesson lesson = lessonRep.findById(lessonId).orElseThrow(() -> new NullPointerException("Lesson with id: " + lessonId + "not found"));
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setLessonTime(lessonRequest.getLessonTime());
        lessonRep.save(lesson);
        return new LessonResponse(lesson.getId(), lesson.getLessonName(), lesson.getLessonTime());
    }
    @Override
    public SimpleResponse deleteLesson(Long lessonId) {
        Lesson lesson = lessonRep.findById(lessonId).orElseThrow(() -> new NullPointerException("Lesson with id: " + lessonId + "not found"));
        List<Course> courses = lesson.getCourses();
        for (Course c:courses) {
            c.setLesson(null);
        }
        lesson.getCourses().clear();
        lessonRep.delete(lesson);

        return SimpleResponse.builder().status("OK").message("Успешно").build();
    }
}
