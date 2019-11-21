/**
* @author Luis Tripa
* Handles the information regarding the Scooter (id, license plate, etc.)
*/
public class Scooter {

	// Instance variables
	private String idTrot;
	private String licensePlate;
	private String state;
	private String clientInUse; // Client using the scooter (NIF)
	private int totalRentals;
	private int minutesMoving; // Total minutes on the move

	private int previousMinutesMoving;

	/**
	* Class constructor
	*
	* PRE: !id.equals("") && !lp.equals("")
	*/
	public Scooter(String id, String lp) {
		idTrot = id;
		licensePlate = lp;
		state = "parada";
		clientInUse = "";
		totalRentals = 0;
		minutesMoving = 0;

		previousMinutesMoving = 0; // Represents the minutesMoving value before the last rental
	}


	/*

	### SETTER METHODS ###

	*/

	/**
	* Activates or deactivates the scooter
	*
	* @param st String. The status of the scooter (alugada, inactiva, parada)
	* 
	* PRE: st.equals("parada") || st.equals("alugada") || st.equals("inactiva")
	*/
	public void setState(String st) {
		state = st;
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
	* Increments one to the total amount of rentals of the scooter
	*
	*/
	public void incTotalRentals() {
		totalRentals++;
	}

	/**
	* Increments given minutes to the total moving minutes
	*
	* @param minutes Integer representing the amount of minutes to increment
	*
	* PRE: minutes > 0
	*/
	public void addMinutesMoving(int minutes) {
		minutesMoving+=minutes;
	}

	/**
	* Updates previous state variables. These are used to process a promotion and reset the scooter to the previous state
	*
	*/
	public void updatePrevious() {
		previousMinutesMoving = minutesMoving;
	}

	/**
	* Resets the scooter to the previous state
	*
	*/
	public void resetToPrevious() {
		minutesMoving = previousMinutesMoving;
		totalRentals--;
	}

	/*

	### GETTER METHODS

	*/

	/**
	* Returns scooter's id
	*
	* @return idTrot String containing the scooter's id 
	*/
	public String getScooterID() {
		return idTrot;
	}

	/**
	* Returns scooter's license plate
	*
	* @return licensePlate String containing the scooter's license plate 
	*/
	public String getLicensePlate() {
		return licensePlate;
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
	* Returns the name of the client using the scooter
	*
	* @return clientInUse String containing the name of the client using the scooter 
	*/
	public String getClientInUse() {
		return clientInUse;
	}

	/**
	* Returns scooter's total rentals number
	*
	* @return totalRentals Integer containing the scooter's total rentals number
	*/
	public int getTotalRentals() {
		return totalRentals;
	}

	/**
	* Returns scooter's total minutes moving
	*
	* @return minutesmoving Integer containing the scooter's total minutes moving
	*/
	public int getMinutesMoving() {
		return minutesMoving;
	}












}






