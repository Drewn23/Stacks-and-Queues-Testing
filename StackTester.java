import java.util.*; // import java utilities

public class StackTester {
    public static void main(String[] args) {

        // Create a new stack
        DSAStack myStack = new DSAStack();

        // Define elements to push
        int[] numbersToAdd = {11, 22, 33, 44, 55, 66, 77};

        // Push elements onto the stack
        for (int number : numbersToAdd) {
            myStack.push(number);
            System.out.println("Inserted: " + number);
        }

        // Prompts
        System.out.println("\nStack Information:");
        System.out.println("Number of items: " + myStack.getCount());
        System.out.println("Is stack empty? " + myStack.isEmpty());
        System.out.println("Is stack full? " + myStack.isFull());
        System.out.println("Current top: " + myStack.top());

        System.out.println("\nRemoving elements from the stack:");
        try {
            while (!myStack.isEmpty()) { // while loop to remove stacked elements
                System.out.println("Removed: " + myStack.pop());
            }

            // Attempt to pop from empty stack
            System.out.println("Removed: " + myStack.pop());
        } catch (EmptyStackException e) {
            System.err.println("Cannot remove: Stack is already empty!");
        }
    }
}