package es.upm.miw.devops.functionaltests;

import es.upm.miw.devops.infrastructure.data.models.Role;
import es.upm.miw.devops.resources.dtos.UserActiveUpdatingDto;
import es.upm.miw.devops.resources.dtos.UserUpdatingDto;
import es.upm.miw.devops.seeder.UserSeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class UserResourceFT {

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

    @Test
    void testDeleteById() {
        this.webTestClient.delete()
                .uri("/user/1")
                .exchange()
                .expectStatus().isOk();

        this.webTestClient.get()
                .uri("/user/1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteByIdNotFound() {
        this.webTestClient.delete()
                .uri("/user/999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateActive() {
        this.webTestClient.put()
                .uri("/user/1/active")
                .bodyValue(false)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.active").isEqualTo(false);
    }

    @Test
    void testUpdateActiveNotFound() {
        this.webTestClient.put()
                .uri("/user/999/active")
                .bodyValue(false)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdate() {
        UserUpdatingDto user = new UserUpdatingDto(
                "Pedro",
                "Lopez",
                "pedro@example.com",
                "98765432B",
                "Calle Nueva 10",
                "Madrid",
                "Madrid",
                "28010",
                false,
                null
        );

        this.webTestClient.put()
                .uri("/user/1")
                .bodyValue(user)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.name").isEqualTo("Pedro")
                .jsonPath("$.familyName").isEqualTo("Lopez")
                .jsonPath("$.email").isEqualTo("pedro@example.com")
                .jsonPath("$.identity").isEqualTo("98765432B")
                .jsonPath("$.address").isEqualTo("Calle Nueva 10")
                .jsonPath("$.city").isEqualTo("Madrid")
                .jsonPath("$.province").isEqualTo("Madrid")
                .jsonPath("$.postalCode").isEqualTo("28010")
                .jsonPath("$.active").isEqualTo(false);
    }

    @Test
    void testUpdateNotFound() {
        UserUpdatingDto user = new UserUpdatingDto(
                "Pedro",
                "Lopez",
                "pedro@example.com",
                "98765432B",
                "Calle Nueva 10",
                "Madrid",
                "Madrid",
                "28010",
                false,
                null
        );

        this.webTestClient.put()
                .uri("/user/999")
                .bodyValue(user)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateActiveList() {
        UserActiveUpdatingDto user1 = new UserActiveUpdatingDto("1", false);
        UserActiveUpdatingDto user3 = new UserActiveUpdatingDto("3", false);

        this.webTestClient.patch()
                .uri("/user")
                .bodyValue(List.of(user1, user3))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo("1")
                .jsonPath("$[0].active").isEqualTo(false)
                .jsonPath("$[1].id").isEqualTo("3")
                .jsonPath("$[1].active").isEqualTo(false);
    }

    @Test
    void testUpdateActiveListNotFound() {
        UserActiveUpdatingDto user = new UserActiveUpdatingDto("999", false);

        this.webTestClient.patch()
                .uri("/user")
                .bodyValue(List.of(user))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateActiveAdminNotAllowed() {
        UserUpdatingDto admin = new UserUpdatingDto(
                "Oscar",
                "Fernandez",
                "oscar@example.com",
                "12345678A",
                "Calle Mayor 1",
                "Madrid",
                "Madrid",
                "28001",
                true,
                Role.ADMIN
        );

        this.webTestClient.put()
                .uri("/user/1")
                .bodyValue(admin)
                .exchange()
                .expectStatus().isOk();

        this.webTestClient.put()
                .uri("/user/1/active")
                .bodyValue(false)
                .exchange()
                .expectStatus().isForbidden();
    }
}