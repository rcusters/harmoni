package be.opcodes.harmoni.drivingport.usecase;

import be.opcodes.harmoni.core.domain.Workspace;

public interface CreateWorkspaceUseCase {
    Workspace create(String name, String description);
}
