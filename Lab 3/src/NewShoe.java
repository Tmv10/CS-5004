import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * JUnit test class for shoes
 */
public class NewShoe {
  // Instance variables representing the shoe's attributes
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
    // Validate that the type is not empty
    if(type.isEmpty()){
      throw new IllegalArgumentException("Cannot be empty");
    }
    this.type = type;
    this.color = color;
    this.brand = brand;

    // Validate that the brand is not empty
    if(brand.isEmpty()) {
      throw new IllegalArgumentException("Cannot be empty");
    }
    this.size = size;
  }
  // Getter method for the type of the shoe
  public String getType() {
    return type;
  }
  // Getter method for the color of the shoe
  public Color getColor() {
    return color;
  }

  // Getter method for the brand of the shoe
  public String getBrand() {
    return brand;
  }
  // Getter method for the size of the shoe
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
    String colorStr;// Variable to hold the string representation of the color

    // Switch case to determine the string representation of the color
    switch (this.color) {
      case RED:
        colorStr = "RED";
        break;
      case BLUE:
        colorStr =  "BLUE";
        break;
      case GREEN:
        colorStr =  "GREEN";
        break;

      case YELLOW:
        colorStr = "YELLOW";
        break;
      default:
        colorStr = "To Be Determined"; // Added default case
        break;
    }

    // Return the formatted string with shoe details
    return String.format("Type: %s%nColor: %s%nBrand: %s%nSize: %.2f",
        type, colorStr, brand, size);
  }
}
