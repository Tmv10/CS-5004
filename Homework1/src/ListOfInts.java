/**
 * Represents a list of integers.
 */
public interface ListOfInts {

  /**
   * Adds data to the front of the list.
   *
   * @param data the integer to add
   * @return the new list with the element prepended
   */
  ListOfInts prepend(int data);
  /**
   * Adds the given data to the end of the list.
   *
   * @param data the integer to add
   * @return the new list with the element appended
   */

  ListOfInts append(int data);
  /**
   * Inserts the given data at the specified index.
   *
   * @param data  the integer to add
   * @param index the position to insert at (0-based)
   * @return the new list with the element inserted
   * @throws IndexOutOfBoundsException if the index is invalid
   */

  ListOfInts insertAtIndex(int data, int index);
  /**
   * Returns the data at the specified index.
   *
   * @param index the position to retrieve (0-based)
   * @return the integer at the specified index
   * @throws IndexOutOfBoundsException if the index is invalid
   */
  int getDataAtIndex(int index);
  /**
   * Returns the list containing all but the first element.
   *
   * @return the rest of the list
   */

  ListOfInts getRest();
  /**
   * Returns the number of elements in the list (classic recursion).
   *
   * @return the length of the list
   */

  int getCountClassic();
  /**
   * Returns the number of elements in the list (using a helper).
   *
   * @return the length of the list
   */

  int getCountWithHelper();
  /**
   * Helper function to assist in counting elements.
   *
   * @return the length of the list
   */

  int getCountHelper(int acc);
  /**
   * Returns the sum of the elements in the list.
   *
   * @return the sum of the elements
   */

  int getSum();
  /**
  * Returns the sum of all the elements in the list
   **/
}
