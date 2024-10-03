import static org.junit.jupiter.api.Assertions.*;// Importing assertions for unit tests


import org.junit.jupiter.api.BeforeEach;// Importing annotation for setup method
import org.junit.jupiter.api.Test;// Importing annotation for test methods

public class NewShoeTest {

  private NewShoe newShoe;// Declare a variable for a NewShoe object
  private NewShoe newShoe2;// Declare another variable for a second NewShoe object


  @BeforeEach
  public void setUp() {
    // Initialize NewShoe objects before each test
    newShoe = new NewShoe("Sneaker", Color.RED, "Nike", 10.5);
    newShoe2 = new NewShoe("Boot", Color.BLUE, "Adidas", 9.0);
  }

  @Test
  public void testConstructor() {
    // Test that the constructor throws an exception for an empty type
    assertThrows(IllegalArgumentException.class, () ->
        new NewShoe("", Color.GREEN, "Puma", 8));
    assertThrows(IllegalArgumentException.class, () ->
        new NewShoe("", Color.BLUE, "Adidas", 9.0));
  }

  @Test
  public void testGetColor() {
    // Test that the getColor method returns the correct color
    assertEquals(Color.RED, newShoe.getColor());
    assertEquals(Color.BLUE, newShoe2.getColor());
  }
  @Test
  public void testGetType() {
    // Test that the getType method returns the correct type
    assertEquals("Sneaker", newShoe.getType());
    assertEquals("Boot", newShoe2.getType());
  }

  @Test
  public void testGetBrand() {
    // Test that the getBrand method returns the correct brand
    assertEquals("Nike", newShoe.getBrand());
    assertEquals("Adidas", newShoe2.getBrand());
  }

  @Test
  public void testGetSize() {
    // Test that the getSize method returns the correct size
    assertEquals(10.5, newShoe.getSize(), 0.01);
    assertEquals(9.0, newShoe2.getSize(), 0.01);  // Added delta for double comparison
  }

  @Test
  public void testToString() {
    // Expected string representations of the NewShoe objects
    String expected1 = "Type: Sneaker\nColor: RED\nBrand: Nike\nSize: 10.50";
    String expected2 = "Type: Boot\nColor: BLUE\nBrand: Adidas\nSize: 9.00";
    assertEquals(expected1, newShoe.toString());
    assertEquals(expected2, newShoe2.toString());
  }
}

