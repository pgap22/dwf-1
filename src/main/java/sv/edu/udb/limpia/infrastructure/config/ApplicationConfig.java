package sv.edu.udb.limpia.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sv.edu.udb.limpia.application.port.out.TaskRepositoryPort;
import sv.edu.udb.limpia.application.usecase.TaskUseCase;
import sv.edu.udb.limpia.application.usecase.TaskUseCaseImpl;

@Configuration
public class ApplicationConfig {

    @Bean
    public TaskUseCase taskUseCase(TaskRepositoryPort taskRepositoryPort) {
        // Le decimos a Spring que la implementación para el puerto TaskUseCase
        // es nuestra clase TaskUseCaseImpl. Le pasamos el puerto de repositorio
        // que Spring encontrará e inyectará automáticamente.
        return new TaskUseCaseImpl(taskRepositoryPort);
    }
}