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
                new User("1", "Oscar", "Fernandez", List.of()),
                new User("2", "Ana", "Blanco", List.of()),
                new User("3", "Oscar", "López", List.of()),
                new User("4", "Paula", "Torres", List.of()),
                new User("5", "Antonio", "Blanco", List.of()),
                new User("6", "Paula", "Torres", List.of())
        ));
    }
}
