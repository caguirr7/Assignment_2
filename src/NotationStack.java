
import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T>{

    private ArrayList<T> stack;
    private final int full;


    /**
     * Default Constructor uses default as the size of the stack
     */
    public NotationStack(){
        full = Integer.MAX_VALUE;
        stack = new ArrayList<>();
    }

    /**
     * Takes an int as teh size of the stack
     * @param size Size of the stack
     */
    public NotationStack(int size){
        full = size;
        stack = new ArrayList<>();
    }



    /**
     * Determines if the stack is empty
     * @return True if stack is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return stack.size() == 0;
    }

    /**
     * Determines if the stack is full
     * @return True if stack is full, false if not
     */
    @Override
    public boolean isFull() {
        return stack.size() == full;
    }

    /**
     * Deletes and returns the element at the top of the stack
     * @return The element at the top of the stack
     * @throws StackUnderflowException Once it reaches past bottom of stack
     */
    @Override
    public T pop() throws StackUnderflowException {
        if(stack.size() == 0)
            throw new StackUnderflowException();
        return stack.remove(0);
    }

    /**
     * Returns the element at the top of the stack
     * Does not pop it off the stack
     * @return The element at the top of the stack
     * @throws StackUnderflowException Once it reaches past bottom of stack
     */

    @Override
    public T top() throws StackUnderflowException {
        if(stack.size() == 0)
            throw new StackUnderflowException();
        return stack.get(0);
    }

    /**
     * Number of elements in the stack
     * @return The number of elements in the stack
     */

    @Override
    public int size() {
        return stack.size();
    }

    /**
     * Adds an element to the top of the stack
     * @param e the element to add to the top of the Stack
     * @return true if the add was successful, false if not
     * @throws StackOverflowException If it goes beyond the stack
     */
    @Override
    public boolean push(T e) throws StackOverflowException {
        if(stack.size() == full)
            throw new StackOverflowException();
        stack.add(0, e);
        return true;
    }

    /**
     * Returns the elements of the Stack in a string
     * from bottom to top, the beginning of the String is
     * the bottom of the stack
     * @return A String which represents the Objects in the Stack
     * from bottom to top
     */

    @Override
    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        for(int i = stack.size()-1; i >= 0; i--)
            toReturn.append(stack.get(i));

        return toReturn.toString();
    }

    /**
     * Returns the string representation of the elements in the Stack.
     * The beginning of the string is the bottom of the stack.
     * Place the delimiter between all elements of the stack
     * @param delimiter Separator delimiter to be placed between elements
     * @return String representation of the stack from
     *               bottom to top with elements separated with
     *                    the delimiter.
     */

    @Override
    public String toString(String delimiter) {
        StringBuilder toReturn = new StringBuilder();
        for(int i = stack.size() -1; i >= 0; i--)
            toReturn.append(stack.get(i)).append(delimiter);
        toReturn = new StringBuilder(toReturn.substring(0, toReturn.length() - 1));
        return toReturn.toString();
    }
    /**
     * Fills the Stack with the elements of the ArrayList,
     * First element in the ArrayList
     * is the first bottom element of the Stack
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS
     * TO THE STACK, if you use the
     * list reference within your Stack, you will be
     * allowing direct access to the data of
     * your Stack causing a possible security breech.
     * @param list elements to be added to the Stack
     *             from bottom to top
     */

    @Override
    public void fill(ArrayList<T> list) {
        stack = new ArrayList<>();

        for(int i = list.size() -1; i >= 0; i--)
            stack.add(list.get(i));

    }

}
