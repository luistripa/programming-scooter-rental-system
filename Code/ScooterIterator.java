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
     * @return
     */
    public boolean hasNext() {
        return next < counter;
    }

    /**
     * 
     * @return
     */
    public Scooter next() {
        return scooters[next++];
    }
}