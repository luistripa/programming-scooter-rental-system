public class Client {

	// Constants
	private static final int DEFAULT_BALANCE = 200;

	// Instance variables
	private String nif, email, name;
	private int phone, balance, totalMinutes, numberRentals, maxTime, moneySpent;
	private Scooter scooterInUse;

	public Client(String nif, String email, int phone, String name) {
		this.nif = nif;
		this.email = email;
		this.phone = phone;
		this.name = name;
		balance = DEFAULT_BALANCE;
		totalMinutes = 0;
		numberRentals = 0;
		maxTime = 0;
		moneySpent = 0;
		scooterInUse = null; // No scooter being used
	}

	/**
	* Method that updates all client's variables when a scooter is released
	*
	* @param minutes The amount of minutes the client used the scooter for
	*
	* PRE: minutes > 0
	*/
	public void releaseScooter(int minutes, int expense) {
		if (minutes > maxTime)
			maxTime = minutes;
		incrementNumberRentals();
		addMinutes(minutes);
		setScooterInUse(null);
		addMoneySpent(expense);
		remBalance(expense);
	}

	/**
	* Adds a specific amount to the client's balance
	*
	* @param amount The amount to be added
	*
	* PRE: amount > 0
	*/
	public void addBalance(int amount) {
		balance += amount;
	}

	/**
	 * Removes a specific amount from the client balance
	 * @param amount The amount to be removed
	 * PRE: amount > 0
	 */
	public void remBalance(int amount) {
		balance -= amount;
	}

	/**
	* Adds minutes to the total moving minutes
	*
	* @param minutes
	*
	* PRE: minutes > 0
	*/
	public void addMinutes(int minutes) {
		totalMinutes += minutes;
	}

	/**
	* Increments to the total number of rentals
	*
	*/
	public void incrementNumberRentals() {
		numberRentals++;
	}

	/**
	* Adds amount to the total money spent
	*
	* @param amount The amount to be added
	*
	* PRE: amount > 0
	*/
	public void addMoneySpent(int amount) {
		moneySpent += amount;
	}

	/**
	* Sets scooterInUse to the id of the scooter the client is going to use
	*
	* @param id The id of the scooter the client is going to use
	*/
	public void setScooterInUse(Scooter scooter) {
		scooterInUse = scooter;
	}

	/**
	*
	* @return The client nif
	*/
	public String getNif() {
		return nif;
	}

	/**
	*
	* @return The client email
	*/
	public String getEmail() {
		return email;
	}

	/**
	*
	* @return The client phone
	*/
	public int getPhone() {
		return phone;
	}

	/**
	*
	* @return The client name
	*/
	public String getName() {
		return name;
	}

	/**
	*
	* @return The client balance
	*/
	public int getBalance() {
		return balance;
	}

	/**
	*
	* @return The client total minutes on the move
	*/
	public int getTotalMinutes() {
		return totalMinutes;
	}

	/**
	*
	* @return The client total number of rentals
	*/
	public int getNumberRentals() {
		return numberRentals;
	}

	/**
	*
	* @return The client max time in a rental
	*/
	public int getMaxTime() {
		return maxTime;
	}

	/**
	*
	* @return The client total money spent on rentals
	*/
	public int getMoneySpent() {
		return moneySpent;
	}

	/**
	*
	* @return the scooterInUse
	*/
	public Scooter getScooterInUse() {
		return scooterInUse;
	}

	public boolean hasRented() {
		return scooterInUse != null;
	}

	/**
	*
	* @return The client average minutes per rental
	*/
	public int getAverageMinutes() {
		int averageMinutes;
		if (numberRentals == 0)
			averageMinutes = 0;
		else
			averageMinutes = getTotalMinutes() / getNumberRentals();
		return averageMinutes;
	}

	/**
	 * 
	 * @param otherClient
	 * @return
	 */
	public boolean balanceGreaterThan(Client other) {
		return this.getBalance() > other.getBalance();
	}

	/**
	 * 
	 * @param other
	 * @return
	 */
	public boolean nifGreaterThan(Client other) {
		return this.getNif().compareTo(other.getNif()) > 0;
	}
}
