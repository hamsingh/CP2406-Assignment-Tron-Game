import java.io.IOException;
import java.net.SocketException;

public class NetworkWorker implements Runnable {
    private boolean running;
    private Network network;
    private int port;

    NetworkWorker(int port) {
        this.port = port;
    }

    void start() {
        if (running) return;

        try {
            network = new Network(port);
            Thread thread = new Thread(this);
            running = true;
            thread.start();

        } catch (IOException e) {
            running = false;
        }
    }

    void stop() {
        if (!running) return;

        network.close();
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                String message = network.receiveRequest(); // blocking
                System.out.println("received: " + message);

            } catch (SocketException socketException) {
                System.out.println("closed");
                break;

            } catch (IOException e) {
                System.err.println("network error: " + e);
                break;
            }
        }

        network.close();
    }
}