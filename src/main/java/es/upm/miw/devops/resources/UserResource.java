package es.upm.miw.devops.resources;

import es.upm.miw.devops.resources.dtos.UserActiveUpdatingDto;
import es.upm.miw.devops.resources.dtos.UserResponseDto;
import es.upm.miw.devops.resources.dtos.UserUpdatingDto;
import es.upm.miw.devops.services.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDto readById(@PathVariable String id) {
        return UserResponseDto.fromEntity(this.userService.readById(id));
    }

    @GetMapping("/search")
    public List<UserResponseDto> findByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String familyName,
            @RequestParam(required = false) Boolean billable) {

        return this.userService.findByFilter(name, familyName, billable)
                .stream()
                .map(UserResponseDto::fromEntity)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        this.userService.deleteById(id);
    }

    @PutMapping("/{id}/active")
    public UserResponseDto updateActive(
            @PathVariable String id,
            @RequestBody boolean active) {

        return UserResponseDto.fromEntity(
                this.userService.updateActive(id, active)
        );
    }

    @PutMapping("/{id}")
    public UserResponseDto update(
            @PathVariable String id,
            @RequestBody UserUpdatingDto user) {

        return UserResponseDto.fromEntity(
                this.userService.update(id, user.toEntity(id))
        );
    }

    @PatchMapping
    public List<UserResponseDto> updateActive(
            @RequestBody List<UserActiveUpdatingDto> users) {

        return users.stream()
                .map(user -> this.userService.updateActive(user.id(), user.active()))
                .map(UserResponseDto::fromEntity)
                .toList();
    }
}