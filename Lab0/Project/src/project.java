/**
 * Return the year of birth of this person
 * @return the year of birth of this person
 */
public int getYearOfBirth() {
  return this.yearOfBirth;
}

/**
 Returns a string representation of this person with first
 and last name
 @return a formatted string
 */

public String toString() {
  return "" + firstName  + " " + lastName;
}

/**
 * check if this person is the same
 * as the person in the argument.
 * two persons are the same iff they
 * have the same first and last names
 * and the same years of birth
 * @param other the other person to be compared to
 * @return true if this person is the same as other, false otherwise
 */
public boolean same(Person other) {
  return  this.firstName.equals(other.firstName)
      && this.lastName.equals(other.lastName)
      && this.yearOfBirth == other.yearOfBirth;
}
}