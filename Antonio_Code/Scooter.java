/**
 * @author Antonio Duarte
 * Handles and makes operations upon information of Scooter objects.
 **/

public class Scooter {

    // Variables.

    private String scooterID, scooterRegistration, clientWhoRentedNIF, clientWhoRentedName;
    private boolean moving, activated;
    private int usageAmount, usageMinutes;

    // Constructor.

    public Scooter(String scooterID, String scooterRegistration) {
        usageAmount = 0;
        usageMinutes = 0;
        activated = true;
        moving = false;
        this.scooterID = scooterID;
        this.scooterRegistration = scooterRegistration;
    }

    // Methods.

    /**
     *
     * @param nif Nif of the client that is renting the scooter.
     * @param name Name of the client that is renting the scooter.
     * @pre moving == true && nif != null & name != null
     */
    public void rentedBy(String nif, String name) {
        clientWhoRentedNIF = nif;
        clientWhoRentedName = name;
    }

    /**
     *
     * @param minutes Amount of minutes that the scooter was rented for.
     * @pre minutes >= 0 && isMoving == true
     */
    public void release(int minutes) {
        incrementUsageAmount();
        setTotalMinutes(minutes);
        stop();
        rentedBy(null, null);
    }

    /**
     *
     * @return Nif of the client who rented the scooter.
     * @pre isMoving() == true
     */
    public String getClientWhoRentedNIF() {
        return clientWhoRentedNIF;
    }

    /**
     *
     * @return Name of the client who rented the scooter.
     * @pre isMoving() == true
     */
    public String getClientWhoRentedName() {
        return clientWhoRentedName;
    }

    /**
     *
     * @return Registration of the scooter.
     */
    public String getScooterRegistration() {
        return scooterRegistration;
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
     * @return True if the scooter in currently being used
     *         false if the scooter isn't being used.
     */
    public boolean isMoving() {
        return moving;
    }

    /**
     * Makes the scooter move by setting the moving boolean to true.
     */
    public void move() {
        moving = true;
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
     * @param minutes Minutes to be added to the Scooter's total usage minutes.
     */
    public void setTotalMinutes(int minutes) {
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
     * Stops the scooter by setting the moving boolean to false.
     */
    public void stop() {
        moving = false;
    }

    /**
     * Deactivates the scooter by setting the activated boolean to false.
     */
    public void deactivate() {
        activated = false;
    }

    /**
     * Activates the scooter by setting the activated boolean to true.
     */
    public void activate() {
        activated = true;
    }

    /**
     * 
     * @return True if the scooter is activated and false if the scooter is
     *         deactivated.
     */
    public boolean isActivated() {
        return activated;
    }
}
