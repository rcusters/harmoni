package be.opcodes.harmoni.drivingadapter.rest;

import be.opcodes.harmoni.drivingport.usecase.CreateWorkspaceUseCase;
import be.opcodes.harmoni.drivingport.usecase.GetAllWorkspacesUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {
    private final GetAllWorkspacesUseCase getAllWorkspacesUseCase;
    private final CreateWorkspaceUseCase createWorkspaceUseCase;

    public WorkspaceController(GetAllWorkspacesUseCase getAllWorkspacesUseCase, CreateWorkspaceUseCase createWorkspaceUseCase) {
        this.getAllWorkspacesUseCase = getAllWorkspacesUseCase;
        this.createWorkspaceUseCase = createWorkspaceUseCase;
    }

    @GetMapping
    public List<WorkspaceResponse> getAll() {
        return getAllWorkspacesUseCase.getAll().stream().map(WorkspaceResponse::from).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkspaceResponse create(@RequestBody WorkspaceRequest request) {
        return WorkspaceResponse.from(createWorkspaceUseCase.create(request.name(), request.description()));
    }
}
