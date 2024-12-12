/**
 * Manages location-related operations and permissions for the LastBite application.
 * This class serves as a wrapper around {@link LocationService} to provide a simplified
 * interface for location functionality, including getting current location updates,
 * retrieving last known location, and handling location permissions.
 *
 * <p>The manager maintains a reference to the most recently received location and
 * provides asynchronous location updates through callback mechanisms.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * LocationManager locationManager = new LocationManager(locationService);
 *
 * // Check and request permissions if needed
 * if (!locationManager.hasLocationPermission()) {
 *     locationManager.requestLocationPermission();
 * }
 *
 * // Get current location
 * locationManager.getCurrentLocation(new LocationCallback() {
 *     {@literal @}Override
 *     public void onLocationReceived(Location location) {
 *         // Handle the received location
 *     }
 *
 *     {@literal @}Override
 *     public void onLocationError(String error) {
 *         // Handle any errors
 *     }
 * });
 * </pre>
 *
 * @see LocationService
 * @see LocationCallback
 */
package com.lastbite.app.managers;

import android.location.Location;
import com.lastbite.app.services.LocationCallback;
import com.lastbite.app.services.LocationService;


public class LocationManager {
    private final LocationService locationService;
    private Location currentLocation;

    /**
     * Constructs a new LocationManager with the specified location service.
     *
     * @param locationService The location service implementation to be used for
     * location-related operations. Must not be null.
     * @throws NullPointerException if locationService is null
     */
    public LocationManager(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Asynchronously retrieves the current device location.
     *
     * <p>This method initiates a location request through the underlying location service.
     * When the location is available, it will be stored as the current location and
     * provided through the callback. If an error occurs during the location request,
     * it will be communicated through the callback's error method.</p>
     *
     * <p>The provided callback may be null, in which case the location will still be
     * updated internally but no callback notifications will be sent.</p>
     *
     * @param callback The callback to receive the location update or error.
     *                 May be null if no callback notifications are needed.
     * @see LocationCallback#onLocationReceived(Location)
     * @see LocationCallback#onLocationError(String)
     */
    public void getCurrentLocation(LocationCallback callback) {
        locationService.getCurrentLocation(new LocationCallback() {
            @Override
            public void onLocationReceived(Location location) {
                currentLocation = location;
                if (callback != null) {
                    callback.onLocationReceived(location);
                }
            }

            @Override
            public void onLocationError(String error) {
                if (callback != null) {
                    callback.onLocationError(error);
                }
            }
        });
    }

    /**
     * Retrieves the last known location that was received by this manager.
     *
     * <p>This method returns the most recent location received from a call to
     * {@link #getCurrentLocation(LocationCallback)}. If no location has been
     * received yet, this method will return null.</p>
     *
     * @return The last known Location object, or null if no location has been
     *         received yet.
     */
    public Location getLastKnownLocation() {
        return currentLocation;
    }

    /**
     * Checks if the application has the necessary location permissions.
     *
     * <p>This method delegates to the underlying location service to determine
     * if the required location permissions are currently granted.</p>
     *
     * @return true if location permissions are granted, false otherwise
     */
    public boolean hasLocationPermission() {
        return locationService.isLocationPermissionGranted();
    }

    /**
     * Requests location permissions from the user.
     *
     * <p>This method initiates the permission request flow through the underlying
     * location service. The actual permission request will be handled by the system's
     * permission dialog.</p>
     *
     * <p>Note: This method should typically be called only after checking
     * {@link #hasLocationPermission()} returns false.</p>
     */
    public void requestLocationPermission() {
        locationService.requestLocationPermission();
    }
}