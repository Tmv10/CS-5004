/**
 * A RecyclerView adapter that manages the display of restaurant cards in the LastBite application.
 * This adapter handles the creation and binding of restaurant card views, as well as click interactions
 * that open restaurant locations in Maps.
 *
 * <p>The adapter works with {@link RestaurantCard} objects and displays their information
 * in a card-based layout defined in R.layout.restaurant_card.</p>
 */
package com.lastbite.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    /** List of RestaurantCard objects to be displayed in the RecyclerView */
    private List<RestaurantCard> restaurants;

    /**
     * Constructs a new RestaurantAdapter with the provided list of restaurants.
     *
     * @param restaurants The list of RestaurantCard objects to display
     */
    public RestaurantAdapter(List<RestaurantCard> restaurants) {
        this.restaurants = restaurants;
    }

    /**
     * Creates a new ViewHolder when needed by the RecyclerView.
     * Inflates the restaurant card layout and sets up click handling to open
     * restaurant locations in Maps when a card is clicked.
     *
     * @param parent The ViewGroup into which the new View will be added
     * @param viewType The view type of the new View (not used in this implementation)
     * @return A new RestaurantViewHolder that holds a restaurant card view
     */
    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_card, parent, false);
        RestaurantViewHolder holder = new RestaurantViewHolder(view);

        // Set click listener on the whole card
        view.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                RestaurantCard restaurant = restaurants.get(position);
                if (v.getContext() instanceof GameActivity) {
                    ((GameActivity) v.getContext()).openInMaps(restaurant);
                }
            }
        });

        return holder;
    }

    /**
     * Binds restaurant data to the views within the ViewHolder.
     * Updates all text views with the corresponding restaurant information.
     *
     * @param holder The ViewHolder which should be updated with restaurant data
     * @param position The position of the restaurant in the data set
     */
    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        RestaurantCard restaurant = restaurants.get(position);
        holder.restaurantName.setText(restaurant.getName());
        holder.cuisineType.setText(restaurant.getCuisine());
        holder.rating.setText(restaurant.getRating());
        holder.address.setText(restaurant.getAddress());
        holder.suggestedFor.setText("Suggested for: " + restaurant.getSuggestedFor());
    }

    /**
     * Returns the total number of restaurants in the adapter's data set.
     *
     * @return The total number of restaurants
     */
    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    /**
     * Removes a restaurant card from the specified position in the list.
     * Notifies the RecyclerView that an item has been removed to trigger appropriate animations.
     *
     * @param position The position of the restaurant to remove
     */
    public void removeItem(int position) {
        restaurants.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * ViewHolder class that contains references to all views within a restaurant card.
     * This class caches view references to avoid repeated calls to findViewById.
     */
    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        /** TextView displaying the restaurant's name */
        TextView restaurantName;

        /** TextView displaying the restaurant's cuisine type */
        TextView cuisineType;

        /** TextView displaying the restaurant's rating */
        TextView rating;

        /** TextView displaying the restaurant's address */
        TextView address;

        /** TextView displaying which player(s) the restaurant is suggested for */
        TextView suggestedFor;

        /**
         * Constructs a new RestaurantViewHolder and finds all required views
         * within the provided item view.
         *
         * @param itemView The View that contains the restaurant card layout
         */
        RestaurantViewHolder(View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            cuisineType = itemView.findViewById(R.id.cuisineType);
            rating = itemView.findViewById(R.id.rating);
            address = itemView.findViewById(R.id.address);
            suggestedFor = itemView.findViewById(R.id.suggestedFor);
        }
    }
}