import java.io.IOException;
import java.net.SocketException;

public class NetworkWorker implements Runnable {
    private boolean running;
    private Network network;
    private int port;
    private String ip;

    NetworkWorker(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    void start() {
        if (running) return;

        try {
            network = new Network(ip, port);
            Thread thread = new Thread(this);
            running = true;
            thread.start();

        } catch (IOException e) {
            running = false;
        }
    }

    void stop() throws Exception{
        if (!running) return;

        network.close();
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                String message = network.listen(); // blocking
                System.out.println("received: " + message);

            } catch (SocketException socketException) {
                System.out.println("closed");
                break;

            } catch (IOException e) {
                System.err.println("network error: " + e);
                break;
            }
        }
        try {
            network.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
