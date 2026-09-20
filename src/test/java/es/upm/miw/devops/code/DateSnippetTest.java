package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DateSnippetTest {

    @Test
    void shouldCreateDate() {
        DateSnippet dateSnippet = new DateSnippet();

        assertDoesNotThrow(dateSnippet::createDate);
    }

    @Test
    void shouldCreateTime() {
        DateSnippet dateSnippet = new DateSnippet();

        assertDoesNotThrow(dateSnippet::createTime);
    }

    @Test
    void shouldCreateDateTime() {
        DateSnippet dateSnippet = new DateSnippet();

        assertDoesNotThrow(dateSnippet::createDateTime);
    }

    @Test
    void shouldCreateInstant() {
        DateSnippet dateSnippet = new DateSnippet();

        assertDoesNotThrow(dateSnippet::instant);
    }

    @Test
    void shouldRunMain() {
        assertDoesNotThrow(() -> DateSnippet.main(new String[0]));
    }
}