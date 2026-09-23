package es.upm.miw.devops.resources.exceptionshandler;

import es.upm.miw.devops.resources.exceptionshandler.ErrorMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorMessageTest {

    @Test
    void shouldCreateErrorMessage() {
        IllegalArgumentException exception =
                new IllegalArgumentException("Invalid argument");

        ErrorMessage errorMessage = new ErrorMessage(exception, 400);

        assertEquals(
                "IllegalArgumentException",
                errorMessage.getError()
        );
        assertEquals(
                "Invalid argument",
                errorMessage.getMessage()
        );
        assertEquals(
                400,
                errorMessage.getCode()
        );
    }

    @Test
    void shouldReturnErrorMessageAsString() {
        IllegalArgumentException exception =
                new IllegalArgumentException("Invalid argument");

        ErrorMessage errorMessage = new ErrorMessage(exception, 400);

        assertEquals(
                "ErrorMessage{error='IllegalArgumentException', message='Invalid argument', code=400}",
                errorMessage.toString()
        );
    }
}