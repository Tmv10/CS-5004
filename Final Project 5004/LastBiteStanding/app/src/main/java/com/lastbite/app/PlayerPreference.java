/**
 * Represents a player's preferences for the LastBite application, including their name and preferred cuisine.
 * This class implements the Parcelable interface to allow efficient data transfer between Android components.
 *
 * <p>The class stores basic user preference information and provides functionality to serialize/deserialize
 * this data using Android's Parcelable system. This makes it suitable for passing player preferences
 * between different Activities, Services, or other Android components.</p>
 */
package com.lastbite.app;

import android.os.Parcel;
import android.os.Parcelable;


public class PlayerPreference implements Parcelable {
    /** The name of the player */
    public String name;

    /** The player's preferred cuisine type */
    public String cuisine;

    /**
     * Constructs a new PlayerPreference with specified name and cuisine preference.
     *
     * @param name    The name of the player
     * @param cuisine The player's preferred cuisine type
     */
    public PlayerPreference(String name, String cuisine) {
        this.name = name;
        this.cuisine = cuisine;
    }

    /**
     * Constructor used by Parcelable to recreate an object from a Parcel.
     * Reads the values in the order they were written in {@link #writeToParcel}.
     *
     * @param in The Parcel containing the PlayerPreference data
     */
    protected PlayerPreference(Parcel in) {
        name = in.readString();
        cuisine = in.readString();
    }

    /**
     * Writes the object's data to a Parcel.
     * This method is used by the Android system to serialize the object.
     *
     * @param dest  The Parcel in which the object should be written
     * @param flags Additional flags about how the object should be written.
     *             May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(cuisine);
    }

    /**
     * Describes the kinds of special objects contained in this Parcelable instance.
     *
     * @return 0, indicating no special objects are contained within this class
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Creator for the Parcelable interface that creates instances of PlayerPreference from a Parcel.
     * This field is required for classes that implement Parcelable.
     */
    public static final Creator<PlayerPreference> CREATOR = new Creator<PlayerPreference>() {
        /**
         * Creates a new instance of PlayerPreference from the given Parcel.
         *
         * @param in The Parcel containing the PlayerPreference data
         * @return A new PlayerPreference instance with data from the Parcel
         */
        @Override
        public PlayerPreference createFromParcel(Parcel in) {
            return new PlayerPreference(in);
        }

        /**
         * Creates a new array of PlayerPreference with the specified size.
         *
         * @param size The size of the array to create
         * @return A new array of PlayerPreference with the specified size
         */
        @Override
        public PlayerPreference[] newArray(int size) {
            return new PlayerPreference[size];
        }
    };
}