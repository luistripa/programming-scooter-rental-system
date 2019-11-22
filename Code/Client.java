public class Client {

    // Constants
	private static final int INITIAL_BALANCE = 200;

    String clientNIF;
    String clientEmail;
    String clientPhone;
    String clientName;

    public Client(String clientNIF, String clientEmail, String clientPhone, String clientName) {
        this.clientNIF = clientNIF;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
        this.clientName = clientName;
    }
}
