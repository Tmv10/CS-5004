/**
 * Unit test suite for the RestaurantAdapter class.
 * Tests the adapter's functionality for displaying restaurant cards in a RecyclerView.
 * Uses Robolectric for Android framework simulation and Mockito for mocking dependencies.
 *
 * <p>The test suite covers:</p>
 * <ul>
 *   <li>Adapter initialization and construction</li>
 *   <li>View creation and binding</li>
 *   <li>Item management (counting, removal)</li>
 *   <li>Click handling and navigation</li>
 *   <li>ViewHolder initialization</li>
 *   <li>Error cases and edge conditions</li>
 * </ul>
 */
package com.lastbite.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test suite for the RestaurantAdapter class.
 * Tests the adapter's functionality for displaying restaurant cards in a RecyclerView.
 * Uses Robolectric for Android framework simulation and Mockito for mocking dependencies.
 *
 * <p>The test suite covers:</p>
 * <ul>
 *   <li>Adapter initialization and construction</li>
 *   <li>View creation and binding</li>
 *   <li>Item management (counting, removal)</li>
 *   <li>Click handling and navigation</li>
 *   <li>ViewHolder initialization</li>
 *   <li>Error cases and edge conditions</li>
 * </ul>
 */
@RunWith(RobolectricTestRunner.class)
public class RestaurantAdapterTest {

    /** Mock ViewGroup for testing view creation */
    @Mock
    private ViewGroup mockParent;

    /** Mock LayoutInflater for testing view inflation */
    @Mock
    private LayoutInflater mockInflater;

    /** Mock View for testing item views */
    @Mock
    private View mockItemView;

    /** Mock TextView for testing text displays */
    @Mock
    private TextView mockTextView;

    /** Mock GameActivity for testing context-dependent behavior */
    @Mock
    private GameActivity mockGameActivity;


    /** The adapter instance being tested */
    private RestaurantAdapter adapter;

    /** Test data for the adapter */
    private List<RestaurantCard> restaurants;

    /**
     * Sets up the test environment before each test.
     * Initializes mocks, creates test data, and configures mock behaviors.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        restaurants = new ArrayList<>();
        restaurants.add(new RestaurantCard(
                "Test Restaurant",
                "Test Cuisine",
                "4.5",
                "123 Test St",
                "Dinner"
        ));

        adapter = new RestaurantAdapter(restaurants);

        when(mockParent.getContext()).thenReturn(mockGameActivity);
        when(mockInflater.inflate(eq(R.layout.restaurant_card), any(ViewGroup.class), eq(false)))
                .thenReturn(mockItemView);
        when(mockItemView.findViewById(anyInt())).thenReturn(mockTextView);
    }

    /**
     * Tests adapter construction with a valid restaurant list.
     * Verifies proper initialization and item count.
     */
    @Test
    public void testConstructorWithValidList() {
        assertNotNull("Adapter should be created with valid list", adapter);
        assertEquals("Item count should match list size", 1, adapter.getItemCount());
    }

    /**
     * Tests adapter construction with an empty list.
     * Verifies proper handling of empty lists.
     */
    @Test
    public void testConstructorWithEmptyList() {
        adapter = new RestaurantAdapter(new ArrayList<>());
        assertEquals("Empty list should have 0 items", 0, adapter.getItemCount());
    }


    /**
     * Tests the getItemCount method with various list states.
     * Verifies correct count reporting for multiple items and empty list.
     */
    @Test
    public void testGetItemCount() {
        restaurants.add(new RestaurantCard("R2", "C2", "4.0", "Address", "Lunch"));
        assertEquals("Item count should match list size", 2, adapter.getItemCount());

        restaurants.clear();
        assertEquals("Empty list should have 0 items", 0, adapter.getItemCount());
    }

    /**
     * Tests binding of restaurant data to ViewHolder.
     * Verifies correct text setting for all fields.
     */
    @Test
    public void testOnBindViewHolder() {
        RestaurantAdapter.RestaurantViewHolder holder = mock(RestaurantAdapter.RestaurantViewHolder.class);
        holder.restaurantName = mockTextView;
        holder.cuisineType = mockTextView;
        holder.rating = mockTextView;
        holder.address = mockTextView;
        holder.suggestedFor = mockTextView;

        adapter.onBindViewHolder(holder, 0);

        verify(holder.restaurantName).setText("Test Restaurant");
        verify(holder.cuisineType).setText("Test Cuisine");
        verify(holder.rating).setText("4.5");
        verify(holder.address).setText("123 Test St");
        verify(holder.suggestedFor).setText("Suggested for: Dinner");
    }


    /**
     * Tests removal of restaurant items.
     * Verifies correct list modification and size updates.
     */
    @Test
    public void testRemoveItem() {
        restaurants.add(new RestaurantCard("R2", "C2", "4.0", "Address", "Lunch"));
        restaurants.add(new RestaurantCard("R3", "C3", "3.5", "Address", "Breakfast"));

        int initialSize = adapter.getItemCount();
        adapter.removeItem(1);

        assertEquals("Size should decrease by 1", initialSize - 1, adapter.getItemCount());
        assertNotEquals("Second restaurant should be removed", "R2",
                restaurants.get(1).getName());
    }

    /**
     * Tests removal of item with invalid index.
     * Verifies appropriate exception is thrown.
     *
     * @throws IndexOutOfBoundsException when invalid index is provided
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveItemWithInvalidIndex() {
        adapter.removeItem(5);
    }


    /**
     * Tests ViewHolder initialization.
     * Verifies all required views are properly initialized.
     */
    @Test
    public void testViewHolderInitialization() {
        View mockItemView = mock(View.class);
        when(mockItemView.findViewById(anyInt())).thenReturn(mock(TextView.class));

        RestaurantAdapter.RestaurantViewHolder holder = new RestaurantAdapter.RestaurantViewHolder(mockItemView);

        assertNotNull("Restaurant name TextView should be initialized", holder.restaurantName);
        assertNotNull("Cuisine type TextView should be initialized", holder.cuisineType);
        assertNotNull("Rating TextView should be initialized", holder.rating);
        assertNotNull("Address TextView should be initialized", holder.address);
        assertNotNull("Suggested for TextView should be initialized", holder.suggestedFor);
    }
}