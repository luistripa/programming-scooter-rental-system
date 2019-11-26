public class ScooterIteratorCloser {

    private int counter;
    private int next;
    private Scooter[] scooters;
    private double latitude, longitude;

    /**
     *
     * @param counter
     * @param scooters
     */
    public ScooterIteratorCloser(int counter, Scooter[] scooters, double latitude, double longitude) {
        this.scooters = new Scooter[counter];
        this.counter = 0;
        this.latitude = latitude;
        this.longitude = longitude;
        next = 0;
        for (int i=0 ; i < counter ; i++) {
            insertSort(scooters[i]);
        }
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

    private void insertSort(Scooter scooter) {
        int pos = -1;
        for (int i = 0; i < counter && pos == -1; i++) {
            if (scooters[i].distanceGreaterThan(scooter, latitude, longitude))
                pos = i;
        }
        if (pos == -1)
            pos = counter;
        insertAt(pos,scooter);
    }

    private void insertAt(int pos, Scooter scooter) {
        for (int i = counter - 1; i >= pos; i--)
            scooters[i + 1] = scooters[i];
        scooters[pos] = scooter;
        counter++;
    }
}
