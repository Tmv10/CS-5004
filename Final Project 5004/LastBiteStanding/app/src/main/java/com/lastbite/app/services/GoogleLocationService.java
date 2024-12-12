/**
 * An implementation of {@link LocationService} that uses Google Play Services'
 * {@link FusedLocationProviderClient} to provide location functionality on Android devices.
 *
 * <p>This service handles:
 * <ul>
 *   <li>High-accuracy location requests</li>
 *   <li>Location permission management</li>
 *   <li>Location updates lifecycle</li>
 * </ul>
 * </p>
 *
 * <p>The service requires the following permission in the Android Manifest:</p>
 * <pre>{@code <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />}</pre>
 *
 * <p>Usage example:</p>
 * <pre>
 * GoogleLocationService locationService = new GoogleLocationService(activity);
 *
 * // Check permissions
 * if (!locationService.isLocationPermissionGranted()) {
 *     locationService.requestLocationPermission();
 *     return;
 * }
 *
 * // Request location
 * locationService.getCurrentLocation(new LocationCallback() {
 *     {@literal @}Override
 *     public void onLocationReceived(Location location) {
 *         // Handle location update
 *     }
 *
 *     {@literal @}Override
 *     public void onLocationError(String error) {
 *         // Handle error
 *     }
 * });
 * </pre>
 *
 * @see LocationService
 * @see FusedLocationProviderClient
 * @see Location
 */
package com.lastbite.app.services;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;

public class GoogleLocationService implements LocationService {
    /** Request code used for location permission requests. */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private final Activity activity;
    private final FusedLocationProviderClient fusedLocationClient;
    private LocationCallback currentCallback;

    /**
     * Constructs a new GoogleLocationService instance.
     *
     * <p>Initializes the FusedLocationProviderClient and stores the activity reference
     * for permission handling.</p>
     *
     * @param activity The activity context used for permission requests and location services.
     *                Must not be null.
     * @throws NullPointerException if activity is null
     */
    public GoogleLocationService(Activity activity) {
        this.activity = activity;
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
    }

    /**
     * Requests the current device location using high accuracy mode.
     *
     * <p>This method will:
     * <ol>
     *   <li>Check for location permissions</li>
     *   <li>Request a single location update with high accuracy</li>
     *   <li>Deliver the result through the provided callback</li>
     * </ol>
     * </p>
     *
     * <p>The method uses {@link Priority#PRIORITY_HIGH_ACCURACY} to get the most accurate
     * location possible. This may use GPS and consume more battery power.</p>
     *
     * @param callback The callback to receive the location update or error.
     *                 Must not be null.
     * @throws NullPointerException if callback is null
     *
     * @see LocationCallback#onLocationReceived(Location)
     * @see LocationCallback#onLocationError(String)
     */
    @Override
    public void getCurrentLocation(LocationCallback callback) {
        this.currentCallback = callback;

        if (!isLocationPermissionGranted()) {
            callback.onLocationError("Location permission not granted");
            return;
        }

        try {
            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, new CancellationToken() {
                        @Override
                        public boolean isCancellationRequested() {
                            return false;
                        }

                        @Override
                        public CancellationToken onCanceledRequested(OnTokenCanceledListener onTokenCanceledListener) {
                            return this;
                        }
                    })
                    .addOnSuccessListener(location -> {
                        if (location != null) {
                            callback.onLocationReceived(location);
                        } else {
                            callback.onLocationError("Unable to get current location");
                        }
                    })
                    .addOnFailureListener(e ->
                            callback.onLocationError("Error getting location: " + e.getMessage()));
        } catch (SecurityException e) {
            callback.onLocationError("Location permission denied");
        }
    }

    /**
     * Stops any ongoing location updates and cleans up resources.
     *
     * <p>This method:
     * <ul>
     *   <li>Flushes any pending locations from the FusedLocationProviderClient</li>
     *   <li>Clears the current callback reference</li>
     * </ul>
     * </p>
     *
     * <p>It's recommended to call this method when the location updates are no longer
     * needed or when the activity is being destroyed.</p>
     */
    @Override
    public void stopLocationUpdates() {
        fusedLocationClient.flushLocations();
        currentCallback = null;
    }

    /**
     * Checks if the app has been granted fine location permission.
     *
     * <p>This method checks for {@link android.Manifest.permission#ACCESS_FINE_LOCATION}
     * permission using {@link ContextCompat#checkSelfPermission}.</p>
     *
     * @return true if location permission is granted, false otherwise
     */
    @Override
    public boolean isLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Requests fine location permission from the user.
     *
     * <p>This method triggers the system permission dialog for
     * {@link android.Manifest.permission#ACCESS_FINE_LOCATION}. The result will be delivered
     * to the activity through {@link Activity#onRequestPermissionsResult}.</p>
     *
     * <p>Note: The calling activity should implement permission result handling to process
     * the user's response.</p>
     *
     * @see Activity#onRequestPermissionsResult(int, String[], int[])
     */
    @Override
    public void requestLocationPermission() {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);
    }
}