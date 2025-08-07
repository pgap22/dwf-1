package sv.edu.udb.limpia.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Esta anotacion hara que Spring Boot responda con el codigo de estado que le indicamos
// cada vez que esta excepcion sea lanzada y no sea atrapada por otro manejador.
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}