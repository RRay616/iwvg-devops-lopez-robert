package es.upm.miw.devops.resources.dtos;

import es.upm.miw.devops.infrastructure.data.models.Role;
import es.upm.miw.devops.infrastructure.data.models.User;

import java.util.List;

public record UserUpdatingDto(
        String name,
        String familyName,
        String email,
        String identity,
        String address,
        String city,
        String province,
        String postalCode,
        boolean active,
        Role role
) {

    public User toEntity(String id) {
        User user = new User(
                id,
                this.name,
                this.familyName,
                this.email,
                this.identity,
                this.address,
                List.of()
        );

        user.setCity(this.city);
        user.setProvince(this.province);
        user.setPostalCode(this.postalCode);
        user.setActive(this.active);
        user.setRole(this.role);

        return user;
    }
}