/**
* @author Luis Tripa
* Handles the information regarding the system (client, scooter, system variables, ...)
*/
public class TrotasFCT {

	private static final int MINIMUM_BALANCE = 100; // The minimum balance the client must have to rent a scooter
	private static final int SCOOTER_INITIAL_TIME = 60; // The amount of minutes a client can use the scooter without fines
	private static final int INITIAL_SCOOTER_COST = 100; // Cost of the scooter rental
	private static final int DELAY_CYCLE = 30; // The client is fined everytime the delay exceeds this amount
	private static final int COST_PER_DELAY_CYCLE = 100; // The cost per delay cycle. This can be applied multiple times, depending on how many delay cycles elapsed

	// Instance variables
	private Client clt;
	private Scooter sco;
	private int totalRentals; // The total ammount of rentals ever registered by the system
	private int totalSystemBalance; // The total ammount of money ever transfered from the client to the system
	private int totalDelayMinutes; // The total amount of delays registered by the system

	// "previous" instance variables. Define the values of other instance variables before the last rental
	private int previousDelayMinutes;
	private int previousSystemBalance;
	
	/**
	* Class constructor
	*
	*/
	public TrotasFCT() {
		clt = null; // No client registered
		sco = null; // No scooter registered
		totalRentals = 0;
		totalSystemBalance = 0;
		totalDelayMinutes = 0;

		previousDelayMinutes = 0;
		previousSystemBalance = 0;
	}

	// Client Related methods

	/**
	* Inserts a client
	*
	* @param nif The client's nif
	* @param email The client's email
	* @param phone The client's phone
	* @param name The client's name
	*
	* PRE: !nif.equals("") && !email.equals("") && !phone.equals("") && !name.equals("") && !clientExists(nif)
	*/
	public void insertClient(String nif, String email, String phone, String name) {
		clt = new Client(nif, email, phone, name);
	}

	/**
	* Removes a client.
	*
	* PRE: clientExists(nif)
	*/
	public void removeClient(String nif) {
		clt=null;
	}

	/**
	* Checks for client existance. Checks if clt object in not null and if given nif also corresponds to the one saved
	*
	* @param nif The client's nif
	* @return Boolean defining whether client exists or not
	*
	* PRE: !nif.equals("")
	*/
	public boolean clientExists(String nif) {
		boolean exists = false;
		if (clt!= null && clt.getNif().toUpperCase().equals(nif.toUpperCase())) {
			exists = true;
		}

		return exists;
	}

	/**
	* Gets the Scooter client is using, "" if no scooter is being used
	*
	* @return scooter client is using
	*
	* PRE: clientExists(nif)
	*/
	public String getClientScooterInUse() {
		return clt.getScooterInUse();
	}

	/**
	* Gets the Client using the Scooter, "" if no client is using.
	*
	* @return The client which is using the scooter
	*
	* PRE: scooterExists(id)
	*/
	public String getScooterClientInUse() {
		return sco.getClientInUse();
	}

	/**
	* Get the client's name
	*
	* @return The client's name
	*
	* PRE: clientExists(nif)
	*/
	public String getClientName() {
		return clt.getName();
	}

	/**
	* Get the client's nif
	*
	* @return The client's nif
	*
	* PRE: clientExists(nif)
	*/
	public String getClientNif() {
		return clt.getNif();
	}

	/**
	* Get the client's email
	*
	* @return The client's email
	*
	* PRE: clientExists(nif)
	*/
	public String getClientEmail() {
		return clt.getEmail();
	}

	/**
	* Get the client's phone
	*
	* @return The client's phone
	*
	* PRE: clientExists(nif)
	*/
	public String getClientPhone() {
		return clt.getPhone();
	}

	/**
	* Get the client's balance
	*
	* @return The client's balance
	*
	* PRE: clientExists(nif)
	*/
	public int getClientBalance() {
		return clt.getBalance();
	}

	/**
	* Get the client's total moving minutes
	*
	* @return The client's total moving minutes
	*
	* PRE: clientExists(nif)
	*/
	public int getClientTotalMinutes() {
		return clt.getTotalMinutes();
	}

	/**
	* Get the client's total rental number
	*
	* @return The client's total rental number
	*
	* PRE: clientExists(nif)
	*/
	public int getClientRentalNumber() {
		return clt.getRentalNumber();
	}

	/**
	* Get the client's max time on a rental
	*
	* @return The client's max time on a rental
	*
	* PRE: clientExists(nif)
	*/
	public int getClientMaxTimeRental() {
		return clt.getMaxTimeRental();
	}

	/**
	* Get the client's average time on each rental
	*
	* @return The client's average time on each rental
	*
	* PRE: clientExists(nif)
	*/
	public int getClientAverageTimeRental() {
		return clt.getAverageTimeRental();
	}

	/**
	* Get the client's total expense
	*
	* @return The client's total expense
	*
	* PRE: clientExists(nif)
	*/
	public int getClientTotalExpense() {
		return clt.getTotalExpense();
	}

	// Scooter Related methods

	/**
	* Insert the scooter
	*
	* @param id The scooter's id
	* @param lp The scooter's license plate
	*
	* PRE: !scooterExists(nif) && !id.equals("") && !lp.equals("")
	*/
	public void insertScooter(String id, String lp) {
		sco = new Scooter(id, lp);
	}

	/**
	* Check whether scooter exists or not
	*
	* @param id The scooter's id
	* @return Boolean defining whether scooter exists or not
	*
	* PRE: 
	*/
	public boolean scooterExists(String id) {
		boolean exists = false;
		if (sco!= null && sco.getScooterID().toUpperCase().equals(id.toUpperCase())) {
			exists = true;
		}

		return exists;
	}

