package es.upm.miw.devops.seeder;

import es.upm.miw.devops.models.User;
import es.upm.miw.devops.repository.UserRepository;
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

        userRepository.saveAll(List.of(
                new User(
                        "1",
                        "Oscar",
                        "Fernandez",
                        "oscar@example.com",
                        "12345678A",
                        "Calle Mayor 1",
                        "Madrid",
                        "Madrid",
                        "28001",
                        List.of()
                ),
                new User(
                        "2",
                        "Ana",
                        "Blanco",
                        "ana@example.com",
                        "87654321B",
                        "Calle Mayor 2",
                        "Madrid",
                        null,
                        "28002",
                        List.of()
                ),
                new User(
                        "3",
                        "Oscar",
                        "López",
                        "oscar.lopez@example.com",
                        "11223344C",
                        "Calle Mayor 3",
                        "Madrid",
                        "Madrid",
                        "28003",
                        List.of()
                ),
                new User(
                        "4",
                        "Paula",
                        "Torres",
                        "paula@example.com",
                        "22334455D",
                        "Calle Mayor 4",
                        "Madrid",
                        "Madrid",
                        "28004",
                        List.of()
                ),
                new User(
                        "5",
                        "Antonio",
                        "Blanco",
                        null,
                        "33445566E",
                        "Calle Mayor 5",
                        "Madrid",
                        "Madrid",
                        "28005",
                        List.of()
                ),
                new User(
                        "6",
                        "Paula",
                        "Torres",
                        "paula.torres@example.com",
                        "44556677F",
                        null,
                        "Madrid",
                        "Madrid",
                        "28006",
                        List.of()
                )
        ));
    }
}