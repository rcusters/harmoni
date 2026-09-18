package be.opcodes.harmoni.drivingport.usecase;

import be.opcodes.harmoni.core.domain.Workspace;

import java.util.List;

public interface GetAllWorkspacesUseCase {
    List<Workspace> getAll();
}
