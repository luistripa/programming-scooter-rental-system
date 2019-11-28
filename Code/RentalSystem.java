/**
 * Handles everything related to the system (rentals, releases, etc...)
 *
 * @author Antonio Duarte
 * @author Luis Tripa
 */
public class RentalSystem {

    // Constants
    private final int MAX_MINUTES = 60;
    private final int COST_PER_RENTAL = 100;
    private final int MINUTES_PER_FINE = 30;
    private static final String MOVING = "alugada";
    private static final String STOPPED = "parada";
    private static final String DEACTIVATED = "inactiva";
    private static final double NORTH_BOUNDARY = 38.663964;
    private static final double SOUTH_BOUNDARY = 38.658475;
    private static final double WEST_BOUNDARY = -9.209269;
    private static final double EAST_BOUNDARY = -9.201978;

    // Instance Variables
    private ClientCollection clients;
    private ScooterCollection scooters;
    private int totalRentals, systemBalance, totalDelayMinutes;

    /**
     * The class constructor
     */
    public RentalSystem() {
        clients = new ClientCollection();
        scooters = new ScooterCollection();
        totalRentals = 0;
        systemBalance = 0;
        totalDelayMinutes = 0;
    }

    /**
     * Inserts a client into the system
     *
     * @param nif   The client nif
     * @param email The client email
     * @param phone The client phone
     * @param name  The client name
     * PRE: clients.searchIndex(nif)==-1
     */
    public void createClient(String nif, String email, int phone, String name) {
        clients.addClient(nif, email, phone, name);
    }

    /**
     * Tries to find the given nif in the Client vector. Returns -1 if not found
     *
     * @param nif The nif to be searched
     * @return The position of the client in the vector
     */
    public boolean clientExists(String nif) {
        return clients.searchIndex(nif) != -1;
    }

    private Client getClientObject(String nif) {
        return clients.getClientObject(nif);
    }

    /**
     * Check whether client has rented a scooter
     *
     * @param nif The client nif
     * @return A boolean stating whether client has or has not rented a scooter
     */
    public boolean hasClientRented(String nif) {
        return getClientObject(nif).hasRented();
    }

    /**
     * Removes the client with given nif
     *
     * @param nif The client nif
     * PRE: clientExists(nif)
     */
    public void removeClient(String nif) {
        clients.remClient(nif);
    }

    /**
     * Gets the client nif as it is stored in the system
     *
     * @param nif The client nif
     * @return The client nif as it is stored in the system
     * PRE: clientExists(nif)
     */
    public String getClientNif(String nif) {
        return getClientObject(nif).getNif();
    }

    /**
     * Gets the client email stored in the system
     *
     * @param nif The client nif
     * @return The client email
     * PRE: clientExists(nif)
     */
    public String getClientEmail(String nif) {
        return getClientObject(nif).getEmail();
    }

    /**
     * Gets the client phone stored in the system
     *
     * @param nif The client nif
     * @return The client phone
     * PRE: clientExists(nif)
     */
    public int getClientPhone(String nif) {
        return getClientObject(nif).getPhone();
    }

    /**
     * Gets the client name stored in the system
     *
     * @param nif The client nif
     * @return The client name
     * PRE: clientExists(nif)
     */
    public String getClientName(String nif) {
        return getClientObject(nif).getName();
    }

    /**
     * Gets the client balance stored in the system
     *
     * @param nif Thge client nif
     * @return The client balance
     * PRE: clientExists(nif)
     */
    public int getClientBalance(String nif) {
        return getClientObject(nif).getBalance();
    }

    /**
     * Gets the client total moving minutes
     *
     * @param nif The client nif
     * @return The client total moving minutes
     * PRE: clientExists(nif)
     */
    public int getClientTotalMinutes(String nif) {
        return getClientObject(nif).getTotalMinutes();
    }

    /**
     * Gets the client total number of rentals
     *
     * @param nif The client nif
     * @return The client total number of rentals
     * PRE: clientExists(nif)
     */
    public int getClientNumberRentals(String nif) {
        return getClientObject(nif).getNumberRentals();
    }

    /**
     * Gets the client max time in a rental
     *
     * @param nif The client nif
     * @return The client max time in a rental
     * PRE: clientExists(nif)
     */
    public int getClientMaxTime(String nif) {
        return getClientObject(nif).getMaxTime();
    }

    /**
     * Gets the client average rental minutes
     *
     * @param nif The client nif
     * @return The client average rental minutes
     * PRE: clientExists(nif)
     */
    public int getClientAverageRentalTime(String nif) {
        return getClientObject(nif).getAverageMinutes();
    }

