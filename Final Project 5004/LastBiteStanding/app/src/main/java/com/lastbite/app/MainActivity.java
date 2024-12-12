/**
 * The main entry point activity for the LastBite app that handles location selection and initialization.
 * This activity provides users with options to select a location either through manual search using
 * Google Places Autocomplete or by using their current location, and integrates with Google Maps
 * for visual location confirmation before proceeding to player setup.
 *
 * The activity manages:
 * - Location permissions
 * - Google Maps integration
 * - Places API autocomplete
 * - Location services
 * - Navigation to player setup
 */
package com.lastbite.app;

import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.lastbite.app.managers.LocationManager;
import com.lastbite.app.services.GoogleLocationService;
import com.lastbite.app.services.LocationCallback;
import java.util.Arrays;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.cardview.widget.CardView;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    /**
     * Request code for location permission requests.
     * Used to identify permission results in onRequestPermissionsResult callback.
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    private GoogleMap googleMap;
    private MapView mapView;
    private LocationManager locationManager;
    private Button useCurrentLocationBtn;
    /**
     * Initializes the activity, sets up the UI components, and initializes required services.
     * This includes initializing the Places SDK, location services, map view, and places autocomplete.
     *
     * @param savedInstanceState Bundle containing the activity's previously saved state, if any
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
        }

        locationManager = new LocationManager(new GoogleLocationService(this));

        initializeViews(savedInstanceState);
        setupPlacesAutocomplete();
    }

    /**
     * Initializes and sets up all view components including the map view and location button.
     * Configures the MapView with saved instance state and sets up click listeners.
     *
     * @param savedInstanceState Bundle containing the previously saved state
     */
    private void initializeViews(Bundle savedInstanceState) {
        useCurrentLocationBtn = findViewById(R.id.useCurrentLocationBtn);
        mapView = findViewById(R.id.mapView);

        // Set up click listener for current location button
        useCurrentLocationBtn.setOnClickListener(v -> handleGetCurrentLocation());

        // Initialize map
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    /**
     * Sets up the Google Places Autocomplete fragment for location search functionality.
     * Configures the autocomplete fragment with custom styling and place field specifications,
     * and implements place selection handling.
     */
    private void setupPlacesAutocomplete() {
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        if (autocompleteFragment != null) {
            // Specify the types of place data to return
            autocompleteFragment.setPlaceFields(Arrays.asList(
                    Place.Field.ID,
                    Place.Field.NAME,
                    Place.Field.ADDRESS,
                    Place.Field.LAT_LNG
            ));

            // Customize the fragment
            autocompleteFragment.setHint("Search location");

            // Get the fragment's view and customize it
            View fragmentView = autocompleteFragment.getView();
            if (fragmentView != null) {
                // Find the EditText within the fragment
                EditText searchInput = fragmentView.findViewById(com.google.android.libraries.places.R.id.places_autocomplete_search_input);
                if (searchInput != null) {
                    searchInput.setTextSize(16);
                    searchInput.setBackgroundResource(android.R.color.transparent);
                    searchInput.setHint("Enter location");
                }
            }

            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(@NonNull Place place) {
                    String placeName = place.getName();
                    LatLng latLng = place.getLatLng();

                    if (latLng != null) {
                        updateMapLocation(latLng);
                        Toast.makeText(MainActivity.this,
                                "Selected: " + placeName, Toast.LENGTH_SHORT).show();
                        proceedToPlayerSetup(latLng);
                    }
                }

                @Override
                public void onError(@NonNull Status status) {
                    Toast.makeText(MainActivity.this,
                            "Error: " + status.getStatusMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * Handles the process of retrieving the user's current location.
     * Checks for location permissions and requests them if not granted.
     * When permissions are available, retrieves the current location and updates the UI.
     */
    private void handleGetCurrentLocation() {
        if (!checkLocationPermission()) {
            requestLocationPermission();
            return;
        }

        useCurrentLocationBtn.setEnabled(false);
        locationManager.getCurrentLocation(new LocationCallback() {
            @Override
            public void onLocationReceived(Location location) {
                useCurrentLocationBtn.setEnabled(true);
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                updateMapLocation(latLng);
                proceedToPlayerSetup(latLng);
            }

            @Override
            public void onLocationError(String error) {
                useCurrentLocationBtn.setEnabled(true);
                showError(error);
            }
        });
    }

    /**
     * Checks if the app has been granted location permissions.
     *
     * @return true if location permission is granted, false otherwise
     */
    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Requests location permissions from the user.
     * Triggers the system permission dialog for ACCESS_FINE_LOCATION.
     */
    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);
    }

    /**
     * Updates the map display with a new location.
     * Clears existing markers, adds a new marker at the specified location,
     * and animates the camera to center on the new location.
     *
     * @param latLng The latitude and longitude of the new location
     */
    private void updateMapLocation(LatLng latLng) {
        if (googleMap != null) {
            googleMap.clear();
            googleMap.addMarker(new MarkerOptions().position(latLng));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
        }
    }

    /**
     * Navigates to the PlayerSetupActivity with the selected location.
     * Passes the latitude and longitude as extras in the intent.
     *
     * @param location The selected LatLng location to pass to the next activity
     */
    private void proceedToPlayerSetup(LatLng location) {
        Intent intent = new Intent(this, PlayerSetupActivity.class);
        intent.putExtra("latitude", location.latitude);
        intent.putExtra("longitude", location.longitude);
        startActivity(intent);
    }

    /**
     * Displays an error message to the user using a Toast.
     *
     * @param message The error message to display
     */
    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Callback for when the Google Map is ready to be used.
     * Configures initial map settings and displays a default location.
     *
     * @param map The Google Map instance that is ready to be used
     */
    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;
        if (checkLocationPermission()) {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
        // Set default location
        LatLng defaultLocation = new LatLng(37.7749, -122.4194);
        updateMapLocation(defaultLocation);
    }

    /**
     * Handles the result of a permission request.
     * If location permission is granted, proceeds with getting the current location.
     *
     * @param requestCode The request code passed in requestPermissions()
     * @param permissions The requested permissions
     * @param grantResults The grant results for the corresponding permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                handleGetCurrentLocation();
            }
        }
    }

    /**
     * Called when the activity resumes.
     * Ensures proper MapView lifecycle management.
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * Called when the activity is paused.
     * Ensures proper MapView lifecycle management.
     */
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    /**
     * Called when the activity is destroyed.
     * Ensures proper cleanup of the MapView.
     */
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    /**
     * Saves the instance state of the activity.
     * Ensures proper state management for the MapView.
     *
     * @param outState Bundle in which to place your saved state
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * Called when the system is running low on memory.
     * Forwards the low memory notification to MapView.
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}