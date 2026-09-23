package es.upm.miw.devops.functionaltests;

import es.upm.miw.devops.seeder.UserSeeder;
import es.upm.miw.devops.infrastructure.data.models.User;
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
        User user = new User(
                "1",
                "Pedro",
                "Lopez",
                "pedro@example.com",
                "98765432B",
                "Calle Nueva 10",
                List.of()
        );
        user.setCity("Madrid");
        user.setProvince("Madrid");
        user.setPostalCode("28010");
        user.setActive(false);

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
        User user = new User(
                "999",
                "Pedro",
                "Lopez",
                "pedro@example.com",
                "98765432B",
                "Calle Nueva 10",
                List.of()
        );
        user.setCity("Madrid");
        user.setProvince("Madrid");
        user.setPostalCode("28010");
        user.setActive(false);

        this.webTestClient.put()
                .uri("/user/999")
                .bodyValue(user)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateActiveList() {
        User user1 = new User(
                "1",
                "Oscar",
                "Fernandez",
                List.of()
        );
        user1.setActive(false);

        User user3 = new User(
                "3",
                "Oscar",
                "López",
                List.of()
        );
        user3.setActive(false);

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
        User user = new User(
                "999",
                "Pedro",
                "Lopez",
                List.of()
        );
        user.setActive(false);

        this.webTestClient.patch()
                .uri("/user")
                .bodyValue(List.of(user))
                .exchange()
                .expectStatus().isNotFound();
    }
}