/**
 * Handles everything related to managing the array of Clients
 *
 * @author Antonio Duarte
 * @author Luis Tripa
 */

public class ClientCollection {

    // Constants
    private static final int DEFAULT_SIZE = 100;
    private static final int GROWTH_FACTOR = 2;

    // Instance Variables
    Client[] clients;
    private int counter;

    /**
     * The class constructor
     */
    public ClientCollection() {
        counter = 0;
        clients = new Client[DEFAULT_SIZE];
    }

    /**
     * Tries to find the given nif in the Client vector. Returns -1 if not found
     *
     * @param nif The nif to be searched
     * @return The position of the client in the vector
     * PRE nif != null
     */
    public int searchIndex(String nif) {
        int pos = -1;
        for (int i = 0; i < counter && pos == -1; i++) {
            if (clients[i].getNif().equalsIgnoreCase(nif))
                pos = i;
        }
        return pos;
    }

    /**
     * Checks whether the client vector is full
     * @return A boolean representing whether the vector is or not full
     */
    private boolean isFull() {
        return counter == clients.length;
    }

    /**
     * Resizes the client vector by copying the previous vector to a new one with double the memory space
     */
    private void resize() {
        Client[] aux = new Client[clients.length * GROWTH_FACTOR];
        for (int i = 0; i < counter; i++)
            aux[i] = clients[i];
        clients = aux;
    }

    /**
     * Adds a new client to the client vector. The vector is resized if there is no space left
     *
     * @param nif   The new client nif
     * @param email The new client email
     * @param phone The new client phone
     * @param name  The new client name
     * PRE: nif != null && email != null && phone != null && name != null
     */
    public void addClient(String nif, String email, int phone, String name) {
        if (isFull())
            resize();
        insertSort(new Client(nif, email, phone, name));
    }

    /**
     * Removes a client from the vector.
     * Firstly, searches the position of that client in the vector and then moves every object to its right, one step to the left
     *
     * @param nif The client nif
     * PRE: nif != null && searchIndex(nif) != -1
     */
    public void remClient(String nif) {
        int pos = searchIndex(nif);
        for (int i = pos; i < counter - 1; i++)
            clients[i] = clients[i + 1];
        counter--;
    }

    /**
     * Gets the client object from the client collection
     *
     * @param nif The client nif
     * @return The client object
     * PRE: nif != null && searchIndex(nif) != -1
     */
    public Client getClientObject(String nif) {
        return clients[searchIndex(nif)];
    }

    /**
     * Inserting a new client with the lexicographic order
     *
     * @param client
     * PRE: client != null
     */
    private void insertSort(Client client) {
        int pos = -1;
        for (int i = 0; i < counter && pos == -1; i++) {
            if (clients[i].nifGreaterThan(client))
                pos = i;
        }
        if (pos == -1)
            pos = counter;
        insertAt(pos, client);
    }

    /**
     * Organizes the vector by moving every vector object to the right of pos one step to the right and adding the given object to pos
     *
     * @param pos       The position the client object will be added to
     * @param client    The client obejct
     * PRE: pos >= 0 && pos <= clients.length && client != null
     */
    private void insertAt(int pos, Client client) {
        for (int i = counter - 1; i >= pos; i--)
            clients[i + 1] = clients[i];
        clients[pos] = client;
        counter++;
    }

    /**
     * Client iterator that iterates in ascending lexicographic order of nif
     *
     * @return Client Iterator
     */
    public ClientIterator initializeIterator() {
        return new ClientIterator(counter, clients);
    }

    /**
     * Initializes the debtors client iterator, that iterates the client with
     * negative balance in ascending order of balance.
     *
     * @return Client Iterator of debtors
     */
    public ClientIteratorDebtors initializeIteratorDebtors() {
        return new ClientIteratorDebtors(counter, clients);
    }
}
