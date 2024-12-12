/**
 * Defines the contract for location services in the LastBite application.
 * This interface abstracts the location-related operations, providing a unified way to
 * handle location requests, updates, and permissions across different implementations.
 *
 * <p>The interface provides methods for:
 * <ul>
 *   <li>Requesting current location updates</li>
 *   <li>Managing location update lifecycle</li>
 *   <li>Handling location permissions</li>
 * </ul>
 * </p>
 *
 * <p>Implementations of this interface should handle all the necessary platform-specific
 * details for location services, such as connecting to location providers, managing system
 * permissions, and handling location updates.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * LocationService locationService = // get implementation
 *
 * if (!locationService.isLocationPermissionGranted()) {
 *     locationService.requestLocationPermission();
 *     return;
 * }
 *
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
 * @see LocationCallback
 * @see android.location.Location
 */
package com.lastbite.app.services;

import android.location.Location;

public interface LocationService {
    /**
     * Requests the current location of the device.
     *
     * <p>This method initiates an asynchronous location request. The result will be
     * delivered through the provided callback, either as a successful location update
     * or an error message.</p>
     *
     * <p>Implementations should check for necessary permissions before making the
     * location request and handle any potential errors appropriately.</p>
     *
     * @param callback The callback to receive the location update or error.
     *                 Must not be null.
     * @throws NullPointerException if callback is null
     *
     * @see LocationCallback#onLocationReceived(Location)
     * @see LocationCallback#onLocationError(String)
     */
    void getCurrentLocation(LocationCallback callback);

    /**
     * Stops any ongoing location updates and releases associated resources.
     *
     * <p>This method should be called when location updates are no longer needed
     * or when the associated component (e.g., Activity or Service) is being destroyed.
     * Implementations should ensure proper cleanup of resources and removal of
     * any registered listeners.</p>
     */
    void stopLocationUpdates();

    /**
     * Checks if the application has the necessary location permissions.
     *
     * <p>Implementations should verify that all required location permissions
     * are granted by the user. This typically includes checking for both
     * coarse and fine location permissions depending on the implementation's
     * requirements.</p>
     *
     * @return true if all required location permissions are granted,
     *         false otherwise
     */
    boolean isLocationPermissionGranted();

    /**
     * Requests location permissions from the user.
     *
     * <p>This method should initiate the system's permission request flow
     * for the required location permissions. Implementations should handle
     * the appropriate permission request mechanism for their platform or
     * API level.</p>
     *
     * <p>Note: The result of the permission request will typically be delivered
     * through the platform's standard permission callback mechanisms (e.g.,
     * {@code Activity.onRequestPermissionsResult}).</p>
     */
    void requestLocationPermission();
}