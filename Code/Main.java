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
	private static final String SYSTEM_STATE = "ESTADOSISTEMA";
	private static final String EXIT = "SAIR";
	private static final String DEACTIVATE = "DESTROT";
	private static final String ACTIVATE = "REACTROT";

	public static void main(String[] args) {
        RentalSystem system = new RentalSystem();
        Scanner scanner = new Scanner(System.in);
        String option;
        do {
            option = readOption(scanner);
            executeOption(scanner, system, option);
        } while((!option.equalsIgnoreCase(EXIT)));
        scanner.close();
    }

    /**
     * Reads the option that the user wishes to perform.
     * 
     * @param scanner Reads the user's input.
     * @return Option that the user wrote.
     */
    private static String readOption(Scanner scanner) {
        return scanner.next().toUpperCase();
    }

    /**
     * This method executes the option that the user chose to do in the program.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     * @param option Option that the user chose to perform.
     */
    private static void executeOption(Scanner scanner, RentalSystem system, String option) {
        switch (option) {
            case INSERT_CLIENT:
                createClient(scanner, system);
                break;
            case REMOVE_CLIENT:
                removeClient(scanner, system);
                break;
            case INSERT_SCOOTER:
                createScooter(scanner, system);
                break;
            case CLIENT_DATA:
                showClientData(scanner, system);
                break;
            case RENTED_SCOOTER:
                scooterRentedByClient(scanner, system);
                break;
            case SCOOTER_DATA:
                showScooterData(scanner, system);
                break;
            case RENTER:
                clientRentedScooter(scanner, system);
                break;
            case ADD_BALANCE:
                addBalance(scanner, system);
                break;
            case RENT:
                rentScooter(scanner, system);
                break;
            case RELEASE:
                releaseScooter(scanner, system);
                break;
            case SYSTEM_STATE:
                systemState(system);
                break;
            case EXIT:
                exit(system);
                break;
            case DEACTIVATE:
                deactivateScooter(scanner, system);
                break;
            case ACTIVATE:
                reactivateScooter(scanner, system);
                break;
            default:
                System.out.println("Comando invalido.");
        }
    }

    /**
     * Prints the information about the scooter that the Client rented.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void scooterRentedByClient(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        if (isNIFEqual(nif, system)) {
            if (system.hasClientRented())
                System.out.println(system.getIdRentedScooter() + ", " + system.getRegistrationRentedScooter());
            else
                System.out.println("Cliente sem trotinete.");
        } else
            System.out.println("Cliente inexistente.");
    }

    /**
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void clientRentedScooter(Scanner scanner, RentalSystem system) {
        String ScooterID = scanner.next();
        if (isScooterIDEqual(ScooterID, system)) {
            if (system.isScooterMoving())
                System.out.println(system.getClienteWhoRentedNIF() + ", " + system.getClientWhoRentedName());
            else
                System.out.println("Trotinete nao alugada.");
        } else
            System.out.println("Trotinete inexistente.");
    }

    /**
     * Exists the program.
     * 
     * @param system Rental system that is currently being used.
     */
    private static void exit(RentalSystem system) {
        System.out.println("Saindo...");
        systemState(system);
    }

    /**
     * Prints the state of the current Rental System.
     * 
     * @param system
     */
    private static void systemState(RentalSystem system) {
        System.out.println("Estado actual: " + system.getTotalNumberRentals() + ", " + system.getTotalMoneyPaid() + ", " + system.getTotalLateMinutes());
    }

    /**
     * Adds a specified amount to the client's balance.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void addBalance(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        int amount = scanner.nextInt();
        scanner.nextLine();
        if (isNIFEqual(nif, system) && amount > 0) {
            system.addBalance(amount);
            System.out.println("Carregamento efectuado.");
        } else if (amount <= 0)
            System.out.println("Valor invalido.");
        if (!isNIFEqual(nif, system))
            System.out.println("Cliente inexistente.");
    }

    /**
     * Removes a client.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void removeClient(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        scanner.nextLine();
        if (isNIFEqual(nif, system)) {
            if (!system.hasClientRented()) {
                system.removeClient();
                System.out.println("Cliente removido com sucesso.");
            } else
                System.out.println("Cliente em movimento.");
        } else
            System.out.println("Cliente inexistente.");
    }

    /**
     * Rents a scooter.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void rentScooter(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        String scooterID = scanner.next();
        scanner.nextLine();
        if (isNIFEqual(nif, system) && isScooterIDEqual(scooterID, system) && system.getBalance() >= 100 && !system.isScooterMoving() && system.isScooterActivated()) {
            system.rentScooter();
            System.out.println("Aluguer efectuado com sucesso.");
        } else if (!isNIFEqual(nif, system))
            System.out.println("Cliente inexistente.");
        else if (!isScooterIDEqual(scooterID, system))
            System.out.println("Trotinete inexistente.");
        else if (system.isScooterMoving() || !system.isScooterActivated())
            System.out.println("Trotinete nao pode ser alugada.");
        else if (system.getBalance() < 100)
            System.out.println("Cliente sem saldo suficiente.");
    }

    /**
     * Releases the scooter.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void releaseScooter(Scanner scanner, RentalSystem system) {
        String scooterID = scanner.next();
        int minutes = scanner.nextInt();
        scanner.nextLine();
        if (system.isScooterMoving() && minutes > 0 && isScooterIDEqual(scooterID, system)) {
            system.releaseScooter(minutes);
            System.out.println("Aluguer terminado.");
        } else if (minutes <= 0) {
            System.out.println("Valor invalido.");
        } else if (!isScooterIDEqual(scooterID, system)) {
            System.out.println("Trotinete inexistente.");
        } else if (!system.isScooterMoving()) {
            System.out.println("Trotinete nao alugada.");
        }
    }

    /**
     * Creates a client.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void createClient(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        String email = scanner.next();
        int phone = scanner.nextInt();
        String name = scanner.nextLine().trim();
        if (isNIFEqual(nif, system)) {
            System.out.println("Cliente existente.");
        } else {
            system.createClient(nif, email, phone, name);
            System.out.println("Insercao de cliente com sucesso.");
        }
    }

    /**
     * Creates a scooter.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void createScooter(Scanner scanner, RentalSystem system) {
        String scooterID = scanner.next();
        String scooterRegistration = scanner.next();
        scanner.nextLine();
        if (isScooterIDEqual(scooterID, system)) {
            System.out.println("Trotinete existente.");
        } else {
            system.createScooter(scooterID, scooterRegistration);
            System.out.println("Insercao de trotinete com sucesso.");
        }
    }

    /**
     * Shows the data regarding the client that matches the provided nif.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void showClientData(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        scanner.nextLine();
        if (isNIFEqual(nif, system)) {
            System.out.println(system.getName() + ": " + system.getNIF() + ", "
                    + system.getEmail() + ", " + system.getPhoneNumber() + ", "
                    + system.getBalance() + ", " + system.getMinutes() + ", "
                    + system.getNumberRentals() + ", " + system.getMax() + ", "
                    + system.getAverageMinutes() + ", " + system.getMoneySpent());
        } else {
            System.out.println("Cliente inexistente.");
        }
    }

    /**
     * Shows the data regarding the scooter that matches the provided scooterID.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void showScooterData(Scanner scanner, RentalSystem system) {
        String scooterID = scanner.next();
        scanner.nextLine();
        if (isScooterIDEqual(scooterID, system)) {
            String state;
            if (system.isScooterMoving())
                state = "alugada";
            else if (system.isScooterActivated())
                state = "parada";
            else
                state = "inactiva";
            System.out.println(system.getScooterRegistration() + ": " + state + ", " + system.getUsageAmount()
                    + ", " + system.getUsageMinutes());
        } else
            System.out.println("Trotinete inexistente.");
    }

    /**
     * Deactivates the scooter that matches the provided scooterID.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void deactivateScooter(Scanner scanner, RentalSystem system) {
        String scooterID = scanner.next();
        scanner.nextLine();
        if (isScooterIDEqual(scooterID, system) && !system.isScooterMoving()) {
            system.deactivateScooter();
            System.out.println("Trotinete desactivada.");
        } else if (!isScooterIDEqual(scooterID, system))
            System.out.println("Trotinete inexistente.");
        else if (system.isScooterMoving())
            System.out.println("Trotinete em movimento.");
    }

    /**
     * Reactivates the scooter that matches the provided scooterID.
     * 
     * @param scanner Reads the user's input.
     * @param system Rental system that is currently being used.
     */
    private static void reactivateScooter(Scanner scanner, RentalSystem system) {
        String scooterID = scanner.next();
        scanner.nextLine();
        if (isScooterIDEqual(scooterID, system) && !system.isScooterActivated()) {
            system.reactivateScooter();
            System.out.println("Trotinete reactivada.");
        } else if (!isScooterIDEqual(scooterID, system))
            System.out.println("Trotinete inexistente.");
        else if (system.isScooterActivated())
            System.out.println("Trotinete nao inactiva.");
    }

    /**
     * Verifies if there is a client with the provided nif.
     * 
     * @param nif NIF that is being compared.
     * @param system Rental system that is currently being used.
     * @return If the NIF is or isn't equal to the one introduced.
     */
    private static boolean isNIFEqual(String nif, RentalSystem system) {
        if (system.doesClientExist())
            return nif.equalsIgnoreCase(system.getNIF());
        else
            return false;
    }

    /**
     * Verifies if there is a scooter with the provided scooterID.
     * 
     * @param scooterID ScooterID that is being compared.
     * @param system Rental system that is currently being used.
     * @return If the ScooterID is or isn't equal to the one introduced.
     */
    private static boolean isScooterIDEqual(String scooterID, RentalSystem system) {
        if (system.doesScooterExist())
            return scooterID.equalsIgnoreCase(system.getScooterID());
        else
            return false;
    }
}
