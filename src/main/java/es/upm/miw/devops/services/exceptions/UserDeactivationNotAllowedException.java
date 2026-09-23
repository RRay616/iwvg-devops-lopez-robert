package es.upm.miw.devops.services.exceptions;

public class UserDeactivationNotAllowedException extends RuntimeException {

    public UserDeactivationNotAllowedException(String id) {
        super("ADMIN user with id '" + id + "' cannot be deactivated");
    }
}