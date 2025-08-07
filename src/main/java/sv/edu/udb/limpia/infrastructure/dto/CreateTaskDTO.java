package sv.edu.udb.limpia.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTaskDTO {
    @NotBlank(message = "El título es obligatorio.")
    @Size(min = 3, max = 100)
    private String title;

    @Size(max = 255)
    private String description;
}