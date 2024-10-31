import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * A generic stack implementation using an ArrayList to store elements.
 * Follows LIFO (Last In First Out) principle.
 * @param <T> the type of elements in this stack
 */
public class MyStack<T> {
  /** The ArrayList that is to store the elements of the stack */
  private ArrayList<T> stack;

  /**
   * Constructs an empty stack.
   */
  public MyStack() {

    stack = new ArrayList<>();
  }


  /**
   * Adds an element to the top of the stack.
   *
   * @param x the element to be added to the stack
   */
  public void push(T x) {

    stack.add(x);
  }

  /**
   * Removes and returns the top element of the stack.
   *
   * @return the top element of the stack
   * @throws EmptyStackException if the stack is empty
   */
  public T pop() {
    if (stack.isEmpty()) {
      throw new EmptyStackException();
    }
    return stack.remove(
        stack.size() - 1);
  }

  /**
   * Returns the top element of the stack without removing it.
   *
   * @return the top element of the stack
   * @throws EmptyStackException if the stack is empty
   */
  public T top() {
    if (stack.isEmpty()) {
      throw new EmptyStackException();
    }
    return stack.get(
        stack.size() - 1);
  }

  /**
   * Checks whether the stack is empty.
   *
   * @return true if the stack is empty, false otherwise
   */
  public boolean isEmpty() {

    return stack.isEmpty();
  }

  /**
   * Returns a string representation of the stack, displaying all elements.
   *
   * @return a string representing the elements in the stack
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("Stack: ");
    for (T element : stack) {
      result.append(element).append(" ");
    }
    return result.toString().trim();
  }
}
