/**
 * @author Antonio Duarte
 * Handles operations with the Scooter and Client classes, managing a rental system in between the two of them.
 **/

public class RentalSystem {

    // Constants.

    private final int MAX_MINUTES = 60;
    private final int COST = 100;
    private final int MINUTES_PER_FINE = 30;

    // Variables.

    public Scooter scooter;
    public Client client;
    private int totalNumberRentals, totalMoneyPaid, totalLateMinutes, lastRentalMinutes, lastRentalCost, lastRentalMax, lastLateMinutes;
    private boolean clientExists, scooterExists, promotionApplied;

    // Constructor.

    public RentalSystem() {
        totalNumberRentals = 0;
        totalMoneyPaid = 0;
        clientExists = false;
        scooterExists = false;
        lastRentalMinutes = 0;
        lastRentalCost = 0;
        lastRentalMax = 0;
        lastLateMinutes = 0;
        promotionApplied = true;
    }

    // Methods.

    /**
     *
     * @return  NIF of the client
     */
    public String getNIF() {
        return client.getNIF();
    }

    /**
     *
     * @return Name of the client
     */
    public String getName() {
        return client.getName();
    }

    /**
     *
     * @return Email of the client
     */
    public String getEmail() {
        return client.getEmail();
    }

    /**
     *
     * @return ID of the scooter that the client rented
     */
    public String getIdRentedScooter() {
        return client.getIdRentedScooter();
    }

    /**
     *
     * @return Registration of the scooter that the client rented
     */
    public String getRegistrationRentedScooter() {
        return client.getRegistrationRentedScooter();
    }

    /**
     *
     * @return Balance of the client
     */
    public int getBalance() {
        return client.getBalance();
    }

    /**
     *
     * @return Number of times that the client performed a rental
     */
    public int getNumberRentals() {
        return client.getNumRentals();
    }

    /**
     *
     * @return Average rental minutes per rental of the client
     */
    public int getAverageMinutes() {
        return client.getAverageMinutes();
    }

    /**
     *
     * @return Maximum minutes of a single rental by the client
     */
    public int getMax() {
        return client.getMax();
    }

    /**
     *
     * @return Total money spent by the client
     */
    public int getMoneySpent() {
        return client.getMoneySpent();
    }

    /**
     *
     * @return Client's phone number
     */
    public int getPhoneNumber() {
        return client.getPhoneNumber();
    }

    /**
     *
     * @return Total number of rental minutes of the client
     */
    public int getMinutes() {
        return client.getMinutes();
    }

    /**
     *
     * @return NIF of the client who rented the scooter
     */
    public String getClienteWhoRentedNIF() {
        return scooter.getClientWhoRentedNIF();
    }

    /**
     *
     * @return Name of the client who rented the scooter
     */
    public String getClientWhoRentedName() {
        return scooter.getClientWhoRentedName();
    }

    /**
     *
     * @return Registration of the scooter
     */
    public String getScooterRegistration() {
        return scooter.getScooterRegistration();
    }

    /**
     *
     * @return ID of the scooter
     */
    public String getScooterID() {
        return scooter.getScooterID();
    }

    /**
     *
     * @return The amount of times that the scooter was rented
     */
    public int getUsageAmount() {
        return scooter.getUsageAmount();
    }

    /**
     *
     * @return Total number of minutes that the scooter was rented
     */
    public int getUsageMinutes() {
        return scooter.getUsageMinutes();
    }

    /**
     *
     * @return Total number of rentals performed by the system
     */
    public int getTotalNumberRentals() {
        return totalNumberRentals;
    }

    /**
     *
     * @return Total money paid to the system
     */
    public int getTotalMoneyPaid() {
        return totalMoneyPaid;
    }

    /**
     *
     * @return Total late minutes in rentals performed by the system
     */
    public int getTotalLateMinutes() {
        return totalLateMinutes;
    }

    /**
     *
     * @return Returns true if there is a client in the system
     */
    public boolean doesClientExist() {
        return clientExists;
    }

    /**
     *
     * @return Returns true if there is a scooter in the system
     */
    public boolean doesScooterExist() {
        return scooterExists;
    }

    /**
     *
     * @param nif Identification of the client
     * @param email Email of the client
     * @param phoneNumber Phone number of the client
     * @param name Name of the client
     */
    public void createClient(String nif, String email, int phoneNumber, String name) {
        clientExists = true;
        client = new Client(nif, email, phoneNumber, name);
    }

