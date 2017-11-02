import java.io.IOException;
import java.net.SocketException;

public class NetworkSender implements Runnable {
    private boolean running;
    private Network network;
    private int multicastPort;
    private String multicastIP;

    NetworkSender(String multicastIP, int multicastPort) {
        this.multicastIP = multicastIP;
        this.multicastPort = multicastPort;
    }

    void start() {
        if (running) return;

        try {
            network = new Network(multicastIP, multicastPort);
            Thread sendThread = new Thread(this);
            running = true;
            sendThread.start();

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
        while (running) {
            try {
                String message = "";
                //message = getMessage();
                network.broadcast(message); // blocking
                //System.out.println("received: " + message);

            } catch (SocketException socketException) {
                System.out.println("closed");
                break;

            } catch (IOException error) {
                System.err.println("network error: " + error);
                break;
            }
        }
        try {
            network.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    public String getMessage(String message){
        return message;
    }

}