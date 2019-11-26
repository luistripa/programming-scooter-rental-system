public class ClientCollection {

    private static final int DEFAULT_SIZE = 100;
    private static final int GROWTH_FACTOR = 2;

    Client[] clients;
    private int counter;

    public ClientCollection() {
        counter = 0;
        clients = new Client[DEFAULT_SIZE];
    }

    /**
     * Tries to find the given nif in the Client vector. Returns -1 if not found
     * @param nif The nif to be searched
     * @return The position of the client in the vector
     */
    public int searchIndex(String nif) {
        int pos = -1;
        for (int i = 0; i < counter && pos == -1; i++) {
            if (clients[i].getNif().equalsIgnoreCase(nif))
                pos=i;
        }
        return pos;
	}

    private boolean isFull() {
        return counter == clients.length;
    }

    private void resize() {
        Client[] aux = new Client[clients.length * GROWTH_FACTOR];
        for (int i = 0; i < counter; i++) {
            aux[i] = clients[i];
        }
        clients = aux;
    }

	/**
	 * 
	 * @param nif
	 * @param email
	 * @param phone
	 * @param name
	 */
    public void addClient(String nif, String email, int phone, String name) {
        if (isFull()) resize();
        insertSort(new Client(nif, email, phone, name));
    }

    /**
     * Gets the client nif as it is stored in the system
     * @param nif The client nif
     * @return The client nif as it is stored in the system
     * PRE: searchIndex(nif)!=-1
     */
    public String getClientNif(String nif) {
        return clients[searchIndex(nif)].getNif();
    }

    /**
     * Gets the client email stored in the system
     * @param nif The client nif
     * @return The client email
     * PRE: searchIndex(nif)!=-1
     */
    public String getClientEmail(String nif) {
        return clients[searchIndex(nif)].getEmail();
    }

    /**
     * Gets the client phone stored in the system
     * @param  nif The client nif
     * @return The client phone
     * PRE: searchIndex(nif)!=-1
     */
    public int getClientPhone(String nif) {
        return clients[searchIndex(nif)].getPhone();
    }

    /**
     * Gets the client name stored in the system
     * @param  nif The client nif
     * @return The client name
     * PRE: searchIndex(nif)!=-1
     */
    public String getClientName(String nif) {
        return clients[searchIndex(nif)].getName();
    }

    /**
     * Gets the client balance stored in the system
     * @param  nif Thge client nif
     * @return The client balance
     * PRE: searchIndex(nif)!=-1
     */
    public int getClientBalance(String nif) {
        return clients[searchIndex(nif)].getBalance();
    }

    /**
     * Gets the client total moving minutes
     * @param  nif The client nif
     * @return The client total moving minutes
     * PRE: searchIndex(nif)!=-1
     */
    public int getClientTotalMinutes(String nif) {
        return clients[searchIndex(nif)].getTotalMinutes();
    }

    /**
     * Gets the client total number of rentals
     * @param  nif The client nif
     * @return The client total number of rentals
     * PRE: searchIndex(nif)!=-1
     */
    public int getClientNumberRentals(String nif) {
        return clients[searchIndex(nif)].getNumberRentals();
    }

    /**
     * Gets the client max time in a rental
     * @param  nif The client nif
     * @return The client max time in a rental
     * PRE: searchIndex(nif)!=-1
     */
    public int getClientMaxTime(String nif) {
        return clients[searchIndex(nif)].getMaxTime();
    }

    /**
     * Gets the client average rental minutes
     * @param  nif The client nif
     * @return The client average rental minutes
     * PRE: searchIndex(nif)!=-1
     */
    public int getClientAverageRentalTime(String nif) {
        return clients[searchIndex(nif)].getAverageMinutes();
    }

    /**
     * Gets the client total money spent
     * @param  nif The client nif
     * @return The client total money spent
     * PRE: searchIndex(nif)!=-1
     */
    public int getClientMoneySpent(String nif) {
        return clients[searchIndex(nif)].getMoneySpent();
    }

    /**
     * Get the scooter the client is using
     * @param  nif The client nif
     * @return The scooter object which the client is using
     * PRE: searchIndex(nif)!=-1
     */
    public Scooter getClientScooterInUse(String nif) {
        return clients[searchIndex(nif)].getScooterInUse();
    }

    /**
     * 
     * @param pos
     * @param client
     */
    private void insertAt(int pos, Client client) {
        for (int i = counter - 1; i >= pos; i--)
            clients[i + 1] = clients[i];
        clients[pos] = client;
        counter++;
	}

	/**
	 * Inserting a new client with the lexicographic order
	 * @param client 
	 */
    private void insertSort(Client client) {
        int pos = -1;
        for (int i = 0; i < counter && pos == -1; i++) {
            if (clients[i].nifSmallerThan(client))
                pos = i;
        }
        if (pos == -1)
            pos = counter;
        insertAt(pos,client);
    }
}