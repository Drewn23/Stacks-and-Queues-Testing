import java.util.*; // import java utilities

public class ShufflingQueue extends DSAQueue {

    // Default constructor
    public ShufflingQueue() {
        queue = new Object[maxSize]; // default size for array of objects
        count = 0; // set count to 0
    }

    // Alternating constructor
    public ShufflingQueue(int qSize) {
        queue = new Object[qSize]; // determine size of array of objects
        count = 0; // set count to 0
    }

    // peek
    @Override
    public Object peek() {
        if (isEmpty()) { // if queue is empty throw exceptions
            throw new IllegalArgumentException("Queue empty.");
        }
        Object pObj = queue[0]; // store value of first array element
        return pObj; // return stored value
    }

    // enqueue
    @Override
    public void enqueue(Object qObj) {
        if (isFull()) { // validation if queue is full
            System.out.println("Queue full.");
        } else {
            queue[count] = qObj; // insert object into queue
            count++; // increase count by 1
        }
    }

    // dequeue
    @Override
    public Object dequeue() {
        if (isEmpty()) { // throw exception if queue is empty
            throw new IllegalArgumentException("Queue empty.");
        }
        Object frontVal = peek(); // store first element in queue
        for (int i = 0; i < count - 1; i++) {
            queue[i] = queue[i + 1]; // displace elements in queue by 1
        }
        queue[count - 1] = null; // remove last element in array
        count--; // decrease count by 1
        return frontVal; // return stored value
    }

    // isEmpty (already in parent class)
    // isFull (located in parent class)
    // count (located in parent class)

    public static void main(String[] args) {
        ShufflingQueue queue = new ShufflingQueue(5);

        System.out.println("Queueing some names:");
        System.out.println(" Jacob, Sam, Diana, Howard, Jessica");

        queue.enqueue("Jacob");
        queue.enqueue("Sam");
        queue.enqueue("Diana");
        queue.enqueue("Howard");
        queue.enqueue("Jessica");

        System.out.println("\nFront name: " + queue.peek());
        System.out.println("\nDequeueing names......");

        while (!queue.isEmpty()) {
            System.out.println("\nRemoving :" + queue.dequeue());
        }
    }
}