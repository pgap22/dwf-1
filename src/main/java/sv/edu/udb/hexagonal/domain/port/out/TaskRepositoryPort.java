package sv.edu.udb.hexagonal.domain.port.out;

import sv.edu.udb.hexagonal.domain.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findAll();
    Optional<Task> update(Task task);
    void deleteById(Long id);
}