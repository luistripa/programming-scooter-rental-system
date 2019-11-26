public class ScooterIterator {

    private int counter;
    private int next;
    private Scooter[] scooters;

    /**
     *
     * @param counter
     * @param scooters
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
