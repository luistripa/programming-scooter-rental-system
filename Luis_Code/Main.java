import java.util.Scanner;

public class Main {

	// Constants
	private static final String INSERT_CLIENT = "ADCLIENTE";
	private static final String REMOVE_CLIENT = "REMCLIENTE";
	private static final String INSERT_SCOOTER = "ADTROT";
	private static final String CLIENT_DATA = "DADOSCLIENTE";
	private static final String RENTED_SCOOTER = "TROT";
	private static final String SCOOTER_DATA = "DADOSTROT";
	private static final String RENTER = "CLIENTE";
	private static final String ADD_BALANCE = "CARRSALDO";
	private static final String RENT = "ALUGAR";
	private static final String RELEASE = "LIBERTAR";
	private static final String SYS_STATE = "ESTADOSISTEMA";
	private static final String EXIT = "SAIR";

	// Bonus Command Constants
	private static final String PROMOTION = "PROMOCAO";
	private static final String DEACTIVATE = "DESTROT";
	private static final String ACTIVATE = "REACTROT";

	// Other Constants
	private static final int MINIMUM_BALANCE = 100; // The minimum balance the client must have to rent a scooter
	private static final int SCOOTER_INITIAL_TIME = 60; // The amount of minutes a client can use the scooter without fines
	private static final int INITIAL_SCOOTER_COST = 100; // Cost of the scooter rental
	private static final int DELAY_CYCLE = 30; // The client is fined everytime the delay exceeds this amount
	private static final int COST_PER_DELAY_CYCLE = 100; // The cost per delay cycle. This can be applied multiple times, depending on how many delay cycles elapsed

	// The main method
	public static void main(String[] args) {

		String opt = "";

		Scanner scan = new Scanner(System.in); // Initialize the scanner

		TrotasFCT sys = new TrotasFCT(); // The system object


		while (!opt.equals(EXIT)) {
			opt = getStringInput(scan).toUpperCase();
			executeOption(scan, sys, opt);
		}

		scan.close();
	}

	/**
	* Executes the command given by the user. Prints 'Comando invalido.' if command is not found
	*
	* @param scan The scanner object
	* @param clt The client object
	* @param sco The scooter object
	* @param sys The system (TrotasFCT) object
	* @param opt The user inputted command
	*/
	private static void executeOption(Scanner scan, TrotasFCT sys, String opt) {
		switch (opt) {
			case INSERT_CLIENT: {
				processInsertClient(scan, sys);
				// This does not require a scan.nextline() because one has already been used in the processInsertClient method
				break;
			}
			case REMOVE_CLIENT: {
				processRemoveClient(scan, sys);
				scan.nextLine();
				break;
			}
			case INSERT_SCOOTER: {
				processInsertScooter(scan, sys);
				scan.nextLine();
				break;
			}
			case CLIENT_DATA: {
				processClientData(scan, sys);
				scan.nextLine();
				break;
			}
			case RENTED_SCOOTER: {
				processRentedScooter(scan, sys);
				scan.nextLine();
				break;
			}
			case SCOOTER_DATA: {
				processScooterData(scan, sys);
				scan.nextLine();
				break;
			}

			case RENTER: {
				processRenter(scan, sys);
				scan.nextLine();
				break;
			}
			case ADD_BALANCE: {
				processAddBalance(scan, sys);
				scan.nextLine();
				break;
			}
			case RENT: {
				processRent(scan, sys);
				scan.nextLine();
				break;
			}
			case RELEASE: {
				processRelease(scan, sys);
				scan.nextLine();
				break;
			}
			case SYS_STATE: {
				processSysState(sys);
				scan.nextLine();
				break;
			}
			case PROMOTION: {
				processPromotion(scan, sys);
				scan.nextLine();
				break;
			}
			case DEACTIVATE: {
				processDeactivate(scan, sys);
				scan.nextLine();
				break;
			}
			case ACTIVATE: {
				processActivate(scan, sys);
				scan.nextLine();
				break;
			}
			case EXIT: {
				scan.nextLine();
				System.out.println("Saindo...");
				processSysState(sys);
				break;
			}
			default: {
				// Command not found
				printError("Comando invalido.");
				scan.nextLine();
				break;
			}

		}
	}

	/**
	* Processes the Insert Client command
	*
	* @param scan The scanner object
	* @param sys The system object
	*
	*/
	private static void processInsertClient(Scanner scan, TrotasFCT sys) {
		// Get client's nif
		String clientNif = getStringInput(scan);

		// Get client's email
		String clientEmail = getStringInput(scan);

		// Get client's phone
		String clientPhone = getStringInput(scan);

		// Get client's name
		String clientName = scan.nextLine().trim(); // Get the client's full name

		// Insert user if not exists
		if (sys.clientExists(clientNif)) {
			printError("Cliente existente.");
		} else {
			sys.insertClient(clientNif, clientEmail, clientPhone, clientName);
			System.out.println("Insercao de cliente com sucesso.");
		}
	}

