/**
 * Unit test suite for the PlayerSetupActivity class.
 * Tests the player setup flow including input validation, UI state management,
 * and navigation to the game activity. Uses Robolectric for Android framework simulation.
 *
 * <p>The test suite covers:</p>
 * <ul>
 *   <li>Initial activity state and view initialization</li>
 *   <li>Location data handling from intent</li>
 *   <li>Player input creation and validation</li>
 *   <li>Navigation to game activity</li>
 *   <li>Error handling and user feedback</li>
 *   <li>Cuisine selection options</li>
 * </ul>
 */
package com.lastbite.app;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.robolectric.shadows.ShadowView.clickOn;

@RunWith(RobolectricTestRunner.class)
public class PlayerSetupActivityTest {

    /** The activity under test */
    private PlayerSetupActivity activity;

    /** Input field for number of players */
    private EditText numPlayersInput;

    /** Button to confirm number of players */
    private Button confirmPlayersBtn;

    /** Container for dynamically created player input fields */
    private LinearLayout playerInputsContainer;

    /** Button to start the game */
    private Button startGameBtn;

    /** Test latitude value for location testing */
    private static final double TEST_LATITUDE = 37.7749;

    /** Test longitude value for location testing */
    private static final double TEST_LONGITUDE = -122.4194;

    /**
     * Sets up the test environment before each test.
     * Creates the activity with test coordinates and initializes all view references.
     */
    @Before
    public void setUp() {
        Intent intent = new Intent(RuntimeEnvironment.application, PlayerSetupActivity.class);
        intent.putExtra("latitude", TEST_LATITUDE);
        intent.putExtra("longitude", TEST_LONGITUDE);

        activity = Robolectric.buildActivity(PlayerSetupActivity.class, intent)
                .create()
                .start()
                .resume()
                .get();

        numPlayersInput = activity.findViewById(R.id.numPlayersInput);
        confirmPlayersBtn = activity.findViewById(R.id.confirmPlayersBtn);
        playerInputsContainer = activity.findViewById(R.id.playerInputsContainer);
        startGameBtn = activity.findViewById(R.id.startGameBtn);
    }

    /**
     * Tests the initial state of the activity.
     * Verifies that all views are properly initialized and in their default states.
     */
    @Test
    public void testInitialState() {
        assertNotNull("Activity should not be null", activity);
        assertNotNull("Number of players input should not be null", numPlayersInput);
        assertNotNull("Confirm players button should not be null", confirmPlayersBtn);
        assertNotNull("Player inputs container should not be null", playerInputsContainer);
        assertNotNull("Start game button should not be null", startGameBtn);

        assertEquals("Player inputs container should be empty initially",
                0, playerInputsContainer.getChildCount());
        assertEquals("Start game button should be invisible initially",
                View.GONE, startGameBtn.getVisibility());
    }

    /**
     * Tests that location coordinates are correctly extracted from the intent.
     */
    @Test
    public void testLocationExtraction() {
        assertEquals("Latitude should match intent value",
                TEST_LATITUDE, activity.getIntent().getDoubleExtra("latitude", 0), 0.0001);
        assertEquals("Longitude should match intent value",
                TEST_LONGITUDE, activity.getIntent().getDoubleExtra("longitude", 0), 0.0001);
    }

    /**
     * Tests player input creation with a valid number of players.
     * Verifies that correct number of input fields are created with proper initialization.
     */
    @Test
    public void testCreatePlayerInputsWithValidNumber() {
        numPlayersInput.setText("3");
        confirmPlayersBtn.performClick();

        assertEquals("Should create correct number of player inputs",
                3, playerInputsContainer.getChildCount());
        assertEquals("Start game button should be visible",
                View.VISIBLE, startGameBtn.getVisibility());

        for (int i = 0; i < 3; i++) {
            View playerView = playerInputsContainer.getChildAt(i);
            EditText nameInput = playerView.findViewById(R.id.playerNameInput);
            Spinner cuisineSpinner = playerView.findViewById(R.id.cuisineSpinner);

            assertNotNull("Player name input should not be null", nameInput);
            assertNotNull("Cuisine spinner should not be null", cuisineSpinner);
            assertEquals("Player hint should be correct",
                    "Player " + (i + 1) + " name", nameInput.getHint().toString());
        }
    }

    /**
     * Tests error handling when no number of players is entered.
     * Verifies appropriate error message and no input fields created.
     */
    @Test
    public void testCreatePlayerInputsWithEmptyInput() {
        numPlayersInput.setText("");
        confirmPlayersBtn.performClick();

        assertEquals("Should show error for empty input",
                "Please enter number of players", ShadowToast.getTextOfLatestToast());
        assertEquals("Should not create any player inputs",
                0, playerInputsContainer.getChildCount());
    }

    /**
     * Tests validation of minimum player count.
     * Verifies error handling when too few players are specified.
     */
    @Test
    public void testCreatePlayerInputsWithTooFewPlayers() {
        numPlayersInput.setText("1");
        confirmPlayersBtn.performClick();

        assertEquals("Should show error for too few players",
                "Number of players must be between 2 and 10", ShadowToast.getTextOfLatestToast());
        assertEquals("Should not create any player inputs",
                0, playerInputsContainer.getChildCount());
    }

