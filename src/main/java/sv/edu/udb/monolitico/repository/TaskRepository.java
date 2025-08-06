package sv.edu.udb.monolitico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.monolitico.model.Task;

@Repository // Buena practica para marcarlo como un componente de acceso a datos.
public interface TaskRepository extends JpaRepository<Task, Long> {

}