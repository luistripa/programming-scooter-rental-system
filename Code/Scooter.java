public class Scooter {

    private String scooterID, registration, state, clientInUse;
    private int totalRentals, usageMinutes, usageAmount;

    public Scooter(String scooterID, String registration) {
        this.scooterID = scooterID;
        this.registration = registration;
        state = "parada";
        clientInUse = null;
        totalRentals = 0;
        usageMinutes = 0;
        usageAmount = 0;
    }

    /**
     * @return the totalRentals
     */
    public int getTotalRentals() {
        return totalRentals;
    }

    /**
     * @return the clientInUse
     */
    public String getClientInUse() {
        return clientInUse;
    }

    /**
     *
     * @param minutes Amount of minutes that the scooter was rented for.
     * @pre minutes >= 0 && isMoving == true
     */
    public void release(int minutes) {
        incrementUsageAmount();
        addTotalMinutes(minutes);
        setState("parada");
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
	* @param scooterClientInUse String which contains the name of the client riding the scooter

	* PRE: !scooterClientInUse.equals("")
	*/
	public void setClientInUse(String scooterClientInUse) {
		clientInUse = scooterClientInUse;
    }

	/**
	* Activates or deactivates the scooter
	*
	* @param status String. The status of the scooter (alugada, inactiva, parada)
	* 
	* PRE: st.equals("parada") || st.equals("alugada") || st.equals("inactiva")
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
}