    /**
     * Tests validation of maximum player count.
     * Verifies error handling when too many players are specified.
     */
    @Test
    public void testCreatePlayerInputsWithTooManyPlayers() {
        numPlayersInput.setText("11");
        confirmPlayersBtn.performClick();

        assertEquals("Should show error for too many players",
                "Number of players must be between 2 and 10", ShadowToast.getTextOfLatestToast());
        assertEquals("Should not create any player inputs",
                0, playerInputsContainer.getChildCount());
    }

    /**
     * Tests successful game start with valid player inputs.
     * Verifies proper intent creation and data passing to next activity.
     */
    @Test
    public void testValidateAndProceedWithValidInput() {
        numPlayersInput.setText("2");
        confirmPlayersBtn.performClick();

        for (int i = 0; i < 2; i++) {
            View playerView = playerInputsContainer.getChildAt(i);
            EditText nameInput = playerView.findViewById(R.id.playerNameInput);
            nameInput.setText("Player " + (i + 1));
        }

        startGameBtn.performClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent nextActivity = shadowActivity.getNextStartedActivity();
        assertNotNull("Should start next activity", nextActivity);
        assertEquals("Should start GameActivity",
                GameActivity.class.getName(), nextActivity.getComponent().getClassName());

        assertEquals("Latitude should be passed to next activity",
                TEST_LATITUDE, nextActivity.getDoubleExtra("latitude", 0), 0.0001);
        assertEquals("Longitude should be passed to next activity",
                TEST_LONGITUDE, nextActivity.getDoubleExtra("longitude", 0), 0.0001);

        ArrayList<PlayerPreference> players = nextActivity.getParcelableArrayListExtra("players");
        assertNotNull("Players list should not be null", players);
        assertEquals("Should have correct number of players", 2, players.size());
    }

    /**
     * Tests error handling when player names are not provided.
     * Verifies appropriate error message and prevented navigation.
     */
    @Test
    public void testValidateAndProceedWithEmptyNames() {
        numPlayersInput.setText("2");
        confirmPlayersBtn.performClick();

        startGameBtn.performClick();

        assertEquals("Should show error for empty names",
                "Please enter all player names", ShadowToast.getTextOfLatestToast());

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        assertNull("Should not start next activity", shadowActivity.getNextStartedActivity());
    }

    /**
     * Tests cuisine spinner initialization and content.
     * Verifies that cuisine options are properly populated.
     */
    @Test
    public void testCuisineSpinnerContents() {
        numPlayersInput.setText("2");
        confirmPlayersBtn.performClick();

        assertTrue("Player inputs container should have children",
                playerInputsContainer.getChildCount() > 0);

        View playerView = playerInputsContainer.getChildAt(0);
        assertNotNull("Player view should not be null", playerView);

        Spinner cuisineSpinner = playerView.findViewById(R.id.cuisineSpinner);
        assertNotNull("Cuisine spinner should not be null", cuisineSpinner);
        assertTrue("Cuisine spinner should have items", cuisineSpinner.getCount() > 0);

        boolean hasAmerican = false;
        boolean hasItalian = false;
        for (int i = 0; i < cuisineSpinner.getCount(); i++) {
            String cuisine = cuisineSpinner.getItemAtPosition(i).toString();
            if (cuisine.equals("American")) hasAmerican = true;
            if (cuisine.equals("Italian")) hasItalian = true;
        }
        assertTrue("Should contain American cuisine", hasAmerican);
        assertTrue("Should contain Italian cuisine", hasItalian);
    }

    /**
     * Tests that player input fields are properly cleared when number of players changes.
     * Verifies container is reset with new number of inputs.
     */
    @Test
    public void testPlayerInputsClearOnNewNumberInput() {
        numPlayersInput.setText("3");
        confirmPlayersBtn.performClick();

        int initialCount = playerInputsContainer.getChildCount();

        numPlayersInput.setText("2");
        confirmPlayersBtn.performClick();

        assertNotEquals("Should clear previous inputs",
                initialCount, playerInputsContainer.getChildCount());
        assertEquals("Should have new number of inputs",
                2, playerInputsContainer.getChildCount());
    }

    /**
     * Tests validation when only some player names are filled.
     * Verifies appropriate error handling and prevented navigation.
     */
    @Test
    public void testStartGameWithPartiallyFilledNames() {
        numPlayersInput.setText("2");
        confirmPlayersBtn.performClick();

        View playerView = playerInputsContainer.getChildAt(0);
        EditText nameInput = playerView.findViewById(R.id.playerNameInput);
        nameInput.setText("Player 1");

        startGameBtn.performClick();

        assertEquals("Should show error for incomplete names",
                "Please enter all player names", ShadowToast.getTextOfLatestToast());

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        assertNull("Should not start next activity", shadowActivity.getNextStartedActivity());
    }
}