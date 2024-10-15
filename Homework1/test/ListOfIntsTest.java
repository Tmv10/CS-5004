import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for ListOfInts interface and its implementations.
 */

public class ListOfIntsTest {

  private ListOfInts emptyList;
  private ListOfInts nonEmptyList;


  /**
   * Set up test data before each test case.
   */
  @BeforeEach
  public void setUp() {
    emptyList = new EmptyNode();
    nonEmptyList = new EmptyNode().append(1).append(2).append(3);  // A predefined non-empty list
  }


  /**
   * Tests the prepend method by adding integers to the start of the list.
   */
  @Test
  public void testPrepend() {
    ListOfInts list = new EmptyNode();
    list = list.prepend(5);
    list = list.prepend(10);

    assertEquals(10, list.getDataAtIndex(0));
    assertEquals(5, list.getDataAtIndex(1));
  }


  /**
   * Tests the append method by adding integers to the end of the list.
   */
  @Test
  public void testAppend() {
    ListOfInts list = new EmptyNode();
    list = list.append(5);
    list = list.append(10);

    assertEquals(5, list.getDataAtIndex(0));
    assertEquals(10, list.getDataAtIndex(1));
  }


  /**
   * Tests inserting data at a specific index in the list.
   */

  @Test
  public void testInsertAtIndex() {
    ListOfInts list = new EmptyNode();
    list = list.append(1);
    list = list.append(3);
    list = list.insertAtIndex(2, 1);

    assertEquals(1, list.getDataAtIndex(0));
    assertEquals(2, list.getDataAtIndex(1));
    assertEquals(3, list.getDataAtIndex(2));
  }


  /**
   * Tests inserting data at the start and end of the list.
   */

  @Test
  public void testInsertAtStartAndEnd() {
    ListOfInts list = new EmptyNode().append(1).append(3);

    // Insert at start
    list = list.insertAtIndex(0, 0);
    assertEquals(0, list.getDataAtIndex(0));

    // Insert at the end
    list = list.insertAtIndex(4, 3);
    assertEquals(4, list.getDataAtIndex(3));

  }

  /**
   * Tests retrieving data from a specific index in the list.
   */
  @Test
  public void testGetDataAtIndex() {
    // Initialize the list and assign values
    ListOfInts list = new EmptyNode();
    list = list.append(1);  // [1]
    list = list.append(2);  // [1, 2]

    // Valid index test cases
    assertEquals(1, list.getDataAtIndex(0));  // Check index 0
    assertEquals(2, list.getDataAtIndex(1));  // Check index 1

    // Use a final reference to the list for the lambda
    final ListOfInts finalList = list;

    // Out-of-bounds index case
    assertThrows(IndexOutOfBoundsException.class,
        () -> finalList.getDataAtIndex(2));  // Index out of bounds
  }

  /**
   * Tests the getCountClassic method which uses classic recursion to count elements.
   */
  @Test
  public void testGetCountClassic() {
    ListOfInts list = new EmptyNode();
    list = list.append(1);
    list = list.append(2);
    list = list.append(3);

    assertEquals(3, list.getCountClassic());
    list = list.append(4);
    assertEquals(4, list.getCountClassic());
  }

  /**
   * Tests the getCountWithHelper method which uses a helper function to count elements.
   */
  @Test
  public void testGetCountWithHelper() {
    ListOfInts list = new EmptyNode();
    list = list.append(1);
    list = list.append(2);
    list = list.append(3);

    assertEquals(3, list.getCountWithHelper()); // Count should be 3 after appending 1, 2, 3
    // Add more elements and check the updated count
    list = list.append(4).append(5);
    assertEquals(5, list.getCountWithHelper());  // Count should now be 5 after adding 4, 5

  }


  /**
   * Tests the getSum method which sums the elements of the list.
   */
  @Test
  public void testGetSum() {
    ListOfInts list = new EmptyNode();
    list = list.append(1);
    list = list.append(2);
    list = list.append(3);

    assertEquals(6, list.getSum());// Sum of 1 + 2 + 3
    assertNotEquals(0, list.getSum()); // Ensure the sum is not zero
  }

  /**
   * Tests accessing data at an invalid index, expecting an IndexOutOfBoundsException.
   */
  @Test
  public void testOutOfBoundsInNonEmptyList() {
    // Non-empty list with 3 elements (1, 2, 3)
    ListOfInts list = new EmptyNode().append(1).append(2).append(3);

    // Try accessing index 5 which is out of bounds for a list of size 3
    assertThrows(IndexOutOfBoundsException.class, () -> list.getDataAtIndex(5));
    assertEquals(3, list.getCountClassic());
  }

  /**
   * Tests the prepend method on a non-empty list to ensure data is correctly added.
   */
  @Test
  public void testPrependOnNonEmptyList() {
    ListOfInts list = new EmptyNode().append(1).append(2);
    list = list.prepend(0);

    assertEquals(0, list.getDataAtIndex(0));  // Check that the prepended value is at the start
    assertEquals(1, list.getDataAtIndex(1));  // Next value should be 1
  }

  /**
   * Tests the append method on a non-empty list to ensure data is correctly added.
   */
  @Test
  public void testAppendOnNonEmptyList() {
    ListOfInts list = new EmptyNode().append(1).append(2);
    list = list.append(3);

    assertEquals(3, list.getDataAtIndex(2)); // Check that the appended value is at the end
    assertEquals(3, list.getCountClassic());// Ensure list length is correct

  }

  /**
   * Tests inserting data at the beginning of the list (index 0).
   */
  @Test
  public void testInsertAtBeginning() {
    ListOfInts list = new EmptyNode().append(2).append(3);
    list = list.insertAtIndex(1, 0);

    assertEquals(1, list.getDataAtIndex(0));  // Check that 1 was inserted at the beginning
    assertEquals(2, list.getDataAtIndex(1));  // The other elements should follow
  }

  /**
   * Tests the sum of an empty list, which should return 0.
   */
  @Test
  public void testSumOfEmptyList() {
    ListOfInts list = new EmptyNode();

    assertEquals(0, list.getSum());  // Sum of an empty list should be 0

    // Adding an element and check again
    list = list.append(5);
    assertEquals(5, list.getSum());  // Sum should now be 5 after adding one element

  }

  /**
   * Tests that the count of an empty list is 0.
   */
  @Test
  public void testCountOfEmptyList() {
    ListOfInts list = new EmptyNode();

    assertEquals(0, list.getCountClassic());  // Count of an empty list should be 0
    assertEquals(0, list.getCountWithHelper());  // Count with helper should also be 0
  }

  /**
   * Tests appending multiple elements and verifying their count.
   */
  @Test
  public void testAppendMultipleElements() {
    ListOfInts list = new EmptyNode();
    list = list.append(1).append(2).append(3).append(4).append(5);

    assertEquals(5, list.getCountClassic());  // Should count all 5 elements

    // Check that the elements are correctly stored
    assertEquals(1, list.getDataAtIndex(0));
    assertEquals(2, list.getDataAtIndex(1));
    assertEquals(3, list.getDataAtIndex(2));
    assertEquals(4, list.getDataAtIndex(3));
    assertEquals(5, list.getDataAtIndex(4));

  }

}








