package es.upm.miw.devops.code;

import es.upm.miw.devops.models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersDatabaseTest {

    @Test
    void shouldReturnAllUsers() {
        List<User> users = new UsersDatabase()
                .findAll()
                .toList();

        assertEquals(6, users.size());
    }

    @Test
    void shouldReturnFirstUserCorrectly() {
        User user = new UsersDatabase()
                .findAll()
                .toList()
                .get(0);

        assertEquals("1", user.getId());
        assertEquals("Oscar", user.getName());
        assertEquals("Fernandez", user.getFamilyName());
        assertEquals(3, user.getFractions().size());
    }

    @Test
    void shouldReturnSecondUserWithFourFractions() {
        User user = new UsersDatabase()
                .findAll()
                .toList()
                .get(1);

        assertEquals("2", user.getId());
        assertEquals("Ana", user.getName());
        assertEquals("Blanco", user.getFamilyName());
        assertEquals(4, user.getFractions().size());
    }

    @Test
    void shouldReturnThirdUserCorrectly() {
        User user = new UsersDatabase()
                .findAll()
                .toList()
                .get(2);

        assertEquals("3", user.getId());
        assertEquals("Oscar", user.getName());
        assertEquals("López", user.getFamilyName());
        assertEquals(4, user.getFractions().size());
    }

    @Test
    void shouldReturnFourthUserCorrectly() {
        User user = new UsersDatabase()
                .findAll()
                .toList()
                .get(3);

        assertEquals("4", user.getId());
        assertEquals("Paula", user.getName());
        assertEquals("Torres", user.getFamilyName());
        assertEquals(2, user.getFractions().size());
    }

    @Test
    void shouldReturnFifthUserCorrectly() {
        User user = new UsersDatabase()
                .findAll()
                .toList()
                .get(4);

        assertEquals("5", user.getId());
        assertEquals("Antonio", user.getName());
        assertEquals("Blanco", user.getFamilyName());
        assertEquals(3, user.getFractions().size());
    }

    @Test
    void shouldReturnSixthUserCorrectly() {
        User user = new UsersDatabase()
                .findAll()
                .toList()
                .get(5);

        assertEquals("6", user.getId());
        assertEquals("Paula", user.getName());
        assertEquals("Torres", user.getFamilyName());
        assertEquals(3, user.getFractions().size());
    }
}