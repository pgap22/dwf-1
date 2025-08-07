package sv.edu.udb.limpia.application.usecase;

import sv.edu.udb.limpia.application.port.out.TaskRepositoryPort;
import sv.edu.udb.limpia.domain.model.Task;
import sv.edu.udb.limpia.infrastructure.exception.ResourceNotFoundException; // ¡Importante!

import java.util.List;
import java.util.Map;

public class TaskUseCaseImpl implements TaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Task createTask(Task task) {
        task.setCompleted(false);
        return taskRepositoryPort.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        // La lógica clave está aquí. Si findById devuelve un Optional vacío,
        // orElseThrow se activa y lanza nuestra excepción personalizada.
        return taskRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con el id: " + id));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepositoryPort.findAll();
    }

    @Override
    public Task updateTask(Long id, Task taskToUpdate) {
        // 1. Primero, obtenemos la tarea existente. Si no existe, getTaskById ya
        // lanzará la excepción.
        Task existingTask = getTaskById(id);

        // 2. Actualizamos los campos del objeto que ya está en memoria.
        existingTask.setTitle(taskToUpdate.getTitle());
        existingTask.setDescription(taskToUpdate.getDescription());
        existingTask.setCompleted(taskToUpdate.isCompleted());

        // 3. Guardamos la entidad actualizada.
        return taskRepositoryPort.save(existingTask);
    }

    @Override
    public Task patchTask(Long id, Map<String, Object> updates) {
        // Obtenemos la tarea existente. Si no se encuentra, la excepción se lanza aquí.
        Task existingTask = getTaskById(id);

        // Aplicamos las actualizaciones parciales.
        if (updates.containsKey("title")) {
            existingTask.setTitle((String) updates.get("title"));
        }
        if (updates.containsKey("description")) {
            existingTask.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("completed")) {
            existingTask.setCompleted((Boolean) updates.get("completed"));
        }

        // Guardamos y devolvemos la tarea actualizada.
        return taskRepositoryPort.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) {
        // Nos aseguramos de que la tarea exista antes de intentar borrarla.
        // Si no existe, getTaskById lanzará la excepción y la ejecución se detendrá.
        getTaskById(id);
        taskRepositoryPort.deleteById(id);
    }
}