	/**
	* Get the scooter's id
	*
	* @return The scooter's id
	*
	* PRE: scooterExists(nif)
	*/
	public String getScooterID() {
		return sco.getScooterID();
	}

	/**
	* Get the scooter's id
	*
	* @return The scooter's id
	*
	* PRE: scooterExists(nif)
	*/
	public String getScooterState() {
		return sco.getState();
	}

	/**
	* Get the scooter's license plate
	*
	* @return The scooter's license plate
	*
	* PRE: scooterExists(nif)
	*/
	public String getScooterLicensePlate() {
		return sco.getLicensePlate();
	}

	/**
	* Get the scooter's total rentals
	*
	* @return The scooter's totals rentals
	*
	* PRE: scooterExists(nif)
	*/
	public int getScooterTotalRentals() {
		return sco.getTotalRentals();
	}

	/**
	* Get the scooter's total minutes moving
	*
	* @return The scooter's total minutes moving
	*
	* PRE: scooterExists(id)
	*/
	public int getScooterMinutesMoving() {
		return sco.getMinutesMoving();
	}

	// Generic methods

	/**
	* Adds a specific amount to the client's balance
	*
	* @param amount The amount to be added to the client's balance
	*
	* PRE: clientExists(nif) && amount > 0
	*/
	public void addBalance(int amount) {
		clt.addBalance(amount);
	}

	/**
	* Rents the scooter
	*
	* PRE: clientExists(nif) && scooterExists(id) && getClientBalance() >= MINIMUM_BALANCE && getScooterState().equals("parada")
	*/
	public void rent() {
		sco.setClientInUse(clt.getNif());
		sco.setState("alugada");
		clt.setScooterInUse(sco.getScooterID());
	}

	/**
	* Releases the scooter and updates all varaibles. Also calculates the total client's expense and the dalay
	*
	* @param minutes The minutes elapsed
	*
	* PRE: scooterExists(id) && minutes > 0
	*/
	public void release(int minutes) {
		int expense = 0;
		int delay = 0;

		expense += INITIAL_SCOOTER_COST; // Price for the first 60 minutes
		// Process total client's expense due to possible delays
		delay = minutes-SCOOTER_INITIAL_TIME; // Calculate delay amount
		if (delay > 0) {
			expense += (delay/DELAY_CYCLE)*COST_PER_DELAY_CYCLE; // Get expense by calculating the number of delay cycles
			if (delay%DELAY_CYCLE > 0) {
				expense += COST_PER_DELAY_CYCLE; // Calculate delay time smaller than delay cycle and update the expense
			}
		} else {
			delay = 0;
		}

		// Decrement the expense
		clt.remBalance(expense);

		// Update remaining client variables
		clt.setScooterInUse("");
		clt.incRentalNumber();
		clt.addMinutes(minutes);
		clt.updateMaxTimeRental(minutes);
		clt.incExpense(expense);
		clt.setPromotion(false);

		// Update scooter variables
		sco.setState("parada");
		sco.setClientInUse("");
		sco.incTotalRentals();
		sco.addMinutesMoving(minutes);

		// Update system variables
		incTotalRentals();
		addTotalSystemBalance(expense);
		addTotalDelayMinutes(delay);
	}

	/**
	* Checks if a promotion was applied to the client recently
	*
	* PRE: clientExists(nif)
	*/
	public boolean isPromotionApplied() {
		return clt.isPromotionApplied();
	}

	/**
	* Activates the scooter. When this method is executed the scooter becomes rentable
	*
	* PRE: scooterExists(id) && getScooterState().equals("inactiva")
	*/
	public void activate() {
		sco.setState("parada");
	}

	/**
	* Deactivates the scooter. When this method is executed the scooter cannot be rented until de activate method is called
	*
	* PRE: scooterExists(id) && !getScooterState().equals("alugada")
	*/
	public void deactivate() {
		sco.setState("inactiva");
	}



	/* Setter Methods */

	/**
	* Increments the total system rentals everytime a rental is complete
	*
	*/
	public void incTotalRentals() {
		totalRentals++;
	}

	/**
	* Adds the rental client's expense to the system total balance
	*
	* @param amount The amount to be added to the system balance
	*
	* PRE: amount > 0
	*/
	public void addTotalSystemBalance(int amount) {
		totalSystemBalance += amount;
	}

	/**
	* Adds the rental delay minutes to the system's total delay minutes variable
	*
	* @param amount The amount to be added to the system total delay minutes
	*
	* PRE: amount >= 0
	*/
	public void addTotalDelayMinutes(int amount) {
		totalDelayMinutes += amount;
	}

	/**
	* Resets all system's client's and scooter's variables to their "previous" state, completely ignoring the last rental effects
	*
	*/
	public void resetToPrevious() {
		clt.resetToPrevious();
		clt.setPromotion(true);
		sco.resetToPrevious();
		totalRentals--;
		totalDelayMinutes = previousDelayMinutes;
		totalSystemBalance = previousSystemBalance;
	}

	/**
	* Updates all "previous" variables, including the scooter and client's ones
	*
	*/
	public void updatePrevious() {
		clt.updatePrevious();
		sco.updatePrevious();
		previousDelayMinutes = totalDelayMinutes;
		previousSystemBalance = totalSystemBalance;
	}


	/* Getter Methods */

	/**
	* Get the system's total rentals
	*
	* @return Total rentals processed by the system
	*
	*/
	public int getTotalRentals() {
		return totalRentals;
	}

	/**
	* Get the system's total balance
	*
	* @return Total system's balance
	*
	*/
	public int getTotalSystemBalance() {
		return totalSystemBalance;
	}

	/**
	* Get the system's total delay minutes
	*
	* @return Total delay minutes processed by the system
	*
	*/
	public int getTotalDelayMinutes() {
		return totalDelayMinutes;
	}
}