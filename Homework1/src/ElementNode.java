
 /**
  * Class representing a non-empty node in a list of integers.
 */
public class ElementNode implements ListOfInts {
  private final int data;
  private final ListOfInts next;



   /**
    * Constructs an ElementNode with the given data and the next node in the list.
    *
    * @param data the integer stored in this node
    * @param next the next node in the list
    */
  public ElementNode(int data, ListOfInts next) {
    this.data = data;
    this.next = next;
  }



   /**
    * Adds a new element to the front of the list.
    *
    * @param data the integer to prepend
    * @return the new list with the element prepended
    */
  @Override
  public ListOfInts prepend(int data) {
    return new ElementNode(data, this);
  }


   /**
    * Adds a new element to the end of the list.
    *
    * @param data the integer to append
    * @return the new list with the element appended
    */
  @Override
  public ListOfInts append(int data) {
    return new ElementNode(this.data, this.next.append(data));
  }


   /**
    * Inserts a new element at the specified index in the list.
    *
    * @param data  the integer to insert
    * @param index the position to insert the data at (0-based)
    * @return the new list with the element inserted
    * @throws IndexOutOfBoundsException if the index is negative
    */
  @Override
  public ListOfInts insertAtIndex(int data, int index) {
    if (index == 0) {
      return prepend(data);
    } else if (index> 0 ){
        return new ElementNode(this.data, this.next.insertAtIndex(data, index - 1));
    } else {
        throw new IndexOutOfBoundsException("Index cannot be negative");
    }

  }


   /**
    * Retrieves the data at the specified index.
    *
    * @param index the position to retrieve (0-based)
    * @return the integer at the specified index
    * @throws IndexOutOfBoundsException if the index is invalid
    */
  @Override
  public int getDataAtIndex(int index) {
    if (index == 0) {
      return this.data;
    } else if (index > 0) {
        return this.next.getDataAtIndex(index - 1);
    } else {
      throw new IndexOutOfBoundsException("Invalid index.");
    }
  }


   /**
    * Returns the rest of the list (all but the first element).
    *
    * @return the list containing all but the first element
    */
  @Override
  public ListOfInts getRest() {
    return this.next;
  }



   /**
    * Returns the number of elements in the list using classic recursion.
    *
    * @return the length of the list
    */
  @Override
  public int getCountClassic() {
    return 1 + this.next.getCountClassic();
  }


   /**
    * Returns the number of elements in the list using a helper function.
    *
    * @return the length of the list
    */
  @Override
  public int getCountWithHelper() {
    return getCountHelper(0);
  }


   /**
    * Helper function for counting the number of elements in the list.
    *
    * @param acc the accumulator
    * @return the length of the list
    */
  @Override
  public int getCountHelper(int acc) {
    return this.next.getCountHelper(acc + 1);
  }


   /**
    * Returns the sum of the elements in the list.
    *
    * @return the sum of the elements
    */
  @Override
  public int getSum() {
    return this.data + this.next.getSum();
  }



   /**
    * Returns a string representation of the list.
    *
    * @return a string showing the elements in the list
    */
  @Override
  public String toString() {
    return this.data + " -> " + this.next.toString();
  }
}

