public class ScooterIterator {

    private int counter;
    private int next;
    private Scooter[] scooters;

    public ScooterIterator(int counter, Scooter[] scooters) {
        this.counter = counter;
        this.scooters = scooters;
        next = 0;
    }

    public boolean hasNext() {
        return next < counter;
    }

    public Scooter next() {
        return scooters[next++];
    }
}