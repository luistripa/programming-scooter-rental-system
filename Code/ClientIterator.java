public class ClientIterator {

    private int counter;
    private int next;
    private Client[] clients;
    
    public ClientIterator(int counter, Client[] clients) {
        this.counter = counter;
        this.clients = clients;
        next = 0;
    }

    public boolean hasNext() {
        return next < counter;
    }

    public Client next() {
        return clients[next++];
    }
}