import java.util.*; // import java utilities

public abstract class DSAQueue {

    // Class fields
    protected int start = 0;
    protected int end = 0;
    protected int count;
    protected Object[] queue; // queue is declared as an array of objects
    protected int maxSize = 50; // limit array size to 50

    // enqueue
    public abstract void enqueue(Object value);

    // dequeue
    public abstract Object dequeue();

    // peek
    public abstract Object peek();

    // getCount
    public int getCount() {
        return count;
    }

    // isEmpty
    public boolean isEmpty() {
        return count == 0; // return true/false if count equals 0
    }

    // isFull
    public boolean isFull() {
        return count == queue.length; // return true/false if count equals length of the queue
    }

    @Override
    public String toString() { // declare new toString function to trim and separate spacings in string

        // Allocate enough space
        char[] charArray = new char[queue.length * 2];
        int index = 0;

        for (int i = start; i != end; i = (i + 1) % queue.length) {
            if (queue[i] != null) { // if spacing is detected skip following code to append space directly

                // Convert value to string
                String value = queue[i].toString();

                // Convert each character manually
                for (char c : value.toCharArray()) {
                    charArray[index++] = c;
                }

                // Append space
                charArray[index++] = ' ';
            }
        }

        // Construct a string from the char array
        return new String(charArray, 0, index);
    }
}