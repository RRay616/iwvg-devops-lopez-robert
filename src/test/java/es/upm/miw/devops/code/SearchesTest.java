package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchesTest {

    private final Searches searches = new Searches();

    @Test
    void shouldFindFamilyNamesByUserNameDistinct() {
        assertEquals(
                List.of("Fernandez", "López"),
                searches.findUserFamilyNameByUserNameDistinct("Oscar").toList()
        );
    }

    @Test
    void shouldReturnEmptyWhenUserNameDoesNotExist() {
        assertTrue(
                searches.findUserFamilyNameByUserNameDistinct("Unknown").toList().isEmpty()
        );
    }

    @Test
    void shouldFindFractionNumeratorsByUserFamilyName() {
        assertEquals(
                List.of(2, -1, 2, 4, 0, 0, 0),
                searches.findFractionNumeratorByUserFamilyName("Blanco").toList()
        );
    }

    @Test
    void shouldReturnEmptyWhenFamilyNameDoesNotExist() {
        assertTrue(
                searches.findFractionNumeratorByUserFamilyName("Unknown").toList().isEmpty()
        );
    }

    @Test
    void shouldFindUserFamilyNamesByFractionDenominator() {
        assertEquals(
                List.of("Blanco", "López", "Torres"),
                searches.findUserFamilyNameByFractionDenominator(4).toList()
        );
    }

    @Test
    void shouldReturnEmptyWhenDenominatorDoesNotExist() {
        assertTrue(
                searches.findUserFamilyNameByFractionDenominator(99).toList().isEmpty()
        );
    }

    @Test
    void shouldReturnEmptyForFamilyNameInitialByAnyProperFraction() {
        assertTrue(
                searches.findUserFamilyNameInitialByAnyProperFraction()
                        .toList()
                        .isEmpty()
        );
    }

    @Test
    void shouldReturnEmptyForUserIdByAnyProperFraction() {
        assertTrue(
                searches.findUserIdByAnyProperFraction()
                        .toList()
                        .isEmpty()
        );
    }

    @Test
    void shouldReturnNullForFractionMultiplicationByUserFamilyName() {
        assertNull(
                searches.findFractionMultiplicationByUserFamilyName("Fernandez")
        );
    }

    @Test
    void shouldReturnNullForFirstFractionDivisionByUserId() {
        assertNull(
                searches.findFirstFractionDivisionByUserId("1")
        );
    }

    @Test
    void shouldReturnNullForFirstDecimalFractionByUserName() {
        assertNull(
                searches.findFirstDecimalFractionByUserName("Oscar")
        );
    }

    @Test
    void shouldReturnEmptyForUserIdByAllProperFraction() {
        assertTrue(
                searches.findUserIdByAllProperFraction()
                        .toList()
                        .isEmpty()
        );
    }

    @Test
    void shouldReturnEmptyForDecimalImproperFractionByUserName() {
        assertTrue(
                searches.findDecimalImproperFractionByUserName("Oscar")
                        .toList()
                        .isEmpty()
        );
    }

    @Test
    void shouldReturnNullForFirstProperFractionByUserId() {
        assertNull(
                searches.findFirstProperFractionByUserId("1")
        );
    }

    @Test
    void shouldReturnEmptyForUserFamilyNameByImproperFraction() {
        assertTrue(
                searches.findUserFamilyNameByImproperFraction()
                        .toList()
                        .isEmpty()
        );
    }

    @Test
    void shouldReturnNullForHighestFraction() {
        assertNull(
                searches.findHighestFraction()
        );
    }

    @Test
    void shouldReturnEmptyForUserNameByAnyImproperFraction() {
        assertTrue(
                searches.findUserNameByAnyImproperFraction()
                        .toList()
                        .isEmpty()
        );
    }

    @Test
    void shouldReturnEmptyForFamilyNameByAllNegativeSignFractionDistinct() {
        assertTrue(
                searches.findUserFamilyNameByAllNegativeSignFractionDistinct()
                        .toList()
                        .isEmpty()
        );
    }

    @Test
    void shouldReturnEmptyForDecimalFractionByUserName() {
        assertTrue(
                searches.findDecimalFractionByUserName("Oscar")
                        .toList()
                        .isEmpty()
        );
    }

    @Test
    void shouldReturnEmptyForDecimalFractionByNegativeSignFraction() {
        assertTrue(
                searches.findDecimalFractionByNegativeSignFraction()
                        .toList()
                        .isEmpty()
        );
    }

    @Test
    void shouldReturnNullForFractionAdditionByUserId() {
        assertNull(
                searches.findFractionAdditionByUserId("1")
        );
    }

    @Test
    void shouldReturnNullForFirstFractionSubtractionByUserName() {
        assertNull(
                searches.findFirstFractionSubtractionByUserName("Oscar")
        );
    }
}