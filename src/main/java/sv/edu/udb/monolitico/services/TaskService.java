package sv.edu.udb.monolitico.services;

import sv.edu.udb.monolitico.dto.CreateTaskDTO;
import sv.edu.udb.monolitico.exception.ResourceNotFoundException;
import sv.edu.udb.monolitico.model.Task;
import sv.edu.udb.monolitico.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service // Marca esta clase como un servicio, lógica de negocio.
public class TaskService {

    @Autowired // Le pedimos a Spring que nos de una instancia lista para usar.
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con el id: " + id));

    }

    public Task createTask(CreateTaskDTO taskDTO) {
        Task newTask = new Task();

        newTask.setTitle(taskDTO.getTitle());
        newTask.setDescription(taskDTO.getDescription());
        
        return taskRepository.save(newTask);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());

        return taskRepository.save(task);
    }

    public Task patchTask(Long id, Map<String, Object> updates) {
        Task task = getTaskById(id);

        // Iteramos sobre las actualizaciones que nos llega.
        updates.forEach((key, value) -> {
            // Usamos una tecnica llamada "reflexion" para encontrar un campo en la clase
            // Task
            // que coincida con la clave del mapeo (ej. "title", "completed").
            Field field = ReflectionUtils.findField(Task.class, key);

            // Si encontramos el campo lo hacemos accesible.
            if (field != null) {
                field.setAccessible(true);
                // Le asignamos el nuevo valor al campo del objeto 'task'.
                ReflectionUtils.setField(field, task, value);
            }
        });

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar la tarea: " + id);
        }

        taskRepository.deleteById(id);
    }
}