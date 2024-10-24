import java.util.Objects;
/**
 * PetRock is a class that represents a pet rock.
 * <p>
 * The purpose of this class is to practice implementing the equals and hashCode
 * methods. Each PetRock has a name, a happiness state, and a mass.
 * </p>
 * <p>
 * PetRock objects are immutable in terms of their name and mass, but their
 * happiness state can change after the rock is petted.
 * </p>
 */
public class PetRock {
  private final String name;
  private boolean happiness;
  private final double mass;



  /**
   * Constructs a PetRock instance with the specified name and mass.
   * <p>
   * The name and mass are final and cannot be changed once the
   * PetRock is created. happiness is initialized to false,
   * indicating the pet rock starts in an unhappy state.
   * </p>
   *
   * @param name the name of the pet rock. Must be non-null and non-empty.
   * @param mass the mass of the pet rock. Must be a positive value.
   * @throws IllegalArgumentException if the name is null, empty, or if the mass is non-positive.
   */
  public PetRock(String name, double mass) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (mass <= 0) {
      throw new IllegalArgumentException("Mass must be positive");
    }
    this.name = name;
    this.happiness = false;
    this.mass = mass;
  }



  /**
   * Returns the name of this pet rock.
   *
   * @return the immutable name of the pet rock
   */
  public String getName() {

    return name;
  }


  /**
   * Returns the current happiness state of this pet rock.
   *
   * @return true if the rock has been petted, false otherwise
   */
  public boolean getHappiness() {

    return happiness;
  }


  /**
   * Makes the pet rock happy by petting it.
   * <p>
   * Once a rock has been petted, it remains happy forever.
   */
  public void petTheRock() {

    this.happiness = true;
  }




  /**
   * Returns the mass of the pet rock.
   *
   * @return the immutable mass of the pet rock in arbitrary units
   */
  public double getMass() {

    return mass;
  }

  /**
   * Compares this PetRock to the specified object.
   * The result is true if and only if the argument is not null,
   * is an instance of PetRock, and has the same name, happiness status,
   * and mass (within three decimal places).
   *
   * @param obj the object to compare this PetRock against
   * @return true if the given object represents a PetRock with
   *         the same name, happiness, and mass (within three decimal places);
   *         false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof PetRock)) return false;

    PetRock other = (PetRock) obj;
    return this.name != null && this.name.equals(other.name) &&
        this.happiness == other.happiness &&
        Math.abs(this.mass - other.mass) < 0.001;
  }


  /**
   * Returns the hash code value for PetRock.
   * <p>
   * The hash code is computed based on the name, happiness, and mass (rounded to three decimal places).
   * </p>
   *
   * @return the hash code value for PetRock.
   */
  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + (happiness ? 1 : 0);
    long massLong = Double.doubleToLongBits(Math.round(mass * 1000.0) / 1000.0);
    result = 31 * result + (int) (massLong ^ (massLong >>> 32));
    return result;
  }


  /**
   * Returns a string representation of  PetRock.
   * <p>
   * The format is:  PetRock{name='name', mass=mass, happiness=happiness}}
   * </p>
   *
   * @return a string representation of this pet rock
   */
  @Override
  public String toString() {
    return String.format("PetRock{name='%s', mass=%.3f, happiness=%b}",
        name, mass, happiness);
  }
}

