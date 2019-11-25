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
    private static final String MOVING = "alugada";
    private static final String STOPPED = "parada";
    private static final String DEACTIVATED = "inactiva";

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
        clients = new Client[DEFAULT_VECTOR_SIZE];
        scooters = new Scooter[DEFAULT_VECTOR_SIZE];
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
    public void createClient(String nif, String email, int phone, String name) {
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
            if (clients[i].getNif().equalsIgnoreCase(nif))
                pos=i;
        }
        return pos;
    }

    /**
     * Check whether client has rented a scooter
     * @param  nif The client nif
     * @return A boolean stating whether client has or has not rented a scooter
     */
    public boolean hasClientRented(String nif) {
        return clients[searchIndexOfClient(nif)].hasRented();
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
        return clients[searchIndexOfClient(nif)].getNif();
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
    public int getClientPhone(String nif) {
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
    public int getClientBalance(String nif) {
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
     * Get the scooter the client is using
     * @param  nif The client nif
     * @return The scooter object which the client is using
     * PRE: searchIndexOfClient(nif)!=-1
     */
    public Scooter getClientScooterInUse(String nif) {
        return clients[searchIndexOfClient(nif)].getScooterInUse();
    }

    /**
     * Inserts a scooter into the system
     * @param id The scooter id
     * @param registration The scooter registration
     * PRE: searchIndexOfScooter(id)==-1
     */
    public void createScooter(String id, String registration) {
        scooters[scooterCounter] = new Scooter(id, registration);
        scooterCounter++;
    }

    /**
     * Search scooter by id. Returns -1 if not found
     * @param  id The scooter id to be searched
     * @return The position of the scooter object in the vector
     */
    public int searchIndexOfScooter(String id) {
        int pos=-1;
        for (int i=0 ; i<scooterCounter&&pos==-1 ; i++) {
            if (scooters[i].getScooterID().equalsIgnoreCase(id)) {
                pos = i;
            }
        }
        return pos;
    }

    /**
     * Get the scooter id stored in the system
     * @param  id The scooter id
     * @return The scooter id as it is stored in the system
     * PRE: searchIndexOfScooter(id)!=-1
     */
    public String getScooterID(String id) {
        return scooters[searchIndexOfScooter(id)].getScooterID();
    }

    /**
     * Gets the scooter registration
     * @param  id The scooter id
     * @return The scooter registration number
     * PRE: searchIndexOfScooter(id)!=-1
     */
    public String getScooterRegistration(String id) {
        return scooters[searchIndexOfScooter(id)].getScooterRegistration();
    }
    /**
     * Gets the scooter state
     * @param  id The scooter id
     * @return The scooter state
     * PRE: searchIndexOfScooter(id)!=-1
     */
    public String getScooterState(String id) {
        return scooters[searchIndexOfScooter(id)].getState();
    }

    /**
     * Gets the client that is using the scooter
     * @param  id The scooter id
     * @return The object of the client that is using the scooter
     * PRE: searchIndexOfScooter(id)!=-1
     */
    public Client getScooterClientInUse(String id) {
        return scooters[searchIndexOfScooter(id)].getClientInUse();
    }

    /**
     * Gets the scooter total amount of rentals
     * @param  id The scooter id
     * @return The scooter total amount of rentals
     * PRE: searchIndexOfScooter(id)!=-1
     */
    public int getScooterTotalRentals(String id) {
        return scooters[searchIndexOfScooter(id)].getTotalRentals();
    }

    /**
     * Gets the total amount of minutes scooter was used for
     * @param  id The scooter id
     * @return The total amount of minutes scooter was used for
     * PRE: searchIndexOfScooter(id)!=-1
     */
    public int getScooterUsageMinutes(String id) {
        return scooters[searchIndexOfScooter(id)].getUsageMinutes();
    }

    /**
     * Gets the amount of time the scooter has been used
     * @param  id The scooter id
     * @return The amount of times the scooter has been used
     * PRE: searchIndexOfScooter(id)!=-1
     */
    public int getScooterUsageAmount(String id) {
        return scooters[searchIndexOfScooter(id)].getUsageAmount();
    }

    /**
     * Checks if scooter is moving
     * @param  id The scooter id
     * @return A boolean stating whether the scooter is or not moving
     */
    public boolean isScooterMoving(String id) {
        return scooters[searchIndexOfScooter(id)].getState() == MOVING;
    }

    /**
     * Checks if scooter is active
     * @param  id The scooter id
     * @return Boolean stating whether the scooter is or not active
     */
    public boolean isScooterActivated(String id) {
        return scooters[searchIndexOfScooter(id)].getState() != DEACTIVATED;
    }

    /**
     * Deactivates a scooter
     * @param id The scooter id
     * PRE: searchIndexOfScooter(id)!=-1
     */
    public void deactivateScooter(String id) {
        scooters[searchIndexOfScooter(id)].setState(DEACTIVATED);
    }

    /**
     * Reactivates a scooter
     * @param id The scooter id
     * PRE: searchIndexOfScooter(id)!=-1 && scooters[searchIndexOfScooter(id)].getstate()!="parada" || scooters[searchIndexOfScooter(id)].getstate()!="alugada"
     */
    public void reactivateScooter(String id) {
        scooters[searchIndexOfScooter(id)].setState(STOPPED);
    }

    /**
     * Adds a specific amount to a client balance
     * @param nif The nif of the client
     * @param amount The amount to be added
     */
    public void addBalance(String nif, int amount) {
        clients[searchIndexOfClient(nif)].addBalance(amount);
    }

    /**
     * Rents the scooter with given id
     * @param nif The client renting the scooter nif
     * @param id  The scooter id
     */
    public void rentScooter(String nif, String id) {

        Client client = clients[searchIndexOfClient(nif)];
        Scooter scooter = scooters[searchIndexOfScooter(id)];

        client.setScooterInUse(scooter);
        scooter.rent(client);
    }

    /**
     * Releases the scooter from the client
     *
     * Calculates the amount of delay since the max rental time and cimputes the total expense (fines included)
     *
     * @param nif     The client nif using the scooter
     * @param id      The scooteid of the scooter being used
     * @param minutes The minutes client used the scooter for
     */
    public void releaseScooter(String nif, String id, int minutes) {

        Client client = clients[searchIndexOfClient(nif)];
        Scooter scooter = scooters[searchIndexOfScooter(id)];

        int expense = 0;
        int delay = 0;

        expense += COST_PER_RENTAL;
        delay = minutes-MAX_MINUTES;
        if (delay > 0) {
            expense += (delay/MINUTES_PER_FINE)*COST_PER_RENTAL;
            if (delay%MINUTES_PER_FINE > 0)
                expense += COST_PER_RENTAL;
        } else {
            delay = 0;
        }
        client.releaseScooter(minutes, expense);
        scooter.release(minutes);
        incTotalRentals();
        addSystemBalance(expense);
        addTotalDelayMinutes(delay);

    }

    public void incTotalRentals() {
        totalRentals++;
    }

    public void addSystemBalance(int amount) {
        systemBalance += amount;
    }

    public void addTotalDelayMinutes(int minutes) {
        totalDelayMinutes += minutes;
    }

    public int getTotalRentals() {
        return totalRentals;
    }

    public int getSystemBalance() {
        return systemBalance;
    }

    public int getTotalDelayMinutes() {
        return totalDelayMinutes;
    }




    private boolean isFull(Client[] vector, int counter) {
        return counter==vector.length;
    }

    private boolean isFull(Scooter[] vector, int counter) {
        return counter==vector.length;
    }

    private void resize(Client[] vector) {
        Client[] aux = new Client[vector.length*VECTOR_GROWTH_FACTOR];
        for (int i=0 ; i<clientCounter ; i++) {
            aux[i] = vector[i];
        }
        vector = aux;
    }

    private void resize(Scooter[] vector) {
        Scooter[] aux = new Scooter[vector.length*VECTOR_GROWTH_FACTOR];
        for (int i=0 ; i<scooterCounter ; i++) {
            aux[i] = vector[i];
        }
        vector = aux;
    }
}