    /**
     * Gets the client total money spent
     *
     * @param nif The client nif
     * @return The client total money spent
     * PRE: clientExists(nif)
     */
    public int getClientMoneySpent(String nif) {
        return getClientObject(nif).getMoneySpent();
    }

    /**
     * Gets the scooter the client is using
     *
     * @param nif The client nif
     * @return The scooter object which the client is using
     * PRE: clientExists(nif)
     */
    public Scooter getClientScooterInUse(String nif) {
        return getClientObject(nif).getScooterInUse();
    }

    /**
     * Inserts a scooter into the system
     *
     * @param id           The scooter id
     * @param registration The scooter registration
     * PRE: searchIndexOfScooter(id)==-1
     */
    public void createScooter(String id, String registration) {
        scooters.addScooter(id, registration);
    }

    /**
     * Check if the scooter with the specified id exists
     *
     * @param id Scooter id
     * @return Boolean representing if scooter exists
     */
    public boolean scooterExists(String id) {
        return scooters.searchIndex(id) != -1;
    }

    /**
     * Gets the scooter object from the collection
     *
     * @param id The scooter id
     * @return The scooter object PRE:
     * scooterExists(id)
     */
    private Scooter getScooterObject(String id) {
        return scooters.getScooterObject(id);
    }

    /**
     * Get the scooter id stored in the system
     *
     * @param id The scooter id
     * @return The scooter id as it is stored in the system
     * PRE: scooterExists(id)
     */
    public String getScooterID(String id) {
        return getScooterObject(id).getScooterID();
    }

    /**
     * Gets the scooter registration
     *
     * @param id The scooter id
     * @return The scooter registration number
     * PRE: scooterExists(id)
     */
    public String getScooterRegistration(String id) {
        return getScooterObject(id).getScooterRegistration();
    }

    /**
     * Gets the scooter state
     *
     * @param id The scooter id
     * @return The scooter state
     * PRE: scooterExists(id)
     */
    public String getScooterState(String id) {
        return getScooterObject(id).getState();
    }

    /**
     * Gets the client that is using the scooter
     *
     * @param id The scooter id
     * @return The object of the client that is using the scooter
     * PRE: scooterExists(id)
     */
    public Client getScooterClientInUse(String id) {
        return getScooterObject(id).getClientInUse();
    }

    /**
     * Gets the scooter total amount of rentals
     *
     * @param id The scooter id
     * @return The scooter total amount of rentals
     * PRE: scooterExists(id)
     */
    public int getScooterTotalRentals(String id) {
        return getScooterObject(id).getUsageAmount();
    }

    /**
     * Gets the total amount of minutes scooter was used for
     *
     * @param id The scooter id
     * @return The total amount of minutes scooter was used for
     * PRE: scooterExists(id)
     */
    public int getScooterUsageMinutes(String id) {
        return getScooterObject(id).getUsageMinutes();
    }

    /**
     * Gets the amount of time the scooter has been used
     *
     * @param id The scooter id
     * @return The amount of times the scooter has been used
     * PRE: scooterExists(id)
     */
    public int getScooterUsageAmount(String id) {
        return getScooterObject(id).getUsageAmount();
    }

    /**
     * Checks if scooter is moving
     *
     * @param id The scooter id
     * @return A boolean stating whether the scooter is or not moving
     * PRE:scooterExists(id)
     */
    public boolean isScooterMoving(String id) {
        return getScooterObject(id).getState() == MOVING;
    }

    /**
     * Checks if scooter is active
     *
     * @param id The scooter id
     * @return Boolean stating whether the scooter is or not active
     * PRE: scooterExists(id)
     */
    public boolean isScooterActivated(String id) {
        return getScooterObject(id).getState() != DEACTIVATED;
    }

    /**
     * Deactivates a scooter
     *
     * @param id The scooter id
     * PRE: scooterExists(id)
     */
    public void deactivateScooter(String id) {
        getScooterObject(id).setState(DEACTIVATED);
    }

    /**
     * Reactivates a scooter
     *
     * @param id The scooter id
     * PRE: scooterExists(id) &&
     *      scooters[searchIndexOfScooter(id)].getstate()!="parada" ||
     *      scooters[searchIndexOfScooter(id)].getstate()!="alugada"
     */
    public void reactivateScooter(String id) {
        getScooterObject(id).setState(STOPPED);
    }

