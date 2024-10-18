/**
 * This class represents a triangle.  It defines all the operations mandated by
 * the Shape interface
 */
public class Triangle extends AbstractShape {
  private Point2D point2;
  private Point2D point3;


public Triangle(Point2D reference, Point2D point2, Point2D point3) {
  super(reference);
  if (reference.equals(point2) || reference.equals(point3) || point2.equals(point3)) {
    throw new IllegalArgumentException("All points of the triangle must be unique.");
  }
  this.point2 = point2;
  this.point3 = point3;
}
