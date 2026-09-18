package be.opcodes.harmoni.core.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WorkspaceTest {

    @Test
    void rejectsBlankName() {
        assertThatThrownBy(() -> new Workspace("workspace-1", "   ", "A description"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("name");
    }
}
