package sv.edu.udb.hexagonal.domain.port.in;

import sv.edu.udb.hexagonal.domain.model.Task;
import java.util.List;
import java.util.Optional;
import java.util.Map;

public interface TaskUseCase {

    Task createTask(Task task);
    Optional<Task> getTaskById(Long id);
    List<Task> getAllTasks();
    Optional<Task> updateTask(Long id, Task task);
    Optional<Task> patchTask(Long id, Map<String, Object> updates);
    void deleteTask(Long id);
}