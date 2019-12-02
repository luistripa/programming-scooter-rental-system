/**
 * Handles everything related with the scooter collection
 *
 * @author Antonio Duarte
 * @author Luis Tripa
 */
public class ScooterCollection {

    // Constants
    private static final int DEFAULT_SIZE = 100;
    private static final int GROWTH_FACTOR = 2;

    // Instance variables
    private Scooter[] scooters;
    private int counter;

    /**
     * The class constructor
     */
    public ScooterCollection() {
        counter = 0;
        scooters = new Scooter[DEFAULT_SIZE];
    }

    /**
     * Inserts a scooter into the system
     *
     * @param id           The scooter id
     * @param registration The scooter registration
     * PRE: searchIndexOfScooter(id) == -1
     */
    public void addScooter(String id, String registration) {
        if (isFull())
            resize();
        scooters[counter] = new Scooter(id, registration);
        counter++;
    }

    /**
     * Search scooter by id. Returns -1 if not found
     *
     * @param id The scooter id to be searched
     * @return The position of the scooter object in the vector
     * PRE: id != null
     */
    public int searchIndex(String id) {
        int pos = -1;
        for (int i = 0; i < counter && pos == -1; i++) {
            if (scooters[i].getScooterID().equalsIgnoreCase(id))
                pos = i;
        }
        return pos;
    }

    /**
     * Gets the scooter object
     *
     * @param id The scooter id
     * @return The scooter object
     * PRE: id != null && searchIndex(id) != -1
     */
    public Scooter getScooterObject(String id) {
        return scooters[searchIndex(id)];
    }

    /**
     * Checks if scooters vector is full
     *
     * @return A boolean representing if the vector is full
     */
    private boolean isFull() {
        return counter == scooters.length;
    }

    /**
     * Resizes the scooters vector in order to add more available spaces
     */
    private void resize() {
        Scooter[] aux = new Scooter[scooters.length * GROWTH_FACTOR];
        for (int i = 0; i < counter; i++)
            aux[i] = scooters[i];
        scooters = aux;
    }

    /**
     * Initializes the scooter iterator. This iterator is based on the inserted
     * order
     *
     * @return The iterator object
     */
    public ScooterIterator initializeScooterIterator() {
        return new ScooterIterator(counter, scooters);
    }

    public ScooterIteratorCloser initializeIteratorCloser(double latitude, double longitude) {
        return new ScooterIteratorCloser(counter, scooters, latitude, longitude);
    }
}
