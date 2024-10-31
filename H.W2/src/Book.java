import java.util.Objects;

public class Book {
  /** * Represents a book with a title.
      * Used as an example of a custom object that can be stored in MyStack.
      */
    private String title;

    /**
     * Constructs a Book with the given title.
     *
     * @param title the title of the book
     */
    public Book(String title) {

      this.title = title;
    }

    /**
     * Gets the title of the book.
     *
     * @return the book's title
     */
    public String getTitle() {

      return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the new title for the book
     */
    public void setTitle(String title) {

      this.title = title;
    }

    /**
     * Returns string representation of Book object.
     *
     * @return the title of the book
     */
    @Override
    public String toString() {

      return title;
    }

    /**
     * Checks if this book is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the books have the same title
     */
    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass())
        return false;
      Book book = (Book) obj;
      return Objects.equals(title, book.title);
    }

    /**
     * Generates a hash code for the book.
     *
     * @return hash code based on the book's title
     */
    @Override
    public int hashCode() {
      return title != null ? title.hashCode() : 0;
    }
  }