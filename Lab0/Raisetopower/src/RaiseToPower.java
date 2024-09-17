public class RaiseToPower {
  public static void main(String[] args) {

    double base = 5.0;
    int exponent = 6;
    double result1 = Math.pow(base, exponent);

    double result2 = 1;
    for (int i = 0; i < Math.abs(exponent); i++) {
      result2 *= base;
    }

    System.out.println("method1: " + base + " rise to power of " + exponent + " is: " + result1);
    System.out.println("method2: " + base + " rise to power of " + exponent + " is: " + result2);
  }
  }

