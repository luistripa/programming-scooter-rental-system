/**
 * @author Antonio Duarte
 * Handles and makes operations upon Client objects
 **/

public class Client {

    // Constants.

    private static final int DEFAULT_BALANCE = 200;

    // Variables.

    private boolean hasRented;
    private String nif, name, email, idRentedScooter, registrationRentedScooter;
    private int balance, numberRentals, max, moneySpent, phoneNumber, minutes;

    // Constructor.

    public Client(String nif, String email, int phoneNumber, String name) {
        this.nif = nif;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        balance = DEFAULT_BALANCE;
        minutes = 0;
        numberRentals = 0;
        moneySpent = 0;
        max = 0;
    }

    // Methods.

    /**
     * Method that sets the variables idRentedScooter and registrationRentedScooter to the respective scooterID
     * and scooterRegistration of the scooter that the client is renting.
     *
     * Sets the variable hasRented to true.
     *
     * @param idScooter The id of the scooter that the client is going to rent.
     * @param registration The registration of the scooter that the client is going to rent.
     */
    public void rentScooter(String idScooter, String registration) {
        idRentedScooter = idScooter;
        registrationRentedScooter = registration;
        hasRented = true;
    }

    /**
     *
     * @return Indicates wether the client is currently renting a scooter or not.
     */
    public boolean hasRented() {
        return hasRented;
    }

    /**
     *
     * @return Id of the scooter that the client rented.
     */
    public String getIdRentedScooter() {
        return idRentedScooter;
    }

    /**
     *
     * @return Registration of the scooter that the client rented.
     */
    public String getRegistrationRentedScooter() {
        return registrationRentedScooter;
    }

    /**
     *
     * @return Client's name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Total amount of money spent by the client.
     */
    public int getMoneySpent() {
        return moneySpent;
    }

    /**
     *
     * @return Client's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return Client's phone number.
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @return Client's nif.
     */
    public String getNIF() {
        return nif;
    }

    /**
     *
     * @return Client's current balance.
     */
    public int getBalance() {
        return balance;
    }

    /**
     *
     * @return Number of rentals performed by the client.
     */
    public int getNumRentals() {
        return numberRentals;
    }

    /**
     *
     * @return Average number minutes performed per rental by the client.
     */
    public int getAverageMinutes() {
        int averageMinutes;
        if (numberRentals == 0)
            averageMinutes = 0;
        else
            averageMinutes = getMinutes() / getNumRentals();
        return averageMinutes;
    }

    /**
     *
     * @return Total number of rental minutes performed by the client.
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     *
     * @return Maximum amount of rental minutes of the client in a single rental.
     */
    public int getMax() {
        return max;
    }

    /**
     * This method compares the maximum amount of minutes in a rental with the amount of minutes of the rental
     * that the client is releasing the Scooter from, and atributes the amount of minutes of the rental to the max variable
     * if minutes is bigger than max.
     * Atributes null to the variables registrationRentedScooter and idRentedScooter because the client is no longer
     * renting any scooter.
     *
     * @param minutes the amount minutes that the client used the scooter for, to add to the total amount of minutes and
     *                compare with the current amount of minutes.
     * @pre minutes > 0
     */
    public void releaseScooter(int minutes) {
        if (minutes > max)
            max = minutes;
        incrementNumberRentals();
        addMinutes(minutes);
        registrationRentedScooter = null;
        idRentedScooter = null;
        hasRented = false;
    }

    /**
     * Incrementes the number of rentals.
     */
    public void incrementNumberRentals() {
        numberRentals++;
    }

    /**
     * Decrements the number of rentals.
     */
    public void decrementNumberRentals() {
        numberRentals--;
    }

    /**
     *
     * @param minutes The minutes to add to the total of rental minutes.
     */
    public void addMinutes(int minutes) {
        this.minutes += minutes;
    }

    /**
     * Sets the max amount of
     *
     * @param max Value to be attributed to the max amount of minutes in a rental.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     *
     * @param moneySpent The amount of money spent in a rental to be added to the total money spent by the client.
     */
    public void addMoneySpent(int moneySpent) {
        this.moneySpent += moneySpent;
    }

    /**
     *
     * @param amount The amount of money to be added to the balance.
     * @pre amount > 0
     */
    public void addBalance(int amount) {
        balance += amount;
    }

    /**
     *
     * @param amount The amount of money to be removed from the balance.
     * @pre amount > 0
     */
    public void removeBalance(int amount) {
        moneySpent += amount;
        balance -= amount;
    }
}
