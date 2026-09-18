package be.opcodes.harmoni.core.application;

import be.opcodes.harmoni.core.domain.Workspace;
import be.opcodes.harmoni.drivenport.repository.WorkspaceRepository;
import be.opcodes.harmoni.drivingport.usecase.CreateWorkspaceUseCase;

import java.util.UUID;

public class CreateWorkspaceService implements CreateWorkspaceUseCase {
    private final WorkspaceRepository workspaceRepository;

    public CreateWorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public Workspace create(String name, String description) {
        var workspace = new Workspace(UUID.randomUUID().toString(), name, description);
        return workspaceRepository.save(workspace);
    }
}
