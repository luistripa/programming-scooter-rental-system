/**
 * Handles client interation
 *
 * @author Antonio Duarte
 * @author Luis Tripa
 */
public class ClientIterator {

    // Instance Variables
    private int counter;
    private int next;
    private Client[] clients;

    /**
     * The class constructor. It creates a copy of the client collection vector without sorting it
     *
     * @param counter
     * @param clients
     * PRE: counter >= 0 && clients != null
     */
    public ClientIterator(int counter, Client[] clients) {
        this.counter = counter;
        this.clients = clients;
        next = 0;
    }

    /**
     *
     * @return Boolean representing if there is a client in the next position
     */
    public boolean hasNext() {
        return next < counter;
    }

    /**
     * Gets the client in the next position in the vector
     *
     * @return The client object in the next position
     */
    public Client next() {
        return clients[next++];
    }
}
