import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the NewShoe class.
 * This class tests the functionality of the NewShoe class by
 * validating constructor behavior and ensuring getter methods return
 * the correct values.
 */

public class NewShoeTest {

  private NewShoe newShoe;// Declare a variable for a NewShoe object
  private NewShoe newShoe2;// Declare another variable for a second NewShoe object


  @BeforeEach
  public void setUp() {
    // Initialize NewShoe objects before each test
    newShoe = new NewShoe("Sneaker", Color.RED, "Nike", 10.5);
    newShoe2 = new NewShoe("Boot", Color.BLUE, "Adidas", 9.0);
  }


  /**
   * Tests the constructor of NewShoe class to ensure it throws
   * IllegalArgumentException when invalid parameters are provided.
   */
  @Test
  public void testConstructor() {
    assertThrows(IllegalArgumentException.class, () ->
        new NewShoe("", Color.GREEN, "Puma", 8));
    assertThrows(IllegalArgumentException.class, () ->
        new NewShoe("", Color.BLUE, "Adidas", 9.0));
  }

  /**
   * Tests the getColor method to ensure it returns the correct color
   * of the shoe.
   */
  @Test
  public void testGetColor() {
    assertEquals(Color.RED, newShoe.getColor());
    assertEquals(Color.BLUE, newShoe2.getColor());
  }

  /**
   * Tests the getType method to ensure it returns the correct type
   * of the shoe.
   */
  @Test
  public void testGetType() {
    assertEquals("Sneaker", newShoe.getType());
    assertEquals("Boot", newShoe2.getType());
  }

  /**
   * Tests the getBrand method to ensure it returns the correct brand
   * of the shoe.
   */
  @Test
  public void testGetBrand() {
    assertEquals("Nike", newShoe.getBrand());
    assertEquals("Adidas", newShoe2.getBrand());
  }


  /**
   * Tests the getSize method to ensure it returns the correct size
   * of the shoe. Includes a delta for comparing double values.
   */
  @Test
  public void testGetSize() {
    assertEquals(10.5, newShoe.getSize(), 0.01);
    assertEquals(9.0, newShoe2.getSize(), 0.01);  // Added delta for double comparison
  }


  /**
   * Tests the toString method to ensure it returns the correct string
   * representation of the NewShoe object.
   */
  @Test
  public void testToString() {
    String expected1 = "Type: Sneaker\nColor: RED\nBrand: Nike\nSize: 10.50";
    String expected2 = "Type: Boot\nColor: BLUE\nBrand: Adidas\nSize: 9.00";
    assertEquals(expected1, newShoe.toString());
    assertEquals(expected2, newShoe2.toString());
  }
}

