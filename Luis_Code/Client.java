/**
* @author Luis Tripa
* Handles all Client information and its manipulation
*/
public class Client {

	// Constants
	private static final int INITIAL_BALANCE = 200;

	// Instance variables
	private String nif, email, phone, name;
	private int balance;
	private int totalMinutes;
	private int rentalNumber;
	private int maxTimeRental;
	private int totalExpense;
	private String scooterInUse; // ID of the scooter being used

	// More instance variables. These represent client's values before the last rental. Used when a promotion is applied
	private boolean promotionApplied;
	private int previousBalance;
	private int previousTotalMinutes;
	private int previousMaxTimeRental;
	private int previousTotalExpense;

	/**
	* Class constructor
	*
	* !clientNid.equals("") && !clientEmail.equals("") && !clientPhone.equals("") && !clientName.equals("")
	*/
	public Client(String clientNif, String clientEmail, String clientPhone, String clientName) {
		nif = clientNif;
		email = clientEmail;
		phone = clientPhone;
		name = clientName;
		balance = INITIAL_BALANCE;
		totalMinutes = 0;
		rentalNumber = 0;
		maxTimeRental = 0;
		totalExpense = 0;
		scooterInUse = "";

		promotionApplied = false;
	}

	/**
	* Adds a specific amount to the client's balance
	*
	* @param amount The amount to be added to the client's balance
	*
	* PRE: amount > 0;
	*/
	public void addBalance(int amount) {
		balance += amount;
	}

	/**
	* Removes a specific amount from the client's balance
	*
	* @param amount The amount to be removed to the client's balance
	*
	* PRE: amount > 0;
	*/
	public void remBalance(int amount) {
		balance -= amount;
	}

	/**
	* Adds minutes to the totalMinutes variable. This variable represents the amount of minutes the client has been using the scooter
	*
	* @param minutes The amount of time
	*
	* PRE: minutes > 0;
	*/
	public void addMinutes(int minutes) {
		totalMinutes += minutes;
	}

	/**
	* Increments one to the client's total rental number
	*
	*/
	public void incRentalNumber() {
		promotionApplied = false;
		rentalNumber++;
	}

	/**
	* Decrements one to the client's total rental number. This method is only used when a promotion is applied
	*
	*/
	private void decRentalNumber() {
		rentalNumber--;
	}

	/**
	* Verifies if the recently finished rental took longer than any other. If this is the case, it updates the maxTimeRental variable to the total amount of time this rental took
	*
	* @param minutes The amount of time
	*
	* PRE: minutes > 0;
	*/
	public void updateMaxTimeRental(int minutes) {
		if (getRentalNumber()==0 || getMaxTimeRental()<minutes) {
			maxTimeRental = minutes;
		}
	}

	/**
	* Increments a specific amount to the total client's expense
	*
	* @param exp The amount of expense
	*
	* PRE: exp >= 0;
	*/
	public void incExpense(int exp) {
		totalExpense += exp;
	}

	/**
	* Defines the scooter the client is currently using. When a client releases a scooter, this method sets the scooterInUse variable to ""
	*
	* @param clientScooterInUse The scooter's id which the client is using
	*
	*/
	public void setScooterInUse(String clientScooterInUse) {
		scooterInUse = clientScooterInUse;
	}

	/**
	* Sets whether a promotion has been applied or not. Remmember a promotion cannot be applied twice in a row
	*
	* @param val Boolean defining the value that promotionApplied should hold
	*
	* PRE: minutes > 0;
	*/
	public void setPromotion(boolean val) {
		promotionApplied = val;
	}

	/**
	* Updates all "previous" variables
	*
	*/
	public void updatePrevious() {
		previousBalance = balance;
		previousTotalExpense = totalExpense;
		previousTotalMinutes = totalMinutes;
		previousMaxTimeRental = maxTimeRental;
		previousTotalExpense = totalExpense;
	}

	/**
	* Resets all variables to "previous" variables. Called when a promotion is applied
	*
	*/
	public void resetToPrevious() {
		balance = previousBalance;
		totalExpense = previousTotalExpense;
		totalMinutes = previousTotalMinutes;
		rentalNumber--;
		maxTimeRental = previousMaxTimeRental;
		totalExpense = previousTotalExpense;
	}


	/**
	* Gets the client's nif
	*
	* @return The client's nif
	*/
	public String getNif() {
		return nif;
	}

	/**
	* Gets the client's email
	*
	* @return The client's email
	*/
	public String getEmail() {
		return email;
	}

	/**
	* Gets the client's phone
	*
	* @return The client's phone
	*/
	public String getPhone() {
		return phone;
	}

	/**
	* Gets the client's name
	*
	* @return The client's name
	*/
	public String getName() {
		return name;
	}

	/**
	* Gets the client's balance
	*
	* @return The client's balance
	*/
	public int getBalance() {
		return balance;
	}

	/**
	* Gets the client's total minutes using a scooter
	*
	* @return The client's total minutes using a scooter
	*/
	public int getTotalMinutes() {
		return totalMinutes;
	}

	/**
	* Gets the client's total number of rentals
	*
	* @return The client's total number of rentals
	*/
	public int getRentalNumber() {
		return rentalNumber;
	}

	/**
	* Gets the client's greatest duration of a rental
	*
	* @return The client's greatest duration of a rental
	*/
	public int getMaxTimeRental() {
		return maxTimeRental;
	}

	/**
	* Gets the client's average time of rentals
	*
	* @return The client's average time of rentals
	*/
	public int getAverageTimeRental() {
		if (getRentalNumber()==0) {
			return 0;
		}
		return getTotalMinutes()/getRentalNumber();
	}

	/**
	* Gets the client's total expense on scooters
	*
	* @return The client's total expense on scooters
	*/
	public int getTotalExpense() {
		return totalExpense;
	}

	/**
	* Gets the scooter client is using
	*
	* @return The scooter client is using
	*/
	public String getScooterInUse() {
		return scooterInUse;
	}

	/**
	* Checks if a promotion has been applied
	*
	* @return (boolean) Whether a promotion has been applied or not
	*/
	public boolean isPromotionApplied() {
		return promotionApplied;
	}

}















