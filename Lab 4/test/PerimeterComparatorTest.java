import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for the PerimeterComparator class.
 * This class verifies the functionality of comparing different shapes based on their perimeters.
 */

class PerimeterComparatorTest {
  private PerimeterComparator comparator;
  private Circle circle;
  private Rectangle rectangle;
  private Triangle triangle;
  /**
   * Sets up the test environment by initializing the PerimeterComparator
   * and creating instances of Circle, Rectangle, and Triangle before each test.
   */
  @BeforeEach
  void setUp() {
    comparator = new PerimeterComparator();
    // Create shapes with specific dimensions
    circle = new Circle(0, 0, 5); // Circle with radius 5
    rectangle = new Rectangle(0, 0, 10, 5); // Rectangle with width 10 and height 5
    triangle = new Triangle(0, 0, 4, 0, 0, 3); // Triangle with vertices (0,0), (4,0), and (0,3)
  }


  /**
   * Tests the comparison between a Circle and a Rectangle.
   * Asserts that the perimeter of the Circle is greater than that of the Rectangle.
   */

  @Test
  void testCompareCircleAndRectangle() {
    // Circle has a perimeter of 31.42, Rectangle has a perimeter of 30
    assertTrue(comparator.compare(circle, rectangle) > 0, "Circle should have a greater perimeter than the rectangle");
    assertTrue(comparator.compare(rectangle, circle) < 0, "Rectangle should have a smaller perimeter than the circle");
  }

  /**
   * Tests the comparison between a Circle and a Triangle.
   * Asserts that the perimeter of the Circle is greater than that of the Triangle.
   */
  @Test
  void testCompareCircleAndTriangle() {
    // Circle has a perimeter of 31.42, Triangle has a perimeter of 12
    assertTrue(comparator.compare(circle, triangle) > 0, "Circle should have a greater perimeter than the triangle");
    assertTrue(comparator.compare(triangle, circle) < 0, "Triangle should have a smaller perimeter than the circle");
  }

  /**
   * Tests the comparison between a Rectangle and a Triangle.
   * Asserts that the perimeter of the Rectangle is greater than that of the Triangle.
   */

  @Test
  void testCompareRectangleAndTriangle() {
    // Rectangle has a perimeter of 30, Triangle has a perimeter of 12
    assertTrue(comparator.compare(rectangle, triangle) > 0, "Rectangle should have a greater perimeter than the triangle");
    assertTrue(comparator.compare(triangle, rectangle) < 0, "Triangle should have a smaller perimeter than the rectangle");
  }


  /**
   * Tests the comparison of two shapes with equal perimeters.
   * Asserts that the comparator returns zero for two triangles with the same perimeter.
   */

  @Test
  void testEqualPerimeters() {
    // Create two triangles with the same perimeter
    Triangle triangle1 = new Triangle(0, 0, 4, 0, 0, 3); // Perimeter = 12
    Triangle triangle2 = new Triangle(0, 0, 0, 3, 4, 0); // Same perimeter as triangle1

    assertEquals(0, comparator.compare(triangle1, triangle2), "Two triangles with equal perimeters should be considered equal");
  }
}
