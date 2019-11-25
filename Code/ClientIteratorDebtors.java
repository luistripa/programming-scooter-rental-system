public class ClientIteratorDebtors {

    private int counter;
    private int next;
    private Client[] clients;
    
    /**
     * 
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
     * @return
     */
    public boolean hasNext() {
        return next < counter;
    }

    /**
     * 
     * @return
     */
    public Client next() {
        return clients[next++];
    }
    
    /**
     * 
     * @param client
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
     * 
     * @param pos
     * @param client
     */
    private void insertAt(int pos, Client client) {
        for (int i = counter - 1; i >= pos; i--)
            clients[i + 1] = clients[i];
        clients[pos] = client;
        counter++;
    }
}