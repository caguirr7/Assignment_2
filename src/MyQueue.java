import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>{

    private ArrayList<T> myQueue;
    private final int full;

    /**
     * Takes an integer as the size of the queue
     * @param size size of the queue
     */
    public MyQueue(int size){
        this.full = size;
        myQueue = new ArrayList<>(size);
    }

    /**
     * Default constructor
     * Uses a default as the size of the queue
     */
    public MyQueue(){
        this(5);
        myQueue = new ArrayList<>();
    }

    /**
     * Determines if Queue is empty
     * @return true if Queue is empty, false if not
     */
    @Override
    public boolean isEmpty() {

        return myQueue.size() == 0;
    }

    /**
     * Determines if the queue is full
     * @return True if queue is full, otherwise false
     */

    @Override
    public boolean isFull() {
        return myQueue.size() == full;
    }

    /**
     * Deletes and returns the element at the front of the Queue
     * @return The element at the front of the Queue
     * @throws QueueUnderflowException If it reaches the first element
     */
    @Override
    public T dequeue() throws QueueUnderflowException {
        if(isEmpty())
            throw new QueueUnderflowException();

        T result = myQueue.get(0);
        myQueue.remove(0);
        return result;
    }

    /**
     * Number of elements in the Queue
     * @return The number of elements in the Queue
     */

    @Override
    public int size() {
        return myQueue.size();
    }

    /**
     * Adds an element to the end of the Queue
     * @param e the element to add to the end of the Queue
     * @return True if the add was successful, false if not
     * @throws QueueOverflowException If it goes beyond the queue
     */

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if(isFull())
            throw new QueueOverflowException();

        myQueue.add(e);
        return true;
    }


    /**
     * Returns the string representation of the elements in the Queue,
     * the beginning of the string is the front of the queue
     * Place the delimiter between all elements of the Queue
     * @param delimiter character to be placed in between each element
     * @return String representation of the Queue with elements
     * separated by a delimiter
     */

    @Override
    public String toString(String delimiter) {
        StringBuilder result = new StringBuilder();
        for (T ele:myQueue) {
            if(myQueue.indexOf(ele)==myQueue.size()-1)
                result.append(ele);
            else
                result.append(ele).append(delimiter); // add elements separated by delimiter

        }
        return result.toString();
    }

    /**
     * Returns the string representation of the elements in the Queue,
     * the beginning of the string is the front of the queue.
     * @return String representation of the Queue with elements.
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(T ele:myQueue){
            result.append(ele);

        }
        return result.toString();
    }

    /**
     * Fills the Queue with the elements of the ArrayList,
     * First element in the ArrayList is the first element in the Queue
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE
     * QUEUE.
     * If you use the list reference within your Queue, you will be
     * allowing direct access to the data of your Queue causing a
     * possible security breach.
     * @param list elements to be added to the Queue
     */
    @Override
    public void fill(ArrayList<T> list) {
        myQueue = new ArrayList<>(list);



    }
}
