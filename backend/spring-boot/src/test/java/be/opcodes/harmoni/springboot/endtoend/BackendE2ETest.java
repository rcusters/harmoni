package be.opcodes.harmoni.springboot.endtoend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestClient;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendE2ETest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestClient restClient;

    @Test
    void shouldExposeSeedWorkspace() {
        var response = restClient.get()
                .uri("http://localhost:" + port + "/harmoni/workspaces")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(List.class);

        assertThat(response).isNotEmpty();
        assertThat(response.getFirst()).asInstanceOf(org.assertj.core.api.InstanceOfAssertFactories.MAP)
                .containsEntry("name", "Schedule Foundations");
    }

    @Test
    void shouldCreateWorkspace() {
        var request = Map.of(
                "name", "Exam Planning",
                "description", "Coordinate invigilators, rooms, and time slots."
        );

        var created = restClient.post()
                .uri("http://localhost:" + port + "/harmoni/workspaces")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(Map.class);

        assertThat(created).containsEntry("name", "Exam Planning");
        assertThat(created).containsKey("id");
    }
}
