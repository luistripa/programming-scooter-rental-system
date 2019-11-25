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
    private Client[] client;
    private Scooter[] scooter;
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
     * PRE: !clientExists(nif)
     */
    public void insertClient(String nif, String email, int phone, String name) {
        client[clientCounter] = new Client(nif, email, phone, name);
        clientCounter++;
    }

    /**
     * Tries to find the given nif in the Client vector
     * @param nif The nif to be searched
     * @return A boolean representing if the nif was or not found
     */
    public boolean clientExists(String nif) {
        for (int i=0 ; i<clientCounter ; i++) {
            if (Client[i].getNif.equals(nif))
                return true;
        }
    }

    public void insertScooter()





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
