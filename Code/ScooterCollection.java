/**
 * Handles everything related with the scooter collection
 * @author Antonio Duarte
 * @author Luis Tripa
 */
public class ScooterCollection {

    // Instance variables
    private Scooter[] scooters;
    private counter;

    /**
     * The class constructor
     */
    public ScooterCollection() {
        counter = 0;
    }

    /**
     * Inserts a scooter into the system
     * @param id The scooter id
     * @param registration The scooter registration
     * PRE: searchIndexOfScooter(id)==-1
     */
    public void createScooter(String id, String registration) {
        if (isFull()) resize();
        scooters[counter] = new Scooter(id, registration);
        counter++;
    }

    /**
     * Search scooter by id. Returns -1 if not found
     * @param  id The scooter id to be searched
     * @return The position of the scooter object in the vector
     */
    public int searchIndexOfScooter(String id) {
        int pos=-1;
        for (int i=0 ; i<counter&&pos==-1 ; i++) {
            if (scooters[i].getScooterID().equalsIgnoreCase(id)) {
                pos = i;
            }
        }
        return pos;
    }

    /**
     * Gets the scooter object
     * @param  id The scooter id
     * @return The scooter object
     */
    public Scooter getScooterObject(String id) {
        return scooters[searchIndexOfScooter(id)];
    }

    /**
     * Checks if scooters vector is full
     * @return A boolean representing if the vector is full
     */
    private boolean isFull() {
        return counter == scooters.length
    }

    /**
     * Resizes the scooters vector in order to add more available spaces
     */
    private void resize() {
        Scooter[] aux = new Scooter[scooters.length*VECTOR_GROWTH_FACTOR];
        for (int i=0 ; i<counter ; i++) {
            aux[i] = scooters[i];
        }
        scooters = aux;
    }
}
