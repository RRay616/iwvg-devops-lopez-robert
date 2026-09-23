package es.upm.miw.devops.resources.dtos;

import es.upm.miw.devops.infrastructure.data.models.Role;
import es.upm.miw.devops.infrastructure.data.models.User;

public record UserResponseDto(
        String id,
        String name,
        String familyName,
        String email,
        String identity,
        String address,
        String city,
        String province,
        String postalCode,
        boolean active,
        Role role,
        boolean billable
) {

    public static UserResponseDto fromEntity(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getFamilyName(),
                user.getEmail(),
                user.getIdentity(),
                user.getAddress(),
                user.getCity(),
                user.getProvince(),
                user.getPostalCode(),
                user.isActive(),
                user.getRole(),
                user.isBillable()
        );
    }
}