package es.upm.miw.devops.service;

import es.upm.miw.devops.models.User;
import es.upm.miw.devops.seeder.UserSeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSeeder userSeeder;

    @BeforeEach
    void setUp() {
        userSeeder.seed();
    }

    @Test
    void testReadById() {
        User user = this.userService.readById("1");

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo("1");
        assertThat(user.getName()).isEqualTo("Oscar");
        assertThat(user.getFamilyName()).isEqualTo("Fernandez");
    }

    @Test
    void testReadByIdNotFound() {
        assertThatThrownBy(() -> this.userService.readById("999"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("404 NOT_FOUND")
                .hasMessageContaining("User not found");
    }

    @Test
    void testFindByFilterBillableTrue() {
        List<User> users = this.userService.findByFilter(null, null, true);

        assertThat(users)
                .extracting(User::getId)
                .containsExactly("1", "3", "4");
    }

    @Test
    void testFindByFilterBillableFalse() {
        List<User> users = this.userService.findByFilter(null, null, false);

        assertThat(users)
                .extracting(User::getId)
                .containsExactly("2", "5", "6");
    }

    @Test
    void testFindByFilterByName() {
        List<User> users = this.userService.findByFilter("Oscar", null, null);

        assertThat(users)
                .extracting(User::getId)
                .containsExactly("1", "3");
    }

    @Test
    void testFindByFilterByNameAndBillable() {
        List<User> users = this.userService.findByFilter("Oscar", null, true);

        assertThat(users)
                .extracting(User::getId)
                .containsExactly("1", "3");
    }

    @Test
    void testDeleteById() {
        this.userService.deleteById("1");

        assertThatThrownBy(() -> this.userService.readById("1"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("404 NOT_FOUND")
                .hasMessageContaining("User not found");
    }

    @Test
    void testDeleteByIdNotFound() {
        assertThatThrownBy(() -> this.userService.deleteById("999"))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("404 NOT_FOUND")
                .hasMessageContaining("User not found");
    }

    @Test
    void testUpdateActive() {
        User user = this.userService.updateActive("1", false);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo("1");
        assertThat(user.isActive()).isFalse();

        User updatedUser = this.userService.readById("1");
        assertThat(updatedUser.isActive()).isFalse();
    }

    @Test
    void testUpdateActiveNotFound() {
        assertThatThrownBy(() -> this.userService.updateActive("999", false))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("404 NOT_FOUND")
                .hasMessageContaining("User not found");
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

        User updatedUser = this.userService.update("1", user);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo("1");
        assertThat(updatedUser.getName()).isEqualTo("Pedro");
        assertThat(updatedUser.getFamilyName()).isEqualTo("Lopez");
        assertThat(updatedUser.getEmail()).isEqualTo("pedro@example.com");
        assertThat(updatedUser.getIdentity()).isEqualTo("98765432B");
        assertThat(updatedUser.getAddress()).isEqualTo("Calle Nueva 10");
        assertThat(updatedUser.getCity()).isEqualTo("Madrid");
        assertThat(updatedUser.getProvince()).isEqualTo("Madrid");
        assertThat(updatedUser.getPostalCode()).isEqualTo("28010");
        assertThat(updatedUser.isActive()).isFalse();

        User persistedUser = this.userService.readById("1");

        assertThat(persistedUser.getName()).isEqualTo("Pedro");
        assertThat(persistedUser.getFamilyName()).isEqualTo("Lopez");
        assertThat(persistedUser.getEmail()).isEqualTo("pedro@example.com");
        assertThat(persistedUser.getIdentity()).isEqualTo("98765432B");
        assertThat(persistedUser.getAddress()).isEqualTo("Calle Nueva 10");
        assertThat(persistedUser.getCity()).isEqualTo("Madrid");
        assertThat(persistedUser.getProvince()).isEqualTo("Madrid");
        assertThat(persistedUser.getPostalCode()).isEqualTo("28010");
        assertThat(persistedUser.isActive()).isFalse();
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

        assertThatThrownBy(() -> this.userService.update("999", user))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("404 NOT_FOUND")
                .hasMessageContaining("User not found");
    }

    @Test
    void testUpdateActiveList() {
        User user1 = this.userService.readById("1");
        User user3 = this.userService.readById("3");

        user1.setActive(false);
        user3.setActive(false);

        List<User> updatedUsers = this.userService.updateActive(
                List.of(user1, user3)
        );

        assertThat(updatedUsers)
                .extracting(User::getId)
                .containsExactly("1", "3");

        assertThat(updatedUsers)
                .extracting(User::isActive)
                .containsExactly(false, false);

        assertThat(this.userService.readById("1").isActive()).isFalse();
        assertThat(this.userService.readById("3").isActive()).isFalse();
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

        assertThatThrownBy(() -> this.userService.updateActive(
                List.of(user)
        ))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("404 NOT_FOUND")
                .hasMessageContaining("User not found");
    }
}