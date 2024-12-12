/**
 * Unit test suite for the RestaurantCard class.
 * Tests the data model's construction and getter methods with various input types
 * and edge cases to ensure proper data handling.
 *
 * <p>The test suite covers:</p>
 * <ul>
 *   <li>Valid parameter handling</li>
 *   <li>Edge cases (empty strings, null values)</li>
 *   <li>Special character handling</li>
 *   <li>Long string support</li>
 *   <li>Whitespace handling</li>
 * </ul>
 */
package com.lastbite.app;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class RestaurantCardTest {
    /** Test instance of RestaurantCard */
    private RestaurantCard restaurantCard;

    /** Valid test restaurant name */
    private final String VALID_NAME = "Joe's Diner";

    /** Valid test cuisine type */
    private final String VALID_CUISINE = "American";

    /** Valid test rating */
    private final String VALID_RATING = "4.5";

    /** Valid test address */
    private final String VALID_ADDRESS = "123 Main St";

    /** Valid test suggestion description */
    private final String VALID_SUGGESTED_FOR = "Breakfast";

    /**
     * Sets up the test environment before each test.
     * Creates a RestaurantCard instance with valid test data.
     */
    @Before
    public void setUp() {
        restaurantCard = new RestaurantCard(
                VALID_NAME,
                VALID_CUISINE,
                VALID_RATING,
                VALID_ADDRESS,
                VALID_SUGGESTED_FOR
        );
    }

    /**
     * Tests constructor with valid parameters.
     * Verifies that all fields are correctly initialized.
     */
    @Test
    public void testConstructorWithValidParameters() {
        assertNotNull(restaurantCard);
        assertEquals(VALID_NAME, restaurantCard.getName());
        assertEquals(VALID_CUISINE, restaurantCard.getCuisine());
        assertEquals(VALID_RATING, restaurantCard.getRating());
        assertEquals(VALID_ADDRESS, restaurantCard.getAddress());
        assertEquals(VALID_SUGGESTED_FOR, restaurantCard.getSuggestedFor());
    }

    /**
     * Tests constructor with empty strings.
     * Verifies that empty strings are properly stored.
     */
    @Test
    public void testConstructorWithEmptyStrings() {
        RestaurantCard emptyCard = new RestaurantCard("", "", "", "", "");

        assertEquals("", emptyCard.getName());
        assertEquals("", emptyCard.getCuisine());
        assertEquals("", emptyCard.getRating());
        assertEquals("", emptyCard.getAddress());
        assertEquals("", emptyCard.getSuggestedFor());
    }

    /**
     * Tests constructor with null values.
     * Verifies that null values are properly handled.
     */
    @Test
    public void testConstructorWithNullValues() {
        RestaurantCard nullCard = new RestaurantCard(null, null, null, null, null);

        assertNull(nullCard.getName());
        assertNull(nullCard.getCuisine());
        assertNull(nullCard.getRating());
        assertNull(nullCard.getAddress());
        assertNull(nullCard.getSuggestedFor());
    }

    /**
     * Tests the getName getter method.
     * Verifies correct retrieval of restaurant name.
     */
    @Test
    public void testGetName() {
        assertEquals(VALID_NAME, restaurantCard.getName());
    }

    /**
     * Tests the getCuisine getter method.
     * Verifies correct retrieval of cuisine type.
     */
    @Test
    public void testGetCuisine() {
        assertEquals(VALID_CUISINE, restaurantCard.getCuisine());
    }

    /**
     * Tests the getRating getter method.
     * Verifies correct retrieval of rating.
     */
    @Test
    public void testGetRating() {
        assertEquals(VALID_RATING, restaurantCard.getRating());
    }

    /**
     * Tests the getAddress getter method.
     * Verifies correct retrieval of address.
     */
    @Test
    public void testGetAddress() {
        assertEquals(VALID_ADDRESS, restaurantCard.getAddress());
    }

    /**
     * Tests the getSuggestedFor getter method.
     * Verifies correct retrieval of suggestion description.
     */
    @Test
    public void testGetSuggestedFor() {
        assertEquals(VALID_SUGGESTED_FOR, restaurantCard.getSuggestedFor());
    }

    /**
     * Tests constructor with strings containing special characters.
     * Verifies that special characters are properly stored and retrieved.
     */
    @Test
    public void testConstructorWithSpecialCharacters() {
        String specialChars = "!@#$%^&*()_+-=[]{}|;:'\",.<>?/\\";
        RestaurantCard specialCard = new RestaurantCard(
                specialChars,
                specialChars,
                specialChars,
                specialChars,
                specialChars
        );

        assertEquals(specialChars, specialCard.getName());
        assertEquals(specialChars, specialCard.getCuisine());
        assertEquals(specialChars, specialCard.getRating());
        assertEquals(specialChars, specialCard.getAddress());
        assertEquals(specialChars, specialCard.getSuggestedFor());
    }

    /**
     * Tests constructor with very long strings.
     * Verifies that long strings are properly handled.
     */
    @Test
    public void testConstructorWithLongStrings() {
        String longString = "a".repeat(1000);
        RestaurantCard longCard = new RestaurantCard(
                longString,
                longString,
                longString,
                longString,
                longString
        );

        assertEquals(longString, longCard.getName());
        assertEquals(longString, longCard.getCuisine());
        assertEquals(longString, longCard.getRating());
        assertEquals(longString, longCard.getAddress());
        assertEquals(longString, longCard.getSuggestedFor());
    }

    /**
     * Tests constructor with whitespace-only strings.
     * Verifies that whitespace strings are properly handled.
     */
    @Test
    public void testConstructorWithWhitespaceStrings() {
        String whitespace = "   ";
        RestaurantCard whitespaceCard = new RestaurantCard(
                whitespace,
                whitespace,
                whitespace,
                whitespace,
                whitespace
        );

        assertEquals(whitespace, whitespaceCard.getName());
        assertEquals(whitespace, whitespaceCard.getCuisine());
        assertEquals(whitespace, whitespaceCard.getRating());
        assertEquals(whitespace, whitespaceCard.getAddress());
        assertEquals(whitespace, whitespaceCard.getSuggestedFor());
    }
}