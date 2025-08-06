package sv.edu.udb.monolitico.controllers;

import sv.edu.udb.monolitico.dto.CreateTaskDTO;
import sv.edu.udb.monolitico.model.Task;
import sv.edu.udb.monolitico.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController // esta clase devolverá JSON
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Endpoint para CREAR una tarea
    @PostMapping
    public Task createTask(@Valid @RequestBody CreateTaskDTO task) {
        return taskService.createTask(task);
    }

    // Endpoint para OBTENER TODAS las tareas
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Endpoint para OBTENER UNA tarea por ID
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // Endpoint para ACTUALIZAR una tarea
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return taskService.updateTask(id, taskDetails);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> patchTask(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Task updatedTask = taskService.patchTask(id, updates);
        return ResponseEntity.ok(updatedTask);
    }

    // Endpoint para BORRAR una tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build(); // 204 No Content, que significa "OK, lo borre".
    }
}