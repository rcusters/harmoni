package be.opcodes.harmoni.drivingadapter.rest;

import be.opcodes.harmoni.core.domain.Workspace;

public record WorkspaceResponse(String id, String name, String description) {
    public static WorkspaceResponse from(Workspace workspace) {
        return new WorkspaceResponse(workspace.id(), workspace.name(), workspace.description());
    }
}
