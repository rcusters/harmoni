package be.opcodes.harmoni.springboot.configuration;

import be.opcodes.harmoni.core.application.CreateWorkspaceService;
import be.opcodes.harmoni.core.application.GetAllWorkspacesService;
import be.opcodes.harmoni.drivenadapter.repository.InMemoryWorkspaceRepository;
import be.opcodes.harmoni.drivenport.repository.WorkspaceRepository;
import be.opcodes.harmoni.drivingport.usecase.CreateWorkspaceUseCase;
import be.opcodes.harmoni.drivingport.usecase.GetAllWorkspacesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HarmoniConfiguration {

    @Bean
    WorkspaceRepository workspaceRepository() {
        return new InMemoryWorkspaceRepository();
    }

    @Bean
    GetAllWorkspacesUseCase getAllWorkspacesUseCase(WorkspaceRepository workspaceRepository) {
        return new GetAllWorkspacesService(workspaceRepository);
    }

    @Bean
    CreateWorkspaceUseCase createWorkspaceUseCase(WorkspaceRepository workspaceRepository) {
        return new CreateWorkspaceService(workspaceRepository);
    }
}
