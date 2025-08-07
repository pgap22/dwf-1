package sv.edu.udb.limpia.infrastructure.provider.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.limpia.infrastructure.provider.persistence.entity.TaskEntity;

public interface SpringDataJpaTaskRepository extends JpaRepository<TaskEntity, Long> {
}