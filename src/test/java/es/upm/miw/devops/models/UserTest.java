package es.upm.miw.devops.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateDefaultUser() {
        User user = new User();

        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getFamilyName());
        assertNull(user.getEmail());
        assertNull(user.getIdentity());
        assertNull(user.getAddress());
        assertNull(user.getCity());
        assertNull(user.getProvince());
        assertNull(user.getPostalCode());
        assertTrue(user.isActive());
        assertNotNull(user.getFractions());
        assertTrue(user.getFractions().isEmpty());
    }

    @Test
    void shouldCreateUserWithBasicData() {
        List<Fraction> fractions = List.of(
                new Fraction(1, 2)
        );

        User user = new User(
                "1",
                "Oscar",
                "Fernandez",
                fractions
        );

        assertEquals("1", user.getId());
        assertEquals("Oscar", user.getName());
        assertEquals("Fernandez", user.getFamilyName());
        assertEquals(fractions, user.getFractions());
    }

    @Test
    void shouldCreateUserWithCompleteData() {
        List<Fraction> fractions = List.of(
                new Fraction(1, 2),
                new Fraction(3, 4)
        );

        User user = new User(
                "1",
                "Oscar",
                "Fernandez",
                "oscar@example.com",
                "12345678A",
                "Calle Mayor 1",
                fractions
        );

        assertEquals("1", user.getId());
        assertEquals("Oscar", user.getName());
        assertEquals("Fernandez", user.getFamilyName());
        assertEquals("oscar@example.com", user.getEmail());
        assertEquals("12345678A", user.getIdentity());
        assertEquals("Calle Mayor 1", user.getAddress());
        assertEquals(fractions, user.getFractions());
    }

    @Test
    void shouldSetUserFields() {
        User user = new User();

        user.setName("Ana");
        user.setFamilyName("Blanco");
        user.setEmail("ana@example.com");
        user.setIdentity("87654321B");
        user.setAddress("Calle Mayor 2");
        user.setCity("Madrid");
        user.setProvince("Madrid");
        user.setPostalCode("28001");

        assertEquals("Ana", user.getName());
        assertEquals("Blanco", user.getFamilyName());
        assertEquals("ana@example.com", user.getEmail());
        assertEquals("87654321B", user.getIdentity());
        assertEquals("Calle Mayor 2", user.getAddress());
        assertEquals("Madrid", user.getCity());
        assertEquals("Madrid", user.getProvince());
        assertEquals("28001", user.getPostalCode());
    }

    @Test
    void shouldSetActiveStatus() {
        User user = new User();

        assertTrue(user.isActive());

        user.setActive(false);

        assertFalse(user.isActive());
    }

    @Test
    void shouldSetFractions() {
        User user = new User();

        List<Fraction> fractions = List.of(
                new Fraction(1, 2),
                new Fraction(2, 3)
        );

        user.setFractions(fractions);

        assertEquals(fractions, user.getFractions());
    }

    @Test
    void shouldAddFraction() {
        User user = new User();
        Fraction fraction = new Fraction(2, 3);

        user.addFraction(fraction);

        assertEquals(
                List.of(fraction),
                user.getFractions()
        );
    }

    @Test
    void shouldReturnFullName() {
        User user = new User(
                "1",
                "Oscar",
                "Fernandez",
                new ArrayList<>()
        );

        assertEquals(
                "Oscar Fernandez",
                user.fullName()
        );
    }

    @Test
    void shouldReturnInitials() {
        User user = new User(
                "1",
                "Oscar",
                "Fernandez",
                new ArrayList<>()
        );

        assertEquals(
                "O.",
                user.initials()
        );
    }

    @Test
    void shouldBeBillableWhenAllFieldsAreValid() {
        User user = new User(
                "1",
                "Oscar",
                "Fernandez",
                "oscar@example.com",
                "12345678A",
                "Calle Mayor 1",
                new ArrayList<>()
        );

        user.setCity("Madrid");
        user.setProvince("Madrid");
        user.setPostalCode("28001");

        assertTrue(user.isBillable());
    }

    @Test
    void shouldNotBeBillableWhenAFieldIsNull() {
        User user = new User(
                "1",
                "Oscar",
                "Fernandez",
                "oscar@example.com",
                "12345678A",
                "Calle Mayor 1",
                new ArrayList<>()
        );

        user.setCity(null);
        user.setProvince("Madrid");
        user.setPostalCode("28001");

        assertFalse(user.isBillable());
    }

    @Test
    void shouldNotBeBillableWhenAFieldIsBlank() {
        User user = new User(
                "1",
                "Oscar",
                "Fernandez",
                "oscar@example.com",
                "12345678A",
                "Calle Mayor 1",
                new ArrayList<>()
        );

        user.setCity("Madrid");
        user.setProvince("Madrid");
        user.setPostalCode("   ");

        assertFalse(user.isBillable());
    }

    @Test
    void shouldReturnUserAsString() {
        User user = new User(
                "1",
                "Oscar",
                "Fernandez",
                "oscar@example.com",
                "12345678A",
                "Calle Mayor 1",
                new ArrayList<>()
        );

        user.setCity("Madrid");
        user.setProvince("Madrid");
        user.setPostalCode("28001");

        assertEquals(
                "User{id='1', name='Oscar', familyName='Fernandez', email='oscar@example.com', identity='12345678A', address='Calle Mayor 1', city='Madrid', province='Madrid', postalCode='28001', fractions=[]}",
                user.toString()
        );
    }
}