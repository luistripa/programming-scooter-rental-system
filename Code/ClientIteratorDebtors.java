public class ClientIteratorDebtors {

    private int counter;
    private int next;
    private Client[] clients;
    
    /**
     * Constructor of the iterator that contains all the clients with negative balance
     * and orders them in ascending order of balance.
     * @param counter
     * @param clients
     */
    public ClientIteratorDebtors(int counter, Client[] clients) {
        this.clients = new Client[counter];
        this.counter = 0;
        next = 0;
        for (int i = 0; i < counter; i++) {
            if (clients[i].getBalance() < 0)
                insertSort(clients[i]);
        }
    }

    /**
     * 
     * @return If there is or isn't a next Client in the iterator
     */
    public boolean hasNext() {
        return next < counter;
    }

    /**
     * 
     * @return Next Client in the iterator
     */
    public Client next() {
        return clients[next++];
    }
    
    /**
     * Inserts a client in ascending order of balance in the array
     * @param client Client that is going to be inserted in the array
     */
    private void insertSort(Client client) {
        int pos = -1;
        for (int i = 0; i < counter && pos == -1; i++) {
            if (clients[i].balanceGreaterThan(client))
                pos = i;
        }
        if (pos == -1)
            pos = counter;
        insertAt(pos,client);
    }
    
    /**
     * Inserts a client in the array, opening a space for the new client to be inserted
     * @param pos Position of the array
     * @param client Client that is going to be inserted in the array
     */
    private void insertAt(int pos, Client client) {
        for (int i = counter - 1; i >= pos; i--)
            clients[i + 1] = clients[i];
        clients[pos] = client;
        counter++;
    }
}