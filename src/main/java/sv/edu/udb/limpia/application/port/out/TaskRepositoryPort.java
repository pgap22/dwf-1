package sv.edu.udb.limpia.application.port.out;

import sv.edu.udb.limpia.domain.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * Este es un puerto de salida. Define el contrato que la capa de aplicación
 * necesita que la capa de infraestructura implemente para la persistencia.
 *
 * Depende de objetos del 'domain' (Task), pero no sabe cómo se guardarán.
 */
public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findAll();
    Optional<Task> update(Task task);
    void deleteById(Long id);
}