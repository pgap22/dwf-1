package sv.edu.udb.limpia.infrastructure.provider.persistence;

import org.springframework.stereotype.Component;
import sv.edu.udb.limpia.application.port.out.TaskRepositoryPort;
import sv.edu.udb.limpia.domain.model.Task;
import sv.edu.udb.limpia.infrastructure.provider.persistence.entity.TaskEntity;
import sv.edu.udb.limpia.infrastructure.provider.persistence.mapper.TaskMapper;
import sv.edu.udb.limpia.infrastructure.provider.persistence.repository.SpringDataJpaTaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskRepositoryProvider implements TaskRepositoryPort {

    private final SpringDataJpaTaskRepository jpaTaskRepository;
    private final TaskMapper taskMapper;

    public TaskRepositoryProvider(SpringDataJpaTaskRepository jpaTaskRepository, TaskMapper taskMapper) {
        this.jpaTaskRepository = jpaTaskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public Task save(Task task) {
        TaskEntity entity = taskMapper.toEntity(task);
        TaskEntity savedEntity = jpaTaskRepository.save(entity);
        return taskMapper.toDomainModel(savedEntity);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return jpaTaskRepository.findById(id).map(taskMapper::toDomainModel);
    }

    @Override
    public List<Task> findAll() {
        return jpaTaskRepository.findAll().stream()
                .map(taskMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Task> update(Task task) {
        if (jpaTaskRepository.existsById(task.getId())) {
            TaskEntity entity = taskMapper.toEntity(task);
            TaskEntity updatedEntity = jpaTaskRepository.save(entity);
            return Optional.of(taskMapper.toDomainModel(updatedEntity));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        if (jpaTaskRepository.existsById(id)) {
            jpaTaskRepository.deleteById(id);
        }
    }
}