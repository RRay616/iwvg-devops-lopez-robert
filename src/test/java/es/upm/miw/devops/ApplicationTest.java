package es.upm.miw.devops;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApplicationTest {

    @Test
    void shouldStartApplication() {
        try (MockedStatic<SpringApplication> springApplication =
                     Mockito.mockStatic(SpringApplication.class)) {

            String[] args = {};

            Application.main(args);

            springApplication.verify(
                    () -> SpringApplication.run(Application.class, args)
            );
        }
    }

    @Test
    void shouldHaveSpringBootApplicationAnnotation() {
        assertNotNull(
                Application.class.getAnnotation(
                        org.springframework.boot.autoconfigure.SpringBootApplication.class
                )
        );
    }
}