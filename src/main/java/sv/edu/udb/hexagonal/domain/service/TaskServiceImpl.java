package sv.edu.udb.hexagonal.domain.service;

import sv.edu.udb.hexagonal.domain.model.Task;
import sv.edu.udb.hexagonal.domain.port.in.TaskUseCase;
import sv.edu.udb.hexagonal.domain.port.out.TaskRepositoryPort;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class TaskServiceImpl implements TaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskServiceImpl(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Task createTask(Task task) {
        task.setCompleted(false);
        return taskRepositoryPort.save(task);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepositoryPort.findById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepositoryPort.findAll();
    }

    @Override
    public Optional<Task> updateTask(Long id, Task task) {
        task.setId(id);
        return taskRepositoryPort.update(task);
    }
    
    @Override
    public Optional<Task> patchTask(Long id, Map<String, Object> updates) {
        return taskRepositoryPort.findById(id).map(existingTask -> {
            if (updates.containsKey("title")) {
                existingTask.setTitle((String) updates.get("title"));
            }
            if (updates.containsKey("description")) {
                existingTask.setDescription((String) updates.get("description"));
            }
            if (updates.containsKey("completed")) {
                existingTask.setCompleted((Boolean) updates.get("completed"));
            }
            // Guardamos la tarea actualizada a través del puerto.
            return taskRepositoryPort.save(existingTask);
        });
    }

    @Override
    public void deleteTask(Long id) {
        taskRepositoryPort.deleteById(id);
    }
}