
public abstract class AbstractShape implements Shape {
  protected Point2D reference;

  public AbstractShape(Point2D reference) {
    this.reference = reference;
  }

  @Override
  public double distanceFromOrigin() {
    return reference.distToOrigin();
  }


  @Override
  public int compareTo(Shape s) {
    double areaThis = this.area();
    double areaOther = s.area();

    if (areaThis < areaOther) {
      return -1;
    } else if (areaOther < areaThis) {
      return 1;
    } else {
      return 0;
    }
  }
}


/** Answer 3
 *To change the comparison of shapes from area to perimeter,
 * we would need to modify the `compareTo` method in **AbstractShape.java** to use perimeter instead of area.
 * If the concrete shape classes (like Circle.java, Rectangle.java, and Triangle.java)
 * inherit from **AbstractShape**, no changes would be needed in those files since they would automatically use the updated comparison method.
 * However, without the shared code in **AbstractShape**,
 * we would have to modify all four files: **AbstractShape.java**, **Circle.java**, **Rectangle.java**, and **Triangle.java**,
 * making maintenance more cumbersome and error-prone. **/