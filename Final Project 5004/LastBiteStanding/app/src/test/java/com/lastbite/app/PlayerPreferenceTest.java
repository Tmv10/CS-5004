/**
 * Unit test suite for the PlayerPreference class.
 * Tests both basic functionality and Parcelable implementation of the PlayerPreference class.
 * Uses Robolectric test runner to simulate Android framework components.
 *
 * <p>The test suite covers:</p>
 * <ul>
 *   <li>Basic constructor functionality with various input types</li>
 *   <li>Parcelable implementation for data persistence</li>
 *   <li>Edge cases including null values and special characters</li>
 *   <li>Creator functionality for array creation</li>
 * </ul>
 */
package com.lastbite.app;

import android.os.Parcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class PlayerPreferenceTest {

    /**
     * Tests the constructor with valid string inputs.
     * Verifies that name and cuisine are correctly stored.
     */
    @Test
    public void testConstructorWithValidInput() {
        String name = "John";
        String cuisine = "Italian";
        PlayerPreference preference = new PlayerPreference(name, cuisine);

        assertEquals("Name should match constructor input", name, preference.name);
        assertEquals("Cuisine should match constructor input", cuisine, preference.cuisine);
    }

    /**
     * Tests the constructor with empty strings.
     * Verifies that empty strings are handled correctly.
     */
    @Test
    public void testConstructorWithEmptyStrings() {
        PlayerPreference preference = new PlayerPreference("", "");

        assertEquals("Name should be empty string", "", preference.name);
        assertEquals("Cuisine should be empty string", "", preference.cuisine);
    }

    /**
     * Tests the constructor with null values.
     * Verifies that null values are handled correctly.
     */
    @Test
    public void testConstructorWithNullValues() {
        PlayerPreference preference = new PlayerPreference(null, null);

        assertNull("Name should be null", preference.name);
        assertNull("Cuisine should be null", preference.cuisine);
    }

    /**
     * Tests the constructor with strings containing special characters.
     * Verifies that special characters are preserved.
     */
    @Test
    public void testConstructorWithSpecialCharacters() {
        String specialName = "John!@#$%^&*()";
        String specialCuisine = "Italian~`{}[]|\\";
        PlayerPreference preference = new PlayerPreference(specialName, specialCuisine);

        assertEquals("Name should handle special characters", specialName, preference.name);
        assertEquals("Cuisine should handle special characters", specialCuisine, preference.cuisine);
    }

    /**
     * Tests the Parcelable implementation with valid string inputs.
     * Verifies that object can be correctly written to and read from a Parcel.
     */
    @Test
    public void testParcelableWriteAndRead() {
        String name = "John";
        String cuisine = "Italian";
        PlayerPreference originalPreference = new PlayerPreference(name, cuisine);

        Parcel parcel = Parcel.obtain();
        originalPreference.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        PlayerPreference createdFromParcel = PlayerPreference.CREATOR.createFromParcel(parcel);

        assertEquals("Name should survive parceling", name, createdFromParcel.name);
        assertEquals("Cuisine should survive parceling", cuisine, createdFromParcel.cuisine);

        parcel.recycle();
    }

    /**
     * Tests the Parcelable implementation with empty strings.
     * Verifies that empty strings are preserved through the parceling process.
     */
    @Test
    public void testParcelableWithEmptyStrings() {
        PlayerPreference originalPreference = new PlayerPreference("", "");

        Parcel parcel = Parcel.obtain();
        originalPreference.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        PlayerPreference createdFromParcel = PlayerPreference.CREATOR.createFromParcel(parcel);

        assertEquals("Empty name should survive parceling", "", createdFromParcel.name);
        assertEquals("Empty cuisine should survive parceling", "", createdFromParcel.cuisine);

        parcel.recycle();
    }

    /**
     * Tests the Parcelable implementation with null values.
     * Verifies that null values are preserved through the parceling process.
     */
    @Test
    public void testParcelableWithNullValues() {
        PlayerPreference originalPreference = new PlayerPreference(null, null);

        Parcel parcel = Parcel.obtain();
        originalPreference.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        PlayerPreference createdFromParcel = PlayerPreference.CREATOR.createFromParcel(parcel);

        assertNull("Null name should survive parceling", createdFromParcel.name);
        assertNull("Null cuisine should survive parceling", createdFromParcel.cuisine);

        parcel.recycle();
    }

    /**
     * Tests the Parcelable implementation with strings containing special characters.
     * Verifies that special characters are preserved through the parceling process.
     */
    @Test
    public void testParcelableWithSpecialCharacters() {
        String specialName = "John!@#$%^&*()";
        String specialCuisine = "Italian~`{}[]|\\";
        PlayerPreference originalPreference = new PlayerPreference(specialName, specialCuisine);

        Parcel parcel = Parcel.obtain();
        originalPreference.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        PlayerPreference createdFromParcel = PlayerPreference.CREATOR.createFromParcel(parcel);

        assertEquals("Special characters in name should survive parceling",
                specialName, createdFromParcel.name);
        assertEquals("Special characters in cuisine should survive parceling",
                specialCuisine, createdFromParcel.cuisine);

        parcel.recycle();
    }

    /**
     * Tests the Parcelable implementation with very long strings.
     * Verifies that long strings are handled correctly in the parceling process.
     */
    @Test
    public void testParcelableWithLongStrings() {
        String longName = "a".repeat(1000);
        String longCuisine = "b".repeat(1000);
        PlayerPreference originalPreference = new PlayerPreference(longName, longCuisine);

        Parcel parcel = Parcel.obtain();
        originalPreference.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        PlayerPreference createdFromParcel = PlayerPreference.CREATOR.createFromParcel(parcel);

        assertEquals("Long name should survive parceling", longName, createdFromParcel.name);
        assertEquals("Long cuisine should survive parceling", longCuisine, createdFromParcel.cuisine);

        parcel.recycle();
    }

    /**
     * Tests the describeContents method.
     * Verifies that it returns the expected value of 0.
     */
    @Test
    public void testDescribeContents() {
        PlayerPreference preference = new PlayerPreference("John", "Italian");
        assertEquals("describeContents should return 0", 0, preference.describeContents());
    }

    /**
     * Tests the CREATOR.newArray method with a positive size.
     * Verifies that the created array has the correct length and initial values.
     */
    @Test
    public void testCreatorNewArray() {
        PlayerPreference[] array = PlayerPreference.CREATOR.newArray(5);

        assertNotNull("Created array should not be null", array);
        assertEquals("Created array should have correct length", 5, array.length);
        assertNull("Array elements should be null initially", array[0]);
    }

    /**
     * Tests the CREATOR.newArray method with zero size.
     * Verifies that an empty array is created correctly.
     */
    @Test
    public void testCreatorNewArrayWithZeroSize() {
        PlayerPreference[] array = PlayerPreference.CREATOR.newArray(0);

        assertNotNull("Created array should not be null", array);
        assertEquals("Created array should have zero length", 0, array.length);
    }

    /**
     * Tests the CREATOR.newArray method with negative size.
     * Verifies that a NegativeArraySizeException is thrown.
     *
     * @throws NegativeArraySizeException when a negative size is provided
     */
    @Test(expected = NegativeArraySizeException.class)
    public void testCreatorNewArrayWithNegativeSize() {
        PlayerPreference.CREATOR.newArray(-1);
    }
}