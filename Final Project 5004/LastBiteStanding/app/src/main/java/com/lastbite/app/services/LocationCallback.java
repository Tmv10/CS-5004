/**
 * A callback interface for handling location-related operations in the LastBite application.
 * This interface provides methods to receive location updates and handle location-related errors.
 *
 * <p>Implementations of this interface can be used to receive asynchronous updates
 * about location requests, whether successful or failed. The callback methods are
 * designed to handle both successful location acquisition and various error scenarios.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * LocationCallback callback = new LocationCallback() {
 *     {@literal @}Override
 *     public void onLocationReceived(Location location) {
 *         // Handle the received location
 *         double latitude = location.getLatitude();
 *         double longitude = location.getLongitude();
 *     }
 *
 *     {@literal @}Override
 *     public void onLocationError(String error) {
 *         // Handle the error
 *         Log.e("Location", "Error getting location: " + error);
 *     }
 * };
 * </pre>
 *
 * @see android.location.Location
 * @see com.lastbite.app.services.LocationService
 */
package com.lastbite.app.services;

import android.location.Location;

public interface LocationCallback {
    /**
     * Called when a location is successfully received.
     *
     * <p>This method will be invoked with the most recent location when available.
     * The provided Location object contains various details about the device's position,
     * including latitude, longitude, accuracy, and timestamp.</p>
     *
     * @param location The received Location object containing the device's current position.
     *                 Will never be null.
     *
     * @see android.location.Location#getLatitude()
     * @see android.location.Location#getLongitude()
     * @see android.location.Location#getAccuracy()
     */
    void onLocationReceived(Location location);

    /**
     * Called when an error occurs during location acquisition.
     *
     * <p>This method will be invoked when the location request fails for any reason,
     * including but not limited to:
     * <ul>
     *   <li>Location permissions not granted</li>
     *   <li>Location services disabled</li>
     *   <li>GPS/network provider unavailable</li>
     *   <li>Timeout while acquiring location</li>
     * </ul>
     * </p>
     *
     * @param error A human-readable string describing the error that occurred.
     *              Will never be null.
     */
    void onLocationError(String error);
}
