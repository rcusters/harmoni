package be.opcodes.harmoni.drivenadapter.repository;

import be.opcodes.harmoni.core.domain.Workspace;
import be.opcodes.harmoni.drivenport.repository.WorkspaceRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryWorkspaceRepository implements WorkspaceRepository {
    private final Map<String, Workspace> workspaces = new ConcurrentHashMap<>(Map.of(
            "schedule-foundations",
            new Workspace(
                    "schedule-foundations",
                    "Schedule Foundations",
                    "Explore baseline scheduling, staffing, and room-allocation constraints."
            )
    ));

    @Override
    public List<Workspace> findAll() {
        return workspaces.values().stream()
                .sorted(Comparator.comparing(Workspace::name))
                .toList();
    }

    @Override
    public Optional<Workspace> findById(String id) {
        return Optional.ofNullable(workspaces.get(id));
    }

    @Override
    public Workspace save(Workspace workspace) {
        workspaces.put(workspace.id(), workspace);
        return workspace;
    }
}
