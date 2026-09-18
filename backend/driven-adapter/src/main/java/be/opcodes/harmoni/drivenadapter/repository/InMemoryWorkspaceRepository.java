package be.opcodes.harmoni.drivenadapter.repository;

import be.opcodes.harmoni.core.domain.Workspace;
import be.opcodes.harmoni.drivenport.repository.WorkspaceRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryWorkspaceRepository implements WorkspaceRepository {
    private final CopyOnWriteArrayList<Workspace> workspaces = new CopyOnWriteArrayList<>(List.of(
            new Workspace(
                    "schedule-foundations",
                    "Schedule Foundations",
                    "Explore baseline scheduling, staffing, and room-allocation constraints."
            )
    ));

    @Override
    public List<Workspace> findAll() {
        return workspaces.stream()
                .sorted(Comparator.comparing(Workspace::name))
                .toList();
    }

    @Override
    public Optional<Workspace> findById(String id) {
        return workspaces.stream()
                .filter(workspace -> workspace.id().equals(id))
                .findFirst();
    }

    @Override
    public Workspace save(Workspace workspace) {
        workspaces.add(workspace);
        return workspace;
    }
}
