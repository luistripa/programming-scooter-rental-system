public class ClientIterator {

    private int counter;
    private int next;
    private Client[] clients;
    
    /**
     * 
     * @param counter
     * @param clients
     */
    public ClientIterator(int counter, Client[] clients) {
        this.counter = counter;
        this.clients = clients;
        next = 0;
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
}