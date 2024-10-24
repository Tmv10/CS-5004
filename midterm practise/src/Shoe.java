/**This class represent shoe **/
public class Shoe {
  private double size;
  private String color;
  private double type;
  private String brand;
}

/// Construct a shoe object that has the provided type,color,brand and size
/// @param shoeSize the color of the shoe
/// @param shoeType the brand of the shoe
/// @param shoePrice the price of the shoe
 ///
//constructor

public Shoe(Double size,String type){
  this.size = size;
  this.type = type;
  this.price = price;
  if (type.isEmpty()){
    throw new IllegalArgumentException("Shoe type cannot be empty");
  }
  this.type = type;
  this.color = color;
  this.brand = brand;
}








public String toString() {
  String colorStr;
  switch (this.color) {
    case "red":
      colorStr = "red";
      break;
    case "blue":
      colorStr = "blue";
      break;
    case "yellow":
      colorStr = "yellow";
      break;

  }

  return String.format("Type:%s%n Color: %s%n size:%.2f",
      type, colorStr, brand, size);
}

}






