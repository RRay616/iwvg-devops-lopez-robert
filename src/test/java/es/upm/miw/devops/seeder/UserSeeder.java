package es.upm.miw.devops.seeder;

import es.upm.miw.devops.infrastructure.data.models.User;
import es.upm.miw.devops.infrastructure.data.daos.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserSeeder {

    private final UserRepository userRepository;

    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void seed() {
        userRepository.deleteAll();

        User user1 = new User(
                "1",
                "Oscar",
                "Fernandez",
                "oscar@example.com",
                "12345678A",
                "Calle Mayor 1",
                List.of()
        );
        user1.setCity("Madrid");
        user1.setProvince("Madrid");
        user1.setPostalCode("28001");

        User user2 = new User(
                "2",
                "Ana",
                "Blanco",
                "ana@example.com",
                "87654321B",
                "Calle Mayor 2",
                List.of()
        );
        user2.setCity("Madrid");
        user2.setProvince(null);
        user2.setPostalCode("28002");

        User user3 = new User(
                "3",
                "Oscar",
                "López",
                "oscar.lopez@example.com",
                "11223344C",
                "Calle Mayor 3",
                List.of()
        );
        user3.setCity("Madrid");
        user3.setProvince("Madrid");
        user3.setPostalCode("28003");

        User user4 = new User(
                "4",
                "Paula",
                "Torres",
                "paula@example.com",
                "22334455D",
                "Calle Mayor 4",
                List.of()
        );
        user4.setCity("Madrid");
        user4.setProvince("Madrid");
        user4.setPostalCode("28004");

        User user5 = new User(
                "5",
                "Antonio",
                "Blanco",
                null,
                "33445566E",
                "Calle Mayor 5",
                List.of()
        );
        user5.setCity("Madrid");
        user5.setProvince("Madrid");
        user5.setPostalCode("28005");

        User user6 = new User(
                "6",
                "Paula",
                "Torres",
                "paula.torres@example.com",
                "44556677F",
                null,
                List.of()
        );
        user6.setCity("Madrid");
        user6.setProvince("Madrid");
        user6.setPostalCode("28006");

        userRepository.saveAll(List.of(
                user1,
                user2,
                user3,
                user4,
                user5,
                user6
        ));
    }
}