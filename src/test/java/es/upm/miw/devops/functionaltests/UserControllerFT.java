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

    @Test
    void testFindByFilterBillableTrue() {
        this.webTestClient.get()
                .uri("/user/search?billable=true")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo("1")
                .jsonPath("$[0].billable").isEqualTo(true)
                .jsonPath("$[1].id").isEqualTo("3")
                .jsonPath("$[1].billable").isEqualTo(true)
                .jsonPath("$[2].id").isEqualTo("4")
                .jsonPath("$[2].billable").isEqualTo(true);
    }

    @Test
    void testFindByFilterBillableFalse() {
        this.webTestClient.get()
                .uri("/user/search?billable=false")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo("2")
                .jsonPath("$[0].billable").isEqualTo(false)
                .jsonPath("$[1].id").isEqualTo("5")
                .jsonPath("$[1].billable").isEqualTo(false)
                .jsonPath("$[2].id").isEqualTo("6")
                .jsonPath("$[2].billable").isEqualTo(false);
    }

    @Test
    void testFindByFilterByNameAndBillable() {
        this.webTestClient.get()
                .uri("/user/search?name=Oscar&billable=true")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].id").isEqualTo("1")
                .jsonPath("$[1].id").isEqualTo("3");
    }
}