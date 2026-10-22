import java.util.*; // import java utilities

public class CircularQueue extends DSAQueue {

    // Default constructor
    public CircularQueue() {
        queue = new Object[maxSize]; // default size for array of objects
        count = 0; // set count to 0
    }

    // Alternating constructor
    public CircularQueue(int qSize) {
        queue = new Object[qSize]; // determine size for array of objects
        count = 0; // set count to 0
    }

    // peek
    @Override
    public Object peek() {
        if (isEmpty()) { // if stack is empty, throw an exception
            throw new IllegalArgumentException("Queue empty.");
        }
        Object pObj = queue[start]; // store value for current start element
        return pObj; // return stored value
    }

    // enqueue
    @Override
    public void enqueue(Object qObj) {
        if (isFull()) { // validation if stack is full
            System.out.println("Queue full.");
        } else {
            queue[end] = qObj; // insert new element into queue
            end = (end + 1) % queue.length; // increment to state new end value
            count++; // increase count
        }
    }

    // dequeue
    @Override
    public Object dequeue() {
        if (isEmpty()) { // throw exception if stack is empty
            throw new IllegalArgumentException("Queue empty.");
        }
        Object qVal = queue[start]; // store front queue element
        queue[start] = null; // empty front queue element
        start = (start + 1) % queue.length; // increment to state new start value
        count--; // decrease count by 1
        return qVal; // return stored value
    }

    // isEmpty (already in parent class)
    // isFull (located in parent class)
    // count (located in parent class)

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);

        System.out.println("Queue in values: 1, 2, 3, 4, 5");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        // Get count
        System.out.println("Current count: " + queue.getCount());

        // Should be 1
        System.out.println("Front: " + queue.peek());

        // Should be 1
        System.out.println("Dequeue: " + queue.dequeue());

        // Should be 2
        System.out.println("Front: " + queue.peek());

        // Should be 2
        System.out.println("Dequeue: " + queue.dequeue());

        System.out.println("Queue in new values: 6, 7");
        queue.enqueue(6);
        queue.enqueue(7);

        // Should be 3
        System.out.println("Front: " + queue.peek());

        // Get count
        System.out.println("Current count: " + queue.getCount());
    }
}