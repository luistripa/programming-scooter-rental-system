/**
 * Handles iteration of Scooters
 * 
 * @author Antonio Duarte
 * @author Luis Tripa
 */

public class ScooterIterator {

    private int counter;
    private int next;
    private Scooter[] scooters;

    /**
     * Iterator that organizes the Scooters by order of insertion.
     * 
     * @param counter Number of scooters to iterate.
     * @param scooters Scooter collection to iterate.
     * PRE: counter >= 0 && scooters != null
     */
    public ScooterIterator(int counter, Scooter[] scooters) {
        this.counter = counter;
        this.scooters = scooters;
        next = 0;
    }

    /**
     *
     * @return A boolean representing if there is a client in the next position
     */
    public boolean hasNext() {
        return next < counter;
    }

    /**
     *
     * @return The next client object
     */
    public Scooter next() {
        return scooters[next++];
    }
}
