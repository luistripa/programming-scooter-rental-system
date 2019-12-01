/**
 * Handles everything related to the iteration of scooters closest to a given
 * client location.
 * 
 * @author Antonio Duarte
 * @author Luis Tripa
 */

public class ScooterIteratorCloser {

    private int counter;
    private int next;
    private Scooter[] scooters;
    private double latitude, longitude;

    /**
     * Iterator that organizes the Scooter objects by distance to a given latitude and longitude.
     * 
     * @param counter Number of Scooters to iterate
     * @param scooters Scooter collection to iterate
     */
    public ScooterIteratorCloser(int counter, Scooter[] scooters, double latitude, double longitude) {
        this.scooters = new Scooter[counter];
        this.counter = 0;
        this.latitude = latitude;
        this.longitude = longitude;
        next = 0;
        for (int i = 0; i < counter; i++) {
            if (scooters[i].getLatitude() != 0 && scooters[i].getLongitude() != 0)
                insertSort(scooters[i]);
        }
    }

    /**
     *
     * @return A boolean representing if there is a scooter in the next position.
     */
    public boolean hasNext() {
        return next < counter;
    }

    /**
     *
     * @return The next scooter object.
     */
    public Scooter next() {
        return scooters[next++];
    }

    /**
     * Inserts a scooter in the iterator sorting it by distance to a given latitude and longitude.
     * 
     * @param scooter Scooter to insert.
     */
    private void insertSort(Scooter scooter) {
        int pos = -1;
        for (int i = 0; i < counter && pos == -1; i++) {
            if (scooters[i].distanceGreaterThan(scooter, latitude, longitude))
                pos = i;
        }
        if (pos == -1)
            pos = counter;
        insertAt(pos, scooter);
    }

    /**
     * Inserts a Scooter in the given position, opening a space in the array by pushing all the other Scooters
     * to the right.
     * 
     * @param pos Position to insert the Scooter at.
     * @param scooter Scooter to be inserted at the given position.
     */
    private void insertAt(int pos, Scooter scooter) {
        for (int i = counter - 1; i >= pos; i--)
            scooters[i + 1] = scooters[i];
        scooters[pos] = scooter;
        counter++;
    }
}
