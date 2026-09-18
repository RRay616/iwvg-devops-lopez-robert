package es.upm.miw.devops.functionaltests;

import es.upm.miw.devops.seeder.UserSeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class UserControllerFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserSeeder userSeeder;

    @BeforeEach
    void setUp() {
        userSeeder.seed();
    }

    @Test
    void testReadById() {
        this.webTestClient.get()
                .uri("/user/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.name").isEqualTo("Oscar")
                .jsonPath("$.familyName").isEqualTo("Fernandez");
    }

    @Test
    void testReadByIdNotFound() {
        this.webTestClient.get()
                .uri("/user/999")
                .exchange()
                .expectStatus().isNotFound();
    }
}