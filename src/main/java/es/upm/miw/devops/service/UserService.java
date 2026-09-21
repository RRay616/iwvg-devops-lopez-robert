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

    public void deleteById(String id) {
        if (!this.userRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
            );
        }

        this.userRepository.deleteById(id);
    }

    public User updateActive(String id, boolean active) {
        User user = this.readById(id);
        user.setActive(active);
        return this.userRepository.save(user);
    }

    public User update(String id, User user) {
        User existingUser = this.readById(id);

        existingUser.setName(user.getName());
        existingUser.setFamilyName(user.getFamilyName());
        existingUser.setEmail(user.getEmail());
        existingUser.setIdentity(user.getIdentity());
        existingUser.setAddress(user.getAddress());
        existingUser.setCity(user.getCity());
        existingUser.setProvince(user.getProvince());
        existingUser.setPostalCode(user.getPostalCode());
        existingUser.setActive(user.isActive());

        return this.userRepository.save(existingUser);
    }
}