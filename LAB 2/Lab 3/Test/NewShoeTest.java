import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NewShoeTest {
  private NewShoe newShoe;
  private NewShoe newShoe2;

  @BeforeEach
  public void setUp() {
    newShoe = new NewShoe("Sneaker", Color.RED, "Nike", 10.5);
    newShoe2 = new NewShoe("Boot", Color.BLUE, "Adidas", 9.0);
  }

  @Test
  public void testConstructorAndGetters() {
    assertEquals("Sneaker", newShoe.getType());
    assertEquals(Color.RED, newShoe.getColor());
    assertEquals("Nike", newShoe.getBrand());
    assertEquals(10.5, newShoe.getSize(), 0.01);

    assertEquals("Boot", newShoe2.getType());
    assertEquals(Color.BLUE, newShoe2.getColor());
    assertEquals("Adidas", newShoe2.getBrand());
    assertEquals(9.0, newShoe2.getSize(), 0.01);  // Added delta for double comparison
  }

  @Test
  public void testEmptyThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      new NewShoe("", Color.GREEN, "Puma", 8);
    });
  }

  @Test
  public void testToString() {
    String expected1 = "Type: Sneaker\nColor: RED\nBrand: Nike\nSize: 10.50";
    String expected2 = "Type: Boot\nColor: BLUE\nBrand: Adidas\nSize: 9.00";
    assertEquals(expected1, newShoe.toString());
    assertEquals(expected2, newShoe2.toString());
  }

  @Test
  public void testDefaultColorInToString() {
    NewShoe shoe = new NewShoe("Sandal", Color.PURPLE, "Gucci", 7.5);
    String expected = "Type: Sandal\nColor: To Be Determined\nBrand: Gucci\nSize: 7.50";
    assertEquals(expected, shoe.toString());
  }
}