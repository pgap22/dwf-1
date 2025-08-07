package sv.edu.udb.hexagonal.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.hexagonal.infrastructure.adapter.out.persistence.entity.TaskEntity;

public interface SpringDataJpaTaskRepository extends JpaRepository<TaskEntity, Long> {
    // Spring implementará automáticamente todos los métodos CRUD para TaskEntity.
}