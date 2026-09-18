package es.upm.miw.devops.service;

import es.upm.miw.devops.models.User;
import es.upm.miw.devops.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User readById(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));
    }

    public List<User> findByFilter(String name, String familyName, Boolean billable) {
        return this.userRepository.findAll().stream()
                .filter(user -> name == null || name.equals(user.getName()))
                .filter(user -> familyName == null || familyName.equals(user.getFamilyName()))
                .filter(user -> billable == null || billable == user.isBillable())
                .sorted(Comparator.comparing(User::getId))
                .toList();
    }
}