    /**
     * Adds a specific amount to a client balance
     *
     * @param nif    The nif of the client
     * @param amount The amount to be added
     * PRE: clientExists(nif) && amount > 0
     */
    public void addBalance(String nif, int amount) {
        getClientObject(nif).addBalance(amount);
    }

    /**
     * Rents the scooter with given id
     *
     * @param nif The client renting the scooter nif
     * @param id  The scooter id
     * PRE: clientExists(nif) && scooterExists(id)
     */
    public void rentScooter(String nif, String id) {

        Client client = getClientObject(nif);
        Scooter scooter = getScooterObject(id);

        client.setScooterInUse(scooter);
        scooter.rent(client);
    }

    /**
     * Releases the scooter from the client
     *
     * Calculates the amount of delay since the max rental time and cimputes the
     * total expense (fines included)
     *
     * @param id      The scooteid of the scooter being used
     * @param minutes The minutes client used the scooter for
     */
    public void releaseScooter(String id, int minutes) {

        Client client = getScooterClientInUse(id);
        Scooter scooter = getScooterObject(id);

        int expense = 0;
        int delay = 0;

        expense += COST_PER_RENTAL;
        delay = minutes - MAX_MINUTES;
        if (delay > 0) {
            expense += (delay / MINUTES_PER_FINE) * COST_PER_RENTAL;
            if (delay % MINUTES_PER_FINE > 0)
                expense += COST_PER_RENTAL;
        } else
            delay = 0;
        client.releaseScooter(minutes, expense);
        scooter.release(minutes);
        incTotalRentals();
        addSystemBalance(expense);
        addTotalDelayMinutes(delay);

    }

    /**
     * Same as release scooter but with latitude and longitude options
     *
     * @param id        The scooter ID
     * @param minutes   The minutes client used the scooter for
     * @param latitude  The scooter latitude
     * @param longitude The scooter longitude
     */
    public void releaseScooter(String id, int minutes, double latitude, double longitude) {

        Client client = getScooterClientInUse(id);
        Scooter scooter = getScooterObject(id);

        int expense = 0;
        int delay = 0;

        expense += COST_PER_RENTAL;
        delay = minutes - MAX_MINUTES;
        if (delay > 0) {
            expense += (delay / MINUTES_PER_FINE) * COST_PER_RENTAL;
            if (delay % MINUTES_PER_FINE > 0)
                expense += COST_PER_RENTAL;
        } else
            delay = 0;

        client.releaseScooter(minutes, expense);
        scooter.release(minutes, latitude, longitude);
        incTotalRentals();
        addSystemBalance(expense);
        addTotalDelayMinutes(delay);
    }

    /**
     * Lists all scooters in the system
     *
     * @return A string with all scooters in the system
     */
    public ScooterIterator listScooter() {
        ScooterIterator iterator = scooters.initializeScooterIterator();
        return iterator;
    }

    /**
     * Lists all clients in the system organized by their nif
     *
     * @return A string with all clients
     */
    public ClientIterator listClient() {
        ClientIterator iterator = clients.initializeIterator();
        return iterator;
    }

    /**
     * Lists all clients that have a negative balance. Ascending order of balance
     *
     * @return A String with all clients
     */
    public ClientIteratorDebtors listDebtors() {
        return clients.initializeIteratorDebtors();
    }

    public boolean isScooterInBoundaries(double latitude, double longitude) {
        return latitude <= NORTH_BOUNDARY && latitude >= SOUTH_BOUNDARY && longitude >= WEST_BOUNDARY
                && longitude <= EAST_BOUNDARY;
    }

    public ScooterIteratorCloser listCloserScooters(double latitude, double longitude) {
        return scooters.initializeIteratorCloser(latitude, longitude);
    }

    /**
     * Increments the system's total rentals
     */
    public void incTotalRentals() {
        totalRentals++;
    }

    /**
     * Adds balance to the total money spent by clients on the system
     *
     * @param amount Amount to add to the System Balance
     */
    public void addSystemBalance(int amount) {
        systemBalance += amount;
    }

    /**
     * Adds minutes to the total delay minutes of the system
     *
     * @param minutes Minutes to add to the total delay minutes of the system
     */
    public void addTotalDelayMinutes(int minutes) {
        totalDelayMinutes += minutes;
    }

    /**
     *
     * @return Total Rentals performed by the system
     */
    public int getTotalRentals() {
        return totalRentals;
    }

    /**
     *
     * @return Total Money spent in the System
     */
    public int getSystemBalance() {
        return systemBalance;
    }

    /**
     *
     * @return Total delay minutes performed in the system
     */
    public int getTotalDelayMinutes() {
        return totalDelayMinutes;
    }

}
