package sv.edu.udb.hexagonal.infrastructure.adapter.out.persistence;

import sv.edu.udb.hexagonal.domain.model.Task;
import sv.edu.udb.hexagonal.domain.port.out.TaskRepositoryPort;
import sv.edu.udb.hexagonal.infrastructure.adapter.out.persistence.entity.TaskEntity;
import sv.edu.udb.hexagonal.infrastructure.adapter.out.persistence.mapper.TaskMapper;
import sv.edu.udb.hexagonal.infrastructure.adapter.out.persistence.repository.SpringDataJpaTaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component // Lo marcamos como un componente de Spring para que pueda ser inyectado.
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final SpringDataJpaTaskRepository jpaTaskRepository;
    private final TaskMapper taskMapper;

    public TaskRepositoryAdapter(SpringDataJpaTaskRepository jpaTaskRepository, TaskMapper taskMapper) {
        this.jpaTaskRepository = jpaTaskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public Task save(Task task) {
        TaskEntity taskEntity = taskMapper.toEntity(task);
        TaskEntity savedTaskEntity = jpaTaskRepository.save(taskEntity);
        return taskMapper.toDomainModel(savedTaskEntity);
    }

    @Override
    public Optional<Task> findById(Long id) {
        // .map() del Optional es una forma elegante de transformar el contenido si existe.
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
        // Para actualizar, primero verificamos si la tarea existe.
        if (jpaTaskRepository.existsById(task.getId())) {
            TaskEntity taskEntity = taskMapper.toEntity(task);
            TaskEntity updatedTaskEntity = jpaTaskRepository.save(taskEntity);
            return Optional.of(taskMapper.toDomainModel(updatedTaskEntity));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        // Verificamos si existe antes de borrar para poder manejar el error en el servicio si es necesario.
        if (jpaTaskRepository.existsById(id)) {
            jpaTaskRepository.deleteById(id);
        }
    }
}