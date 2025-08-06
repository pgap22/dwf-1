package sv.edu.udb.monolitico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Crea getters, setters
@Entity // Le dice a Spring que esta clase es una tabla en la BD.
public class Task {

    @Id // (Primary Key).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID automáticamente.
    private Long id;

    private String title;
    private String description;
    private boolean completed = false;
}