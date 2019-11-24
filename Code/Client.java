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
}
