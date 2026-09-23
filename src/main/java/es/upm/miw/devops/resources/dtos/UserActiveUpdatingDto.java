package es.upm.miw.devops.resources.dtos;

public record UserActiveUpdatingDto(
        String id,
        boolean active
) {
}