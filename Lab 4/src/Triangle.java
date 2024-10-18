
/**
 * This class represents a triangle defined by three points in 2D space.
 * It extends the AbstractShape class and provides methods to calculate the
 * area and perimeter of the triangle. Additionally, it allows resizing the
 * triangle while maintaining its shape based on a scaling factor.
 *
 * The triangle is defined by three vertices: the reference point (the first
 * vertex) and two additional points. The class ensures that all three points
 * are unique; if not, an IllegalArgumentException is thrown during
 * construction.
 */

public class Triangle extends AbstractShape {

  private Point2D point2;
  private Point2D point3;


  /**
   * Constructs a triangle with the specified vertices.
   *
   * @param x1 the x-coordinate of the first vertex
   * @param y1 the y-coordinate of the first vertex
   * @param x2 the x-coordinate of the second vertex
   * @param y2 the y-coordinate of the second vertex
   * @param x3 the x-coordinate of the third vertex
   * @param y3 the y-coordinate of the third vertex
   * @throws IllegalArgumentException if any two vertices are the same
   */
  public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
    super(new Point2D(x1, y1));
    this.point2 = new Point2D(x2, y2);
    this.point3 = new Point2D(x3, y3);

    if (reference.equals(point2) || reference.equals(point3) || point2.equals(point3)) {
      throw new IllegalArgumentException("Points must be unique");
    }
  }

  /**
   * Calculates the area of the triangle using the formula:
   * <pre>
   * area = |(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0|
   * </pre>
   *
   * @return the area of the triangle
   */

  @Override
  public double area() {
    double x1 = reference.getX();
    double y1 = reference.getY();
    double x2 = point2.getX();
    double y2 = point2.getY();
    double x3 = point3.getX();
    double y3 = point3.getY();


    return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
  }

  /**
   * Calculates the perimeter of the triangle by summing the lengths of all its sides.
   *
   * @return the perimeter of the triangle
   */

  @Override
  public double perimeter() {

    //Calculate the distances between the points:
    double distanceAB = Math.sqrt(Math.pow(reference.getX() - point2.getX(), 2) +
        Math.pow(reference.getY() - point2.getY(), 2));

    double distanceBC = Math.sqrt(Math.pow(point2.getX() - point3.getX(), 2) +
        Math.pow(point2.getY() - point3.getY(), 2));

    double distanceCA = Math.sqrt(Math.pow(point3.getX() - reference.getX(), 2) +
        Math.pow(point3.getY() - reference.getY(), 2));

    return distanceAB + distanceBC + distanceCA; // Sum of the lengths of all sides
  }


  /**
   * Resizes the triangle by a given scaling factor. The size of the triangle
   * is modified while maintaining its shape.
   *
   * @param factor the scaling factor; must be greater than zero
   * @return a new Triangle instance with the resized dimensions
   * @throws IllegalArgumentException if the resize factor is less than or equal to zero
   */
  @Override
  public Shape resize(double factor) {
    if (factor <= 0) {
      throw new IllegalArgumentException("Resize factor must be greater than zero.");
    }
    // Calculate the linear scaling factor to maintain the new area
    double scaleFactor = Math.sqrt(factor); // Linear scale factor for area resizing

    double newX2 = reference.getX() + (point2.getX() - reference.getX()) * scaleFactor;
    double newY2 = reference.getY() + (point2.getY() - reference.getY()) * scaleFactor;
    double newX3 = reference.getX() + (point3.getX() - reference.getX()) * scaleFactor;
    double newY3 = reference.getY() + (point3.getY() - reference.getY()) * scaleFactor;

    // Return a new Triangle instance with the new points
    return new Triangle(reference.getX(), reference.getY(), newX2, newY2, newX3, newY3);
  }

  /**
   * Returns a string representation of the triangle, displaying the coordinates
   * of its vertices.
   *
   * @return a string formatted as "Triangle with points: (x1, y1), (x2, y2), (x3, y3)"
   */
  @Override
  public String toString() {
    return String.format("Triangle with points: (%.3f, %.3f), (%.3f, %.3f), (%.3f, %.3f)",
        reference.getX(), reference.getY(), point2.getX(), point2.getY(), point3.getX(), point3.getY());
  }
}


