import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.EmptyStackException;

/**
 * Test class for MyStack implementation.
 * Contains comprehensive tests for all stack operations including edge cases
 * and different data types. Each method is tested with at least three different
 * test cases to ensure proper functionality.
 */
public class MyStackTest {

  /**
   * Tests pushing a single String element onto the stack.
   * Verifies correct string representation after push.
   */
  @Test
  void testPushString(){
    MyStack<String> myStack = new MyStack<>();
    myStack.push("Hello");
    assertEquals("Stack: Hello", myStack.toString());
  }

  /**
   * Tests pushing multiple String elements onto the stack.
   * Verifies correct order and representation of multiple elements.
   */
  @Test
  void testPushMultipleStrings(){
    MyStack<String> myStack = new MyStack<>();
    myStack.push("First");
    myStack.push("Second");
    assertEquals("Stack: First Second", myStack.toString());
  }

  /**
   * Tests pushing a Double element onto the stack.
   * Verifies stack is not empty after push operation.
   */
  @Test
  void testPushDouble(){
    MyStack<Double> myStack = new MyStack<>();
    myStack.push(3.14);
    assertFalse(myStack.isEmpty());
  }

  /**
   * Tests that pop returns the last element added to the stack.
   * Verifies LIFO (Last In First Out) behavior.
   */
  @Test
  void testPopReturnLastElement(){
    MyStack<Integer> myStack = new MyStack<>();
    myStack.push(1);
    myStack.push(2);
    assertEquals(2, myStack.pop());
  }

  /**
   * Tests that pop removes the element from the stack.
   * Verifies correct string representation after pop.
   */
  @Test
  void testPopRemovesElement(){
    MyStack<String> myStack = new MyStack<>();
    myStack.push("First");
    myStack.push("Second");
    myStack.pop();
    assertEquals("Stack: First", myStack.toString());
  }

  /**
   * Tests that pop throws EmptyStackException when stack is empty.
   * Verifies proper exception handling.
   */
  @Test
  void testPopEmptyStack() {
    MyStack<Character> stack = new MyStack<>();
    assertThrows(EmptyStackException.class, stack::pop);
  }

  /**
   * Tests that top returns the last element without removing it.
   * Verifies correct element is returned.
   */
  @Test
  void testTopReturnsLastElement() {
    MyStack<Integer> stack = new MyStack<>();
    stack.push(1);
    stack.push(2);
    assertEquals(2, stack.top());
  }

  /**
   * Tests that top does not remove the element from stack.
   * Verifies stack remains non-empty after top operation.
   */
  @Test
  void testTopDoesNotRemoveElement() {
    MyStack<String> stack = new MyStack<>();
    stack.push("Testing");
    stack.top();
    assertFalse(stack.isEmpty());
  }

  /**
   * Tests that top throws EmptyStackException when stack is empty.
   * Verifies proper exception handling.
   */
  @Test
  void testTopEmptyStack() {
    MyStack<Double> stack = new MyStack<>();
    assertThrows(EmptyStackException.class, stack::top);
  }

  /**
   * Tests that a newly created stack is empty.
   * Verifies initial state of stack.
   */
  @Test
  void testEmptyNewStack() {
    MyStack<String> stack = new MyStack<>();
    assertTrue(stack.isEmpty());
  }

  /**
   * Tests that stack is not empty after push operation.
   * Verifies empty state changes correctly after push.
   */
  @Test
  void testEmptyAfterPush() {
    MyStack<Integer> stack = new MyStack<>();
    stack.push(1);
    assertFalse(stack.isEmpty());
  }

  /**
   * Tests that stack becomes empty after pushing and popping an element.
   * Verifies empty state changes correctly after pop.
   */
  @Test
  void testEmptyAfterPushAndPop() {
    MyStack<Character> stack = new MyStack<>();
    stack.push('X');
    stack.pop();
    assertTrue(stack.isEmpty());
  }




  /**
   * Sample Book class for testing stack with custom objects.
   * Demonstrates stack's ability to handle user-defined types.
   */
  static class Book {
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
     * Returns string representation of Book object.
     *
     * @return the title of the book
     */
    @Override
    public String toString() {
      return title;
    }
  }

  /**
   * Tests stack operations with custom Book class.
   * Verifies stack can handle custom objects correctly.
   */
  @Test
  void testCustomClassBook() {
    MyStack<Book> stack = new MyStack<>();
    Book book1 = new Book("Harry Potter 1");
    Book book2 = new Book("Harry Potter 2");
    stack.push(book1);
    stack.push(book2);
    assertEquals(book2, stack.pop());
    assertEquals(book1, stack.top());
  }
}


