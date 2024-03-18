package Objects;

/**
 *
 * @author Victor Daniel
 */
public class Queue {

    private Fighter head;
    private Fighter tail;
    private int size;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Fighter getHead() {
        return head;
    }

    public void setHead(Fighter head) {
        this.head = head;
    }

    public Fighter getTail() {
        return tail;
    }

    public void setTail(Fighter tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void enqueue(Fighter element) {

        if (isEmpty()) {
            setTail(element);
            setHead(element);
        } else {
            Fighter pointer = getTail();
            pointer.setNext(element);
            setTail(element);
        }
        size++;
    }

    
    public Fighter dequeue() {
        if (isEmpty()) {
            System.out.println("The queue is empty");
        } else {
            Fighter pointer = getHead();
            setHead((Fighter) pointer.getNext());
            pointer.setNext(null);
            if (getHead() == null) {
                setTail(null);
            }
            size--;
            return pointer;
        }
        return null;
    }

    public boolean isEmpty() {
        return getHead() == null && getTail() == null;
    }

    public String printQueue() {
        String queue = "";
        Fighter pointer = getHead();
        while (pointer != null) {

            queue = queue + pointer.getId() + " | ";
            
            pointer = (Fighter) pointer.getNext();
        }

        return "| "+queue;
    }

    public void deleteFromQueue(Fighter elementToRemove) {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return;
        }

  
        if (head.getId() == elementToRemove.getId()) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            System.out.println("Element " + elementToRemove + " removed from the front of the queue.");
            size--;
            return;
        }

        Fighter current = head;
        Fighter previous = null;

        while (current != null && current.getId() != elementToRemove.getId()) {
            previous = current;
            current = current.getNext();
        }

        if (current != null) {
            previous.setNext(current.getNext());
            if (current == tail) {
                tail = previous;
            }
            System.out.println("Element " + elementToRemove.getId() + " removed from the queue.");
            size--;
        } else {
            System.out.println("Element " + elementToRemove.getId() + " not found in the queue.");
        }
    }

}
