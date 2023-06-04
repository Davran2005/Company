package peaksoft.service;

import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.TaskResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entyti.Task;

import java.util.List;

public interface TaskService {
    TaskResponse save(Long lessonId, TaskRequest taskRequest);
    List<TaskResponse> getAllTasks();
    TaskResponse getTaskById(Long taskId);
    TaskResponse updateTask(Long taskId,TaskRequest taskRequest);
    SimpleResponse deleteTask(Long taskId);
}
