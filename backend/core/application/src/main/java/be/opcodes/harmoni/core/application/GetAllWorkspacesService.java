package be.opcodes.harmoni.core.application;

import be.opcodes.harmoni.core.domain.Workspace;
import be.opcodes.harmoni.drivenport.repository.WorkspaceRepository;
import be.opcodes.harmoni.drivingport.usecase.GetAllWorkspacesUseCase;

import java.util.List;

public class GetAllWorkspacesService implements GetAllWorkspacesUseCase {
    private final WorkspaceRepository workspaceRepository;

    public GetAllWorkspacesService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public List<Workspace> getAll() {
        return workspaceRepository.findAll();
    }
}
