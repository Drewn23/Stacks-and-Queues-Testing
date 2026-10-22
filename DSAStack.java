import java.util.*; // import java utilities

public class DSAStack {

    // Class fields. No need to call again
    private Object[] stack; // array of type Objects
    private int count;

    // Private. Stop from being involved in any other code
    private static int stackSize = 10;

    // Default constructor
    public DSAStack() {
        stack = new Object[stackSize]; // create array of Objects with default size
        count = 0; // set count to 0
    }

    // Alternating constructor
    public DSAStack(int newStack) {
        stack = new Object[newStack]; // construct an array with a determined size
        count = 0; // set count to 0
    }

    // getCount
    public int getCount() {
        return count; // return count value
    }

    // isEmpty. Questionnaire if stack is empty
    public boolean isEmpty() {
        return count == 0; // determine if count is 0
    }

    // isFull. Query if stack is full
    public boolean isFull() {
        return count == stack.length; // determine if count is equal to maximum stack size
    }

    // Top
    public Object top() {
        if (isEmpty()) {
            throw new EmptyStackException(); // throw exception
        }
        return stack[count - 1]; // return the top value of array
    }

    // Push
    public void push(Object inObject) {
        if (isFull()) { // if stack is full
            System.out.println("Stack is full");
            return;
        } else {
            stack[count] = inObject; // insert the pushed object into the stack
            count++; // increase count
        }
    }

    // Pop
    public Object pop() {
        if (isEmpty()) { // if stack is empty
            throw new EmptyStackException();
        }
        Object returnVal = stack[count - 1]; // store top value
        stack[count - 1] = null; // empty top stack value
        count--; // decrease count by 1
        return returnVal; // return the stored top value
    }
}