    /**
     *
     * @param scooterID Identification of the scooter
     * @param scooterRegistration Registration of the scooter
     */
    public void createScooter(String scooterID, String scooterRegistration) {
        scooterExists = true;
        scooter = new Scooter(scooterID, scooterRegistration);
    }

    /**
     * Rents the scooter by switching the isMoving variable of the scooter to true.
     *
     * Changes the variables clientWhoRentedNIF and clientWhoRentedName to the NIF and name of the client of the client.
     *
     * Changes the variables IDRentedScooter and registrationRentedScooter to the scooterID and scooterRegistration
     * of the scooter.
     *
     * Resets the variables that store the values of the last rental (lastRentalCost, lastRentalMax, lastRentalMinutes
     * and lastLateMinutes).
     */
    public void rentScooter() {
        scooter.move();
        scooter.rentedBy(client.getNIF(), client.getName());
        client.rentScooter(scooter.getScooterID(), scooter.getScooterRegistration());

        lastRentalCost = 0;
        lastRentalMax = 0;
        lastRentalMinutes = 0;
        lastLateMinutes = 0;
    }

    /**
     *
     * @return Returns true if the client has rented a scooter. Returns false if the client hasn't rented a scooter or
     * if there isn't a client in the system.
     */
    public boolean hasClientRented() {
        if (clientExists) {
            return client.hasRented();
        }
        return false;
    }

    /**
     *
     * @return Returns true if the scooter has been rented. Returns false if the scooter hasn't been rented or if there
     * isn't a scooter in the system.
     */
    public boolean isScooterMoving() {
        if (scooterExists)
            return scooter.isMoving();
        else
            return false;
    }

    /**
     * Releases the scooter.
     *
     * Verifies if the amount of minutes that the scooter was rented for is above the maximum amount of minutes per rental.
     * If the amount of minutes is above the maximum amount of minutes it calculates the fine and applies it to the
     * cost of the rental.
     *
     * Updates the client's max rental time variable if the amount of minutes of the rental was above the previous
     * max.
     *
     * Increases the total money paid to the system.
     *
     * Increments the total number of rentals performed by the system.
     *
     * Updates the variables that store the values of the last rental (lastRentalCost, lastRentalMax, lastRentalMinutes
     * and lastLateMinutes)
     *
     * @param minutes an int that specifies the amount of minutes that the rental lasted for.
     */
    public void releaseScooter(int minutes) {
        int lateMinutes = 0;
        if (minutes > MAX_MINUTES) {
            lateMinutes = minutes - MAX_MINUTES;
            if (lateMinutes % 30 == 0) {
                client.removeBalance(lateMinutes / MINUTES_PER_FINE * COST);
                totalMoneyPaid += lateMinutes / MINUTES_PER_FINE * COST;
                lastRentalCost += lateMinutes / MINUTES_PER_FINE * COST;
            } else {
                client.removeBalance((lateMinutes / MINUTES_PER_FINE + 1) * COST);
                totalMoneyPaid += (lateMinutes / MINUTES_PER_FINE + 1) * COST;
                lastRentalCost += (lateMinutes / MINUTES_PER_FINE + 1) * COST;
            }
            totalLateMinutes += lateMinutes;
        }
        client.releaseScooter(minutes);
        client.removeBalance(COST);
        scooter.release(minutes);
        totalMoneyPaid += COST;
        totalNumberRentals++;
        lastRentalMinutes = minutes;
        lastLateMinutes = lateMinutes;
        promotionApplied = false;
        lastRentalCost += COST;
    }

    /**
     *
     * @param amount
     */
    public void addBalance(int amount) {
        client.addBalance(amount);
    }

    /**
     * Removes the client by setting the client variable to null and the clientExists boolean to false.
     */
    public void removeClient() {
        client = null;
        clientExists = false;
    }

    /**
     * 
     * @return If a promotion is or isn't applied.
     */
    public boolean isPromotionApplied() {
        return promotionApplied;
    }

    /**
     * Deactivates the scooter.
     */
    public void deactivateScooter() {
        scooter.deactivate();
    }

    /**
     * Reactivates the scooter.
     */
    public void reactivateScooter() {
        scooter.activate();
    }

    /**
     *
     * @return True if a scooter exists and it is activated. Returns false if it's deactivated.
     */
    public boolean isScooterActivated() {
        if (scooterExists) {
            return scooter.isActivated();
        } else {
            return false;
        }
    }
}
