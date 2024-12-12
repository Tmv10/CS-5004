/**
 * Represents a restaurant card in the LastBite application, containing essential
 * information about a restaurant including its name, cuisine type, rating,
 * address, and the players it is suggested for.
 *
 * <p>This class serves as a data model for displaying restaurant information
 * in the application's user interface, particularly within the RecyclerView
 * managed by {@link RestaurantAdapter}.</p>
 */
package com.lastbite.app;

public class RestaurantCard {
    /** The name of the restaurant */
    private String name;

    /** The type of cuisine served at the restaurant */
    private String cuisine;

    /** The rating of the restaurant (typically from Google Places) */
    private String rating;

    /** The physical address of the restaurant */
    private String address;

    /** Names of players for whom this restaurant is suggested based on their preferences */
    private String suggestedFor;

    /**
     * Constructs a new RestaurantCard with all required information.
     *
     * @param name         The name of the restaurant
     * @param cuisine      The type of cuisine served at the restaurant
     * @param rating      The restaurant's rating
     * @param address     The physical address of the restaurant
     * @param suggestedFor The names of players for whom this restaurant is suggested
     */
    public RestaurantCard(String name, String cuisine, String rating, String address, String suggestedFor) {
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
        this.address = address;
        this.suggestedFor = suggestedFor;
    }

    /**
     * Returns the name of the restaurant.
     *
     * @return The restaurant's name
     */
    public String getName() { return name; }

    /**
     * Returns the type of cuisine served at the restaurant.
     *
     * @return The restaurant's cuisine type
     */
    public String getCuisine() { return cuisine; }

    /**
     * Returns the rating of the restaurant.
     *
     * @return The restaurant's rating
     */
    public String getRating() { return rating; }

    /**
     * Returns the physical address of the restaurant.
     *
     * @return The restaurant's address
     */
    public String getAddress() { return address; }

    /**
     * Returns the names of players for whom this restaurant is suggested
     * based on their cuisine preferences.
     *
     * @return A string containing the names of players for whom this restaurant is suggested
     */
    public String getSuggestedFor() { return suggestedFor; }
}