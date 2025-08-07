package sv.edu.udb.limpia.application.usecase;

import sv.edu.udb.limpia.domain.model.Task;
import java.util.List;
import java.util.Map;

public interface TaskUseCase {

    Task createTask(Task task);

    Task getTaskById(Long id);

    List<Task> getAllTasks();

    Task updateTask(Long id, Task task);

    Task patchTask(Long id, Map<String, Object> updates);

    void deleteTask(Long id);
}