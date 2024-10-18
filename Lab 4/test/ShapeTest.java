import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class contains all the unit tests for various kinds of shapes such as circles, rectangles, and triangles.
 * It tests the functionality of methods such as area, perimeter, and resize.
 */
public class ShapeTest {

  Shape circle1, circle2, circle3, rect1, rect2, triangle1, triangle2, triangle3;
  /**
   * Sets up various shape instances (circles, rectangles, triangles) before each test case runs.
   */

  @Before
  public void setup() {
    circle1 = new Circle(3, 4, 5);
    circle2 = new Circle(10.32, 10.43, 10);
    circle3 = new Circle(20);

    rect1 = new Rectangle(5, 6, 2.5, 2);
    rect2 = new Rectangle(2, 3, 10, 10);

    triangle1 = new Triangle(0, 0, 3, 0, 3, 4); // Right triangle with area 6, perimeter 12
    triangle2 = new Triangle(1, 1, 4, 5, 6, 1); // Scalene triangle
    triangle3 = new Triangle(-1, -1, -1, 1, 1, -1); // Isosceles triangle

  }

  /**
   * Tests whether objects have been created with the correct data.
   *
   * This test verifies that the toString methods of various shapes (circles, rectangles, and triangles)
   * produce the expected string representations, confirming that the objects have been instantiated
   * correctly with the appropriate coordinates and dimensions.
   *
   * Specifically, the test checks:
   * - The center and radius for circles.
   * - The lower-left corner, width, and height for rectangles.
   * - The three vertices for triangles.
   *
   * Assertions are made by comparing the expected string output from the toString method
   * with the actual output from the objects.
   */
  @Test
  public void testObjectData() {
    assertEquals("Circle: center (3.000,4.000) radius 5.000", circle1.toString
        ());
    assertEquals("Circle: center (10.320,10.430) radius 10.000", circle2.toString
        ());
    assertEquals("Circle: center (0.000,0.000) radius 20.000", circle3
        .toString
            ());
    assertEquals("Rectangle: LL corner (5.000,6.000) width 2.500 height 2.000",
        rect1.toString());
    assertEquals("Rectangle: LL corner (2.000,3.000) width 10.000 height 10" +
        ".000", rect2
        .toString());
    assertEquals("Triangle with points: (0.000, 0.000), (3.000, 0.000), (3.000, 4.000)",
        triangle1.toString());
    assertEquals("Triangle with points: (1.000, 1.000), (4.000, 5.000), (6.000, 1.000)",
        triangle2.toString());
    assertEquals("Triangle with points: (-1.000, -1.000), (-1.000, 1.000), (1.000, -1.000)",
        triangle3.toString());

  }

  /**
   * Tests whether the area methods work correctly for all shapes.
   *
   * This test checks the correctness of the area calculation for various shapes,
   * including circles, rectangles, and triangles.
   *
   * Specific cases include:
   * - Verifying the area of circles using the formula: {@code Math.PI * radius^2}.
   * - Checking the area of rectangles using the formula: {@code width * height}.
   * - Calculating the area of triangles using the formula: {@code |(x1*(y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2)) / 2|}.
   *
   * The test uses assertions to ensure that the calculated areas match the expected values
   * within a margin of error (0.001).
   */

  @Test
  public void testArea() {
    assertEquals(Math.PI * 25, circle1.area(), 0.001);
    assertEquals(Math.PI * 100, circle2.area(), 0.001);
    assertEquals(Math.PI * 400, circle3.area(), 0.001);
    assertEquals(5, rect1.area(), 0.001);
    assertEquals(100, rect2.area(), 0.001);

    // Triangle tests
    assertEquals(6.0, triangle1.area(), 0.001);
    System.out.println("Triangle 2 Area: " + triangle2.area());
    assertEquals(10.0, triangle2.area(), 0.001);
    System.out.println("Triangle 3 Area: " + triangle3.area());
    assertEquals(2.0, triangle3.area(), 0.001);


  }

  /**
   * Tests whether the perimeter methods for all shapes are working correctly.
   *
   * This test verifies that the perimeter of different shapes (Circle, Rectangle, and Triangle)
   * is correctly calculated and matches the expected values.
   *
   * Specific cases include:
   * - Verifying the perimeter of circles using the formula: {@code 2 * Math.PI * radius}.
   * - Checking the perimeter of rectangles using the formula: {@code 2 * (width + height)}.
   * - Calculating the perimeter of triangles by summing the distances between each pair of vertices.
   *
   * The test uses assertions to check if the calculated perimeter matches the expected values
   * within a margin of error (0.001).
   */

  @Test
  public void testPerimeter() {
    assertEquals(2 * Math.PI * 5, circle1.perimeter(), 0.001);
    assertEquals(2 * Math.PI * 10, circle2.perimeter(), 0.001);
    assertEquals(2 * Math.PI * 20, circle3.perimeter(), 0.001);

    assertEquals(9, rect1.perimeter(), 0.001);
    assertEquals(40, rect2.perimeter(), 0.001);

    assertEquals(12.0, triangle1.perimeter(), 0.001);  // Hypothetically calculated perimeter
    /*expected value for triangle2 perimeter*/
    assertEquals(14.472 ,triangle2.perimeter(), 0.001);
    /*expected value for triangle3 perimeter*/
    assertEquals(6.828, triangle3.perimeter(), 0.001);

  }

  /**
   * Tests the resizing functionality for various shapes, including Circle, Rectangle, and Triangle.
   * This test verifies that the area of the resized shapes is correctly scaled by the given resize factor.
   *
   * The resize factor for each shape is passed, and the method checks whether the resulting area matches
   * the expected area after resizing by the given factor.
   *
   * Specific cases tested include:
   * - Resizing circles with positive factors and zero.
   * - Resizing rectangles with large and small factors.
   * - Resizing triangles and verifying the area after scaling.
   *
   * The assertions compare the actual areas after resizing with the expected areas calculated by
   * multiplying the original area by the resize factor.
   */
  @Test
  public void testResizes() {
    Shape resizedCircle1, resizedCircle2, resizedCircle3, resizedRect1,
        resizedRect2, resizedTriangle1, resizedTriangle2,resizedTriangle3;

    resizedCircle1 = circle1.resize(2.5);
    resizedCircle2 = circle2.resize(0);
    resizedCircle3 = circle3.resize(10);

    resizedRect1 = rect1.resize(12.5);
    resizedRect2 = rect2.resize(0.001);

    resizedTriangle1 = triangle1.resize(3);
    resizedTriangle2 = triangle2.resize(3);
    resizedTriangle3 = triangle3.resize(1.5);

    assertEquals(2.5 * circle1.area(), resizedCircle1.area(), 0.001);
    assertEquals(0 * circle2.area(), resizedCircle2.area(), 0.001);
    assertEquals(10 * circle3.area(), resizedCircle3.area(), 0.001);

    assertEquals(12.5 * rect1.area(), resizedRect1.area(), 0.001);
    assertEquals(0.001 * rect2.area(), resizedRect2.area(), 0.001);


    assertEquals(3 * triangle1.area(), resizedTriangle1.area(), 0.001);
    assertEquals(3 * triangle2.area(), resizedTriangle2.area(), 0.001);
    assertEquals(1.5 * triangle3.area(), resizedTriangle3.area(), 0.001);


  }
}





