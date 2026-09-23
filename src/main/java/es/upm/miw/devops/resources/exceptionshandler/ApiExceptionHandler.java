package es.upm.miw.devops.resources.exceptionshandler;

import es.upm.miw.devops.services.exceptions.UserDeactivationNotAllowedException;
import es.upm.miw.devops.services.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NoResourceFoundException.class,
            UserNotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFound(Exception exception) {
        if (exception instanceof NoResourceFoundException) {
            return new ErrorMessage(
                    new RuntimeException(
                            "Ruta no encontrada. Prueba con: **/actuator/info o **/swagger-ui.html o **/v3/api-docs o **/v3/api-docs.yaml"
                    ),
                    HttpStatus.NOT_FOUND.value()
            );
        }

        return new ErrorMessage(exception, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserDeactivationNotAllowedException.class)
    @ResponseBody
    public ErrorMessage forbidden(UserDeactivationNotAllowedException exception) {
        return new ErrorMessage(exception, HttpStatus.FORBIDDEN.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorMessage exception(Exception exception) {
        return new ErrorMessage(
                new RuntimeException("ERROR"),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }
}