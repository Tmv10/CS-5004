import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.EmptyStackException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the MyStack class.
 */
public class MyStackTest {
  private MyStack<String> stringStack;
  private MyStack<Double> doubleStack;
  private MyStack<Integer> integerStack;

  /**
   * Sets up the test environment by initializing MyStack instances before each test.
   */
  @BeforeEach
  void setUp() {
    stringStack = new MyStack<>();
    doubleStack = new MyStack<>();
    integerStack = new MyStack<>();
  }

  /**
   * Tests the push method of MyStack with String type.
   */
  @Test
  void testPushString() {
    stringStack.push("A");
    assertEquals("Stack: A", stringStack.toString());

    stringStack.push("B");
    assertEquals("Stack: A B", stringStack.toString());

    stringStack.push("C");
    assertEquals("Stack: A B C", stringStack.toString());
  }

  /**
   * Tests the pop method of MyStack with String type.
   */
  @Test
  void testPopString() {
    stringStack.push("A");
    stringStack.push("B");
    stringStack.push("C");

    assertEquals("C", stringStack.pop());
    assertEquals("Stack: A B", stringStack.toString());

    assertEquals("B", stringStack.pop());
    assertEquals("Stack: A", stringStack.toString());

    assertEquals("A", stringStack.pop());
    assertEquals("Stack:", stringStack.toString());
    assertThrows(EmptyStackException.class, () -> stringStack.pop());
  }

  /**
   * Tests the push and pop methods with Double type in MyStack.
   */
  @Test
  void testPushPopDouble() {
    doubleStack.push(1.1);
    assertEquals("Stack: 1.1", doubleStack.toString());

    doubleStack.push(2.2);
    assertEquals("Stack: 1.1 2.2", doubleStack.toString());

    assertEquals(2.2, doubleStack.pop());
    assertEquals("Stack: 1.1", doubleStack.toString());
  }

  /**
   * Tests the top method with Double type in MyStack.
   */
  @Test
  void testTopDouble() {
    doubleStack.push(1.1);
    doubleStack.push(2.2);
    assertEquals(2.2, doubleStack.top());
    assertEquals("Stack: 1.1 2.2", doubleStack.toString());
  }

  /**
   * Tests the push and pop methods with Integer type in MyStack.
   */
  @Test
  void testPushPopInteger() {
    integerStack.push(10);
    assertEquals("Stack: 10", integerStack.toString());

    integerStack.push(20);
    assertEquals("Stack: 10 20", integerStack.toString());

    assertEquals(20, integerStack.pop());
    assertEquals("Stack: 10", integerStack.toString());

    integerStack.push(30);
    assertEquals("Stack: 10 30", integerStack.toString());

    integerStack.pop();
    integerStack.pop();
    assertThrows(EmptyStackException.class, () -> integerStack.pop());
  }

  /**
   * Tests the top method with Integer type in MyStack.
   */
  @Test
  void testTopInteger() {
    integerStack.push(10);
    integerStack.push(20);
    assertEquals(20, integerStack.top());
    assertEquals("Stack: 10 20", integerStack.toString());

    integerStack.pop();
    assertEquals(10, integerStack.top());
  }

  /**
   * Tests the empty method of MyStack with different data types.
   */
  @Test
  void testEmptyWithVariousTypes() {
    assertTrue(stringStack.isEmpty());
    assertTrue(doubleStack.isEmpty());
    assertTrue(integerStack.isEmpty());

    stringStack.push("A");
    doubleStack.push(1.1);
    integerStack.push(100);

    assertFalse(stringStack.isEmpty());
    assertFalse(doubleStack.isEmpty());
    assertFalse(integerStack.isEmpty());

    stringStack.pop();
    doubleStack.pop();
    integerStack.pop();

    assertTrue(stringStack.isEmpty());
    assertTrue(doubleStack.isEmpty());
    assertTrue(integerStack.isEmpty());
  }

  @Test
  void testPushPopWithBook() {
    MyStack<Book> bookStack = new MyStack<>();
    Book book1 = new Book("Twilight I");
    Book book2 = new Book("Twilight II");
    Book book3 = new Book("Twilight III");


    bookStack.push(book1);
    assertEquals("Stack: Twilight I", bookStack.toString());
    bookStack.push(book2);
    assertEquals("Stack: Twilight I Twilight II", bookStack.toString());
    bookStack.push(book3);
    assertEquals("Stack: Twilight I Twilight II Twilight III", bookStack.toString());

    assertEquals(book3, bookStack.pop());
    assertEquals("Stack: Twilight I Twilight II", bookStack.toString());

    assertEquals(book2, bookStack.pop());
    assertEquals("Stack: Twilight I", bookStack.toString());
    assertEquals(book1, bookStack.pop());
    assertEquals("Stack:", bookStack.toString());

    assertThrows(EmptyStackException.class, bookStack::pop);
  }

  @Test
  void testTopWithBook() {
    MyStack<Book> bookStack = new MyStack<>();
    Book book1 = new Book("Twilight I");
    Book book2 = new Book("Twilight II");

    bookStack.push(book1);
    assertEquals(book1, bookStack.top());

    bookStack.push(book2);
    assertEquals(book2, bookStack.top());

    bookStack.pop();
    assertEquals(book1, bookStack.top());

    bookStack.pop();
    assertThrows(EmptyStackException.class, bookStack::top);
  }

  @Test
  void testIsEmptyWithBook() {
    MyStack<Book> bookStack = new MyStack<>();
    assertTrue(bookStack.isEmpty());
    bookStack.push(new Book("Twilight I"));
    assertFalse(bookStack.isEmpty());

    bookStack.pop();
    assertTrue(bookStack.isEmpty());
  }

  @Test
  void testToStringWithBook() {
    MyStack<Book> bookStack = new MyStack<>();
    assertEquals("Stack:", bookStack.toString());

    Book book1 = new Book("Twilight I");
    Book book2 = new Book("Twilight II");
    bookStack.push(book1);
    assertEquals("Stack: Twilight I", bookStack.toString());
    bookStack.push(book2);
    assertEquals("Stack: Twilight I Twilight II", bookStack.toString());
    bookStack.pop();
    assertEquals("Stack: Twilight I", bookStack.toString());
    bookStack.pop();
    assertEquals("Stack:", bookStack.toString());
  }

}



