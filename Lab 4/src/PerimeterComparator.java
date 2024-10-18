import java.util.Comparator;

  /**
   * This class implements Comparator for Shape objects, allowing comparison based on perimeter.
   */
  public class PerimeterComparator implements Comparator<Shape> {
    /**
     * Compares two Shape objects based on their perimeter.
     * @param shape1 the first Shape to compare
     * @param shape2 the second Shape to compare
     * @return a negative integer, zero, or a positive integer as the first shape
     *         has less than, equal to, or greater than the second shape's perimeter.
     */
    @Override
    public int compare(Shape shape1, Shape shape2) {
      double perimeter1 = shape1.perimeter();
      double perimeter2 = shape2.perimeter();



      // Compare based on perimeter
      // shape1 is greater than shape2
      // both are equal
      return Double.compare(perimeter1, perimeter2); // shape1 is less than shape2
    }
  }

