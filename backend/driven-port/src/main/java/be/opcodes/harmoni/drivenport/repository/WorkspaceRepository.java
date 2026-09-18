package be.opcodes.harmoni.drivenport.repository;

import be.opcodes.harmoni.core.domain.Workspace;

import java.util.List;
import java.util.Optional;

public interface WorkspaceRepository {
    List<Workspace> findAll();
    Optional<Workspace> findById(String id);
    Workspace save(Workspace workspace);
}
