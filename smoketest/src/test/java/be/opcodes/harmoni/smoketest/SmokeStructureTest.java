package be.opcodes.harmoni.smoketest;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("smoke")
class SmokeStructureTest {

    @Test
    void shouldExposeExpectedDefaultEndpoints() {
        assertThat(System.getenv().getOrDefault("BACKEND_URL", "http://localhost:8080/harmoni"))
                .endsWith("/harmoni");
        assertThat(System.getenv().getOrDefault("FRONTEND_URL", "http://localhost:3000"))
                .isEqualTo("http://localhost:3000");
    }
}
