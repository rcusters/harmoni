package be.opcodes.harmoni.drivenadapter.repository;

import be.opcodes.harmoni.core.domain.Workspace;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryWorkspaceRepositoryTest {

    @Test
    void saveReplacesExistingWorkspaceWithSameId() {
        var repository = new InMemoryWorkspaceRepository();

        repository.save(new Workspace("schedule-foundations", "Updated", "Updated description"));

        var workspaces = repository.findAll();
        assertThat(workspaces).hasSize(1);
        assertThat(workspaces.getFirst().name()).isEqualTo("Updated");
    }
}
