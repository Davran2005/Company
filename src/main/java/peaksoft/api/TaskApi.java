package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.TaskResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskApi {
    private final TaskService taskService;

    @GetMapping
    public List<TaskResponse> getAllTask() {
        return taskService.getAllTasks();
    }

    @PostMapping("/{lessonId}")
    public TaskResponse saveTask(@PathVariable Long lessonId, @RequestBody TaskRequest taskRequest) {
        return taskService.save(lessonId, taskRequest);
    }

    @GetMapping("/{taskId}")
    public TaskResponse getById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }

    @DeleteMapping("/{taskId}")
    public SimpleResponse deleteById(@PathVariable Long taskId) {
        return taskService.deleteTask(taskId);
    }

}