	/**
	* Processes the Remove Client command
	*
	* @param scan The scanner object
	* @param sys The system object
	*
	*/
	private static void processRemoveClient(Scanner scan, TrotasFCT sys) {

		Client clt;

		// Get client's nif
		String nif = getStringInput(scan);

		if (sys.clientExists(nif)) {
			if (sys.getClientScooterInUse().equals("")) {
				sys.removeClient(nif);
				System.out.println("Cliente removido com sucesso.");
			} else {
				printError("Cliente em movimento.");
			}
			
		} else {
			printError("Cliente inexistente.");
		}
	}

	/**
	* Processes the Insert Scooter command. If inputted scooterID exists, prints error
	*
	* @param scan The scanner object
	* @param sys The system object
	*
	*/
	private static void processInsertScooter(Scanner scan, TrotasFCT sys) {
		// Get scooter's ID
		String scooterID = getStringInput(scan);

		// Get scooter's license plate number
		String scooterLicensePlate = getStringInput(scan);

		if (sys.scooterExists(scooterID)) {
			printError("Trotinete existente.");
		} else {
			sys.insertScooter(scooterID, scooterLicensePlate);
			System.out.println("Insercao de trotinete com sucesso.");
		}
	}

	/**
	* Processes the Client Data command
	*
	* @param scan The scanner object
	* @param sys The system object
	*
	*/
	private static void processClientData(Scanner scan, TrotasFCT sys) {

		// Get client's nif
		String clientNif = getStringInput(scan);

		if (sys.clientExists(clientNif)) {
			System.out.printf("%s: %s, %s, %s, %d, %d, %d, %d, %d, %d\n", sys.getClientName(), sys.getClientNif(), sys.getClientEmail(), sys.getClientPhone(), sys.getClientBalance(), sys.getClientTotalMinutes(), sys.getClientRentalNumber(), sys.getClientMaxTimeRental(), sys.getClientAverageTimeRental(), sys.getClientTotalExpense());
		} else {
			printError("Cliente inexistente.");
		}
	}

	/**
	* Shows information of the scooter rented by the client with specific nif
	*
	* @param scan The scanner object
	* @param sys The system object
	* 
	*/
	private static void processRentedScooter(Scanner scan, TrotasFCT sys) {

		String scooterInUse;

		// Get current client's rented scooter nif
		String clientNif = getStringInput(scan);

		if (sys.clientExists(clientNif)) {

			// The scooter the client is using. "" if no scooter.
			scooterInUse = sys.getClientScooterInUse();

			if (scooterInUse.equals("")) {
				printError("Cliente sem trotinete.");
			} else {
				System.out.println(scooterInUse+", "+sys.getScooterLicensePlate());
			}
		} else {
			printError("Cliente inexistente.");
		}

		
	}

	/**
	* Shows scooter information (license plate, state, total rentals, ...)
	*
	* @param scan The scanner object
	* @param sys The system object
	*
	*/
	private static void processScooterData(Scanner scan, TrotasFCT sys) {

		String state = "";

		// Get scooter ID
		String scooterID = getStringInput(scan);
		if (sys.scooterExists(scooterID)) {
			state = sys.getScooterState();
			System.out.printf("%s: %s, %d, %d\n", sys.getScooterLicensePlate(), state, sys.getScooterTotalRentals(), sys.getScooterMinutesMoving());
		} else {
			printError("Trotinete inexistente.");
		}

	}

	/**
	* Prints the client which is currently using the specified scooter
	*
	* @param scan The scanner object
	* @param sys The system object
	* 
	*/
	private static void processRenter(Scanner scan, TrotasFCT sys) {

		String clientInUse;
		
		// Get scooter ID
		String id = getStringInput(scan);

		if (sys.scooterExists(id)) {
			clientInUse = sys.getScooterClientInUse();
			if (clientInUse.equals("")) {
				printError("Trotinete nao alugada.");
			} else {
				System.out.println(sys.getClientNif()+", "+sys.getClientName());
			}
		} else {
			printError("Trotinete inexistente.");
		}
	}

	/**
	* Adds a specific amount to the client's account. The value must be valid (>0) for the transfer to be successful
	*
	* @param scan The scanner object
	* @param sys The system object
	*
	*/
	private static void processAddBalance(Scanner scan, TrotasFCT sys) {


		// Get client's nif
		String nif = getStringInput(scan);

		// Get amount of cents
		int amount = getIntValue(scan);

		if (amount>0) {
			if (sys.clientExists(nif)) {
				sys.addBalance(amount);
				System.out.println("Carregamento efectuado.");
			} else {
				printError("Cliente inexistente.");
			}
		} else {
			printError("Valor invalido.");
		}
		

	}

