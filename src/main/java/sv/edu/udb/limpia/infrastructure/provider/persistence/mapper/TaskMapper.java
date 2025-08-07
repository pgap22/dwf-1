package sv.edu.udb.limpia.infrastructure.provider.persistence.mapper;

import org.springframework.stereotype.Component;
import sv.edu.udb.limpia.domain.model.Task;
import sv.edu.udb.limpia.infrastructure.provider.persistence.entity.TaskEntity;

@Component
public class TaskMapper {
    public TaskEntity toEntity(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setCompleted(task.isCompleted());
        return entity;
    }

    public Task toDomainModel(TaskEntity entity) {
        Task task = new Task();
        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        task.setDescription(entity.getDescription());
        task.setCompleted(entity.isCompleted());
        return task;
    }
}