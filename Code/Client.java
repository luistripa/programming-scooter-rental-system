public class Client {

	// Constants
	private static final int DEFAULT_BALANCE = 200;

	// Instance variables
	private String nif, email, phone, name;
	private int balance;
	private int totalMinutes;
	private int numberRentals;
	private int maxTime;
	private int moneySpent;
	private String scooterInUse; // ID of the scooter being used by the client

    public Client(String nif, String email, String phone, String name) {
        this.nif = nif;
        this.email = email;
        this.phone = phone;
		this.name = name;
		balance = DEFAULT_BALANCE;
		totalMinutes = 0;
		numberRentals = 0;
		maxTime = 0;
		moneySpent = 0;
    }

	/**
	* Method that updates all client's variables when a scooter is released
	*
	* @param minutes The amount of minutes the client used the scooter for
	*
	* PRE: minutes > 0
	*/
	public void releaseScooter() {
		if (minutes > maxTime)
			maxTime = minutes;
		incrementNumberRentals();
		addMinutes(minutes);
		setScooterInUse("");
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
	public addMoneySpent(int amount) {
		moneySpent += amount;
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
	public String getPhone() {
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




}
