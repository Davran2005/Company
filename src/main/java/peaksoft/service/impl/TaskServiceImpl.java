package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.TaskResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entyti.Course;
import peaksoft.entyti.Lesson;
import peaksoft.entyti.Task;
import peaksoft.repository.LessonRep;
import peaksoft.repository.TaskRep;
import peaksoft.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final LessonRep lessonRep;
    private final TaskRep taskRep;

    @Override
    public TaskResponse save(Long lessonId, TaskRequest taskRequest) {
        Lesson lesson = lessonRep.findById(lessonId).orElseThrow(() -> new NullPointerException("Lesson with id: " + lessonId + "is not found!"));
        Task task1 = new Task();
        task1.setTaskName(taskRequest.getTaskName());
        task1.setTaskText(taskRequest.getTaskText());
        task1.setDeadLine(taskRequest.getDeadLine());
        lesson.getTasks().add(task1);
        task1.setLesson(lesson);
        taskRep.save(task1);
        return new TaskResponse(task1.getId(),
                task1.getTaskName(),
                task1.getTaskText(),
                task1.getDeadLine());
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRep.getAllTask();
    }

    @Override
    public TaskResponse getTaskById(Long taskId) {
        Task task = taskRep.findById(taskId).orElseThrow(() -> new NullPointerException("Task with id: " + taskId + "is not found!"));
        return new TaskResponse(task.getId(),
                task.getTaskName(),
                task.getTaskText(),
                task.getDeadLine());
    }

    @Override
    public TaskResponse updateTask(Long taskId, TaskRequest taskRequest) {
        Task task1 = taskRep.findById(taskId).orElseThrow(() -> new NullPointerException("Task with id: " + taskId + "is not found!"));
        task1.setTaskName(taskRequest.getTaskName());
        task1.setTaskText(taskRequest.getTaskText());
        task1.setDeadLine(taskRequest.getDeadLine());
        taskRep.save(task1);
        return new TaskResponse(task1.getId(),
                task1.getTaskName(),
                task1.getTaskText(),
                task1.getDeadLine());
    }

    @Override
    public SimpleResponse deleteTask(Long taskId) {
        Task task1 = taskRep.findById(taskId).orElseThrow(() -> new NullPointerException("Task with id: " + taskId + "is not found!"));
        taskRep.delete(task1);
        return SimpleResponse.builder().status("OK").message("Успешно").build();
    }
}
