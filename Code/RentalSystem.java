/**
 * @author Antonio Duarte
 * @author Luis Tripa
 * Handles everything related to the system (rentals, releases, etc...)
 */
public class RentalSystem {

    // Constants
    private final int DEFAULT_VECTOR_SIZE = 100;
    private final int VECTOR_GROWTH_FACTOR = 2;
    private final int MAX_MINUTES = 60;
    private final int COST_PER_RENTAL = 100;
    private final int MINUTES_PER_FINE = 30;

    // Instance Variables
    private Client[] clients;
    private Scooter[] scooters;
    private int scooterCounter;
    private int clientCounter;
    private int totalRentals;
    private int systemBalance;
    private int totalDelayMinutes;

    /**
     * The class constructor
     */
    public RentalSystem() {
        client = new Client[DEFAULT_VECTOR_SIZE];
        scooter = new Scooter[DEFAULT_VECTOR_SIZE];
        totalRentals = 0;
        systemBalance = 0;
        totalDelayMinutes = 0;
    }

    /**
     * Inserts a client into the system
     * @param nif The client nif
     * @param email The client email
     * @param phone The client phone
     * @param name The client name
     * PRE: searchIndexOfClient(nif)==-1
     */
    public void insertClient(String nif, String email, int phone, String name) {
        clients[clientCounter] = new Client(nif, email, phone, name);
        clientCounter++;
    }

    /**
     * Tries to find the given nif in the Client vector. Returns -1 if not found
     * @param nif The nif to be searched
     * @return The position of the client in the vector
     */
    public int searchIndexOfClient(String nif) {
        int pos=-1;
        for (int i=0 ; i<clientCounter&&pos==-1 ; i++) {
            if (clients[i].getNif.equalsIgnoreCase(nif))
                pos=i;
        }
        return pos;
    }

    /**
     * Removes the client with given nif
     * @param nif The client nif
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public void removeClient(String nif) {
        int pos = searchIndexOfClient(nif);
        for (int i=pos ; i<clientCounter-1 ; i++) {
            clients[i] = clients[i+1];
        }
    }

    /**
     * Gets the client nif as it is stored in the system
     * @param nif The client nif
     * @return The client nif as it is stored in the system
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public String getClientNif(String nif) {
        return clients[searchIndexOfClient].getNif();
    }

    /**
     * Gets the client email stored in the system
     * @param nif The client nif
     * @return The client email
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public String getClientEmail(String nif) {
        return clients[searchIndexOfClient(nif)].getEmail();
    }

    /**
     * Gets the client phone stored in the system
     * @param  nif The client nif
     * @return The client phone
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public String getClientPhone(String nif) {
        return clients[searchIndexOfClient(nif)].getPhone();
    }

    /**
     * Gets the client name stored in the system
     * @param  nif The client nif
     * @return The client name
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public String getClientName(String nif) {
        return clients[searchIndexOfClient(nif)].getName();
    }

    /**
     * Gets the client balance stored in the system
     * @param  nif Thge client nif
     * @return The client balance
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public int getClientBalance(int nif) {
        return clients[searchIndexOfClient(nif)].getBalance();
    }

    /**
     * Gets the client total moving minutes
     * @param  nif The client nif
     * @return The client total moving minutes
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public int getClientTotalMinutes(String nif) {
        return clients[searchIndexOfClient(nif)].getTotalMinutes();
    }

    /**
     * Gets the client total number of rentals
     * @param  nif The client nif
     * @return The client total number of rentals
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public int getClientNumberRentals(String nif) {
        return clients[searchIndexOfClient(nif)].getNumberRentals();
    }

    /**
     * Gets the client max time in a rental
     * @param  nif The client nif
     * @return The client max time in a rental
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public int getClientMaxTime(String nif) {
        return clients[searchIndexOfClient(nif)].getMaxTime();
    }

    /**
     * Gets the client average rental minutes
     * @param  nif The client nif
     * @return The client average rental minutes
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public int getClientAverageRentalTime(String nif) {
        return clients[searchIndexOfClient(nif)].getAverageMinutes();
    }

    /**
     * Gets the client total money spent
     * @param  nif The client nif
     * @return The client total money spent
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public int getClientMoneySpent(String nif) {
        return clients[searchIndexOfClient(nif)].getMoneySpent();
    }

    /**
     * Inserts a scooter in the system
     * @param id           [description]
     * @param registration [description]
     * PRE: searchIndexOfScooter(id)==-1
     */
    public void insertScooter(String id, String registration) {
        scooters[scooterCounter] = new Scooter(id, registration);
        scooterCounter++;
    }

    public int searchIndexOfScooter(String id) {
        int pos=-1;
        for (int i=0 ; i<scooterCounter&&pos==-1 ; i++) {
            if (scooters[i].getScooterID().equalsIgnoreCase(id)) {
                pos = i;
            }
        }
        return pos;
    }





    private boolean isFull(Client[] vector, int counter) {
        return counter==vector.length;
    }

    private boolean isFull(Scooter[] vector, int counter) {
        return coutner==vector.length;
    }

    private void resize(Client[] vector) {
        Client[] aux = new Client[vector.length*VECTOR_GROWTH_FACTOR];
        for (int i=0 ; i<counter ; i++) {
            aux[i] = vector[i];
        }
        vector = aux;
    }

    private void resize(Scooter[] vector) {
        Scooter[] aux = new Scooter[vector.length*VECTOR_GROWTH_FACTOR];
        for (int i=0 ; i<counter ; i++) {
            aux[i] = vector[i];
        }
        vector = aux;
    }
}