	/**
	* Processes the rental of a scooter. Client must have a minimum balance in order to proceed
	*
	* @param scan The scanner object
	* @param sys The system object
	*
	*/
	private static void processRent(Scanner scan, TrotasFCT sys) {

		sys.updatePrevious(); // Update the "previous" variables in the system, client and scooter.

		// Get client nif
		String nif = getStringInput(scan);

		// Get scooter id
		String id = getStringInput(scan);

		if (sys.clientExists(nif)) {
			if (sys.scooterExists(id)) {
				if (sys.getClientBalance()>=MINIMUM_BALANCE) {
					if (sys.getScooterState().equals("parada")) {
						sys.rent(); // Rent the scooter
						System.out.println("Aluguer efectuado com sucesso.");
					} else {
						printError("Trotinete nao pode ser alugada.");
					}
				} else {
					printError("Cliente sem saldo suficiente.");
				}
			} else {
				printError("Trotinete inexistente.");
			}
		} else {
			printError("Cliente inexistente.");
		}
	}

	/**
	* Process the release of a scooter.
	*
	* @param scan The scanner object
	* @param sys The system object
	*/
	private static void processRelease(Scanner scan, TrotasFCT sys) {

		String clientInUse;

		// Get scooter ID
		String id = getStringInput(scan);

		// Get minutes
		int minutes = getIntValue(scan);

		if (minutes > 0) {
			if (sys.scooterExists(id)) {
				clientInUse = sys.getScooterClientInUse();
				if (clientInUse.equals("")) {
					printError("Trotinete nao alugada.");
				} else {
					sys.release(minutes); // Release the scooter and update all variables
					System.out.println("Aluguer terminado.");
				}
			} else {
				printError("Trotinete inexistente.");
			}
		} else {
			printError("Valor invalido.");
		}
	}

	/**
	* Get the system state (Total client rentals, total client expense and the total delay minutes on the delivery of the scooter)
	*
	* @param sys The system object
	*/
	private static void processSysState(TrotasFCT sys) {
		System.out.println("Estado actual: "+sys.getTotalRentals()+", "+sys.getTotalSystemBalance()+", "+sys.getTotalDelayMinutes());
	}

	// Bonus

	/**
	* Processes the promotion command. Resets all variables from system, client and scooter to their respective "previous" variables
	*
	* @param scan The scanner object
	* @param sys The system object
	*/
	private static void processPromotion(Scanner scan, TrotasFCT sys) {

		// Get client's nif
		String nif = scan.next();

		if (sys.clientExists(nif)) {
			if (sys.getClientScooterInUse().equals("")) {
				if (sys.isPromotionApplied()) {
					printError("Promocao ja aplicada.");
				} else {
					sys.resetToPrevious();
					System.out.println("Promocao aplicada.");
				}
			} else {
				printError("Cliente iniciou novo aluguer.");
			}
			
		} else {
			printError("Cliente inexistente.");
		}
		
	}

	/**
	* Deactivates the scooter. It cannot be rented by any client while it is deactivated
	*
	* @param scan The scanner object
	* @param sys The system object
	*
	*/
	private static void processDeactivate(Scanner scan, TrotasFCT sys) {

		// Get scooter's id
		String id = scan.next();

		if (sys.scooterExists(id)) {
			if (sys.getScooterState().equals("alugada")) {
				printError("Trotinete em movimento.");
			} else {
				sys.deactivate();
				System.out.println("Trotinete desactivada.");
			}
		} else {
			System.out.println("Trotinete inexistente.");
		}

	}

	/**
	* Activates the scooter. If it was inactive, now it can be rented again by the client
	*
	* @param scan The scanner object
	* @param sys The system object
	*
	*/
	private static void processActivate(Scanner scan, TrotasFCT sys) {

		// Get scooter's id
		String id = scan.next();

		if (sys.scooterExists(id)) {
			if (sys.getScooterState().equals("inactiva")) {
				sys.activate();
				System.out.println("Trotinete reactivada.");
			} else {
				printError("Trotinete nao inactiva.");
			}
		} else {
			printError("Trotinete inexistente.");
		}
	}

	/**
	* Prints an error with custom message
	*
	* @param msg String containing the error message
	*
	*/
	private static void printError(String msg) {
		System.out.println(msg);
	}


	/**
	* Returns user input. This method only returns the string until a space or line break is found
	*
	* @param scan The scanner object
	* @return String that user inputted
	*
	*/
	private static String getStringInput(Scanner scan) {
		return scan.next();
	}

	/**
	* Returns an integer value inputted by the user
	*
	* @param scan The scanner object
	* @return Integer that user inputted
	*
	*/
	private static int getIntValue(Scanner scan) {
		return scan.nextInt();
	}
}