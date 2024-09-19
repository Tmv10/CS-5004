/** This class represents Shoe. A Shoe has a type,color,brand and size
 */
public class Shoe {
  private String type;
  private String color;
  private String brand;
  private double size;

  /**
   * Construct a shoe object that has the provided type,color,brand and size
   * @param type the type of shoe
   * @param color the color of the shoe
   * @param brand the brand of the shoe
   * @param size the size of the shoe
   */
public Shoe(String type, String color,String brand, double size) {
    this.type = type;
    this.color = color;
    this.brand = brand;
    this.size = size;
  }

  public String gettype() {
    return type;
  }

  public String getcolor() {
    return color;
  }

  public String getbrand() {
    return brand;
  }

  public double getsize(){
    return size;}

  /**
   * Return a formatted string that contains the information
   * of this object. The string should be in the following format:
   * <p>
   * Type: [Type of shoe]
   * Color: [color of shoe]
   * Brand: [Brand of the shoe]
   * Size:[Size of the shoe in decimal points]
   * @return the formatted string as above
   */

  @Override
  public String toString() {
    return "Shoe{" +
        "type='" + type + '\'' +
        ", color='" + color + '\'' +
        ", brand='" + brand + '\'' +
        ", size=" + size +
        '}';
  }
}





