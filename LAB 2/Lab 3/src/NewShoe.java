import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * JUnit test class for shoes
 */
public class NewShoe {
  private String type;
  private Color color;
  private String brand;
  private double size;

  /**
   * Construct a shoe object that has the provided type,color,brand and size
   * @param type the type of shoe
   * @param color the color of the shoe
   * @param brand the brand of the shoe
   * @param size the size of the shoe
   */
  public NewShoe(String type, Color color, String brand, double size) {
    if(type.isEmpty()){
      throw new IllegalArgumentException("Type cannot be empty");
    }
    this.type = type;
    this.color = color;
    this.brand = brand;
    this.size = size;
  }

  public String getType() {
    return type;
  }

  public Color getColor() {
    return color;
  }

  public String getBrand() {
    return brand;
  }

  public double getSize(){
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

  public String toString() {
    String colorstr;


    switch (this.color) {
      case RED:
        colorstr = "RED";
        break;
      case BLUE:
        colorstr =  "BLUE";
        break;
      case GREEN:
        colorstr =  "GREEN";
        break;

      case YELLOW:
        colorstr = "YELLOW";
        break;
      default:
        colorstr = "To Be Determined"; // Added default case
        break;
    }

    return String.format("Type: %s\nColor: %s\nBrand: %s\nSize: %.2f",
        type, colorstr, brand, size);
  }
}
