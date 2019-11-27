public class Scooter {

    private static final String MOVING = "alugada";
    private static final String STOPPED = "parada";
    private static final String DEACTIVATED = "inactiva";

    private String scooterID, registration, state;
    private int totalRentals, usageMinutes, usageAmount;
    private Client clientInUse;
    private double latitude, longitude;

    public Scooter(String scooterID, String registration) {
        this.scooterID = scooterID;
        this.registration = registration;
        state = STOPPED;
        clientInUse = null;
        usageMinutes = 0;
        usageAmount = 0;
        latitude = 0;
        longitude = 0;
    }

    /**
     * @return the clientInUse
     */
    public Client getClientInUse() {
        return clientInUse;
    }

    /**
     * Rents the scooter
     * 
     * @param nif The nif of the client renting the scooter
     */
    public void rent(Client client) {
        setState(MOVING);
        setClientInUse(client);
    }

    /**
     *
     * @param minutes Amount of minutes that the scooter was rented for.
     * @pre minutes >= 0 && isMoving == true
     */
    public void release(int minutes) {
        incrementUsageAmount();
        addTotalMinutes(minutes);
        setState(STOPPED);
        setClientInUse(null);
    }

    public void release(int minutes, double latitude, double longitude) {
        incrementUsageAmount();
        addTotalMinutes(minutes);
        setState(STOPPED);
        setClientInUse(null);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns scooter's current state
     *
     * @return String with the state of the scooter
     */
    public String getState() {
        return state;
    }

    /**
     * Set which client is using the scooter
     *
     * @param scooterClientInUse String which contains the name of the client riding
     *                           the scooter
     * 
     *                           PRE: getClientInUse() == null
     */
    public void setClientInUse(Client clientInUse) {
        this.clientInUse = clientInUse;
    }

    /**
     * Activates or deactivates the scooter
     *
     * @param status String. The status of the scooter (alugada, inactiva, parada)
     *
     *               PRE: state.equals(STOPPED) || state.equals(MOVING) ||
     *               state.equals(DEACTIVATED)
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return Registration of the scooter.
     */
    public String getScooterRegistration() {
        return registration;
    }

    /**
     *
     * @return Id of the scooter.
     */
    public String getScooterID() {
        return scooterID;
    }

    /**
     *
     * @return Amount of times that the scooter was used.
     */
    public int getUsageAmount() {
        return usageAmount;
    }

    /**
     *
     * @return Scooter latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     *
     * @return Scooter longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Increments the amount of times that the scooter was used.
     */
    public void incrementUsageAmount() {
        usageAmount++;
    }

    /**
     * Decrements the amount of times that the scooter was used.
     */
    public void decrementUsageAmount() {
        usageAmount--;
    }

    /**
     *
     * @param minutes Minutes to be added to the Scooter's total usage minutes. PRE:
     *                minutes > 0
     */
    public void addTotalMinutes(int minutes) {
        usageMinutes += minutes;
    }

    /**
     *
     * @return Amount of minutes that the scooter was used for.
     */
    public int getUsageMinutes() {
        return usageMinutes;
    }

    /**
     * Calculates distance between two points (The client and this scooter)
     * 
     * @param lat1  The client latitude
     * @param long1 the client longitude
     * @return The distance between client and scooter
     */
    public double calculateDistance(double lat1, double long1) {
        double lat2 = getLatitude();
        double long2 = getLongitude();
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(long2 - long1, 2));
    }

    public boolean distanceGreaterThan(Scooter other, double latitude, double longitude) {
        return this.calculateDistance(latitude, longitude) > other.calculateDistance(latitude, longitude);
    }
}
