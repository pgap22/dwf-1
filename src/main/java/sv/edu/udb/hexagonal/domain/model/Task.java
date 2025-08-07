package sv.edu.udb.hexagonal.domain.model;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}