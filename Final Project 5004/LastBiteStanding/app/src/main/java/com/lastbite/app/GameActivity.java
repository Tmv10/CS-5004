/**
 * A core gameplay activity that manages the restaurant selection and veto process for the LastBite app.
 * This activity implements a turn-based voting system where players can veto restaurant choices until
 * a final selection is made, combining user preferences with location-based restaurant recommendations
 * from the Google Places API.
 *
 * The activity manages multiple aspects of the game:
 * - Player turn management
 * - Restaurant fetching based on player preferences
 * - Veto system implementation
 * - Final restaurant selection
 * - Google Maps integration
 */
package com.lastbite.app;

import android.os.Bundle;
import android.graphics.Canvas;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.res.ColorStateList;
import android.graphics.Color;


public class GameActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private List<RestaurantCard> restaurants;
    private List<PlayerPreference> players;
    private TextView currentPlayerText;
    private int currentPlayerIndex = 0;
    private int vetosRemaining;
    private PlacesClient placesClient;
    private double latitude;
    private double longitude;


    /**
     * Initializes the game activity and sets up the necessary components for gameplay.
     * This method handles the initialization of Google Places API, retrieves player preferences
     * and location data from the intent, and sets up the UI components including the RecyclerView
     * for restaurant cards.
     *
     * @param savedInstanceState Bundle containing the activity's previously saved state, if any
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        latitude = getIntent().getDoubleExtra("latitude", 0);
        longitude = getIntent().getDoubleExtra("longitude", 0);
        players = getIntent().getParcelableArrayListExtra("players");
        vetosRemaining = players.size();

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
        }
        placesClient = Places.createClient(this);

        initializeViews();
        setupRecyclerView();
        fetchRestaurants();
        updateCurrentPlayerText();
    }

    /**
     * Initializes and sets up all view components used in the activity.
     * This includes the current player text display and the RecyclerView for restaurants.
     */
    private void initializeViews() {
        currentPlayerText = findViewById(R.id.currentPlayerText);
        recyclerView = findViewById(R.id.restaurantsRecyclerView);
    }

    /**
     * Configures the RecyclerView with appropriate layout manager, adapter, and swipe functionality.
     * Sets up the ItemTouchHelper for handling left swipe gestures that represent veto actions.
     * The swipe callback includes visual feedback and handles the veto logic for removing restaurants.
     */
    private void setupRecyclerView() {
        restaurants = new ArrayList<>();
        adapter = new RestaurantAdapter(restaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback swipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (vetosRemaining > 0) {
                    adapter.removeItem(position);
                    vetosRemaining--;

                    if (vetosRemaining > 0) {
                        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                        updateCurrentPlayerText();
                    } else {
                        showFinalResult();
                    }
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    float alpha = 1.0f - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                    viewHolder.itemView.setAlpha(alpha);
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        new ItemTouchHelper(swipeCallback).attachToRecyclerView(recyclerView);
    }

    /**
     * Configures the RecyclerView with appropriate layout manager, adapter, and swipe functionality.
     * Sets up the ItemTouchHelper for handling left swipe gestures that represent veto actions.
     * The swipe callback includes visual feedback and handles the veto logic for removing restaurants.
     */
    private void updateCurrentPlayerText() {
        if (vetosRemaining > 0) {
            PlayerPreference currentPlayer = players.get(currentPlayerIndex);
            currentPlayerText.setText(currentPlayer.name + "'s turn to veto");
        }
    }

    /**
     * Displays the final selected restaurant and creates a replay button.
     * This method is called when all vetos have been used and only one restaurant remains.
     * It updates the UI to show the final selection and provides options to:
     * - View the result
     * - Open the restaurant in Google Maps
     * - Start a new game
     */
    private void showFinalResult() {
        if (restaurants.size() == 1) {
            RestaurantCard finalChoice = restaurants.get(0);
            currentPlayerText.setText("Final Selection: " + finalChoice.getName());

            Button replayButton = new Button(this);
            replayButton.setText("Play Again");
            replayButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_green)));
            replayButton.setTextColor(Color.WHITE);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(32, 32, 32, 32);
            replayButton.setLayoutParams(params);

            LinearLayout parentLayout = findViewById(R.id.gameLayout);
            parentLayout.addView(replayButton);

            replayButton.setOnClickListener(v -> restartGame());

            Toast.makeText(this, "Tap the restaurant card to open in Maps!",
                    Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Interface for handling asynchronous restaurant fetching operations.
     * Provides a callback method to be executed when a restaurant has been successfully fetched.
     */
    private interface RestaurantFetchCallback {
        void onRestaurantFetched();
    }

    /**
     * Initiates the process of fetching restaurants based on player preferences.
     * Creates a geographical bound for the search area and fetches restaurants for each player's
     * preferred cuisine. Once all player-preferred restaurants are fetched, it proceeds to fetch
     * an additional high-rated restaurant as a bonus option.
     */
    private void fetchRestaurants() {
        double radiusInDegrees = 0.02; // Aproxx 2km
        final RectangularBounds searchBounds = RectangularBounds.newInstance(
                new LatLng(latitude - radiusInDegrees, longitude - radiusInDegrees),
                new LatLng(latitude + radiusInDegrees, longitude + radiusInDegrees)
        );

        final AtomicInteger restaurantsFetched = new AtomicInteger(0);

        for (PlayerPreference player : players) {
            findRestaurantForCuisine(player.cuisine, player.name, searchBounds, new RestaurantFetchCallback() {
                /**
                 * Called when a restaurant has been successfully fetched from the Places API.
                 */

                @Override
                public void onRestaurantFetched() {
                    int fetched = restaurantsFetched.incrementAndGet();
                    if (fetched == players.size()) {
                        findRandomHighRatedRestaurant(searchBounds);
                    }
                }
            });
        }
    }

    /**
     * Searches for and fetches a restaurant matching the specified cuisine within the given bounds.
     * Uses the Google Places API to find restaurants and adds them to the list when found.
     *
     * @param cuisine The type of cuisine to search for
     * @param playerName The name of the player who preferred this cuisine
     * @param bounds The geographical bounds to search within
     * @param callback Callback to execute after the restaurant is fetched
     */
    private void findRestaurantForCuisine(String cuisine, String playerName,
                                          RectangularBounds bounds,
                                          RestaurantFetchCallback callback) {
        String query = cuisine + " restaurant";

        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setLocationBias(bounds)
                .setQuery(query)
                .setTypesFilter(Arrays.asList("restaurant"))
                .build();

        placesClient.findAutocompletePredictions(request)
                .addOnSuccessListener(response -> {
                    if (!response.getAutocompletePredictions().isEmpty()) {
                        AutocompletePrediction prediction = response.getAutocompletePredictions().get(0);

                        List<Place.Field> placeFields = Arrays.asList(
                                Place.Field.NAME,
                                Place.Field.RATING,
                                Place.Field.ADDRESS,
                                Place.Field.TYPES
                        );

                        FetchPlaceRequest placeRequest = FetchPlaceRequest.builder(
                                prediction.getPlaceId(), placeFields).build();

                        placesClient.fetchPlace(placeRequest)
                                .addOnSuccessListener(placeResponse -> {
                                    Place place = placeResponse.getPlace();
                                    restaurants.add(new RestaurantCard(
                                            place.getName(),
                                            cuisine,
                                            place.getRating() != null ? place.getRating() + " ★" : "Rating N/A",
                                            place.getAddress(),
                                            playerName
                                    ));
                                    adapter.notifyDataSetChanged();
                                    callback.onRestaurantFetched();
                                })
                                .addOnFailureListener(exception -> {
                                    Log.e("Places", "Place details fetch failed: " + exception.getMessage());
                                    callback.onRestaurantFetched();
                                });
                    } else {
                        callback.onRestaurantFetched();
                    }
                })
                .addOnFailureListener(exception -> {
                    Log.e("Places", "Place prediction failed: " + exception.getMessage());
                    callback.onRestaurantFetched();
                });
    }

    /**
     * Finds a random highly-rated restaurant of a cuisine different from player preferences.
     * Filters out cuisines already chosen by players and selects a random available cuisine
     * for the search.
     *
     * @param bounds The geographical bounds to search within
     */
    private void findRandomHighRatedRestaurant(RectangularBounds bounds) {
        String[] randomCuisines = {"Italian", "Mexican", "Chinese", "Thai", "Indian", "Japanese",
                "Mediterranean", "French", "Korean", "Vietnamese", "Middle Eastern"};

        List<String> playerCuisines = players.stream()
                .map(p -> p.cuisine.toLowerCase())
                .collect(Collectors.toList());

        List<String> availableCuisines = Arrays.stream(randomCuisines)
                .filter(cuisine -> !playerCuisines.contains(cuisine.toLowerCase()))
                .collect(Collectors.toList());

        if (availableCuisines.isEmpty()) {
            findHighRatedRestaurant(bounds, "highly rated restaurant");
        } else {
            Random random = new Random();
            String randomCuisine = availableCuisines.get(random.nextInt(availableCuisines.size()));
            findHighRatedRestaurant(bounds, randomCuisine + " restaurant");
        }
    }

    /**
     * Searches for a highly-rated restaurant matching the specified query within given bounds.
     * Uses the Google Places API to find restaurants with ratings of 4.0 or higher.
     *
     * @param bounds The geographical bounds to search within
     * @param query The search query string for finding restaurants
     */
    private void findHighRatedRestaurant(RectangularBounds bounds, String query) {
        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setLocationBias(bounds)
                .setQuery(query + " rating:4")
                .setTypesFilter(Arrays.asList("restaurant"))
                .build();

        placesClient.findAutocompletePredictions(request)
                .addOnSuccessListener(response -> {
                    if (!response.getAutocompletePredictions().isEmpty()) {
                        Random random = new Random();
                        AutocompletePrediction prediction =
                                response.getAutocompletePredictions().get(
                                        random.nextInt(response.getAutocompletePredictions().size())
                                );

                        List<Place.Field> placeFields = Arrays.asList(
                                Place.Field.NAME,
                                Place.Field.RATING,
                                Place.Field.ADDRESS,
                                Place.Field.TYPES
                        );

                        FetchPlaceRequest placeRequest = FetchPlaceRequest.builder(
                                prediction.getPlaceId(), placeFields).build();

                        placesClient.fetchPlace(placeRequest)
                                .addOnSuccessListener(placeResponse -> {
                                    Place place = placeResponse.getPlace();
                                    if (place.getRating() != null && place.getRating() >= 4.0) {
                                        restaurants.add(new RestaurantCard(
                                                place.getName(),
                                                "Our Special Pick",
                                                place.getRating() + " ★",
                                                place.getAddress(),
                                                "Game's Recommendation"
                                        ));
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        findHighRatedRestaurant(bounds, query);
                                    }
                                })
                                .addOnFailureListener(exception -> {
                                    Log.e("Places", "Place details fetch failed: " + exception.getMessage());
                                });
                    }
                })
                .addOnFailureListener(exception -> {
                    Log.e("Places", "Place prediction failed: " + exception.getMessage());
                });
    }

    /**
     * Opens the selected restaurant in Google Maps application for navigation and additional details.
     * Creates an intent to launch Google Maps with the restaurant's name and address as search parameters,
     * falling back to a toast message if Google Maps is not installed on the device.
     *
     * @param restaurant The RestaurantCard object containing the selected restaurant's details
     */
    public void openInMaps(RestaurantCard restaurant) {
        String query = restaurant.getName() + " " + restaurant.getAddress();
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(query));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Google Maps app is not installed", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Restarts the game by clearing the activity stack and returning to the MainActivity.
     * This method is called when players want to start a new game after completing the current session,
     * ensuring a clean state for the new game. It clears the activity stack to prevent multiple
     * game instances from running simultaneously.
     */
    private void restartGame() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}