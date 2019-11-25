public class ClientIterator {

    private int counter;
    private int nextClient;
    private Client[] clients;
    
    public ClientIterator(int counter, Client[] clients) {
        this.counter = counter;
        this.clients = clients;
        nextClient = 0;
    }

    public boolean hasNext() {
        return nextClient < counter;
    }

    public Client next() {
        return clients[nextClient++];
    }
}