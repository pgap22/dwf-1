package sv.edu.udb.hexagonal.infrastructure.adapter.out.persistence.mapper;

import sv.edu.udb.hexagonal.domain.model.Task;
import sv.edu.udb.hexagonal.infrastructure.adapter.out.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component // Lo marcamos como un componente de Spring para poder inyectarlo después.
public class TaskMapper {

    public TaskEntity toEntity(Task task) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(task.getId());
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setCompleted(task.isCompleted());
        return taskEntity;
    }

    public Task toDomainModel(TaskEntity taskEntity) {
        Task task = new Task();
        task.setId(taskEntity.getId());
        task.setTitle(taskEntity.getTitle());
        task.setDescription(taskEntity.getDescription());
        task.setCompleted(taskEntity.isCompleted());
        return task;
    }
}