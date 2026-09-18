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
}