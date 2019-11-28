import java.util.Scanner;
import java.util.Locale;

public class Main {

    // Constants
    private static final int MINIMUM_BALANCE = 100;

    // Command Constants
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
    private static final String LIST_CLIENTS = "LISTCLIENTE";
    private static final String LIST_SCOOTER = "LISTTROT";
    private static final String LIST_NEGATIVE_BALANCE = "LISTDEV";
    private static final String RELEASE_WITH_LOCATION = "LIBLOC";
    private static final String LOCATE_CLOSER = "LOCTROT";

    // Error messages
    private static final String INVALID_COMMAND = "Comando invalido.";
    private static final String CLIENT_WITHOUT_SCOOTER = "Cliente sem trotinete.";
    private static final String CLIENT_DOESNT_EXIST = "Cliente inexistente.";
    private static final String SCOOTER_NOT_RENTED = "Trotinete nao alugada.";
    private static final String SCOOTER_DOESNT_EXIST = "Trotinete inexistente.";
    private static final String LEAVING = "Saindo...";
    private static final String BALANCE_ADDED = "Carregamento efectuado.";
    private static final String INVALID_VALUE = "Valor invalido.";
    private static final String CLIENT_REMOVED = "Cliente removido com sucesso.";
    private static final String CLIENT_MOVING = "Cliente em movimento.";
    private static final String RENTED = "Aluguer efectuado com sucesso.";
    private static final String SCOOTER_CANT_BE_RENTED = "Trotinete nao pode ser alugada.";
    private static final String NOT_ENOUGH_BALANCE = "Cliente sem saldo suficiente.";
    private static final String RENTAL_FINISHED = "Aluguer terminado.";
    private static final String CLIENT_EXISTS = "Cliente existente.";
    private static final String SCOOTER_EXISTS = "Trotinete existente.";
    private static final String SCOOTER_MOVING = "Trotinete em movimento.";
    private static final String SCOOTER_DEACTIVATED = "Trotinete desactivada.";
    private static final String CLIENT_INSERTED = "Insercao de cliente com sucesso.";
    private static final String SCOOTER_INSERTED = "Insercao de trotinete com sucesso.";
    private static final String SCOOTER_REACTIVATED = "Trotinete reactivada.";
    private static final String SCOOTER_NOT_INACTIVE = "Trotinete nao inactiva.";
    private static final String INVALID_LOCATION = "Localizacao invalida.";
    private static final String NO_SCOOTER_LOCATED = "Nao existem trotinetes localizadas.";

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        RentalSystem system = new RentalSystem();
        Scanner scanner = new Scanner(System.in);
        String option = "";
        while (!option.equals(EXIT)) {
            option = readOption(scanner);
            executeOption(scanner, system, option);
        }
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
     * @param system  Rental system that is currently being used.
     * @param option  Option that the user chose to perform.
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
        case LIST_SCOOTER:
            listScooter(system, scanner);
            break;
        case LIST_CLIENTS:
            listClient(system, scanner);
            break;
        case LIST_NEGATIVE_BALANCE:
            listDebtors(system, scanner);
            break;
        case RELEASE_WITH_LOCATION:
            releaseLocation(system, scanner);
            break;
        case LOCATE_CLOSER:
            locateCloserScooters(system, scanner);
            break;
        default:
            scanner.nextLine();
            System.out.println(INVALID_COMMAND);
        }
    }

    /**
     * Creates a client.
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void createClient(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        String email = scanner.next();
        int phone = scanner.nextInt();
        String name = scanner.nextLine().trim();
        if (system.clientExists(nif)) {
            System.out.println(CLIENT_EXISTS);
        } else {
            system.createClient(nif, email, phone, name);
            System.out.println(CLIENT_INSERTED);
        }
    }

    /**
     * Removes a client.
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void removeClient(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        scanner.nextLine();
        if (system.clientExists(nif)) {
            if (!system.hasClientRented(nif)) {
                system.removeClient(nif);
                System.out.println(CLIENT_REMOVED);
            } else
                System.out.println(CLIENT_MOVING);
        } else
            System.out.println(CLIENT_DOESNT_EXIST);
    }

    /**
     * Creates a scooter.
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void createScooter(Scanner scanner, RentalSystem system) {
        String scooterID = scanner.next();
        String scooterRegistration = scanner.next();
        scanner.nextLine();
        if (system.scooterExists(scooterID)) {
            System.out.println(SCOOTER_EXISTS);
        } else {
            system.createScooter(scooterID, scooterRegistration);
            System.out.println(SCOOTER_INSERTED);
        }
    }

    /**
     * Shows the data regarding the client that matches the provided nif.
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void showClientData(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        scanner.nextLine();
        if (system.clientExists(nif)) {
            System.out.println(system.getClientName(nif) + ": " + system.getClientNif(nif) + ", "
                    + system.getClientEmail(nif) + ", " + system.getClientPhone(nif) + ", "
                    + system.getClientBalance(nif) + ", " + system.getClientTotalMinutes(nif) + ", "
                    + system.getClientNumberRentals(nif) + ", " + system.getClientMaxTime(nif) + ", "
                    + system.getClientAverageRentalTime(nif) + ", " + system.getClientMoneySpent(nif));
        } else
            System.out.println(CLIENT_DOESNT_EXIST);
    }

    /**
     * Prints the information about the scooter that the Client rented.
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void scooterRentedByClient(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        if (system.clientExists(nif)) {
            if (system.hasClientRented(nif)) {
                String scooterID = system.getClientScooterInUse(nif).getScooterID();
                System.out.println(system.getScooterID(scooterID) + ", " + system.getScooterRegistration(scooterID));
            } else
                System.out.println(CLIENT_WITHOUT_SCOOTER);
        } else
            System.out.println(CLIENT_DOESNT_EXIST);
    }

    /**
     * Shows the data regarding the scooter that matches the provided scooterID.
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void showScooterData(Scanner scanner, RentalSystem system) {
        String scooterID = scanner.next();
        scanner.nextLine();
        if (system.scooterExists(scooterID)) {
            System.out.println(system.getScooterRegistration(scooterID) + ": " + system.getScooterState(scooterID)
                    + ", " + system.getScooterUsageAmount(scooterID) + ", " + system.getScooterUsageMinutes(scooterID));
        } else
            System.out.println(SCOOTER_DOESNT_EXIST);
    }

    /**
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void clientRentedScooter(Scanner scanner, RentalSystem system) {
        String scooterID = scanner.next();
        if (system.scooterExists(scooterID)) {
            if (system.isScooterMoving(scooterID))
                System.out.println(system.getScooterClientInUse(scooterID).getNif() + ", "
                        + system.getClientName(system.getScooterClientInUse(scooterID).getNif()));
            else
                System.out.println(SCOOTER_NOT_RENTED);
        } else
            System.out.println(SCOOTER_DOESNT_EXIST);
    }

    /**
     * Adds a specified amount to the client's balance.
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void addBalance(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        int amount = scanner.nextInt();
        scanner.nextLine();
        if (system.clientExists(nif) && amount > 0) {
            system.addBalance(nif, amount);
            System.out.println(BALANCE_ADDED);
        } else if (amount <= 0)
            System.out.println(INVALID_VALUE);
        if (!system.clientExists(nif))
            System.out.println(CLIENT_DOESNT_EXIST);
    }

    /**
     * Rents a scooter.
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void rentScooter(Scanner scanner, RentalSystem system) {
        String nif = scanner.next();
        String scooterID = scanner.next();
        scanner.nextLine();
        if (system.clientExists(nif) && system.scooterExists(scooterID)
                && system.getClientBalance(nif) >= MINIMUM_BALANCE && !system.isScooterMoving(scooterID)
                && system.isScooterActivated(scooterID) && !system.hasClientRented(nif)) {
            system.rentScooter(nif, scooterID);
            System.out.println(RENTED);
        } else if (!system.clientExists(nif))
            System.out.println(CLIENT_DOESNT_EXIST);
        else if (!system.scooterExists(scooterID))
            System.out.println(SCOOTER_DOESNT_EXIST);
        else if (system.isScooterMoving(scooterID) || !system.isScooterActivated(scooterID))
            System.out.println(SCOOTER_CANT_BE_RENTED);
        else if (system.hasClientRented(nif))
            System.out.println(CLIENT_MOVING);
        else if (system.getClientBalance(nif) < MINIMUM_BALANCE)
            System.out.println(NOT_ENOUGH_BALANCE);
    }

    /**
     * Releases the scooter.
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void releaseScooter(Scanner scanner, RentalSystem system) {
        String scooterID = scanner.next();
        int minutes = scanner.nextInt();
        scanner.nextLine();
        if (system.scooterExists(scooterID) && system.isScooterMoving(scooterID) && minutes > 0) {
            system.releaseScooter(scooterID, minutes);
            System.out.println(RENTAL_FINISHED);
        } else if (minutes <= 0)
            System.out.println(INVALID_VALUE);
        else if (!system.scooterExists(scooterID))
            System.out.println(SCOOTER_DOESNT_EXIST);
        else if (!system.isScooterMoving(scooterID))
            System.out.println(SCOOTER_NOT_RENTED);
    }

    /**
     * Prints the state of the current Rental System.
     *
     * @param system
     */
    private static void systemState(RentalSystem system) {
        System.out.println("Estado actual: " + system.getTotalRentals() + ", " + system.getSystemBalance() + ", "
                + system.getTotalDelayMinutes());
    }

    /**
     * Exists the program.
     *
     * @param system Rental system that is currently being used.
     */
    private static void exit(RentalSystem system) {
        System.out.println(LEAVING);
        systemState(system);
    }

    /**
     * Deactivates the scooter that matches the provided scooterID.
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void deactivateScooter(Scanner scanner, RentalSystem system) {
        String scooterID = scanner.next();
        scanner.nextLine();
        if (system.scooterExists(scooterID) && !system.isScooterMoving(scooterID)) {
            system.deactivateScooter(scooterID);
            System.out.println(SCOOTER_DEACTIVATED);
        } else if (!system.scooterExists(scooterID))
            System.out.println(SCOOTER_DOESNT_EXIST);
        else if (system.isScooterMoving(scooterID))
            System.out.println(SCOOTER_MOVING);
    }

    /**
     * Reactivates the scooter that matches the provided scooterID.
     *
     * @param scanner Reads the user's input.
     * @param system  Rental system that is currently being used.
     */
    private static void reactivateScooter(Scanner scanner, RentalSystem system) {
        String scooterID = scanner.next();
        scanner.nextLine();
        if (system.scooterExists(scooterID) && !system.isScooterActivated(scooterID)) {
            system.reactivateScooter(scooterID);
            System.out.println(SCOOTER_REACTIVATED);
        } else if (!system.scooterExists(scooterID))
            System.out.println(SCOOTER_DOESNT_EXIST);
        else if (system.isScooterActivated(scooterID))
            System.out.println(SCOOTER_NOT_INACTIVE);
    }

    private static void listClient(RentalSystem system, Scanner scanner) {
        scanner.nextLine();
        ClientIterator iterator = system.listClient();
        while (system.listClient().hasNext()) {
            Client client = iterator.next();
            System.out.printf("%s: %s, %s, %d, %d, %d, %d, %d, %d, %d\n", client.getName(), client.getEmail(),
                    client.getPhone(), client.getBalance(), client.getTotalMinutes(), client.getNumberRentals(),
                    client.getMaxTime(), client.getAverageMinutes(), client.getMoneySpent());
        }
    }

    private static void listScooter(RentalSystem system, Scanner scanner) {
        scanner.nextLine();
        ScooterIterator iterator = system.listScooter();
        while (system.listClient().hasNext()) {
            Scooter scooter = iterator.next();
            System.out.printf("%s: %s, %d, %d\n", scooter.getScooterRegistration(), scooter.getUsageAmount(),
                    scooter.getUsageMinutes());
        }
    }

    private static void listDebtors(RentalSystem system, Scanner scanner) {
        scanner.nextLine();
        ClientIteratorDebtors iterator = system.listDebtors();
        while (iterator.hasNext()) {
            Client client = iterator.next();
            System.out.printf("%s: %s, %s, %s, %d, %d, %d, %d, %d, %d\n", client.getName(), client.getNif(), client.getEmail(), client.getPhone(), client.getBalance(), client.getTotalMinutes(), client.getNumberRentals(), client.getMaxTime(), client.getAverageMinutes(), client.getMoneySpent());
        }
    }

    private static void releaseLocation(RentalSystem system, Scanner scanner) {

        String scooterID = scanner.next();
        int minutes = scanner.nextInt();
        double latitude = scanner.nextDouble();
        double longitude = scanner.nextDouble();
        scanner.nextLine();

        if (system.scooterExists(scooterID) && minutes > 0 && system.isScooterMoving(scooterID)
                && system.isScooterInBoundaries(latitude, longitude)) {
            system.releaseScooter(scooterID, minutes, latitude, longitude);
            System.out.println(RENTAL_FINISHED);
        } else if (!system.isScooterInBoundaries(latitude, longitude))
            System.out.println(INVALID_LOCATION);
        else if (minutes < 0)
            System.out.println(INVALID_VALUE);
        else if (!system.scooterExists(scooterID))
            System.out.println(SCOOTER_DOESNT_EXIST);
        else if (!system.isScooterMoving(scooterID))
            System.out.println(SCOOTER_NOT_RENTED);
    }

    private static void locateCloserScooters(RentalSystem system, Scanner scanner) {
        double latitude = scanner.nextDouble();
        double longitude = scanner.nextDouble();
        scanner.nextLine();

        ScooterIteratorCloser iterator = system.listCloserScooters(latitude, longitude);
        if (iterator.hasNext())
            while (iterator.hasNext()) {
                Scooter scooter = iterator.next();
                System.out.printf("Distancia: %.6f\n", scooter.calculateDistance(latitude, longitude));
                System.out.printf("%s: %s, %d, %d, %.6f, %.6f\n", scooter.getScooterRegistration(), scooter.getState(), scooter.getUsageAmount(), scooter.getUsageMinutes(), scooter.getLatitude(), scooter.getLongitude());
            }
        else System.out.println(NO_SCOOTER_LOCATED);
    }
}
