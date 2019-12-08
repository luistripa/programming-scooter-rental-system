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
     * Gets the client nif as it is stored in the system
     *
     * @param nif The client nif
     * @return The client nif as it is stored in the system
     * PRE: nif != null && searchIndex(nif)!=-1
     */
    public String getClientNif(String nif) {
        return clients[searchIndex(nif)].getNif();
    }

    /**
     * Gets the client email stored in the system
     *
     * @param nif The client nif
     * @return The client email
     * PRE: nif != null && searchIndex(nif)!=-1
     */
    public String getClientEmail(String nif) {
        return clients[searchIndex(nif)].getEmail();
    }

    /**
     * Gets the client phone stored in the system
     *
     * @param nif The client nif
     * @return The client phone
     * PRE: nif != null && searchIndex(nif)!=-1
     */
    public int getClientPhone(String nif) {
        return clients[searchIndex(nif)].getPhone();
    }

    /**
     * Gets the client name stored in the system
     *
     * @param nif The client nif
     * @return The client name
     * PRE: nif != null && searchIndex(nif)!=-1
     */
    public String getClientName(String nif) {
        return clients[searchIndex(nif)].getName();
    }

    /**
     * Gets the client balance stored in the system
     *
     * @param nif The client nif
     * @return The client balance
     * PRE: nif != null && searchIndex(nif)!=-1
     */
    public int getClientBalance(String nif) {
        return clients[searchIndex(nif)].getBalance();
    }

    /**
     * Gets the client total moving minutes
     *
     * @param nif The client nif
     * @return The client total moving minutes
     * PRE: nif != null && searchIndex(nif)!=-1
     */
    public int getClientTotalMinutes(String nif) {
        return clients[searchIndex(nif)].getTotalMinutes();
    }

    /**
     * Gets the client total number of rentals
     *
     * @param nif The client nif
     * @return The client total number of rentals
     * PRE: nif != null && searchIndex(nif)!=-1
     */
    public int getClientNumberRentals(String nif) {
        return clients[searchIndex(nif)].getNumberRentals();
    }

    /**
     * Gets the client max time in a rental
     *
     * @param nif The client nif
     * @return The client max time in a rental
     * PRE: nif != null && searchIndex(nif)!=-1
     */
    public int getClientMaxTime(String nif) {
        return clients[searchIndex(nif)].getMaxTime();
    }

    /**
     * Gets the client average rental minutes
     *
     * @param nif The client nif
     * @return The client average rental minutes
     * PRE: nif != null && searchIndex(nif)!=-1
     */
    public int getClientAverageRentalTime(String nif) {
        return clients[searchIndex(nif)].getAverageMinutes();
    }

    /**
     * Gets the client total money spent
     *
     * @param nif The client nif
     * @return The client total money spent
     * PRE: nif != null && searchIndex(nif)!=-1
     */
    public int getClientMoneySpent(String nif) {
        return clients[searchIndex(nif)].getMoneySpent();
    }

    /**
     * Get the scooter the client is using
     *
     * @param nif The client nif
     * @return The scooter object which the client is using
     * PRE: nif != null && searchIndex(nif)!=-1
     */
    public Scooter getClientScooterInUse(String nif) {
        return clients[searchIndex(nif)].getScooterInUse();
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
