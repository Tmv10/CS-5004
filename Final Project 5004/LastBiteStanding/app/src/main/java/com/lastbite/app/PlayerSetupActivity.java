/**
 * An Activity that handles the initial setup of players for the LastBite application.
 * This activity allows users to specify the number of players and their preferences,
 * including names and preferred cuisine types, before starting the game.
 *
 * <p>The activity receives location coordinates from the previous screen and forwards
 * them along with player preferences to the GameActivity. It supports between 2 to 10
 * players and offers a selection of cuisine types from the Google Places API.</p>
 */
package com.lastbite.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerSetupActivity extends AppCompatActivity {
    /** EditText for inputting the number of players */
    private EditText numPlayersInput;

    /** Button to confirm the number of players and generate input fields */
    private Button confirmPlayersBtn;

    /** Container for dynamically generated player input fields */
    private LinearLayout playerInputsContainer;

    /** Button to start the game after player information is entered */
    private Button startGameBtn;

    /** List to store player preferences */
    private List<String> playerPreferences;

    /** Latitude coordinate received from previous activity */
    private double latitude;

    /** Longitude coordinate received from previous activity */
    private double longitude;

    /** ArrayList to store PlayerPreference objects for each player */
    private ArrayList<PlayerPreference> playersList;

    /**
     * Array of cuisine types available for selection, sourced from Google Places API.
     * These options are presented to each player in a dropdown spinner.
     */
    private final String[] cuisineTypes = {
            "American", "Chinese", "French", "Greek", "Indian", "Italian", "Japanese",
            "Korean", "Mexican", "Mediterranean", "Thai", "Vietnamese", "BBQ",
            "Seafood", "Pizza", "Vegetarian", "Vegan", "Halal", "Kosher"
    };

    /**
     * Initializes the activity, sets up view listeners, and retrieves location data
     * from the intent that started this activity.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                          being shut down, this contains the most recent data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_setup);

        // Get location from intent
        latitude = getIntent().getDoubleExtra("latitude", 0);
        longitude = getIntent().getDoubleExtra("longitude", 0);

        // Initialize views
        initializeViews();

        confirmPlayersBtn.setOnClickListener(v -> createPlayerInputs());
        startGameBtn.setOnClickListener(v -> validateAndProceed());
    }

    /**
     * Initializes all view references and creates new ArrayLists for storing
     * player preferences and PlayerPreference objects.
     */
    private void initializeViews() {
        numPlayersInput = findViewById(R.id.numPlayersInput);
        confirmPlayersBtn = findViewById(R.id.confirmPlayersBtn);
        playerInputsContainer = findViewById(R.id.playerInputsContainer);
        startGameBtn = findViewById(R.id.startGameBtn);
        playerPreferences = new ArrayList<>();
        playersList = new ArrayList<>();
    }

    /**
     * Dynamically creates input fields for each player based on the number entered.
     * Validates that the number of players is between 2 and 10, then creates a name
     * input field and cuisine type spinner for each player.
     *
     * <p>If validation fails, displays an error message using Toast.</p>
     */
    private void createPlayerInputs() {
        String numPlayersStr = numPlayersInput.getText().toString();
        if (numPlayersStr.isEmpty()) {
            showError("Please enter number of players");
            return;
        }

        int numPlayers = Integer.parseInt(numPlayersStr);
        if (numPlayers < 2 || numPlayers > 10) {
            showError("Number of players must be between 2 and 10");
            return;
        }

        playerInputsContainer.removeAllViews();
        playerPreferences.clear();

        for (int i = 0; i < numPlayers; i++) {
            View playerView = getLayoutInflater().inflate(R.layout.player_input_item, null);

            EditText playerNameInput = playerView.findViewById(R.id.playerNameInput);
            Spinner cuisineSpinner = playerView.findViewById(R.id.cuisineSpinner);

            playerNameInput.setHint("Player " + (i + 1) + " name");

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    cuisineTypes
            );
            cuisineSpinner.setAdapter(adapter);

            playerInputsContainer.addView(playerView);
        }

        startGameBtn.setVisibility(View.VISIBLE);
    }

    /**
     * Validates all player inputs and proceeds to the GameActivity if validation passes.
     * Creates PlayerPreference objects for each player and passes them along with
     * location data to the GameActivity.
     *
     * <p>Validation ensures that all player names are non-empty. If validation fails,
     * displays an error message using Toast.</p>
     */
    private void validateAndProceed() {
        boolean isValid = true;
        playersList.clear();

        for (int i = 0; i < playerInputsContainer.getChildCount(); i++) {
            View playerView = playerInputsContainer.getChildAt(i);
            EditText playerNameInput = playerView.findViewById(R.id.playerNameInput);
            Spinner cuisineSpinner = playerView.findViewById(R.id.cuisineSpinner);

            String name = playerNameInput.getText().toString().trim();
            String cuisine = cuisineSpinner.getSelectedItem().toString();

            if (name.isEmpty()) {
                isValid = false;
                showError("Please enter all player names");
                break;
            }

            playersList.add(new PlayerPreference(name, cuisine));
        }

        if (isValid) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);
            intent.putParcelableArrayListExtra("players", (ArrayList<? extends Parcelable>) playersList);
            startActivity(intent);
        }
    }

    /**
     * Displays an error message to the user using a Toast notification.
     *
     * @param message The error message to display
     */
    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}