/**
 * Represents an empty node in a list of integers.
 */
public class EmptyNode implements ListOfInts {

  /**
   * Adds data to the front of the list.
   *
   * @param data the integer to add
   * @return a new ElementNode containing the given data
   */
  @Override
  public ListOfInts prepend(int data) {
    return new ElementNode(data, this);
  }
  /**
   * Adds the given data to the end of the list.
   *
   * @param data the integer to add
   * @return a new ElementNode containing the given data
   */

  @Override
  public ListOfInts append(int data) {
    return new ElementNode(data, this);
  }
  /**
   * Inserts the given data at the specified index.
   * Since this is an empty node, it will only allow insertion at index 0.
   *
   * @param data  the integer to add
   * @param index the position to insert at (0-based)
   * @return a new ElementNode containing the given data
   * @throws IndexOutOfBoundsException if the index is not 0
   */

  @Override
  public ListOfInts insertAtIndex(int data, int index) {
    if (index == 0) {
      return new ElementNode(data, this);
    }
    throw new IndexOutOfBoundsException("Index out of bounds.");
  }
  /**
   * Throws an exception because there is no data in an empty node.
   *
   * @param index the position to retrieve (not valid in an empty node)
   * @return never returns a value
   * @throws IndexOutOfBoundsException when trying to access an index in an empty node
   */

  @Override
  public int getDataAtIndex(int index) {
    throw new IndexOutOfBoundsException("Cannot get data from an empty list.");
  }
  /**
   * Throws an exception because there is no "rest" in an empty list.
   *
   * @return never returns a value
   * @throws UnsupportedOperationException when called on an empty list
   */

  @Override
  public ListOfInts getRest() {
    throw new UnsupportedOperationException("No rest in an empty list.");
  }
  /**
   * Returns the count of elements in this list.
   * In the case of an empty node, this will always return 0.
   *
   * @return the number of elements (0 in this case)
   */

  @Override
  public int getCountClassic() {
    return 0;
  }
  /**
   * Returns the count of elements using the helper function.
   *
   * @return the number of elements (0 in this case)
   */

  @Override
  public int getCountWithHelper() {
    return getCountHelper(0);
  }
  /**
   * Helper function to count the elements in the list.
   * Since this is an empty node, it will return the accumulator.
   *
   * @param acc the accumulator, initially 0
   * @return the count of elements (0 in this case)
   */


  @Override
  public int getCountHelper(int acc) {
    return acc;
  }
  /**
   * Returns the sum of the elements in the list.
   * Since this is an empty node, it will always return 0.
   *
   * @return the sum of elements (0 in this case)
   */

  @Override
  public int getSum() {
    return 0;
  }
  /**
   * Returns a string representation of this list.
   * In the case of an empty node, this will return "Empty".
   *
   * @return the string "Empty"
   */


  @Override
  public String toString() {
    return "Empty";
  }
}

