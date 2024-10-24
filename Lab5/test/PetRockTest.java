import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Unit tests for the PetRock class.
 * <p>
 * This class tests the behavior of equals(),  hashCode(),
 * and  petTheRock methods, as well as constructor validation.
 * </p>
 */


public class PetRockTest {
  private PetRock rock1;
  private PetRock rock2;
  private PetRock rock3;
  private PetRock rock4;



  /**
   * Sets up test fixtures before each test case.
   * <p>
   * Four PetRock instances are created for use in the tests.
   * </p>
   */
  @BeforeEach
  public void setUp() {
    rock1 = new PetRock("Rocky", 5.123);
    rock2 = new PetRock("Rocky", 5.123);
    rock3 = new PetRock("Rocky", 5.124); // Slightly different mass
    rock4 = new PetRock("Boulder", 5.123); // Different name
  }


  /**
   * Tests for PetRock constructor validation and initialization.
   */
  @Test
  void testConstructor() {
    PetRock rock = new PetRock("Rocky", 5.0);
    assertEquals("Rocky", rock.getName(), "The name should be 'Rocky'");
    assertEquals(5.0, rock.getMass(), 0.001, "The mass should be 5.0");
    assertFalse(rock.getHappiness(), "Rock should initially be unhappy");
  }

  /**
   * Test that two PetRock instances with the same attributes are equal.
   */
  @Test
  void testEqualsSameAttributes() {
    assertEquals(rock1, rock2, "Rocks with the same name and mass should be equal");
    assertTrue(rock1.equals(rock2), "Rocks should return true when compared with equals()");
  }

  /**
   * Test that two PetRock instances with different masses are not equal.
   */
  @Test
  void testNotEqualsDifferentMass() {
    assertNotEquals(rock1, rock3, "Rocks with different masses should not be equal");
    assertFalse(rock1.equals(rock3),
        "Rocks with different masses should return false when compared with equals()");
  }

  /**
   * Test that two PetRock instances with different names are not equal.
   */
  @Test
  void testNotEqualsDifferentName() {
    assertNotEquals(rock1, rock4, "Rocks with different names should not be equal");
    assertFalse(rock1.equals(rock4),
        "Rocks with different names should return false when compared with equals()");
  }

  /**
   * Test that two PetRock instances with different happiness states are not equal.
   */
  @Test
  void testNotEqualsDifferentHappiness() {
    rock1.petTheRock(); // Pet rock1 to make it happy
    assertNotEquals(rock1, rock2, "Rocks with different happiness states should not be equal");
    assertFalse(rock1.equals(rock2),
        "Rocks with different happiness states should return false when compared with equals()");
  }

  /**
   * Test that hashCodes are equal for two PetRock instances that are equal.
   */
  @Test
  void testHashCodeEqual() {
    assertEquals(rock1.hashCode(), rock2.hashCode(), "Equal rocks should have the same hash code");
    assertTrue(rock1.hashCode() == rock2.hashCode(),
        "Equal rocks should return true when comparing hashCodes directly");
  }

  /**
   * Test that hashCodes are not equal for two PetRock instances that are not equal.
   */
  @Test
  void testHashCodeNotEqual() {
    assertNotEquals(rock1.hashCode(), rock3.hashCode(),
        "Different rocks should have different hash codes");
    assertFalse(rock1.hashCode() == rock3.hashCode(),
        "Different rocks should return false when comparing hashCodes directly");
  }




  /**
   * Test the getter methods of the PetRock class.
   */
  @Test
  void testGetters() {
    PetRock rock = new PetRock("Rocky", 5.123);
    assertEquals("Rocky", rock.getName(), "The name should be 'Rocky'");
    assertEquals(5.123, rock.getMass(), 0.001, "The mass should be 5.123");
    assertFalse(rock.getHappiness(), "Rock should initially be unhappy");
  }




  /**
   * Test that a petting changes the happiness state of the PetRock.
   */
  @Test
  void testPetTheRock() {
    assertFalse(rock1.getHappiness(), "Rock should initially be unhappy");
    rock1.petTheRock();
    assertTrue(rock1.getHappiness(), "Rock should be happy after being petted");
    assertEquals(true, rock1.getHappiness(),
        "Rock happiness state should be true after being petted");
  }

  /**
   * Test that equals method returns false for null.
   */
  @Test
  void testEqualsNull() {
    assertNotEquals(null, rock1, "A rock should not be equal to null");
    assertFalse(rock1.equals(null), "Equals method should return false when comparing with null");
  }

  /**
   * Test that equals method returns false for different object types.
   */
  @Test
  void testEqualsDifferentType() {
    assertNotEquals("Not a PetRock", rock1,
        "A rock should not be equal to a different object type");
    assertFalse(rock1.equals("Not a PetRock"),
        "Equals method should return false when comparing with a different object type");
  }

  /**
   * Tests the toString method to ensure correct string representation of a PetRock.
   */
  @Test
  void testToString() {
    assertEquals ("PetRock{name='Rocky', mass=5.123, happiness=false}", rock1.toString());
    rock1.petTheRock();
    assertEquals("PetRock{name='Rocky', mass=5.123, happiness=true}", rock1.toString());
  }
}
