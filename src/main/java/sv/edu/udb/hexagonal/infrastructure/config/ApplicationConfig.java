package sv.edu.udb.hexagonal.infrastructure.config;

import sv.edu.udb.hexagonal.domain.port.in.TaskUseCase;
import sv.edu.udb.hexagonal.domain.port.out.TaskRepositoryPort;
import sv.edu.udb.hexagonal.domain.service.TaskServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    // Creamos un "Bean" (un objeto gestionado por Spring) para nuestro servicio de dominio.
    // Este método le dice a Spring: "Para crear un TaskUseCase, sigue estas instrucciones".
    @Bean
    public TaskUseCase taskUseCase(TaskRepositoryPort taskRepositoryPort) {
        // Creamos la implementación del servicio y le pasamos el adaptador de repositorio.
        // Spring automáticamente encontrará el bean que implementa TaskRepositoryPort
        // (nuestro TaskRepositoryAdapter) y lo inyectará aquí.
        return new TaskServiceImpl(taskRepositoryPort);
    }

    // No necesitamos definir un Bean para TaskRepositoryAdapter porque ya tiene la
    // anotación @Component, así que Spring lo encuentra automáticamente.
    // Lo mismo para TaskMapper.
}