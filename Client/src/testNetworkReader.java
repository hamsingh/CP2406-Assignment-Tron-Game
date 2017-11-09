import java.io.IOException;
import java.net.SocketException;

public class testNetworkReader implements Runnable {
    private boolean running;
    private Network network;
    private int multicastPort;
    private String multicastIP;

    testNetworkReader(String multicastIP, int multicastPort) {
        this.multicastIP = multicastIP;
        this.multicastPort = multicastPort;
    }

    void start() {
        if (running) return;

        try {
            network = new Network(multicastIP, multicastPort);
            Thread readThread = new Thread(this);
            running = true;
            readThread.start();

        } catch (IOException e) {
            running = false;
        }
    }

    void stop() throws Exception {
        if (!running) return;

        network.close();
        running = false;
    }

    @Override
    public void run(){
        String message;
        while (running) {
            try {
                message = network.read(); // blocking
                System.out.println("received: " + message);

            }
            catch (SocketException socketException) {
                System.out.println("Socket Closed");
                break;

            }
            catch (IOException error) {
                System.err.println("network error: " + error);
                break;
            }
        }
    }
}